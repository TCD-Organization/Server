package fr.tcd.server.analysis.service;

import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.model.AnalysisModel;
import fr.tcd.server.document.model.DocumentModel;

import java.util.Optional;

public abstract class IAnalysisService {
    public abstract boolean processNewAnalysis(Long docID, AnalysisDTO analysisDTO);

    protected abstract Optional<AnalysisModel> createAnalysis(AnalysisDTO analysisDTO);

    abstract boolean formAndSendRunnerAnalysis(DocumentModel document, AnalysisModel analysis);
}
