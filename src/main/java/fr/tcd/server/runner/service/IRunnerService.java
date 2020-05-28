package fr.tcd.server.runner.service;

import fr.tcd.server.runner.dto.RunnerDTO;
import fr.tcd.server.runner.exception.RunnerAlreadyExistsException;
import fr.tcd.server.runner.model.RunnerModel;

public abstract class IRunnerService {
    abstract RunnerModel registerNewRunner(RunnerDTO runnerDTO) throws RunnerAlreadyExistsException;

    abstract boolean runnerAlreadyExists(String token);
}
