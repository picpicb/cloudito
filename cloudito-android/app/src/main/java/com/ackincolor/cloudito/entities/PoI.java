package com.ackincolor.cloudito.entities;

public class PoI {
    private String name;
    private String description;
    private Location emplacement;

    public PoI(String name, String description, Location emplacement) {
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

    public Location getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Location emplacement) {
        this.emplacement = emplacement;
    }

    @Override
    public String toString() {
        return "["+this.name+","+this.description+","+this.emplacement+"]";
    }
}
