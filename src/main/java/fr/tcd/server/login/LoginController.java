package fr.tcd.server.login;

import fr.tcd.server.runner.RunnerService;
import fr.tcd.server.runner.dto.RunnerConnectDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final RunnerService runnerService;

    public LoginController(LoginService loginService, RunnerService runnerService) {
        this.loginService = loginService;
        this.runnerService = runnerService;
    }

    @PostMapping
    public ResponseEntity<Void> loginUser(@RequestBody LoginDTO loginDTO) {
        final String jwtToken = loginService.connectUser(loginDTO.getUsername(), loginDTO.getPassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORIZATION, "Bearer " + jwtToken);
        return ResponseEntity.ok().headers(httpHeaders).build();
    }

    @PostMapping("/runner")
    public ResponseEntity<Void> connectRunner(@RequestBody RunnerConnectDTO runnerConnectDTO) {
        final String jwtToken = loginService.connectUser(runnerConnectDTO.getRunnername(), runnerConnectDTO.getKey());

        runnerService.runnerUp(runnerConnectDTO.getRunnername());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORIZATION, "Bearer " + jwtToken);
        return ResponseEntity.ok().headers(httpHeaders).build();
    }

    // ============== NON-API ==============

}
