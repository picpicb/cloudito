package com.ackincolor.cloudito.entities;

import java.lang.Math;

public class Vector {

    private double x;
    private double y;

    public Vector(Coordinate a,Coordinate b){
        this.x = b.getX()-a.getX();
        this.y = b.getY()-a.getY();
    }

    public Vector(double x,double y){
        this.x = x;
        this.y = y;
    }


    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getNorm(){
        return Math.sqrt((Math.pow(this.x,2)+Math.pow(this.y,2)));
    }

    public double getScalaire(Vector v2){
        return this.x*v2.getX()+this.y*v2.getY();
    }

    public double getDegree(Vector v2){
        System.out.println(Math.toDegrees(Math.acos(this.getScalaire(v2)/(this.getNorm()*v2.getNorm()))));

        return Math.toDegrees(Math.acos(this.getScalaire(v2)/(this.getNorm()*v2.getNorm())));
    }

    public boolean equals(Object o){
        Vector v = (Vector)o;
        return(this.x==v.getX()&&this.y==v.getY());
    }



}
