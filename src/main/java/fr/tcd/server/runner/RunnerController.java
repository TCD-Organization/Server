package fr.tcd.server.runner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/runner")
public class RunnerController {

    private final RunnerService runnerService;

    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    @PostMapping
    public ResponseEntity<String> registerRunner(@Valid @RequestBody RunnerDTO runnerDTO, UriComponentsBuilder uriBuilder) {
        String runnerName = runnerService.registerNewRunner(runnerDTO).getRunnername();
        URI location = uriBuilder.path("/runner/{runnerName}").build(runnerName);

        return ResponseEntity.created(location).body(runnerName);
    }

    // ============== NON-API ==============

}
