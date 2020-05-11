package com.ackincolor.cloudito.entities;

import java.io.Serializable;
import java.util.UUID;

public class AuthStatus implements Serializable {
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
