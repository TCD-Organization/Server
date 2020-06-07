package fr.tcd.server.document;

import fr.tcd.server.user.UserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends MongoRepository<DocumentModel, Long>, DocumentRepositoryCustom {
    boolean existsByOwnerAndChecksum(String owner, String checksum);
    Optional<DocumentModel> findById(String docID);
    Optional<List<DocumentModel>> findAllByOwner(String owner);
    //DocumentModel findById(String docId);
}

interface DocumentRepositoryCustom {
    public void findByUser(String user);
}

class DocumentRepositoryImpl implements DocumentRepositoryCustom {
    private final UserRepository userRepository;

    DocumentRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void findByUser(String user) {

    }
}