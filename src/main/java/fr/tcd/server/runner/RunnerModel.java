package fr.tcd.server.runner;

import fr.tcd.server.runner.status.RunnerStatus;
import fr.tcd.server.user.UserDomain;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="Runners")
@Accessors(chain = true)
@NoArgsConstructor
public class RunnerModel implements UserDomain {
    @Id
    private String id;

    @Indexed(unique = true)
    private String runnername;

    private String key;

    private List<String> roles;

    private RunnerStatus status;

    private String ip;

    private String port;

    public RunnerModel(String id, String runnername, String key, List<String> roles, RunnerStatus status, String ip, String port) {
        this.id = id;
        this.runnername = runnername;
        this.key = key;
        this.roles = roles;
        this.status = status;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return runnername;
    }

    @Override
    public String getPassword() {
        return key;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    public void status(RunnerStatus status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }
}
