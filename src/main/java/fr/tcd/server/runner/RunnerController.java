package fr.tcd.server.runner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/runner")
public class RunnerController {

    private final RunnerService runnerService;

    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    @PostMapping
    public ResponseEntity registerRunner(@Valid @RequestBody RunnerDTO runnerDTO) {
        runnerService.registerNewRunner(runnerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ============== NON-API ==============

}
