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
        String runnerId = runnerService.registerNewRunner(runnerDTO).getId();
        URI location = uriBuilder.path("/runner/{runnerId}").build(runnerId);

        return ResponseEntity.created(location).body(runnerId);
    }

    // ============== NON-API ==============

}
