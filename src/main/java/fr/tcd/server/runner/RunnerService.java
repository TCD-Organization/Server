package fr.tcd.server.runner;

import fr.tcd.server.runner.dto.RunnerDTO;
import fr.tcd.server.runner.exception.RunnerAlreadyExistsException;
import fr.tcd.server.runner.exception.RunnerNotCreatedException;
import fr.tcd.server.runner.exception.RunnerNotFoundException;
import fr.tcd.server.runner.exception.RunnerNotUpdatedException;
import fr.tcd.server.runner.status.RunnerStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        List<String> roles = new ArrayList<>();
        roles.add("RUNNER");
        String key = passwordEncoder.encode(runnerDTO.getKey());
        RunnerModel runner =  new RunnerModel(null,
                runnerDTO.getRunnername(),
                key,
                roles,
                RunnerStatus.DOWN,
                runnerDTO.getIp(),
                runnerDTO.getPort()
                );

        return Optional.of(runnerRepository.save(runner)).orElseThrow(RunnerNotCreatedException::new);
    }

    public void runnerUp(String runnername) {
        RunnerModel runner = runnerRepository.findByRunnername(runnername).orElseThrow(RunnerNotFoundException::new);
        runner.setStatus(RunnerStatus.UP);
        Optional.ofNullable(runnerRepository.save(runner)).orElseThrow(RunnerNotUpdatedException::new);
    }

    private boolean runnerAlreadyExists(String runnername) {
        return runnerRepository.existsByRunnername(runnername);
    }
}
