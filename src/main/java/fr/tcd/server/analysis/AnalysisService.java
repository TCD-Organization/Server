package fr.tcd.server.analysis;

import fr.tcd.server.analysis.exception.AnalysisNotCreatedException;
import fr.tcd.server.analysis.exception.AnalysisNotFoundException;
import fr.tcd.server.analysis.status.AnalysisStatus;
import fr.tcd.server.document.DocumentModel;
import fr.tcd.server.document.DocumentRepository;
import fr.tcd.server.document.DocumentService;
import fr.tcd.server.document.exception.DocumentNotFoundException;
import fr.tcd.server.runner_analysis.RunnerAnalysisService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AnalysisService {

    private final AnalysisRepository analysisRepository;
    private final DocumentService documentService;
    private final RunnerAnalysisService runnerAnalysisService;

    public AnalysisService(AnalysisRepository analysisRepository, DocumentService documentService, RunnerAnalysisService runnerAnalysisService) {
        this.analysisRepository = analysisRepository;
        this.documentService = documentService;
        this.runnerAnalysisService = runnerAnalysisService;
    }

    AnalysisModel processNewAnalysis(AnalysisDTO analysisDTO, String owner) {
        //TODO: If there is any fail, roll back the creation

        DocumentModel document = documentService.getDocument(analysisDTO.getDoc_id(), owner);
        AnalysisModel analysis = createAnalysis(analysisDTO, document.getId(), document.getName(), document.getOwner());
        AnalysisModel savedAnalysis = Optional.ofNullable(analysisRepository.save(analysis))
                .orElseThrow(AnalysisNotCreatedException::new);

        runnerAnalysisService.formAndSendRunnerAnalysis(document, savedAnalysis);

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
}
