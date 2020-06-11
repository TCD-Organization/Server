package fr.tcd.server.amqp.runner_analyses;

import fr.tcd.server.amqp.exception.AmqpAnalysisNotSentException;
import fr.tcd.server.analysis.AnalysisModel;
import fr.tcd.server.document.DocumentModel;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RunnerAnalysisService {

    private final RabbitTemplate rabbitTemplate;

    private static final String NEW_RUNNER_ANALYSIS_ROOTING_KEY = "runner_analyses.new";

    public RunnerAnalysisService(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    public void formAndSendRunnerAnalysis(DocumentModel document, AnalysisModel analysis) {
        RunnerAnalysisDTO runnerAnalysisDTO = new RunnerAnalysisDTO()
                .setId(analysis.getDocument_id())
                .setGenre(document.getGenre())
                .setContent(document.getContent())
                .setAnalysis(analysis);
        try {
            rabbitTemplate.convertAndSend(RunnerAnalysesAmqpConfig.EXCHANGE, NEW_RUNNER_ANALYSIS_ROOTING_KEY, runnerAnalysisDTO);
        } catch (AmqpException e) {
            throw new AmqpAnalysisNotSentException();
        }
    }



}
