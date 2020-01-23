package com.ackincolor.cloudito.entities;

public class PoI {
    private String name;
    private String description;
    private Coordinate emplacement;

    public PoI(String name, String description, Coordinate emplacement) {
        this.name = name;
        this.description = description;
        this.emplacement = emplacement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Coordinate getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Coordinate emplacement) {
        this.emplacement = emplacement;
    }

    @Override
    public String toString() {
        return "["+this.name+","+this.description+","+this.emplacement+"]";
    }
}
