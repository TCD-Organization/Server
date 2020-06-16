package fr.tcd.server.analysis_type;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analysis-types")
public class AnalysisTypeController {

    private final AnalysisTypeService analysisTypeService;

    public AnalysisTypeController(AnalysisTypeService analysisTypeService) {
        this.analysisTypeService = analysisTypeService;
    }

    @GetMapping
    public ResponseEntity<List<AnalysisTypeModel>> getAnalysisTypes() {
        return ResponseEntity.ok(analysisTypeService.getAnalysisTypes());
    }

    @DeleteMapping("/{analysisTypeId}")
    public ResponseEntity<List<AnalysisTypeModel>> deleteAnalysisTypes(@PathVariable("analysisTypeId") String analysisTypeId) {
        analysisTypeService.deleteAnalysisType(analysisTypeId);
        return ResponseEntity.ok().build();
    }
    // ============== NON-API ==============

}
