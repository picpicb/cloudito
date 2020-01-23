package esipe.fr.geolocation.entities;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@ApiModel(description = "InfoTerminal")
@Entity
public class InfoTerminal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOCATION_ID")
    private Location location;
}
