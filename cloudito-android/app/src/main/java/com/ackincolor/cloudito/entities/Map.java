package com.ackincolor.cloudito.entities;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Location>> liste;
    private ArrayList<CourseNode> course;
    public Map(ArrayList<ArrayList<Location>> liste) {
        this.liste = liste;
    }

    public ArrayList<ArrayList<Location>> getListe() {
        return liste;
    }

    public void setListe(ArrayList<ArrayList<Location>> liste) {
        this.liste = liste;
    }

    public void setCourse(ArrayList<CourseNode> course) {
        this.course = course;
    }

    public ArrayList<CourseNode> getCourse() {
        return course;
    }
}
