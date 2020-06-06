package fr.tcd.server.analysis;

import fr.tcd.server.analysis.exception.AnalysisNotCreatedException;
import fr.tcd.server.document.exception.DocumentNotCreatedException;
import fr.tcd.server.runner_analysis.exception.RunnerAnalysisNotSentException;
import fr.tcd.server.analysis.status.AnalysisStatus;
import fr.tcd.server.document.DocumentModel;
import fr.tcd.server.document.DocumentRepository;
import fr.tcd.server.document.exception.DocumentNotFoundException;
import fr.tcd.server.document.exception.DocumentNotUpdatedException;
import fr.tcd.server.runner_analysis.RunnerAnalysisService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalysisService {

    private final AnalysisRepository analysisRepository;
    private final DocumentRepository documentRepository;
    private final RunnerAnalysisService runnerAnalysisService;

    public AnalysisService(AnalysisRepository analysisRepository, DocumentRepository documentRepository, RunnerAnalysisService runnerAnalysisService) {
        this.analysisRepository = analysisRepository;
        this.documentRepository = documentRepository;
        this.runnerAnalysisService = runnerAnalysisService;
    }

    AnalysisModel processNewAnalysis(String docID, AnalysisDTO analysisDTO) {
        //TODO: If there is any fail, roll back the creation

        DocumentModel document = documentRepository.findById(docID).orElseThrow(DocumentNotFoundException::new);
        //TODO: Check if the user is the owner of the document

        AnalysisModel newAnalysis = createAnalysis(analysisDTO);

        List<AnalysisModel> analysesOfDocument = document.getAnalyses();
        analysesOfDocument.add(newAnalysis);
        document.setAnalyses(analysesOfDocument);

        DocumentModel newDocument = Optional.of(documentRepository.save(document)).orElseThrow(DocumentNotCreatedException::new);

        runnerAnalysisService.formAndSendRunnerAnalysis(newDocument, newAnalysis);
        return newAnalysis;
    }

    private AnalysisModel createAnalysis(AnalysisDTO analysisDTO) {

        AnalysisModel newAnalysis = new AnalysisModel()
                .setName(analysisDTO.getName())
                .setType(analysisDTO.getType())
                .setStatus(AnalysisStatus.TO_START);

        return Optional.of(analysisRepository.save(newAnalysis)).orElseThrow(AnalysisNotCreatedException::new);
    }

}
