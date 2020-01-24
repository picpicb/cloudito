package com.ackincolor.cloudito.services;

import com.ackincolor.cloudito.entities.Coordonee;

public class Scope4Service {
    Coordonee[][] map = new Coordonee[4][4];
    Coordonee userPosition;
    Coordonee destinationPosition;


    public Scope4Service(Coordonee userPosition, Coordonee destinationPosition){
        this.userPosition=userPosition;
        this.destinationPosition=destinationPosition;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                map[i][j]= new Coordonee(i,j);
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
