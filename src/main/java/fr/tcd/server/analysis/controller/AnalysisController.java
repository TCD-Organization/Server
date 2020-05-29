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
    public ResponseEntity createAnalysis(@PathVariable("docID") String docID, @Valid @RequestBody AnalysisDTO analysisDTO) {
        boolean newAnalysis = analysisService.processNewAnalysis(Long.valueOf(docID), analysisDTO);
        if (!newAnalysis) {
            throw new AnalysisNotCreatedException("Analysis not created");
        }

        return new ResponseEntity<>("nothing", HttpStatus.CREATED);
    }


    // ============== NON-API ==============

}
