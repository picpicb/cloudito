package com.ackincolor.cloudito;

import com.ackincolor.cloudito.entities.Coordinate;
import com.ackincolor.cloudito.entities.Vector;

import org.junit.Assert;
import org.junit.Test;
import java.lang.Math;


public class VectorUnitTest {


    @Test
    public void vectorConstrutorTest(){
        Vector v = new Vector(new Coordinate(1,1),new Coordinate(1,2));
        Vector vTrue = new Vector(0,1);
        Assert.assertTrue(v.equals(vTrue));

        v = new Vector(new Coordinate(1,1),new Coordinate(1,2));
        Vector vFalse = new Vector(1,1);
        Assert.assertFalse(v.equals(vFalse));

    }

    @Test
    public void getNormTest() {

        Vector v = new Vector(1,0);
        Assert.assertTrue(v.getNorm()==Math.sqrt(1));


        v = new Vector(2,2);
        Assert.assertTrue(v.getNorm()==Math.sqrt(8));

        v = new Vector(3,0);
        Assert.assertTrue(v.getNorm()==3);

    }

    @Test
    public void getScalaireTest() {
        Vector v1 = new Vector(1,0);
        Vector v2 = new Vector(1,-2);
        Assert.assertTrue(v1.getScalaire(v2)==1);
        v1 = new Vector(1,0);
        v2 = new Vector(1,1);
        Assert.assertTrue(v1.getScalaire(v2)==1);

    }

    @Test
    public void getDegreeTest(){
        Vector v1 = new Vector(1,0);
        Vector v2 = new Vector(0,1);
        Assert.assertTrue(v1.getDegree(v2)==90);

        v1 = new Vector(1,0);
        v2 = new Vector(1,0);
        Assert.assertTrue(v1.getDegree(v2)==0);


        v1 = new Vector(1,0);
        v2 = new Vector(-1,0);
        Assert.assertTrue(v1.getDegree(v2)==180);
    }


}
