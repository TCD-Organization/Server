package fr.tcd.server.user.service;

import fr.tcd.server.user.exception.UserAlreadyExistsException;
import fr.tcd.server.user.dto.UserDTO;
import fr.tcd.server.user.model.UserModel;

public abstract class IUserService {
    abstract UserModel registerNewUserAccount(UserDTO accountDTO) throws UserAlreadyExistsException;
    abstract UserModel findByUsername(String username);
    abstract boolean usernameAlreadyExists(String username);
}
