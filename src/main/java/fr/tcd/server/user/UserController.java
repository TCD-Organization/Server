package fr.tcd.server.user;

import fr.tcd.server.user.dto.AdminDTO;
import fr.tcd.server.user.dto.IUserDTO;
import fr.tcd.server.user.dto.UserDTO;
import fr.tcd.server.user.exception.UserAlreadyExistsException;
import fr.tcd.server.user.exception.UserNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity registerUser(@Valid @RequestBody UserDTO userDTO) {
        return registerAccount(userDTO);
    }

    // No template (genericity) because we have to manage rights for this specific route
    // Edit: Also, we can't (ambiguous mapping not allowed)
    @PostMapping("/admin")
    public ResponseEntity registerAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        return registerAccount(adminDTO);
    }

    // ============== NON-API ==============

    private ResponseEntity registerAccount(@Valid @RequestBody IUserDTO accountDTO) {
        userService.registerNewUser(accountDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
