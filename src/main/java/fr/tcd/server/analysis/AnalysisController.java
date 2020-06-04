package fr.tcd.server.analysis;

import fr.tcd.server.analysis.exception.AnalysisAlreadyExistsException;
import fr.tcd.server.analysis.exception.AnalysisNotCreatedException;
import fr.tcd.server.document.exception.DocumentNotFoundException;
import fr.tcd.server.document.exception.DocumentNotUpdatedException;
import fr.tcd.server.runner_analysis.exception.RunnerAnalysisNotSentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/document/{docID}/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping
    public ResponseEntity createAnalysis(@PathVariable("docID") String docID, @Valid @RequestBody AnalysisDTO analysisDTO) {
        String newAnalysisId = analysisService.processNewAnalysis(docID, analysisDTO);
        return new ResponseEntity<>(newAnalysisId, HttpStatus.CREATED);
    }

    // ============== NON-API ==============

}
