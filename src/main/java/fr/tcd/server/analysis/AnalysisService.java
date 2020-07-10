package fr.tcd.server.analysis;

import fr.tcd.server.amqp.front_analyses.FrontAnalysisService;
import fr.tcd.server.amqp.runner_analyses.RunnerAnalysisService;
import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.dto.AnalysisProgressionDTO;
import fr.tcd.server.analysis.exception.AnalysisAlreadyFinishedException;
import fr.tcd.server.analysis.exception.AnalysisNotCreatedException;
import fr.tcd.server.analysis.exception.AnalysisNotFoundException;
import fr.tcd.server.analysis.exception.AnalysisNotUpdatedException;
import fr.tcd.server.analysis.status.AnalysisStatus;
import fr.tcd.server.document.DocumentModel;
import fr.tcd.server.document.DocumentService;
import org.springframework.stereotype.Service;

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
    private final FrontAnalysisService frontAnalysisService;

    public AnalysisService(AnalysisRepository analysisRepository, DocumentService documentService, RunnerAnalysisService runnerAnalysisService, FrontAnalysisService frontAnalysisService) {
        this.analysisRepository = analysisRepository;
        this.documentService = documentService;
        this.runnerAnalysisService = runnerAnalysisService;
        this.frontAnalysisService = frontAnalysisService;
    }

    AnalysisModel createNewAnalysis(AnalysisDTO analysisDTO, String owner) {
        DocumentModel document = documentService.getDocument(analysisDTO.getDoc_id(), owner);
        AnalysisModel analysis = new AnalysisModel(analysisDTO, document);
        AnalysisModel savedAnalysis = Optional.ofNullable(analysisRepository.save(analysis))
                .orElseThrow(AnalysisNotCreatedException::new);

        runnerAnalysisService.formAndSendRunnerAnalysis(document.getContent(), savedAnalysis.getId());
        frontAnalysisService.createQueueAndSendAnalysis(savedAnalysis);

        return savedAnalysis;
    }

    public AnalysisModel processAnalysisUpdate(String analysisId, AnalysisStatus status, String runner, int stepNumber,
                                               int totalSteps, String stepName, Long lastingTime, String result) {
        AnalysisModel analysis = analysisRepository.findById(analysisId).orElseThrow(AnalysisNotFoundException::new);
        analysis.updateProgress(status, runner, stepNumber, totalSteps, stepName, lastingTime, result);
        AnalysisModel savedAnalysis = Optional.ofNullable(analysisRepository.save(analysis)).orElseThrow(AnalysisNotUpdatedException::new);
        frontAnalysisService.createQueueAndSendAnalysis(savedAnalysis);
        return analysis;
    }


    public List<AnalysisModel> getMyAnalyses(String owner) {
        return analysisRepository.findByOwner(owner);
    }

    public AnalysisModel getAnalysis(String id, String owner) {
        return analysisRepository.findByIdAndOwner(id, owner).orElseThrow(AnalysisNotFoundException::new);
    }

    public boolean analysisExists(String id, String owner) {
        return analysisRepository.existsByIdAndOwner(id, owner);
    }

    public void deleteAnalysis(String id, String owner) {
        if (analysisExists(id, owner)) {
            analysisRepository.deleteByIdAndOwner(id, owner);
        } else {
            throw new AnalysisNotFoundException();
        }

    }
}
