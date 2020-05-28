package fr.tcd.server.user.service;

import fr.tcd.server.user.dao.UserRepository;
import fr.tcd.server.user.dto.IUserDTO;
import fr.tcd.server.user.exception.UserAlreadyExistsException;
import fr.tcd.server.user.model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserModel> registerNewUser(IUserDTO userDTO) throws UserAlreadyExistsException {
        if (usernameAlreadyExists(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("A user with that username already exists");
        }
        UserModel user = userDTO.toUserModel(passwordEncoder);

        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    boolean usernameAlreadyExists(String username) {
        return userRepository.existsByUsername(username);
    }


}
