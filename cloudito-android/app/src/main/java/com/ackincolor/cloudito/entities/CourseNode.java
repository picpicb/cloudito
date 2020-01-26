package com.ackincolor.cloudito.entities;

public class CourseNode extends Node {
    public CourseNode(Long id,double x, double y)
    {
        super(id, new Location(id,0,x,y));
    }
}
