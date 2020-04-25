package fr.tcd.server.user.service;

import fr.tcd.server.exceptions.UserAlreadyExistsException;
import fr.tcd.server.user.dto.UserDTO;
import fr.tcd.server.user.model.User;

public abstract class IUserService {
    abstract User registerNewUserAccount(UserDTO accountDTO) throws UserAlreadyExistsException;
    abstract User findByUsername(String username);
    abstract boolean usernameAlreadyExists(String username);
}
