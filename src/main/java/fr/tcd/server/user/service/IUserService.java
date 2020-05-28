package fr.tcd.server.user.service;

import fr.tcd.server.user.dto.IUserDTO;
import fr.tcd.server.user.exception.UserAlreadyExistsException;
import fr.tcd.server.user.model.UserModel;

import java.util.Optional;

public abstract class IUserService {
    abstract Optional<UserModel> registerNewUser(IUserDTO accountDTO) throws UserAlreadyExistsException;
    abstract boolean usernameAlreadyExists(String username);
}
