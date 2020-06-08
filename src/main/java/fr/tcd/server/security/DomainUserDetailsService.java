package fr.tcd.server.security;

import fr.tcd.server.runner.RunnerModel;
import fr.tcd.server.runner.RunnerRepository;
import fr.tcd.server.user.UserModel;
import fr.tcd.server.user.UserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RunnerRepository runnerRepository;

    public DomainUserDetailsService(UserRepository userRepository, RunnerRepository runnerRepository) {
        this.userRepository = userRepository;
        this.runnerRepository = runnerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserModel user;
        RunnerModel runner;
        if((user = userRepository.findByUsername(username).orElse(null)) != null) {
            return buildUserDetails(username, user);
        } else if((runner = runnerRepository.findByRunnername(username).orElse(null)) != null) {
            return buildRunnerDetails(username, runner);
        } else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }

    private UserDetails buildUserDetails(String username, UserModel user) {
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

    private UserDetails buildRunnerDetails(String runnername, RunnerModel runner) {
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(runnername);
        builder.username(runnername)
                .password(runner.getKey())
                .roles(runner.getRoles().toArray(new String[0]))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false);

        return builder.build();
    }



}
