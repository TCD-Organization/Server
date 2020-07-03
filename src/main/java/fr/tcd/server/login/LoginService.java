package fr.tcd.server.login;

import fr.tcd.server.security.DomainUserDetailsService;
import fr.tcd.server.security.utils.TokenProvider;
import fr.tcd.server.user.SecurityUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManager;
    private final DomainUserDetailsService domainUserDetailsService;

    public LoginService(TokenProvider tokenProvider,
                           AuthenticationManagerBuilder authenticationManager, DomainUserDetailsService domainUserDetailsService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.domainUserDetailsService = domainUserDetailsService;
    }

    public String connectUser(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.getObject().authenticate(authenticationToken);

        final SecurityUser userDetails = domainUserDetailsService.loadUserByUsername(username);

        return tokenProvider.generateToken(userDetails);
    }
}
