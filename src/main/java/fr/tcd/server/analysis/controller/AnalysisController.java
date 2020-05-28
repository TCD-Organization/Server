package fr.tcd.server.analysis.controller;

import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.exception.AnalysisNotCreatedException;
import fr.tcd.server.analysis.service.AnalysisService;
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
    public ResponseEntity newAnalysis(@PathVariable("docID") String docID, @Valid @RequestBody AnalysisDTO analysisDTO) {
        String AnalysisId = analysisService.processNewAnalysis(Long.valueOf(docID), analysisDTO);
        if (AnalysisId.isEmpty()) {
            throw new AnalysisNotCreatedException("Analysis not created");
        }
        return new ResponseEntity<>(AnalysisId, HttpStatus.CREATED);
    }


    // ============== NON-API ==============

}
