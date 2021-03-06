package fr.tcd.server.security;

import fr.tcd.server.security.utils.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final DomainUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    SecurityConfig(TokenProvider tokenProvider, DomainUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authenticationProvider(authProvider())
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/login", "/register", "/login/runner", "/ping").permitAll()
                .antMatchers("/document", "/document/*", "/analysis", "/analysis/*", "/analysis-types/all").hasRole("USER")
                .antMatchers("/admin/**", "/register/admin", "/runner", "/analysis-types/", "/analysis/**/progress", "/analysis/**/cancel").hasRole("ADMIN")
                .antMatchers("/analysis/**/progress", "/analysis/**/cancel").hasRole("RUNNER")
            .anyRequest()
                .authenticated()
                .and()
            .addFilterBefore(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
