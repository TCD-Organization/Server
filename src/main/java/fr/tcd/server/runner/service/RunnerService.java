package fr.tcd.server.runner.service;

import fr.tcd.server.runner.dao.RunnerRepository;
import fr.tcd.server.runner.dto.RunnerDTO;
import fr.tcd.server.runner.exception.RunnerAlreadyExistsException;
import fr.tcd.server.runner.model.RunnerModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RunnerService extends IRunnerService {

    private final RunnerRepository runnerRepository;
    private final PasswordEncoder passwordEncoder;

    public RunnerService(RunnerRepository runnerRepository, PasswordEncoder passwordEncoder) {
        this.runnerRepository = runnerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<RunnerModel> registerNewRunner(RunnerDTO runnerDTO) throws RunnerAlreadyExistsException {
        if (runnerAlreadyExists(runnerDTO.getRunnername())) {
            throw new RunnerAlreadyExistsException("A runner with that token already exists");
        }
        RunnerModel runner = runnerDTO.toRunnerModel(passwordEncoder);

        return Optional.ofNullable(runnerRepository.save(runner));
    }

    @Override
    boolean runnerAlreadyExists(String runnername) {
        return runnerRepository.existsByRunnername(runnername);
    }


}
