package fr.tcd.server.analysis.repository;


import fr.tcd.server.analysis.AnalysisModel;
import fr.tcd.server.analysis.DocumentAnalysisContainer;
import fr.tcd.server.document.DocumentModel;
import fr.tcd.server.document.DocumentRepository;
import fr.tcd.server.document.exception.DocumentNotFoundException;
import fr.tcd.server.document.exception.DocumentNotUpdatedException;
import fr.tcd.server.user.UserRepository;
import fr.tcd.server.user.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class AnalysisOperationsImpl implements AnalysisOperations {
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;


    AnalysisOperationsImpl(UserRepository userRepository, DocumentRepository documentRepository) {
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public List<AnalysisModel> findAllByDocumentOwner(String owner) {
        if(!userRepository.existsByUsername(owner))
            throw new UserNotFoundException();
        List<DocumentModel> documents = documentRepository.findAllByOwner(owner).orElseThrow(DocumentNotFoundException::new);
        List<AnalysisModel> allAnalyses = new ArrayList<>();
        documents.forEach((document) -> allAnalyses.addAll(document.getAnalyses()));
        return allAnalyses;
    }

    @Override
    public DocumentAnalysisContainer saveInDocument(AnalysisModel newAnalysis, DocumentModel documentModel) {
        newAnalysis.setId(UUID.randomUUID().toString());
        DocumentModel document = documentRepository.findById(documentModel.getId()).orElseThrow(DocumentNotFoundException::new);
        List<AnalysisModel> analyses = document.getAnalyses();
        analyses.add(newAnalysis);
        document.setAnalyses(analyses);
        document = Optional.ofNullable(documentRepository.save(document)).orElseThrow(DocumentNotUpdatedException::new);
        return new DocumentAnalysisContainer(document, newAnalysis);
    }
}