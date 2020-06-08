package fr.tcd.server.document;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends MongoRepository<DocumentModel, Long> {
    boolean existsByOwnerAndChecksum(String owner, String checksum);
    Optional<DocumentModel> findById(String docID);
    Optional<List<DocumentModel>> findAllByOwner(String owner);
    //DocumentModel findById(String docId);
}