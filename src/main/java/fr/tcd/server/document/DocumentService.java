package fr.tcd.server.document;

import fr.tcd.server.document.exception.DocumentAlreadyExistsException;
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

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentModel createDocument(DocumentDTO documentDTO, String owner) {
        String hash = hashText(documentDTO.getContent());
        if (documentAlreadyExists(hash, owner)) {
            throw new DocumentAlreadyExistsException();
        }

            DocumentModel documentModel = new DocumentModel()
                    .setName(documentDTO.getName())
                    .setHash(hash)
                    .setGenre(documentDTO.getGenre())
                    .setContent(documentDTO.getContent())
                    //.setSize(size)
                    .setOwner(owner);

        return Optional.of(documentRepository.save(documentModel)).orElseThrow(DocumentNotCreatedException::new);
    }


    public List<DocumentModel> getMyDocuments(String name) {
        return documentRepository.findByOwner(name);
    }

    public DocumentModel getDocument(String id, String owner) {
        return documentRepository.findByIdAndOwner(id, owner).orElseThrow(DocumentNotFoundException::new);
    }

    private boolean documentAlreadyExists(String hash, String owner) {
        return documentRepository.existsByHashAndOwner(hash, owner);
    }

    private String hashText(String text) {
        return DigestUtils.md5Hex(text).toUpperCase();
    }
}
