package fr.tcd.server.document;

import fr.tcd.server.document.exception.DocumentAlreadyExistsException;
import fr.tcd.server.document.exception.DocumentContentNotRetrievedException;
import fr.tcd.server.document.exception.DocumentNotCreatedException;
import fr.tcd.server.document.exception.DocumentNotFoundException;
import fr.tcd.server.utils.file_content.exception.FileNotSpecifiedException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static fr.tcd.server.document.DocumentContentType.LINK;
import static fr.tcd.server.utils.file.FileUtils.downloadMultiPartToFile;
import static fr.tcd.server.utils.file_content.FileContentUtils.getContentFromFile;
import static fr.tcd.server.utils.file_content.FileContentUtils.getContentFromLink;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentModel createDocument(String name, String genre, String content , String owner) {
        String hash = hashText(content);
        if (documentAlreadyExists(hash, owner)) {
            throw new DocumentAlreadyExistsException();
        }

        DocumentModel documentModel = new DocumentModel()
                .setName(name)
                .setHash(hash)
                .setGenre(genre)
                .setContent(content)
                .setOwner(owner);

        return Optional.of(documentRepository.save(documentModel)).orElseThrow(DocumentNotCreatedException::new);
    }

    public String getDocumentContent(String content, DocumentContentType contentType, @Nullable MultipartFile mpFile) {
        try {
            switch (contentType) {
                case LINK:
                    content = getContentFromLink(content);

                    break;
                case FILE:
                    if (mpFile == null)
                        throw new FileNotSpecifiedException();

                    File file = downloadMultiPartToFile(mpFile);
                    content = getContentFromFile(file);
                    file.delete();
                    break;
                default:
                    break;
            }
        } catch (IOException | MimeTypeException e) {
            throw new DocumentContentNotRetrievedException(e);
        }
        return content;
    }

    public List<DocumentModel> getMyDocuments(String name) {
        return documentRepository.findByOwner(name);
    }

    public DocumentModel getDocument(String id, String owner) {
        return documentRepository.findByIdAndOwner(id, owner).orElseThrow(DocumentNotFoundException::new);
    }

    public void deleteDocument(String id, String owner) {
        if (documentExists(id, owner)) {
            documentRepository.deleteByIdAndOwner(id, owner);
        } else {
            throw new DocumentNotFoundException();
        }
    }

    private boolean documentAlreadyExists(String hash, String owner) {
        return documentRepository.existsByHashAndOwner(hash, owner);
    }

    private boolean documentExists(String id, String owner) {
        return documentRepository.existsByIdAndOwner(id, owner);
    }

    private String hashText(String text) {
        return DigestUtils.md5Hex(text).toUpperCase();
    }
}
