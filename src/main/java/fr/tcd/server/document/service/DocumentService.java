package fr.tcd.server.document.service;

import fr.tcd.server.document.dao.DocumentRepository;
import fr.tcd.server.document.dto.DocumentDTO;
import fr.tcd.server.document.exception.DocumentAlreadyExistsException;
import fr.tcd.server.document.model.DocumentModel;
import fr.tcd.server.user.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService extends IDocumentService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public DocumentModel processNewTask(DocumentDTO documentDTO) throws DocumentAlreadyExistsException {
        // Do checksum of documentDTO.data.get('value')
        if (analysisForDocumentAlreadyExists(documentDTO.getName())) {
            throw new UserAlreadyExistsException("A user with that username already exists");
        }
        DocumentModel documentModel = new DocumentModel()
                .setUsername(documentDTO.getUsername())
                .setPassword(passwordEncoder.encode(documentDTO.getPassword()))
                .setEmail(documentDTO.getEmail())
                .setRoles(List.of("USER"));

        return documentRepository.save(documentModel);
    }

    boolean analysisForDocumentAlreadyExists(String name, String hashmap) {
        return documentRepository.existsByUsername(name);
        // Vérification des traitements sur le texte :
        // Si déjà effectué renvoyer le résultat au front
        // Si pas effectué
    }
}
