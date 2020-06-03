package fr.tcd.server.document;

import fr.tcd.server.document.exception.DocumentNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
                                         @RequestParam(required = false, name = "file") MultipartFile file) {

        //TODO: Do a Switch on Type of data between file or link and process
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<DocumentModel> newDocument = documentService.createDocument(documentDTO, ((UserDetails)principal).getUsername());
        if(newDocument.isEmpty()) {
            throw new DocumentNotCreatedException("Document not created");
        }

        return new ResponseEntity<>(newDocument.get().getId(), HttpStatus.CREATED);
    }

    // ============== NON-API ==============

}
