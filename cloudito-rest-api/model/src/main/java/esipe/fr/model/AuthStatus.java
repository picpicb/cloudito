package esipe.fr.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@ApiModel(description = "esipe.fr.model.AuthStatus")
public class AuthStatus {
    private int stateAuthent;
    private Date time;
    private UUID uuid;

    public int getStateAuthent() {
        return stateAuthent;
    }

    public void setStateAuthent(int stateAuthent) {
        this.stateAuthent = stateAuthent;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public AuthStatus(int stateAuthent, Date time, UUID uuid) {
        this.stateAuthent = stateAuthent;
        this.time = time;
        this.uuid = uuid;
    }
}
