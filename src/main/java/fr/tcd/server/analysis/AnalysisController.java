package fr.tcd.server.analysis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/document/{docID}/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping
    public ResponseEntity<String> createAnalysis(@PathVariable("docID") String docID, @Valid @RequestBody AnalysisDTO analysisDTO, UriComponentsBuilder uriBuilder) {
        String newAnalysisId = analysisService.processNewAnalysis(docID, analysisDTO).getId();
        URI location = uriBuilder.path("/document/{docId}/analysis/{Id}").build(docID, newAnalysisId);

        return ResponseEntity.created(location).body(newAnalysisId);
    }

    // ============== NON-API ==============

}
