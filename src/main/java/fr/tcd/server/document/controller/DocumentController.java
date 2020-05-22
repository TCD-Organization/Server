package fr.tcd.server.document.controller;

import fr.tcd.server.document.dto.DocumentDTO;
import fr.tcd.server.document.model.DocumentModel;
import fr.tcd.server.document.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

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
        //TODO: DocumentService.createDocument(documentDTO);
        //TODO: Get the user id from the authorization and add it to the created Document
        /*DocumentModel newDocumentModelId = createDocument(documentDTO);
        if(newDocumentModelId != null) {
            return new ResponseEntity<>(newDocumentModelId, HttpStatus.CREATED);
        }
        */
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ============== NON-API ==============

}
