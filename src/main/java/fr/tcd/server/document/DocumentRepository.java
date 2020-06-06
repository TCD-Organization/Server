package fr.tcd.server.document;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Optional;

public interface DocumentRepository extends MongoRepository<DocumentModel, Long> {
    boolean existsByUserIdAndChecksum(String userId, String checksum);
    Optional<DocumentModel> findById(String docID);
    //DocumentModel findById(String docId);
}
