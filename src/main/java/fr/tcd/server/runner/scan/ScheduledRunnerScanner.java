package fr.tcd.server.runner.scan;

import fr.tcd.server.runner.RunnerModel;
import fr.tcd.server.runner.RunnerRepository;
import fr.tcd.server.runner.status.RunnerStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired WebClientBean webClientBean;

    public ScheduledRunnerScanner(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void scanRunnersUp() throws InterruptedException {
        List<RunnerModel> runners = runnerRepository.findByStatus(RunnerStatus.UP);
        System.out.println("runners : "+ runners);
        runners.forEach(this::checkRunner);

    }

    private void checkRunner(RunnerModel runner) {
        System.out.println(runner);
        String url = runner.getIp() + ":" + runner.getPort() + "/ping";
        ResponseEntity<String> response = webClientBean.sendGetRequest(url);
        if(response.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
            runner.setStatus(RunnerStatus.DOWN);
            // TODO: Stop all analyses by this runner
            runnerRepository.save(runner);
        }
    }

}