package fr.tcd.server.document;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends MongoRepository<DocumentModel, Long> {
    boolean existsByHashAndOwner(String hash, String owner);
    Optional<DocumentModel> findByIdAndOwner(String docID, String owner);
    List<DocumentModel> findByOwner(String owner);
    void deleteByIdAndOwner(String id, String owner);
    //DocumentModel findById(String docId);
}