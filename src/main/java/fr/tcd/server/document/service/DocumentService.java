package fr.tcd.server.document.service;

import fr.tcd.server.analysis.model.AnalysisModel;
import fr.tcd.server.document.dao.DocumentRepository;
import fr.tcd.server.document.dto.DocumentDTO;
import fr.tcd.server.document.exception.DocumentAlreadyExistsException;
import fr.tcd.server.document.model.DocumentModel;
import fr.tcd.server.security.utils.TokenProvider;
import fr.tcd.server.user.exception.UserAlreadyExistsException;
import io.jsonwebtoken.Claims;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService extends IDocumentService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public DocumentModel createDocument(DocumentDTO documentDTO) throws DocumentAlreadyExistsException {
        //TODO: Get size
        //TODO: Get user_id Claims claims = tokenProvider.decodeToken(token).getBody();
        String hash = hashText(documentDTO.getContent());
        if (analysisForDocumentAlreadyExists(documentDTO.getName(), hash)) {
            throw new UserAlreadyExistsException("A user with that username already exists");
        }
        DocumentModel documentModel = new DocumentModel()
                .setName(documentDTO.getName())
                .setChecksum(hash)
                .setGenre(documentDTO.getGenre())
                .setContent(documentDTO.getContent())
                //.setSize(size)
                .setAnalyses(new ArrayList<AnalysisModel>());
                //.setUser_id();


        return documentRepository.save(documentModel);
    }

    boolean analysisForDocumentAlreadyExists(String user_id, String checksum) {
        return documentRepository.existsByUserIdAndChecksum(user_id, checksum);
        // Vérification des traitements sur le texte :
        // Si déjà effectué renvoyer le résultat au front
        // Si pas effectué
    }

    public String hashText(String text)  {
        return DigestUtils.md5Hex(text).toUpperCase();
    }
}