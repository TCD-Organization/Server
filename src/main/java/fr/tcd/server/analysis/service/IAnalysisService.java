package fr.tcd.server.analysis.service;

import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.model.AnalysisModel;
import fr.tcd.server.document.model.DocumentModel;

public abstract class IAnalysisService {
    public abstract String processNewAnalysis(String docID, AnalysisDTO analysisDTO);

    protected abstract AnalysisModel createAnalysis(AnalysisDTO analysisDTO);

    abstract void formAndSendRunnerAnalysis(DocumentModel document, AnalysisModel analysis);
}
