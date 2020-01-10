package com.ackincolor.cloudito.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private ArrayList<Node> liste;

    public Course(UUID id, ArrayList<Node> liste) {
        this.id = id;
        this.liste = liste;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ArrayList<Node> getListe() {
        return liste;
    }

    public void setListe(ArrayList<Node> liste) {
        this.liste = liste;
    }

    public String toString(){
        String str = "Course : ";
        for(Node n : this.liste){
            str+=" n:"+n.toString();
        }
        return str;
    }
}
