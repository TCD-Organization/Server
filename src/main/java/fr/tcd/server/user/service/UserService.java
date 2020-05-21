package fr.tcd.server.user.service;

import fr.tcd.server.user.exception.UserAlreadyExistsException;
import fr.tcd.server.user.dao.UserRepository;
import fr.tcd.server.user.dto.UserDTO;
import fr.tcd.server.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistsException {
        if (usernameAlreadyExists(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("A user with that username already exists");
        }
        User user = new User()
                .setUsername(userDTO.getUsername())
                .setPassword(passwordEncoder.encode(userDTO.getPassword()))
                .setEmail(userDTO.getEmail())
                .setRoles(List.of("USER"));

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    boolean usernameAlreadyExists(String username) {
        return userRepository.existsByUsername(username);
    }


}
