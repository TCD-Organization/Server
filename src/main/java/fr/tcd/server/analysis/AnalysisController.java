package fr.tcd.server.analysis;

import fr.tcd.server.analysis.dto.AnalysisDTO;
import fr.tcd.server.analysis.dto.AnalysisProgressionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping
    public ResponseEntity<String> createAnalysis(@Valid @RequestBody AnalysisDTO analysisDTO, Principal principal, UriComponentsBuilder uriBuilder) {
        String newAnalysisId = analysisService.createNewAnalysis(analysisDTO, principal.getName()).getId();
        URI location = uriBuilder.path("/analysis/{analysisId}").build(newAnalysisId);

        return ResponseEntity.created(location).body(newAnalysisId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnalysisModel>> getMyAnalyses(Principal principal) {
        List<AnalysisModel> analyses = analysisService.getMyAnalyses(principal.getName());
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{analysisId}")
    public ResponseEntity<AnalysisModel> getAnalysis(@PathVariable("analysisId") String analysisId, Principal principal) {
        AnalysisModel analysis = analysisService.getAnalysis(analysisId, principal.getName());
        return ResponseEntity.ok(analysis);
    }

    @DeleteMapping("/{analysisId}")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable("analysisId") String analysisId, Principal principal) {
        analysisService.deleteAnalysis(analysisId, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{analysisId}/progress")
    public ResponseEntity<String> updateAnalysisProgression(@PathVariable("analysisId") String analysisId, @Valid @RequestBody AnalysisProgressionDTO analysisProgression, Principal principal) {
        AnalysisModel analysis = analysisService.processAnalysisUpdate(analysisProgression, analysisId, principal.getName());
        return ResponseEntity.ok(analysis.getId());
    }

    // ============== NON-API ==============

}
