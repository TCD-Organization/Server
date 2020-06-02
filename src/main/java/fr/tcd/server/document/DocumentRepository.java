package fr.tcd.server.document;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<DocumentModel, Long> {
    boolean existsByUserIdAndChecksum(String userId, String checksum);

    DocumentModel findById(String docID);
    //DocumentModel findById(String docId);
}
