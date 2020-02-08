package com.ackincolor.cloudito.entities;

public class AccessPoint {

    private String mac;
    private Location location;

    public AccessPoint(String macAdress, Location location) {
        this.mac = macAdress;
        this.location = location;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
