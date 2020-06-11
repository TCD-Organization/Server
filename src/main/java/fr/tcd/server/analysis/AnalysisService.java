package fr.tcd.server.analysis;

import fr.tcd.server.amqp.front_analyses.FrontAnalysisService;
import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.dto.AnalysisProgressionDTO;
import fr.tcd.server.analysis.exception.AnalysisAlreadyFinishedException;
import fr.tcd.server.analysis.exception.AnalysisNotCreatedException;
import fr.tcd.server.analysis.exception.AnalysisNotFoundException;
import fr.tcd.server.analysis.exception.AnalysisNotUpdatedException;
import fr.tcd.server.analysis.runner_analysis.RunnerAnalysisService;
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

    AnalysisModel processNewAnalysis(AnalysisDTO analysisDTO, String owner) {
        //TODO: If there is any fail, roll back the creation

        DocumentModel document = documentService.getDocument(analysisDTO.getDoc_id(), owner);
        AnalysisModel analysis = createAnalysis(analysisDTO, document.getId(), document.getName(), document.getOwner());
        AnalysisModel savedAnalysis = Optional.ofNullable(analysisRepository.save(analysis))
                .orElseThrow(AnalysisNotCreatedException::new);

        runnerAnalysisService.formAndSendRunnerAnalysis(document, savedAnalysis);
        frontAnalysisService.createQueueAndSendAnalysis(savedAnalysis);

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
        AnalysisModel updatedAnalysis = updateAnalysis(analysis, analysisProgression, runner);
        AnalysisModel savedAnalysis = Optional.ofNullable(analysisRepository.save(updatedAnalysis)).orElseThrow(AnalysisNotUpdatedException::new);
        frontAnalysisService.createQueueAndSendAnalysis(savedAnalysis);
        return analysis;
    }

    private AnalysisModel updateAnalysis(AnalysisModel analysis, AnalysisProgressionDTO analysisProgression, String runner) {
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

        return analysis;
    }

}
