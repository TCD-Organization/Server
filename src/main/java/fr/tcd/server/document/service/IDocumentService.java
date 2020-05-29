package fr.tcd.server.document.service;

import fr.tcd.server.document.dto.DocumentDTO;
import fr.tcd.server.document.exception.DocumentAlreadyExistsException;
import fr.tcd.server.document.model.DocumentModel;

import java.util.Optional;

public abstract class IDocumentService {
    abstract Optional<DocumentModel> createDocument(DocumentDTO documentDTO) throws DocumentAlreadyExistsException;
}
