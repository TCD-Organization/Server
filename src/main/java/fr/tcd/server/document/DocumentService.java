package fr.tcd.server.document;

import fr.tcd.server.analysis.AnalysisModel;
import fr.tcd.server.document.exception.DocumentAlreadyExistsException;
import fr.tcd.server.document.exception.DocumentNotCreatedException;
import fr.tcd.server.user.UserModel;
import fr.tcd.server.user.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    public DocumentService(DocumentRepository documentRepository, UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    public DocumentModel createDocument(DocumentDTO documentDTO, String username) throws DocumentAlreadyExistsException {
        //TODO: Get size
        //TODO: Get user_id Claims claims = tokenProvider.decodeToken(token).getBody();
        UserModel user = userRepository.findByUsername(username);
        
        String hash = hashText(documentDTO.getContent());

        DocumentModel documentModel = new DocumentModel()
                .setName(documentDTO.getName())
                .setChecksum(hash)
                .setGenre(documentDTO.getGenre())
                .setContent(documentDTO.getContent())
                //.setSize(size)
                .setAnalyses(new ArrayList<AnalysisModel>())
                .setUserId(user.getId());

        documentModel = documentRepository.save(documentModel);
        if (documentModel == null) {
            throw new DocumentNotCreatedException();
        }

        return documentModel;
    }

    private String hashText(String text)  {
        return DigestUtils.md5Hex(text).toUpperCase();
    }
}
