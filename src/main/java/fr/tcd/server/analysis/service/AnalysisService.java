package fr.tcd.server.analysis.service;

import fr.tcd.server.amqp.AmqpConfig;
import fr.tcd.server.amqp.runner_analyses.RunnerAnalysis;
import fr.tcd.server.analysis.dao.AnalysisRepository;
import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.exception.AnalysisNotCreatedException;
import fr.tcd.server.analysis.exception.RunnerAnalysisNotSentException;
import fr.tcd.server.analysis.model.AnalysisModel;
import fr.tcd.server.analysis.status.AnalysisStatus;
import fr.tcd.server.document.dao.DocumentRepository;
import fr.tcd.server.document.exception.DocumentNotCreatedException;
import fr.tcd.server.document.exception.DocumentNotUpdatedException;
import fr.tcd.server.document.model.DocumentModel;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisService extends IAnalysisService {

    private final AnalysisRepository analysisRepository;
    private final DocumentRepository documentRepository;
    private final RabbitTemplate rabbitTemplate;

    private static final String NEW_RUNNER_ANALYSIS_ROOTING_KEY = "runner_analyses.new";

    public AnalysisService(AnalysisRepository analysisRepository, DocumentRepository documentRepository, RabbitTemplate rabbitTemplate) {
        this.analysisRepository = analysisRepository;
        this.documentRepository = documentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public String processNewAnalysis(String docID, AnalysisDTO analysisDTO) {
        //TODO: If there is any fail, roll back the creation

        DocumentModel document = documentRepository.findById(docID);
        //TODO: Check if the user is the owner of the document

        if(document == null) {
            throw new DocumentNotCreatedException("Document not found");
        }

        AnalysisModel newAnalysis = createAnalysis(analysisDTO);
        if(newAnalysis == null) {
            throw new AnalysisNotCreatedException("Analysis not created");
        }

        List<AnalysisModel> analysesOfDocument = document.getAnalyses();
        analysesOfDocument.add(newAnalysis);
        document.setAnalyses(analysesOfDocument);

        DocumentModel newDocument = documentRepository.save(document);

        if(newDocument == null) {
            throw new DocumentNotUpdatedException("Document not updated");
        }

        formAndSendRunnerAnalysis(newDocument, newAnalysis);
        return newAnalysis.getId();
    }

    @Override
    protected AnalysisModel createAnalysis(AnalysisDTO analysisDTO) {

        AnalysisModel newAnalysis = new AnalysisModel()
                .setName(analysisDTO.getName())
                .setType(analysisDTO.getType())
                .setStatus(AnalysisStatus.TO_START);

        return analysisRepository.save(newAnalysis);

    }

    //TODO: Move all RunnerAnalysis related elements into a package
    @Override
    void formAndSendRunnerAnalysis(DocumentModel document, AnalysisModel analysis) {
        RunnerAnalysis runnerAnalysis = new RunnerAnalysis()
                .setId(document.getId())
                .setGenre(document.getGenre())
                .setContent(document.getContent())
                .setAnalyse(analysis);
        try {
            rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE, NEW_RUNNER_ANALYSIS_ROOTING_KEY, runnerAnalysis);
        } catch (AmqpException e) {
            throw new RunnerAnalysisNotSentException("Analysis not sent to Runners", e);
        }
    }



}
