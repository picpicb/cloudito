package esipe.fr.model;

import io.swagger.annotations.ApiModel;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.ArrayList;

@ApiModel(description = "esipe.fr.model.Store")
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOCATION_ID")
    private Location location;
    private ArrayList<Point> storeArea;
    private String name;
}
