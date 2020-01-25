package com.ackincolor.cloudito.services;

import com.ackincolor.cloudito.entities.Coordinate;

public class Scope4Service {
    Coordinate[][] map = new Coordinate[4][4];
    Coordinate userPosition;
    Coordinate destinationPosition;


    public Scope4Service(Coordinate userPosition, Coordinate destinationPosition){
        this.userPosition=userPosition;
        this.destinationPosition=destinationPosition;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                map[i][j]= new Coordinate(i,j);
            }
        }

    }

    public String printMap(){
        String s="";
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j].equals(userPosition)){
                    s+="(U)";

                }else{
                    if(map[i][j].equals(destinationPosition)){
                        s+="(D)";
                    }
                    else{
                        s+="("+i+","+j+")";
                    }
                }
            }
            s+="\n";
        }
        return s;
    }

    public String toString(){
        String s="";
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j].equals(userPosition)){
                    s+="(U)";

                }else{
                    if(map[i][j].equals(destinationPosition)){
                        s+="(D)";
                    }
                    else{
                        s+="("+i+","+j+")";
                    }
                }
            }
            s+="\n";
        }
        return s;
    }


}
