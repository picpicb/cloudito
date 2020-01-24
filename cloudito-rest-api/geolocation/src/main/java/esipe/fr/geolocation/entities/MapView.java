package esipe.fr.geolocation.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class MapView {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private double maxHeight;
    private double maxWidth;
    private ArrayList<AccessPoint> apiList;
    private ArrayList<InfoTerminal> infoTerminalList;
    private ArrayList<Store> stores;
}
