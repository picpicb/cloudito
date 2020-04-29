package esipe.fr.cloudito_model;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@ApiModel(description = "esipe.fr.cloudito_model.MapView")
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
