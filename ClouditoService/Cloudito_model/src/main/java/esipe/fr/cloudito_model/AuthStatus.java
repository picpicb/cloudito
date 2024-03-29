package esipe.fr.cloudito_model;

import io.swagger.annotations.ApiModel;

import java.util.UUID;

@ApiModel(description = "esipe.fr.model.AuthStatus")
public class AuthStatus {
    private int stateAuthent;
    private Long usrId;
    private UUID uuid;

    public int getStateAuthent() {
        return stateAuthent;
    }

    public void setStateAuthent(int stateAuthent) {
        this.stateAuthent = stateAuthent;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public AuthStatus(int stateAuthent, Long usrId, UUID uuid) {
        this.stateAuthent = stateAuthent;
        this.usrId = usrId;
        this.uuid = uuid;
    }
}
