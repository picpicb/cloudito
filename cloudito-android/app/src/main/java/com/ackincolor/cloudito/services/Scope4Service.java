package com.ackincolor.cloudito.services;

import com.ackincolor.cloudito.entities.Coordinate;
import com.ackincolor.cloudito.entities.Vector;

import java.util.ArrayList;

public class Scope4Service {

    private Coordinate userPositionBefore;
    private Coordinate userPositionNow;
    private Coordinate userBestPosition;
    private ArrayList<Coordinate> pathPositions;


    public Scope4Service(Coordinate userPositionBefore, Coordinate userPositionNow, Coordinate userBestPosition, ArrayList<Coordinate> pathPositions){
        this.userPositionBefore=userPositionBefore;
        this.userPositionNow=userPositionNow;
        this.userBestPosition=userBestPosition;
        this.pathPositions=pathPositions;

    }
    public ArrayList<Coordinate> getPathPositions() {
        return pathPositions;
    }

    public void setPathPositions(ArrayList<Coordinate> pathPositions) {
        this.pathPositions = pathPositions;
    }

    public void setUserPositionBefore(Coordinate userPositionBefore) {
        this.userPositionBefore = userPositionBefore;
    }

    public void setUserPositionNow(Coordinate userPositionNow) {
        this.userPositionNow = userPositionNow;
    }

    public void setUserBestPosition(Coordinate userBestPosition) {
        this.userBestPosition = userBestPosition;
    }

    public Coordinate getUserPositionBefore() {
        return userPositionBefore;
    }

    public Coordinate getUserPositionNow() {
        return userPositionNow;
    }

    public Coordinate getUserBestPosition() {
        return userBestPosition;
    }

    public String getAnalysis(){
        Vector bestVector = new Vector(userPositionBefore,userBestPosition);
        Vector actualVector = new Vector(userPositionBefore,userPositionNow);

        double degree = bestVector.getDegree(actualVector);

        if(degree<30){
            return "Continuez";
        }
        else{
            Vector diff = new Vector(userPositionNow,userBestPosition);
            if(degree<90){
                if(diff.getX()>0){
                    return "Tournez vous vers la droite";
                }
                else{
                    return "Tournez vous vers la gauche";
                }
            }
            else{
                if(degree<135){
                    if(diff.getX()>0){
                        return "Retournez vous vers la droite";
                    }
                    else{
                        return "Retournez vous vers la gauche";
                    }
                }
                else{
                    return "Retournez vous completement";
                }
            }
        }
    }

    public double getDistanceEuclidienne(){
        return Math.sqrt(
                Math.pow(this.pathPositions.get(this.pathPositions.size()-1).getX()-userPositionNow.getX(),2)+
                        Math.pow(this.pathPositions.get(this.pathPositions.size()-1).getY()-userPositionNow.getY(),2)
        );

    }

    public boolean isAtDestinationNode(){
        return(this.pathPositions.get(this.pathPositions.size()-1).equals(this.userPositionNow));
    }


}
