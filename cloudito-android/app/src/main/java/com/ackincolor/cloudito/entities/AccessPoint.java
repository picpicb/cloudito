package com.ackincolor.cloudito.entities;

public class AccessPoint {

    private String macAdress;
    private Location location;

    public AccessPoint(String macAdress, Location location) {
        this.macAdress = macAdress;
        this.location = location;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
