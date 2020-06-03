package fr.tcd.server.login;

import fr.tcd.server.security.utils.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManager;

    public LoginController(TokenProvider tokenProvider,
                           AuthenticationManagerBuilder authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity loginUser(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);
        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORIZATION, "Bearer " + token);
        return new ResponseEntity(httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/runner")
    public ResponseEntity loginRunner(@RequestBody LoginRunnerDTO loginRunnerDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRunnerDTO.getRunnername(), loginRunnerDTO.getKey());
        Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);
        String token = tokenProvider.createRunnerToken(authentication, port);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORIZATION, "Bearer " + token);
        return new ResponseEntity(httpHeaders, HttpStatus.OK);
    }



    // ============== NON-API ==============

}
