package fr.tcd.server.amqp.front_analyses;

import fr.tcd.server.analysis.AnalysisModel;
import fr.tcd.server.analysis.exception.AnalysisNotSentException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class FrontAnalysisService {
    private final RabbitTemplate rabbitTemplate;

    public FrontAnalysisService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void createQueueAndSendAnalysis(AnalysisModel analysis) {
        String FRONT_ANALYSIS_ROOTING_KEY = "analysis."+analysis.getId();
        String FRONT_ANALYSIS_QUEUE = "analysis_"+analysis.getId()+"_q";

        try {
            RabbitAdmin admin = new RabbitAdmin(rabbitTemplate);
            if (admin.getQueueProperties(FRONT_ANALYSIS_QUEUE) == null)
                admin.declareQueue(new Queue(FRONT_ANALYSIS_QUEUE, true, false, true));
            admin.declareBinding(new Binding(FRONT_ANALYSIS_QUEUE, Binding.DestinationType.QUEUE, "type.id.tx", FRONT_ANALYSIS_ROOTING_KEY, null));
            rabbitTemplate.convertAndSend(FrontAnalysesAmqpConfig.EXCHANGE, FRONT_ANALYSIS_ROOTING_KEY, analysis);
        } catch (AmqpException e) {
            throw new AnalysisNotSentException();
        }

    }
}
