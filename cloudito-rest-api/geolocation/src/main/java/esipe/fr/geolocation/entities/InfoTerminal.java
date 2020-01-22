package esipe.fr.geolocation.entities;

import javax.persistence.*;

@Entity
public class InfoTerminal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOCATION_ID")
    private Location location;
}
