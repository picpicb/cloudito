package esipe.fr.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

@ApiModel(description = "UserLocation")
@Entity
public class CustomerLocation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOCATION_ID")
    private Location location;
    private Date lastUpdate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
