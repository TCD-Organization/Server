package fr.tcd.server.analysis.service;

import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.exception.AnalysisAlreadyExistsException;
import fr.tcd.server.analysis.model.AnalysisModel;

public abstract class ITaskService {
    abstract AnalysisModel processNewAnalysis(AnalysisDTO analysisDTO) throws AnalysisAlreadyExistsException;
}
