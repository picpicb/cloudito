package com.ackincolor.cloudito.entities;

import java.util.ArrayList;

public class Map {
    public ArrayList<ArrayList<Coordinate>> liste;
    public Map(ArrayList<ArrayList<Coordinate>> liste) {
        this.liste = liste;
    }

    public ArrayList<ArrayList<Coordinate>> getListe() {
        return liste;
    }

    public void setListe(ArrayList<ArrayList<Coordinate>> liste) {
        this.liste = liste;
    }
}
