package fr.tcd.server.document.dao;

import fr.tcd.server.document.model.DocumentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<DocumentModel, Long> {
    boolean existsByUser_idAndChecksum(String user_id, String checksum);
}
