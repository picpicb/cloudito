package esipe.fr.geolocation.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserPosition {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOCATION_ID")
    private Location location;
    private Date lastUpdate;

}
