package fr.tcd.server.task.controller;

import fr.tcd.server.task.dto.TaskDTO;
import fr.tcd.server.user.dto.UserDTO;
import fr.tcd.server.user.model.User;
import fr.tcd.server.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final UserService userService;

    public TaskController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity register(@Valid @RequestBody TaskDTO taskDTO) {
        Task newTaskId = taskService.processNewTask(userDTO);
        if(newTask != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ============== NON-API ==============

}
