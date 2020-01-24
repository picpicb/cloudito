package com.ackincolor.cloudito.entities;

public class AccessPoint {

    private String macAdress;
    private PoI node;

    public AccessPoint(String macAdress, PoI node) {
        this.macAdress = macAdress;
        this.node = node;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public PoI getNode() {
        return node;
    }

    public void setNode(PoI node) {
        this.node = node;
    }

}
