package com.ackincolor.cloudito.ui.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

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
        this.map = map;
    }
}
