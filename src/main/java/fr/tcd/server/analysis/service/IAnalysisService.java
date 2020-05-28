package fr.tcd.server.analysis.service;

import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.exception.AnalysisAlreadyExistsException;

public abstract class IAnalysisService {
    abstract String processNewAnalysis(Long docId, AnalysisDTO analysisDTO) throws AnalysisAlreadyExistsException;
}
