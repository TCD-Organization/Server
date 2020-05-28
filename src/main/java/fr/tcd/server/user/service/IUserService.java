package fr.tcd.server.user.service;

import fr.tcd.server.user.dto.IUserDTO;
import fr.tcd.server.user.exception.UserAlreadyExistsException;
import fr.tcd.server.user.model.UserModel;

public abstract class IUserService {
    abstract UserModel registerNewUser(IUserDTO accountDTO) throws UserAlreadyExistsException;

    abstract UserModel findByUsername(String username);
    abstract boolean usernameAlreadyExists(String username);
}
