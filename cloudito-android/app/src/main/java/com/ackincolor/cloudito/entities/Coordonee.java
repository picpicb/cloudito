package com.ackincolor.cloudito.entities;

public class Coordonee {
    private int x;
    private int y;

    public Coordonee(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object c){
        return((this.getX()==((Coordonee)c).getX())&&(this.getY()==((Coordonee)c).getY()));
    }
}
