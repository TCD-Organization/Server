package fr.tcd.server.analysis.controller;

import fr.tcd.server.analysis.dto.AnalysisDTO;
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
        // TODO: Vérifier que le docID existe en base
        /* TODO: AnalysisService.createAnalysis(analysisDTO)
        AnalysisModel newAnalysisModelId = createAnalysis(analysisDTO);

        if(newAnalysisModelId != null) {
            return new ResponseEntity<>(newAnalysisModelId, HttpStatus.CREATED);
        }
        */
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // ============== NON-API ==============

}
