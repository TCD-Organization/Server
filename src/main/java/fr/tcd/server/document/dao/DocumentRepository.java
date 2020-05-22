package fr.tcd.server.document.dao;

import fr.tcd.server.document.model.DocumentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<DocumentModel, Long> {
    boolean existsByUserIdAndChecksum(String userId, String checksum);
}
