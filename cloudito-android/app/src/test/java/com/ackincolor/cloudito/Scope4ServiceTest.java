package com.ackincolor.cloudito;

import com.ackincolor.cloudito.entities.Coordinate;
import com.ackincolor.cloudito.services.Scope4Service;
import com.google.android.gms.common.internal.Asserts;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Scope4ServiceTest {
    //renvoie vrai si entre 2 coordonnées la distance euclidienne est bien la bonne
    @Test
    public void distanceEuclidienneTest() {
        Scope4Service s4s = new Scope4Service(
                new Coordinate(1,1),
                new Coordinate(1,2),
                new Coordinate(1,3),
                new ArrayList<Coordinate>()
        );

        ArrayList<Coordinate> pathPositions = s4s.getPathPositions();
        pathPositions.add(new Coordinate(1,3));
        pathPositions.add(new Coordinate(7,4));
        s4s.setPathPositions(pathPositions);

        Assert.assertTrue(6.324555320336759==s4s.getDistanceEuclidienne());

    }

    // renvoie vrai si userPos=destPos
    @Test
    public void isAtDestinationNodeTest() {
        Scope4Service s4s = new Scope4Service(
                new Coordinate(1,1),
                new Coordinate(7,4),
                null,
                new ArrayList<Coordinate>()
        );

        ArrayList<Coordinate> pathPositions = s4s.getPathPositions();
        pathPositions.add(new Coordinate(7,4));
        s4s.setPathPositions(pathPositions);

        Assert.assertTrue(s4s.isAtDestinationNode());
    }

    //getAnalysis renvoie
    // Angle <30 : "Continuez"
    // Angle < 90 : "Rournez vous vers la droite|gauche"
    // Angle < 135 : "Retournez vous vers la droite|gauche"
    // Superieur : "Retournez vous completement"
    @Test
    public void getAnalysisTest(){
        Scope4Service s4s = new Scope4Service(
                new Coordinate(1,1),
                new Coordinate(2,2),
                new Coordinate(1,2),
                null
        );
        Assert.assertTrue("Tournez vous vers la gauche"==s4s.getAnalysis());

        s4s.setUserPositionNow(new Coordinate(0,2));
        Assert.assertTrue("Tournez vous vers la droite"==s4s.getAnalysis());

        s4s.setUserPositionNow(new Coordinate(-1,0));
        Assert.assertTrue("Retournez vous vers la droite"==s4s.getAnalysis());

        s4s.setUserPositionNow(new Coordinate(3,0));
        Assert.assertTrue("Retournez vous vers la gauche"==s4s.getAnalysis());

        s4s.setUserPositionNow(new Coordinate(0,-3));
        Assert.assertTrue("Retournez vous completement"==s4s.getAnalysis());


    }
}
