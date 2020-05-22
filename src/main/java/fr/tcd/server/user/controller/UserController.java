package fr.tcd.server.user.controller;

import fr.tcd.server.user.dto.UserDTO;
import fr.tcd.server.user.model.UserModel;
import fr.tcd.server.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity register(@Valid @RequestBody UserDTO userDTO) {
        UserModel createdUser = userService.registerNewUserAccount(userDTO);
        if(createdUser != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ============== NON-API ==============

}
