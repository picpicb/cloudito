package com.ackincolor.cloudito.entities;

public class Coordinate {

    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String toString(){
        return "X"+this.x+"Y:"+this.y+"";
    }

    public boolean equals(Coordinate c){
        return (c.getX()==this.x&&c.getY()==this.y);
    }
}
