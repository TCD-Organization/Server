package fr.tcd.server.document;

import fr.tcd.server.analysis.AnalysisModel;
import fr.tcd.server.document.exception.DocumentAlreadyExistsException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Optional<DocumentModel> createDocument(DocumentDTO documentDTO) throws DocumentAlreadyExistsException {
        //TODO: Get size
        //TODO: Get user_id Claims claims = tokenProvider.decodeToken(token).getBody();
        String hash = hashText(documentDTO.getContent());

        DocumentModel documentModel = new DocumentModel()
                .setName(documentDTO.getName())
                .setChecksum(hash)
                .setGenre(documentDTO.getGenre())
                .setContent(documentDTO.getContent())
                //.setSize(size)
                .setAnalyses(new ArrayList<AnalysisModel>());
                //.setUser_id();

        return Optional.ofNullable(documentRepository.save(documentModel));
    }

    private String hashText(String text)  {
        return DigestUtils.md5Hex(text).toUpperCase();
    }
}
