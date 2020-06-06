package fr.tcd.server.runner;

import fr.tcd.server.runner.exception.RunnerAlreadyExistsException;
import fr.tcd.server.runner.exception.RunnerNotCreatedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;
    private final PasswordEncoder passwordEncoder;

    public RunnerService(RunnerRepository runnerRepository, PasswordEncoder passwordEncoder) {
        this.runnerRepository = runnerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RunnerModel registerNewRunner(RunnerDTO runnerDTO) {
        if (runnerAlreadyExists(runnerDTO.getRunnername())) {
            throw new RunnerAlreadyExistsException();
        }

        RunnerModel runner = runnerDTO.toRunnerModel(passwordEncoder);

        return Optional.of(runnerRepository.save(runner)).orElseThrow(RunnerNotCreatedException::new);
    }

    private boolean runnerAlreadyExists(String runnername) {
        return runnerRepository.existsByRunnername(runnername);
    }
}
