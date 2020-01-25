package com.ackincolor.cloudito.ui.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.ackincolor.cloudito.entities.Location;
import com.ackincolor.cloudito.entities.Node;

import java.util.ArrayList;

public class Map extends View {
    private com.ackincolor.cloudito.entities.Map map;
    private Paint p;
    private Paint p2;
    private int offsetX,offsetY;
    private float zoomRatio;
    private ScaleGestureDetector mScaleDetector;
    private GestureDetector mGestureListener;
    private float mScaleFactor = 1.f;

    public Map(Context context, AttributeSet attrs){
        super(context,attrs);
        this.p = new Paint();
        this.p.setColor(Color.CYAN);
        this.p2 = new Paint();
        this.p2.setColor(Color.BLACK);
        this.p2.setStyle(Paint.Style.STROKE);
        this.p2.setStrokeWidth(3);
        //demarage de la recuperation de la carte
        this.offsetX = -300;
        this.offsetY = -200;
        this.zoomRatio = 1.0f;
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        mGestureListener = new GestureDetector(context, new GestureListener());

    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);
        mGestureListener.onTouchEvent(ev);
        return true;
    }


    protected void onDraw(Canvas canvas){
        canvas.save();
        //canvas.scale(mScaleFactor, mScaleFactor);

        canvas.drawRect(new Rect(0,0,this.getWidth(),this.getHeight()),this.p);
        if (map != null) {
            //Log.d("DEBUG MAP", "map is'nt nul");
            if(map.liste!=null){
                //Log.d("DEBUG MAP", "liste is'nt nul");
                for(ArrayList<Location> l : map.liste){
                    Path path = new Path();
                    boolean first = true;
                    for(Location c : l){
                        if(first){
                            path.moveTo(((float)c.getX()+offsetX)*zoomRatio,((float)c.getY()+offsetY)*zoomRatio);
                            first = false;
                        }else {
                            path.lineTo(((float)c.getX()+offsetX)*zoomRatio,((float)c.getY()+offsetY)*zoomRatio);
                            //Log.d("DEBUG MAP", "line to : "+(float)c.getX()/10+","+(float)c.getY()/10);
                        }
                    }
                    path.close();
                    canvas.drawPath(path, p2);
                }
            }
        }
        canvas.restore();

    }
    public void setMap(com.ackincolor.cloudito.entities.Map map){
        Log.d("DEBUG","setting map into view");
        //recuperation du min Y /X et max Y/X
        double minX=1000,minY=1000,maxX=0,maxY=0,tx=0,ty=0;
        for(ArrayList<Location> l : map.liste){
            for(Location c : l){
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
        // dessin de la carte
        this.map = map;
        this.invalidate();
    }
    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
            zoomRatio = mScaleFactor;
            invalidate();
            return true;
        }
    }
    private class GestureListener
            extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            offsetX-=distanceX/zoomRatio;
            offsetY-=distanceY/zoomRatio;
            Log.d("Debug Map", "Scrool on map : x:"+distanceX+", y: "+distanceY);
            invalidate();
            return true;
        }
    }

}
