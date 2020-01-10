package esipe.fr.geolocation.entities;


import org.springframework.data.geo.Point;

import java.util.ArrayList;

public class Store {
    private Location location;
    private ArrayList<Point> storeArea;
    private String name;
}
