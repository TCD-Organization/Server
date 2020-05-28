package fr.tcd.server.analysis.service;

import fr.tcd.server.analysis.dao.AnalysisRepository;
import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.model.AnalysisModel;
import fr.tcd.server.analysis.status.AnalysisStatus;
import fr.tcd.server.document.dao.DocumentRepository;
import fr.tcd.server.document.exception.DocumentNotFoundException;
import fr.tcd.server.document.model.DocumentModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnalysisService extends IAnalysisService {

    private final AnalysisRepository analysisRepository;
    private final DocumentRepository documentRepository;

    public AnalysisService(AnalysisRepository analysisRepository, DocumentRepository documentRepository) {
        this.analysisRepository = analysisRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public String processNewAnalysis(Long docID, AnalysisDTO analysisDTO) throws DocumentNotFoundException {
        Optional<DocumentModel> document = documentRepository.findById(docID);
        if(document.isEmpty()) {
            throw new DocumentNotFoundException("Document not found");
        }
        AnalysisModel analysisModel = new AnalysisModel()
                .setName(analysisDTO.getName())
                .setType(analysisDTO.getType())
                .setStatus(AnalysisStatus.TO_START);

        AnalysisModel analysis = analysisRepository.save(analysisModel);
        return analysis.getId();
    }



}
