package com.ackincolor.cloudito.entities;

public class CourseNode extends Node {
    public CourseNode(Long id,double x, double y)
    {
        super(id, new Location(id,0,x,y));
    }
    public void setX(double x){
        if(this.getLocation()==null)
            this.setLocation(new Location(0,0,x,0));
        else
            this.getLocation().setX(x);
    }
    public void setY(double y){
        if(this.getLocation()==null)
            this.setLocation(new Location(0,0,0,y));
        else
            this.getLocation().setY(y);
    }

    public String toString(){
        return "CourseNode : id:"+this.getLocation();
    }
}
