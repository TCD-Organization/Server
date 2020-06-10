package fr.tcd.server.analysis.runner_analysis;

import fr.tcd.server.amqp.AmqpConfig;
import fr.tcd.server.amqp.runner_analyses.RunnerAnalysis;
import fr.tcd.server.analysis.AnalysisModel;
import fr.tcd.server.analysis.runner_analysis.exception.RunnerAnalysisNotSentException;
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
        RunnerAnalysis runnerAnalysis = new RunnerAnalysis()
                .setId(analysis.getDocument_id())
                .setGenre(document.getGenre())
                .setContent(document.getContent())
                .setAnalyse(analysis);
        try {
            rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE, NEW_RUNNER_ANALYSIS_ROOTING_KEY, runnerAnalysis);
        } catch (AmqpException e) {
            throw new RunnerAnalysisNotSentException();
        }
    }



}
