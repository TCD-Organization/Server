package fr.tcd.server.analysis;

import fr.tcd.server.amqp.AmqpConfig;
import fr.tcd.server.amqp.front_analyses.FrontAnalysesAmqpConfig;
import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.dto.AnalysisProgressionDTO;
import fr.tcd.server.analysis.exception.*;
import fr.tcd.server.analysis.status.AnalysisStatus;
import fr.tcd.server.document.DocumentModel;
import fr.tcd.server.document.DocumentService;
import fr.tcd.server.analysis.runner_analysis.RunnerAnalysisService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static fr.tcd.server.analysis.status.AnalysisStatus.FINISHED;
import static fr.tcd.server.analysis.status.AnalysisStatus.TO_START;

@Service
public class AnalysisService {

    private final AnalysisRepository analysisRepository;
    private final DocumentService documentService;
    private final RunnerAnalysisService runnerAnalysisService;
    private final RabbitTemplate rabbitTemplate;

    public AnalysisService(AnalysisRepository analysisRepository, DocumentService documentService, RunnerAnalysisService runnerAnalysisService, RabbitTemplate rabbitTemplate) {
        this.analysisRepository = analysisRepository;
        this.documentService = documentService;
        this.runnerAnalysisService = runnerAnalysisService;
        this.rabbitTemplate = rabbitTemplate;
    }

    AnalysisModel processNewAnalysis(AnalysisDTO analysisDTO, String owner) {
        //TODO: If there is any fail, roll back the creation

        DocumentModel document = documentService.getDocument(analysisDTO.getDoc_id(), owner);
        AnalysisModel analysis = createAnalysis(analysisDTO, document.getId(), document.getName(), document.getOwner());
        AnalysisModel savedAnalysis = Optional.ofNullable(analysisRepository.save(analysis))
                .orElseThrow(AnalysisNotCreatedException::new);

        runnerAnalysisService.formAndSendRunnerAnalysis(document, savedAnalysis);
        formAndSendFrontAnalysis(savedAnalysis);

        return savedAnalysis;
    }

    private AnalysisModel createAnalysis(AnalysisDTO analysisDTO, String doc_id, String doc_name, String owner) {
        return new AnalysisModel()
                .setName(analysisDTO.getName())
                .setType(analysisDTO.getType())
                .setStatus(AnalysisStatus.TO_START)
                .setDocument_id(doc_id)
                .setDocument_name(doc_name)
                .setOwner(owner);
    }

    public List<AnalysisModel> getMyAnalyses(String name) {
        return analysisRepository.findByOwner(name);
    }

    public AnalysisModel getAnalysis(String id, String owner) {
        return analysisRepository.findByIdAndOwner(id, owner).orElseThrow(AnalysisNotFoundException::new);
    }

    public AnalysisModel processAnalysisUpdate(AnalysisProgressionDTO analysisProgression, String analysisId, String runner) {
        AnalysisModel analysis = analysisRepository.findById(analysisId).orElseThrow(AnalysisNotFoundException::new);
        if (analysis.getStatus() == FINISHED) {
            throw new AnalysisAlreadyFinishedException();
        }

        if (analysis.getStatus() == TO_START) {
            analysis.setStart_time(new Date());
        }

        analysis.setStatus(analysisProgression.getStatus());
        analysis.setRunner(runner);
        analysis.setStep_number(analysisProgression.getStep_number());
        analysis.setTotal_steps(analysisProgression.getTotal_steps());
        analysis.setStep_name(analysisProgression.getStep_name());
        analysis.setLasting_time(analysisProgression.getLasting_time());

        if (analysisProgression.getStatus() == FINISHED) {
            analysis.setStep_number(analysisProgression.getTotal_steps());
            analysis.setEnd_time(new Date());
            analysis.setLasting_time("0");
            analysis.setResult(analysisProgression.getResult());
        }

        analysis = Optional.ofNullable(analysisRepository.save(analysis)).orElseThrow(AnalysisNotUpdatedException::new);
        formAndSendFrontAnalysis(analysis);
        return analysis;
    }


    public void formAndSendFrontAnalysis(AnalysisModel analysis) {
        String FRONT_ANALYSIS_ROOTING_KEY = "analysis."+analysis.getId();
        String FRONT_ANALYSIS_QUEUE = "analysis_"+analysis.getId()+"_q";

        try {
            RabbitAdmin admin = new RabbitAdmin(rabbitTemplate);
            if (admin.getQueueProperties(FRONT_ANALYSIS_QUEUE) == null)
                admin.declareQueue(new Queue(FRONT_ANALYSIS_QUEUE, true, false, false));
                admin.declareBinding(new Binding(FRONT_ANALYSIS_QUEUE, Binding.DestinationType.QUEUE, "type.id.tx", FRONT_ANALYSIS_ROOTING_KEY, null));
            rabbitTemplate.convertAndSend(FrontAnalysesAmqpConfig.EXCHANGE, FRONT_ANALYSIS_ROOTING_KEY, analysis);
        } catch (AmqpException e) {
            throw new AnalysisNotSentException();
        }
    }
}
