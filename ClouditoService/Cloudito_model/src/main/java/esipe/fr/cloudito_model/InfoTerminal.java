package esipe.fr.cloudito_model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@ApiModel(description = "esipe.fr.model.InfoTerminal")
@Entity
public class InfoTerminal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOCATION_ID")
    private Location location;
}
