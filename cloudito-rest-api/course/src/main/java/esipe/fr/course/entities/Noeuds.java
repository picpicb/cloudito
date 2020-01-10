package esipe.fr.course.entities;

import java.util.UUID;

public class Noeuds {
    private UUID id;
    private Integer order;
    private String type;
    private PoI poi;

    public Noeuds(UUID id,Integer order, String type, PoI poi) {
        this.id = id;
        this.order = order;
        this.type = type;
        this.poi = poi;
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public PoI getPoi(){
        return this.poi;
    }

    public Integer getOrder() {
        return order;
    }
}
