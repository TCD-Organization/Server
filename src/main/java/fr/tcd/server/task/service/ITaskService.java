package fr.tcd.server.task.service;

import fr.tcd.server.exceptions.TaskAlreadyExistsException;
import fr.tcd.server.task.dto.TaskDTO;
import fr.tcd.server.task.model.Task;

public abstract class ITaskService {
    abstract Task processNewTask(TaskDTO taskDTO) throws TaskAlreadyExistsException;
}
