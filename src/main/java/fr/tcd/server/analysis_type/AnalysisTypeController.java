package fr.tcd.server.analysis_type;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/analysis-types")
public class AnalysisTypeController {

    private final AnalysisTypeService analysisTypeService;

    public AnalysisTypeController(AnalysisTypeService analysisTypeService) {
        this.analysisTypeService = analysisTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnalysisTypeModel>> getAnalysisTypes() {
        return ResponseEntity.ok(analysisTypeService.getAnalysisTypes());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAnalysisType(@RequestParam("name") @NotEmpty String name) {
        String newAnalysisTypeId = analysisTypeService.createAnalysisType(name).getId();
        return ResponseEntity.ok(newAnalysisTypeId);
    }

    @DeleteMapping("/{analysisTypeId}")
    public ResponseEntity<List<AnalysisTypeModel>> deleteAnalysisType(@PathVariable("analysisTypeId") String analysisTypeId) {
        analysisTypeService.deleteAnalysisType(analysisTypeId);
        return ResponseEntity.ok().build();
    }
    // ============== NON-API ==============

}
