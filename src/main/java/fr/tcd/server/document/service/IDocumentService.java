package fr.tcd.server.document.service;

import fr.tcd.server.document.dto.DocumentDTO;
import fr.tcd.server.document.exception.DocumentAlreadyExistsException;
import fr.tcd.server.document.model.DocumentModel;

public abstract class IDocumentService {
    abstract DocumentModel processNewTask(DocumentDTO documentDTO) throws DocumentAlreadyExistsException;
}
