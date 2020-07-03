package fr.tcd.server.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class SecurityUser implements UserDetails {
    private String id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUser(String id, String username, String password, List<String> roles) {
        final Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        String ROLE_PREFIX = "ROLE_";

        if (roles != null) {
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role)));
        }

        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public SecurityUser(String id, String subject, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = subject;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SecurityUser [id=" + getId() + ", subject=" + getUsername() + ", password=" + getPassword() +
                ", authorities=" + getAuthorities() + "]";
    }
}
