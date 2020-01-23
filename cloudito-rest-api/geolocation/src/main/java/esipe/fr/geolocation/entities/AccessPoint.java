package esipe.fr.geolocation.entities;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@ApiModel(description = "Access Point")
@Entity
public class AccessPoint {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOCATION_ID")
    private Location location;

    private String ssid;
    private String mac;
    private double signalStrength;

}
