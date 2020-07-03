package fr.tcd.server.security.utils;

import fr.tcd.server.user.SecurityUser;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String ID_KEY = "id";
    private final long tokenValidityInMilliseconds = Duration.ofHours(48).getSeconds() * 1000;
    private final byte[] secret;

    public TokenProvider(@Value("${security.token.secret}") CharSequence secret) {
        this.secret = secret.toString().getBytes();
    }

    public String generateToken(SecurityUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());

        String authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        claims.put(AUTHORITIES_KEY, authorities);
        return createToken(claims, user.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = decodeToken(token).getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        SecurityUser principal = new SecurityUser(claims.get(ID_KEY).toString(), claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken, HttpServletRequest httpServletRequest) {
        try {
            decodeToken(authToken);
            return true;

        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT Token");
            httpServletRequest.setAttribute("expired",e.getMessage());

        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT token.");
        }
        return false;
    }

    private Jws<Claims> decodeToken(String authToken) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(authToken);
    }
}