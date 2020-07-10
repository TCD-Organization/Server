package fr.tcd.server.document;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotEmpty;
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
    public ResponseEntity<String> createDocument(
            @RequestParam("name") @NotEmpty String name,
            @RequestParam("genre") @NotEmpty String genre,
            @RequestParam("content_type") @NotEmpty DocumentContentType content_type,
            @RequestParam("content") @NotEmpty String content,
            @RequestParam(name = "file", required = false) MultipartFile mpFile,
            Principal principal, UriComponentsBuilder uriBuilder)
    {
        DocumentDTO documentDTO = new DocumentDTO(name, genre, content_type, content);

        content = documentService.getDocumentContent(documentDTO.getContent(), documentDTO.getContent_type(), mpFile);
        documentDTO.setContent(content);

        String newDocumentId = documentService.createDocument(documentDTO.getName(), documentDTO.getGenre(),
                documentDTO.getContent(), principal.getName()).getId();
        URI location = uriBuilder.path("/document/{docId}").build(newDocumentId);

        return ResponseEntity.created(location).body(newDocumentId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DocumentModel>> getMyDocuments(Principal principal) {
        List<DocumentModel> analyses = documentService.getMyDocuments(principal.getName());
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentModel> getDocument(@PathVariable("documentId") String documentId, Principal principal) {
        DocumentModel analysis = documentService.getDocument(documentId, principal.getName());
        return ResponseEntity.ok(analysis);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<DocumentModel> deleteDocument(@PathVariable("documentId") String documentId, Principal principal) {
        documentService.deleteDocument(documentId, principal.getName());
        return ResponseEntity.ok().build();
    }

        // ============== NON-API ==============

}
