package fr.tcd.server.document;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends MongoRepository<DocumentModel, Long> {
    boolean existsByOwnerAndChecksum(String owner, String checksum);
    Optional<DocumentModel> findByIdAndOwner(String docID, String owner);
    List<DocumentModel> findByOwner(String owner);
    //DocumentModel findById(String docId);
}