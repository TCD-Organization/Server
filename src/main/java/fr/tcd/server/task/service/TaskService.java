package fr.tcd.server.task.service;

import fr.tcd.server.exceptions.TaskAlreadyExistsException;
import fr.tcd.server.exceptions.UserAlreadyExistsException;
import fr.tcd.server.task.model.Task;
import fr.tcd.server.user.dao.UserRepository;
import fr.tcd.server.user.dto.UserDTO;
import fr.tcd.server.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService extends ITaskService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User processNewTask(UserDTO taskDTO) throws TaskAlreadyExistsException {
        if (taskAlreadyExists(taskDTO.getUsername())) {
            throw new UserAlreadyExistsException("A user with that username already exists");
        }
        Task task = new Task()
                .setUsername(taskDTO.getUsername())
                .setPassword(passwordEncoder.encode(taskDTO.getPassword()))
                .setEmail(taskDTO.getEmail())
                .setRoles(List.of("USER"));

        return userRepository.save(task);
    }
}
