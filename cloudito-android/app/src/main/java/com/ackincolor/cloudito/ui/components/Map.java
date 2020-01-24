package com.ackincolor.cloudito.ui.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ackincolor.cloudito.entities.Coordinate;
import com.ackincolor.cloudito.entities.Node;

import java.util.ArrayList;

public class Map extends View {
    private ArrayList<Node> parcours;
    private com.ackincolor.cloudito.entities.Map map;
    private Paint p;
    public Map(Context context, AttributeSet attrs){
        super(context,attrs);
        this.p = new Paint();
        this.p.setColor(Color.CYAN);
    }
    protected void onDraw(Canvas canvas){

        canvas.drawRect(new Rect(0,0,this.getWidth(),this.getHeight()),this.p);
    }
    public void setMap(com.ackincolor.cloudito.entities.Map map){
        Log.d("DEBUG","setting map into view");
        //recuperation du min Y /X et max Y/X
        double minX=1000,minY=1000,maxX=0,maxY=0,tx=0,ty=0;
        for(ArrayList<Coordinate> l : map.liste){
            for(Coordinate c : l){
                tx = c.getX();
                ty = c.getY();
                if(tx>maxX)
                    maxX=ty;
                if(tx<minX)
                    minX = tx;
                if(ty>maxY)
                    maxY=ty;
                if(ty<minY)
                    minY=ty;
            }
        }
        this.map = map;
    }
}
