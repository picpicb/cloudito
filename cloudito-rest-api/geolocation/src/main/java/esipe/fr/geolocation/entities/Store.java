package esipe.fr.geolocation.entities;


import org.springframework.data.geo.Point;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Location location;
    private ArrayList<Point> storeArea;
    private String name;
}
