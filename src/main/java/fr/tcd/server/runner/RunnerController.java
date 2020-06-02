package fr.tcd.server.runner;

import fr.tcd.server.runner.exception.RunnerNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/runner")
public class RunnerController {

    private final RunnerService runnerService;

    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    @PostMapping
    public ResponseEntity registerRunner(@Valid @RequestBody RunnerDTO runnerDTO) {
        Optional<RunnerModel> newRunner = runnerService.registerNewRunner(runnerDTO);
        if(newRunner.isEmpty()) {
            throw new RunnerNotCreatedException("Runner not registered");
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ============== NON-API ==============

}
