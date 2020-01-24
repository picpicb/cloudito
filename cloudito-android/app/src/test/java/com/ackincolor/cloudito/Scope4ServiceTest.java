package com.ackincolor.cloudito;

import com.ackincolor.cloudito.entities.Coordonee;
import com.ackincolor.cloudito.services.Scope4Service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Scope4ServiceTest {
    @Test
    public void isGeolocalisationUpdated() {

    }
    @Test
    public void distanceEuclidienne() {

    }
    //renvoie vrai si entre 2 coordonnées la distance euclidienne est bien la bonne
    @Test
    public void isAtDestinationNode() {


    }
    // renvoie vrai si userPos=destPos
    @Test
    public void isNextTill() {
        Scope4Service srvc4 = new Scope4Service(new Coordonee(1,1),new Coordonee(1,1));
    }
    // renvoie vrai si la case indiquée est bien la meilleur pour arrivé à la destPos

}
