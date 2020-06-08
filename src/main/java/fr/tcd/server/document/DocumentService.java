package fr.tcd.server.document;

import fr.tcd.server.document.exception.DocumentNotCreatedException;
import fr.tcd.server.document.exception.DocumentNotFoundException;
import fr.tcd.server.user.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    public DocumentService(DocumentRepository documentRepository, UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    public DocumentModel createDocument(DocumentDTO documentDTO, String username) {
        //TODO: Get size

        String hash = hashText(documentDTO.getContent());

        DocumentModel documentModel = new DocumentModel()
                .setName(documentDTO.getName())
                .setChecksum(hash)
                .setGenre(documentDTO.getGenre())
                .setContent(documentDTO.getContent())
                //.setSize(size)
                .setOwner(username);

        return Optional.of(documentRepository.save(documentModel)).orElseThrow(DocumentNotCreatedException::new);
    }


    public List<DocumentModel> getMyDocuments(String name) {
        return documentRepository.findByOwner(name);
    }

    public DocumentModel getDocument(String id, String owner) {
        return documentRepository.findByIdAndOwner(id, owner).orElseThrow(DocumentNotFoundException::new);
    }

    private String hashText(String text)  {
        return DigestUtils.md5Hex(text).toUpperCase();
    }
}
