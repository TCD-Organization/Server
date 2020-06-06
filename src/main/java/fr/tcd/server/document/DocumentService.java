package fr.tcd.server.document;

import fr.tcd.server.analysis.AnalysisModel;
import fr.tcd.server.document.exception.DocumentAlreadyExistsException;
import fr.tcd.server.document.exception.DocumentNotCreatedException;
import fr.tcd.server.user.UserModel;
import fr.tcd.server.user.UserRepository;
import fr.tcd.server.user.exception.UserNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        //TODO: Get user_id Claims claims = tokenProvider.decodeToken(token).getBody();
        UserModel user = Optional.ofNullable(userRepository.findByUsername(username)).orElseThrow(UserNotFoundException::new);

        String hash = hashText(documentDTO.getContent());

        DocumentModel documentModel = new DocumentModel()
                .setName(documentDTO.getName())
                .setChecksum(hash)
                .setGenre(documentDTO.getGenre())
                .setContent(documentDTO.getContent())
                //.setSize(size)
                .setAnalyses(new ArrayList<AnalysisModel>())
                .setUserId(user.getId());

        return Optional.of(documentRepository.save(documentModel)).orElseThrow(DocumentNotCreatedException::new);
    }

    private String hashText(String text)  {
        return DigestUtils.md5Hex(text).toUpperCase();
    }
}
