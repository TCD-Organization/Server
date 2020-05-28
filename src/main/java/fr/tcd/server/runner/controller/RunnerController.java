package fr.tcd.server.runner.controller;

import fr.tcd.server.document.dto.DocumentDTO;
import fr.tcd.server.document.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/runner")
public class RunnerController {

    private final DocumentService documentService;

    public RunnerController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity authenticate(@Valid @RequestBody DocumentDTO runnerRegisterDTO,
                                         @RequestHeader("Token") String token) {

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
