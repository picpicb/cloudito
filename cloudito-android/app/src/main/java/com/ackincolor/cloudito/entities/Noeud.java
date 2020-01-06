package com.ackincolor.cloudito.entities;

import java.util.UUID;

public class Noeud {
    private UUID id;
    private int order;
    private String type;
    private PoI poi;

    public Noeud(UUID id, int order, String type, PoI poi) {
        this.id = id;
        this.order = order;
        this.type = type;
        this.poi = poi;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PoI getPoi() {
        return poi;
    }

    public void setPoi(PoI poi) {
        this.poi = poi;
    }

    public String toString(){
        return "["+this.id.toString()+","+this.type+","+this.poi+"]";
    }
}
