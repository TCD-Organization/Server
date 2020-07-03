package fr.tcd.server.user.dto;

import fr.tcd.server.user.UserModel;

import java.util.List;

public class AdminDTO extends IUserDTO {
    @Override
    public UserModel toUserModel(String password) {
        return new UserModel(this.getUsername(), password, List.of("USER", "ADMIN"));
    }
}
