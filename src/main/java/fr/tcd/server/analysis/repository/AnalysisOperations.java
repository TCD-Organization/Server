package fr.tcd.server.analysis.repository;

import fr.tcd.server.analysis.AnalysisModel;
import fr.tcd.server.analysis.DocumentAnalysisContainer;
import fr.tcd.server.document.DocumentModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AnalysisOperations {
    List<AnalysisModel> findAllByDocumentOwner(String owner);
    DocumentAnalysisContainer saveInDocument(AnalysisModel analysisModel, DocumentModel documentModel);
}