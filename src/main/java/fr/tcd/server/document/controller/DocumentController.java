package fr.tcd.server.document.controller;

import fr.tcd.server.document.dto.DocumentDTO;
import fr.tcd.server.document.exception.DocumentNotCreatedException;
import fr.tcd.server.document.model.DocumentModel;
import fr.tcd.server.document.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity createDocument(@Valid @RequestBody DocumentDTO documentDTO,
                                         @RequestHeader("Authorization") String authorization,
                                         @RequestParam(required = false, name = "file") MultipartFile file) {

        //TODO: Do a Switch on Type of data between file or link and process
        //TODO: Get the user id from the authorization and add it to the created Document

        Optional<DocumentModel> newDocument = documentService.createDocument(documentDTO);
        if(newDocument.isEmpty()) {
            throw new DocumentNotCreatedException("Document not created");
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ============== NON-API ==============

}
