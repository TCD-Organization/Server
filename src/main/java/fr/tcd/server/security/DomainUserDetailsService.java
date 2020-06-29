package fr.tcd.server.security;

import fr.tcd.server.runner.RunnerModel;
import fr.tcd.server.runner.RunnerRepository;
import fr.tcd.server.user.SecurityUser;
import fr.tcd.server.user.UserDomain;
import fr.tcd.server.user.UserModel;
import fr.tcd.server.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RunnerRepository runnerRepository;

    public DomainUserDetailsService(UserRepository userRepository, RunnerRepository runnerRepository) {
        this.userRepository = userRepository;
        this.runnerRepository = runnerRepository;
    }

    @Override
    public SecurityUser loadUserByUsername(String username) {
        UserDomain user = findUser(username);

        SecurityUser securityUser = new SecurityUser(user.getId(), user.getUsername(), user.getPassword(), user.getRoles());

        System.out.println("User :" + securityUser.toString());
        return securityUser;

    }

    private UserDomain findUser(String username) {
        UserModel user;
        RunnerModel runner;
        if((user = userRepository.findByUsername(username).orElse(null)) != null) {
            System.out.println("User found");
            return user;
        } else if((runner = runnerRepository.findByRunnername(username).orElse(null)) != null) {
            System.out.println("Runner found");
            return runner;
        } else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }
}
