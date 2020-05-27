package fr.tcd.server.security.utils;

import fr.tcd.server.user.service.UserService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private final UserService userService;


    private static final String AUTHORITIES_KEY = "auth";
    private final long tokenValidityInMilliseconds = Duration.ofMinutes(5).getSeconds() * 1000;
    private final byte[] secret;

    public TokenProvider(@Value("${security.token.secret:default}") CharSequence secret, UserService userService) {
        this.secret = secret.toString().getBytes();
        this.userService = userService;
    }

    // Generates a token when login route is called
    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities)
            .claim("id", /*TODO: UserService.findByUsername().getId()*/authentication.getName())
            .signWith(SignatureAlgorithm.HS512, secret)
            .setExpiration(validity)
            .compact();
    }

    // Generates Spring Security Authentication token when filtering requests
    public Authentication getAuthentication(String token) {
        Claims claims = decodeToken(token).getBody();

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            decodeToken(authToken);
            return true;

        } catch (JwtException | IllegalArgumentException e) {
                System.out.println("Invalid JWT token.");

            return false;
        }
    }

    private Jws<Claims> decodeToken(String authToken) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(authToken);
    }
}