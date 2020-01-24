package com.ackincolor.cloudito.entities;

import java.util.ArrayList;

public class Map {
    public ArrayList<ArrayList<Location>> liste;
    public Map(ArrayList<ArrayList<Location>> liste) {
        this.liste = liste;
    }

    public ArrayList<ArrayList<Location>> getListe() {
        return liste;
    }

    public void setListe(ArrayList<ArrayList<Location>> liste) {
        this.liste = liste;
    }
}
