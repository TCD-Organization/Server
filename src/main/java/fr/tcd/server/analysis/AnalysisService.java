package fr.tcd.server.analysis;

import fr.tcd.server.analysis.exception.AnalysisNotCreatedException;
import fr.tcd.server.analysis.status.AnalysisStatus;
import fr.tcd.server.document.DocumentModel;
import fr.tcd.server.document.DocumentRepository;
import fr.tcd.server.document.exception.DocumentNotCreatedException;
import fr.tcd.server.document.exception.DocumentNotUpdatedException;
import fr.tcd.server.runner_analysis.RunnerAnalysisService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisService {

    private final AnalysisRepository analysisRepository;
    private final DocumentRepository documentRepository;
    private final RunnerAnalysisService runnerAnalysisService;
    private final RabbitTemplate rabbitTemplate;

    public AnalysisService(AnalysisRepository analysisRepository, DocumentRepository documentRepository, RunnerAnalysisService runnerAnalysisService, RabbitTemplate rabbitTemplate) {
        this.analysisRepository = analysisRepository;
        this.documentRepository = documentRepository;
        this.runnerAnalysisService = runnerAnalysisService;
        this.rabbitTemplate = rabbitTemplate;
    }

    String processNewAnalysis(String docID, AnalysisDTO analysisDTO) {
        //TODO: If there is any fail, roll back the creation

        DocumentModel document = documentRepository.findById(docID);
        //TODO: Check if the user is the owner of the document
        if(document == null) {
            throw new DocumentNotCreatedException("Document not found");
        }

        AnalysisModel newAnalysis = createAnalysis(analysisDTO);

        List<AnalysisModel> analysesOfDocument = document.getAnalyses();
        analysesOfDocument.add(newAnalysis);
        document.setAnalyses(analysesOfDocument);

        DocumentModel newDocument = documentRepository.save(document);

        if(newDocument == null) {
            throw new DocumentNotUpdatedException("Document not updated");
        }

        runnerAnalysisService.formAndSendRunnerAnalysis(newDocument, newAnalysis);
        return newAnalysis.getId();
    }

    private AnalysisModel createAnalysis(AnalysisDTO analysisDTO) throws AnalysisNotCreatedException {

        AnalysisModel newAnalysis = new AnalysisModel()
                .setName(analysisDTO.getName())
                .setType(analysisDTO.getType())
                .setStatus(AnalysisStatus.TO_START);

        newAnalysis = analysisRepository.save(newAnalysis);
        if(newAnalysis == null) {
            throw new AnalysisNotCreatedException("Analysis not created");
        }

        return newAnalysis;

    }

}
