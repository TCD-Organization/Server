package fr.tcd.server.security.service;

import fr.tcd.server.user.dao.UserRepository;
import fr.tcd.server.user.model.UserModel;
import fr.tcd.server.user.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("UserModel " + username + " not found");
        }

        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.username(username)
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false);


        return builder.build();
    }




}
