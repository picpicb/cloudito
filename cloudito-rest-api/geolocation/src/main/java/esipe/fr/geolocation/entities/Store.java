package esipe.fr.geolocation.entities;


import io.swagger.annotations.ApiModel;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.ArrayList;

@ApiModel(description = "Store")
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
