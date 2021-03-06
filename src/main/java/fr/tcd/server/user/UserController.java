package fr.tcd.server.user;

import fr.tcd.server.user.dto.AdminDTO;
import fr.tcd.server.user.dto.IUserDTO;
import fr.tcd.server.user.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/register")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO, UriComponentsBuilder uriBuilder) {
        return registerAccount(userDTO, uriBuilder);
    }

    // No template (genericity) because we have to manage rights for this specific route
    // Edit: Also, we can't (ambiguous mapping not allowed)
    @PostMapping("/admin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody AdminDTO adminDTO, UriComponentsBuilder uriBuilder) {
        return registerAccount(adminDTO, uriBuilder);
    }

    // ============== NON-API ==============

    private ResponseEntity<String> registerAccount(IUserDTO accountDTO, UriComponentsBuilder uriBuilder) {
        String newUserId = userService.registerNewUser(accountDTO).getId();
        URI location = uriBuilder.path("/user/{Id}").build(newUserId);
        return ResponseEntity.created(location).build();
    }
}
