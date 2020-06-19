package fr.tcd.server.runner;

import fr.tcd.server.runner.dto.RunnerConnectDTO;
import fr.tcd.server.runner.dto.RunnerDTO;
import fr.tcd.server.security.utils.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/runner")
public class RunnerController {

    private final RunnerService runnerService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManager;

    public RunnerController(RunnerService runnerService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManager) {
        this.runnerService = runnerService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<String> registerRunner(@Valid @RequestBody RunnerDTO runnerDTO, UriComponentsBuilder uriBuilder) {
        String runnerName = runnerService.registerNewRunner(runnerDTO).getRunnername();
        URI location = uriBuilder.path("/runner/{runnerName}").build(runnerName);

        return ResponseEntity.created(location).body(runnerName);
    }


    @PostMapping("/connect")
    public ResponseEntity<Void> connectRunner(@RequestBody RunnerConnectDTO runnerConnectDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(runnerConnectDTO.getRunnername(), runnerConnectDTO.getKey());
        Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);
        String token = tokenProvider.createToken(authentication);

        runnerService.runnerUp(runnerConnectDTO.getRunnername());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORIZATION, "Bearer " + token);
        return ResponseEntity.ok().headers(httpHeaders).build();
    }
    // ============== NON-API ==============

}
