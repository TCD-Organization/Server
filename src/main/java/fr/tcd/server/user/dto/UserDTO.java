package fr.tcd.server.user.dto;

import fr.tcd.server.user.UserModel;

import java.util.List;

public class UserDTO extends IUserDTO {
    @Override
    public UserModel toUserModel(String password) {
        return new UserModel(this.getUsername(), password, this.getEmail(), List.of("USER"));
    }
}
