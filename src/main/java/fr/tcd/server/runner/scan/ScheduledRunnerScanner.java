package fr.tcd.server.runner.scan;

import fr.tcd.server.analysis.AnalysisService;
import fr.tcd.server.runner.RunnerModel;
import fr.tcd.server.runner.RunnerRepository;
import fr.tcd.server.runner.status.RunnerStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableAsync
public class ScheduledRunnerScanner {
    private final RunnerRepository runnerRepository;
    private final WebClientBean webClientBean;
    private final AnalysisService analysisService;

    public ScheduledRunnerScanner(RunnerRepository runnerRepository, WebClientBean webClientBean, AnalysisService analysisService) {
        this.runnerRepository = runnerRepository;
        this.webClientBean = webClientBean;
        this.analysisService = analysisService;
    }

    @Async
    @Scheduled(fixedRateString = "${runner.scan.rate}")
    public void scanRunnersUp() {
        List<RunnerModel> runners = runnerRepository.findByStatus(RunnerStatus.UP);
        runners.forEach(this::checkRunner);
    }

    private void checkRunner(RunnerModel runner) {
        String url = "http://" + runner.getIp() + ":" + runner.getPort() + "/ping";
        ResponseEntity<String> response = webClientBean.sendGetRequest(url);
        if(!response.getStatusCode().equals(HttpStatus.OK)) {
            runner.status(RunnerStatus.DOWN);
            analysisService.cancelAllAnalysesOfRunner(runner.getId());
            runnerRepository.save(runner);
        }
    }

}