package fr.tcd.server.analysis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/document/{docID}/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping
    public ResponseEntity<String> createAnalysis(@PathVariable("docID") String docID, @Valid @RequestBody AnalysisDTO analysisDTO) {
        String newAnalysisId = analysisService.processNewAnalysis(docID, analysisDTO);
        return new ResponseEntity<>(newAnalysisId, HttpStatus.CREATED);
    }

    // ============== NON-API ==============

}
