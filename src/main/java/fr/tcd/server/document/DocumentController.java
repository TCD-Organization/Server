package fr.tcd.server.document;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<String> createDocument(@Valid @RequestBody DocumentDTO documentDTO,
                                         @RequestParam(required = false, name = "file") MultipartFile file,
                                                 Principal principal, UriComponentsBuilder uriBuilder) {

        //TODO: Do a Switch on Type of data between file or link and process
        String newDocumentId = documentService.createDocument(documentDTO, principal.getName()).getId();
        URI location = uriBuilder.path("/document/{docId}").build(newDocumentId);

        return ResponseEntity.created(location).body(newDocumentId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DocumentModel>> getMyDocuments(Principal principal) {
        List<DocumentModel> analyses = documentService.getMyDocuments(principal.getName());
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentModel> getAnalysis(@PathVariable("documentId") String documentId, Principal principal) {
        DocumentModel analysis = documentService.getDocument(documentId, principal.getName());
        return ResponseEntity.ok(analysis);
    }

    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestParam(required = false, name = "file") MultipartFile mpFile) {
        File file = documentService.convertMultiPartToFile(mpFile);
        return ResponseEntity.ok(documentService.generateTxtFromPDF(file));
    }

        // ============== NON-API ==============

}
