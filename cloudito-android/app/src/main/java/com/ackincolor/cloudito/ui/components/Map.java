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

import com.ackincolor.cloudito.entities.Course;
import com.ackincolor.cloudito.entities.CourseNode;
import com.ackincolor.cloudito.entities.Location;
import com.ackincolor.cloudito.entities.Node;

import java.util.ArrayList;

public class Map extends View {
    private com.ackincolor.cloudito.entities.Map map;
    private Paint p, p2, p3;
    private int offsetX,offsetY;
    private float zoomRatio;
    private ScaleGestureDetector mScaleDetector;
    private GestureDetector mGestureListener;
    private float mScaleFactor = 1.f;
    private float lastRotation = 0.0f;
    private float realRotation = 0.0f;
    private float effectivRotation = 0.0f;
    private Location center;

    public Map(Context context, AttributeSet attrs){
        super(context,attrs);
        this.p = new Paint();
        this.p.setColor(Color.CYAN);
        this.p2 = new Paint();
        this.p2.setColor(Color.BLACK);
        this.p2.setStyle(Paint.Style.STROKE);
        this.p2.setStrokeWidth(1);
        this.p3 = new Paint();
        this.p3.setColor(Color.GREEN);

        //demarage de la recuperation de la carte
        this.offsetX = -300;
        this.offsetY = -200;
        this.zoomRatio = 1.0f;
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        mGestureListener = new GestureDetector(context, new GestureListener());
        this.center = new Location(0,0,1000,800);

    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);
        mGestureListener.onTouchEvent(ev);
        rotation(ev);
        //Log.d("DEBUG MAP","angle rotation : "+ this.realRotation);

        return true;
    }
    public void setCenter(Location l){
        this.center = l;
    }
    public void setAngle(float deg){
        this.effectivRotation = deg;
    }
    private float rotation(MotionEvent event) {
        if(event.getPointerCount() >= 2) {
            double delta_x = (event.getX(0) - event.getX(1));
            double delta_y = (event.getY(0) - event.getY(1));
            double radians = Math.atan2(delta_y, delta_x);
            //Log.d("Rotation ~~~~~~~~~~~~~~~~~", delta_x + " ## " + delta_y + " ## " + radians + " ## "
            //        + Math.toDegrees(radians));
            float deg = (float) Math.toDegrees(radians);
            if(deg!=0){
                this.realRotation = this.lastRotation -deg;
                this.effectivRotation += this.realRotation;
                this.lastRotation  = deg;
                Log.d("Rotation ~~~~~~~~~~~~~~~~~", "angle : "+this.effectivRotation);
                invalidate();
            }else {
                this.realRotation = 0.0f;
            }
            return (float) Math.toDegrees(radians);
        }
        return 0;
    }
    private void calculNewCoord(){
        //calcul des nouvelle coordonée
        if(this.map!=null)
        {
            if(this.map.getListe()!=null){
                for(ArrayList<Location> listeLocation : this.map.getListe()){
                    for(Location location : listeLocation){
                        Location ltemp = rotateLocation(location,this.center,this.realRotation);
                        location.setX(ltemp.getX());
                        location.setY(ltemp.getY());
                    }
                }
            }
            if(this.map.getCourse()!=null){
                for(CourseNode cn : this.map.getCourse()){
                    Location ltemp = rotateLocation(cn.getLocation(),this.center,this.realRotation);
                    cn.setLocation(ltemp);
                }
            }
        }
    }

    private Location rotateLocation(Location a,Location o,float angle){
        angle*=Math.PI/180;
        double tempX = a.getX()-o.getX();
        double tempY = a.getY()-o.getY();
        return new Location(0,0,tempX * Math.cos (angle) + tempY * Math.sin (angle) + o.getX(),
                - tempX * Math.sin (angle) + tempY * Math.cos (angle) + o.getY());
    }


    protected void onDraw(Canvas canvas){
        calculNewCoord();
        canvas.save();
        //canvas.scale(mScaleFactor, mScaleFactor);
        //canvas.drawRect(new Rect(0,0,this.getWidth(),this.getHeight()),this.p);
        if (map != null) {
            //Log.d("DEBUG MAP", "map is'nt nul");
            if(map.getListe()!=null){
                //Log.d("DEBUG MAP", "liste is'nt nul");
                for(ArrayList<Location> l : map.getListe()){
                    Path path = new Path();
                    boolean first = true;
                    for(Location c : l){
                        //Location c2 = rotateLocation(c,this.center,this.effectivRotation);
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
            }if(map.getCourse()!=null){
                boolean first = true;
                Path path = new Path();
                for(CourseNode c : map.getCourse()){
                    if(first){
                        first = false;
                        path.moveTo(((float)c.getLocation().getX()+offsetX)*zoomRatio, ((float)c.getLocation().getY()+offsetY)*zoomRatio);
                    }else{
                        path.lineTo(((float)c.getLocation().getX()+offsetX)*zoomRatio, ((float)c.getLocation().getY()+offsetY)*zoomRatio);
                    }
                }
                path.close();
                canvas.drawPath(path, p3);
            }
        }
        canvas.restore();

    }

    public void setCourse(ArrayList<CourseNode> liste){
        this.map.setCourse(liste);
        invalidate();
    }

    public void setMap(com.ackincolor.cloudito.entities.Map map){
        Log.d("DEBUG","setting map into view");
        //recuperation du min Y /X et max Y/X
        double minX=1000,minY=1000,maxX=0,maxY=0,tx=0,ty=0;
        for(ArrayList<Location> l : map.getListe()){
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
            //Log.d("Debug Map", "Scrool on map : x:"+distanceX+", y: "+distanceY);
            invalidate();
            return true;
        }
    }

}
