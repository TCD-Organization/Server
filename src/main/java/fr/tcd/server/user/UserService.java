package fr.tcd.server.user;

import fr.tcd.server.user.dto.IUserDTO;
import fr.tcd.server.user.exception.UserAlreadyExistsException;
import fr.tcd.server.user.exception.UserNotCreatedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(IUserDTO userDTO) throws UserAlreadyExistsException, UserNotCreatedException {
        if (usernameAlreadyExists(userDTO.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        UserModel user = userDTO.toUserModel(passwordEncoder);

        UserModel result = userRepository.save(user);
        if (result == null){
            throw new UserNotCreatedException();
        }
    }

    private boolean usernameAlreadyExists(String username) {
        return userRepository.existsByUsername(username);
    }


}
