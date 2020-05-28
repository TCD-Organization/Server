package fr.tcd.server.runner.controller;

import fr.tcd.server.runner.dto.RunnerDTO;
import fr.tcd.server.runner.model.RunnerModel;
import fr.tcd.server.runner.service.RunnerService;
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
        RunnerModel newRunner = runnerService.registerNewRunner(runnerDTO);
        if(newRunner != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ============== NON-API ==============

}
