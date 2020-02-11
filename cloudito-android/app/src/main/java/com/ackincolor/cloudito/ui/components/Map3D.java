package com.ackincolor.cloudito.ui.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.ackincolor.cloudito.R;
import com.ackincolor.cloudito.entities.CourseNode;
import com.ackincolor.cloudito.entities.Location;

import java.util.ArrayList;

public class Map3D extends View implements MapInterface{
    private com.ackincolor.cloudito.entities.Map map;
    public ArrayList<CourseNode> courseNode;
    public ArrayList<Path> magasins;
    public ArrayList<Path> courses;
    public Location center;
    private Paint p, p2, p3,p4;
    private boolean touched,touched2,touched3;
    private double distance;
    private float northAngle,realRotation,lastRotation;
    private float last3y;
    private float mScaleFactor,zoomRatio;
    private GestureDetector mGestureListener;

    private Camera camera;
    private Matrix cameraMatrix;
    private float Xrotation=30f;
    private float offsetX,offsetY;
    private Bitmap boussole;

    public Map3D(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.camera = new Camera();
        this.cameraMatrix = new Matrix();

        this.p = new Paint();
        this.p.setColor(Color.CYAN);
        this.p2 = new Paint();
        this.p2.setColor(Color.argb(255,140,140,140));
        this.p3 = new Paint();
        this.p3.setColor(Color.GREEN);
        this.p3.setStyle(Paint.Style.STROKE);
        this.p3.setStrokeWidth(3);
        this.p4 = new Paint();
        this.p4.setColor(Color.argb(255,255, 255, 255));

        this.touched = this.touched2 = this.touched3 = false;
        this.offsetX = this.offsetY = 200;
        this.mGestureListener = new GestureDetector(context, new GestureListener());
        this.center = new Location(0,0,200,200);
        this.zoomRatio = 1f;
        this.mScaleFactor = 1f;

        this.boussole = getBitmap(R.drawable.boussolearrow);
        if(this.boussole == null) {
            //Log.d("DEBUG MAP","image non trouvé");
        }
    }

    public void onDraw(Canvas canvas){
        //canvas.save();
        //this.camera.getMatrix(this.cameraMatrix);
        canvas.save();
        canvas.translate(this.getWidth()/2,this.getHeight()/2);
        if (this.center != null) {
            this.camera.save();
            this.camera.rotateX(this.Xrotation);
            this.camera.translate(-offsetX,offsetY,0);
            this.camera.applyToCanvas(canvas);
            this.camera.restore();

            /*this.cameraMatrix.setRotate(-this.northAngle,0,0);
            this.cameraMatrix.preScale(this.zoomRatio,this.zoomRatio);
            this.cameraMatrix.preTranslate((float) -this.center.getX(), (float) -this.center.getY());
            this.cameraMatrix.postTranslate((float) this.center.getX(), (float) this.center.getY());*/
            this.cameraMatrix = new Matrix();
            this.cameraMatrix.setScale(this.zoomRatio,this.zoomRatio);
            this.cameraMatrix.preRotate(-northAngle,(float)this.center.getX(),(float)this.center.getY());
        }
        canvas.concat(this.cameraMatrix);
        if(this.magasins!=null) {
            for(Path p : this.magasins)
                canvas.drawPath(p,p2);
        }
        if(this.courses!=null) {
            for(Path p : this.courses)
                canvas.drawPath(p,p3);
        }
        //canvas.restore();
        canvas.restore();
        if(boussole!=null) {
            Matrix matrix = new Matrix();
            matrix.setRotate(this.northAngle, boussole.getHeight() / 2, boussole.getWidth() / 2);
            canvas.drawBitmap(boussole, matrix, null);
        }

    }

    public void setMap(com.ackincolor.cloudito.entities.Map map){
        this.camera.rotateX(this.Xrotation);
        this.map = map;
        if(this.map.getListe()!=null){
            for(ArrayList<Location> listeLocation : this.map.getListe()){
                for(Location location : listeLocation){
                    location.setX((location.getX()-900));
                    location.setY((location.getY()-700));
                }
            }
        }
        invalidate();
        settingPath();
    }
    public void setCourse(ArrayList<CourseNode> liste){
        this.courseNode = liste;
        for(CourseNode cn : this.courseNode){
            cn.setX(cn.getLocation().getX()-900);
            cn.setY(cn.getLocation().getY()-700);
        }
        this.center.setX(this.courseNode.get(liste.size()-1).getLocation().getX());
        this.center.setY(this.courseNode.get(liste.size()-1).getLocation().getY());
        //System.out.println(this.courseNode);
        settingPath();
        invalidate();
    }
    private void settingPath(){
        this.camera = new Camera();
        //this.camera.rotateX(this.Xrotation);
        //this.camera.setLocation(100,100,-8);
        //Log.d("DEBUG CAMERA :", " Potition camera :"+ this.camera.getLocationX()+";"+
        //        this.camera.getLocationY()+";"+this.camera.getLocationZ());
        this.magasins = new ArrayList<>();
        this.courses = new ArrayList<>();
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
                            path.moveTo(((float)c.getX()),((float)c.getY()));
                            first = false;
                        }else {
                            path.lineTo(((float)c.getX()),((float)c.getY()));
                            //Log.d("DEBUG MAP", "line to : "+(float)c.getX()/10+","+(float)c.getY()/10);
                        }
                    }
                    path.close();
                    this.magasins.add(path);
                    //canvas.drawPath(path, p2);
                }
            }if(courseNode!=null){
                boolean first = true;
                Path path = new Path();
                for(CourseNode c : courseNode){
                    if(first){
                        first = false;
                        path.moveTo(((float)c.getLocation().getX()), ((float)c.getLocation().getY()));
                    }else{
                        path.lineTo(((float)c.getLocation().getX()), ((float)c.getLocation().getY()));
                    }
                }
                //path.close();
                this.courses.add(path);
                //canvas.drawPath(path, p3);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        mGestureListener.onTouchEvent(event);
        analyseEvent(event);
        //camera.getMatrix(this.cameraMatrix);
        invalidate();
        return true;
    }
    private float analyseEvent(MotionEvent event) {
        //Log.d("DEBUG MAP"," angle nord :"+this.northAngle);
        if(event.getPointerCount() == 2) {
            double delta_x = (event.getX(0) - event.getX(1));
            double delta_y = (event.getY(0) - event.getY(1));
            double radians = Math.atan2(delta_y, delta_x);
            //Log.d("Rotation ~~~~~~~~~~~~~~~~~", delta_x + " ## " + delta_y + " ## " + radians + " ## "
            //        + Math.toDegrees(radians));
            if(touched) {
                this.mScaleFactor = (float) -((this.distance - (Math.sqrt((Math.pow(delta_x, 2)+Math.pow(delta_y, 2)))))/this.getWidth()) +1;
                this.zoomRatio*=this.mScaleFactor;
                Log.d("DEBUG MAP 3D","mscale = "+this.mScaleFactor+" ; zoomRatio = "+this.zoomRatio);
                this.distance = Math.sqrt((Math.pow(delta_x, 2)+Math.pow(delta_y, 2)));
            }else{
                this.distance = Math.sqrt((Math.pow(delta_x, 2)+Math.pow(delta_y, 2)));
                //this.mScaleFactor = 1f;
                touched = true;
            }
            float deg = (float) Math.toDegrees(radians);
            if(deg!=0 && touched2){
                this.realRotation = this.lastRotation -deg;
                this.northAngle += this.realRotation;
                this.lastRotation  = deg;
                //this.camera.rotateZ(this.realRotation);
                //Log.d("Rotation ~~~~~~~true~~~~~~~", "angle : "+this.realRotation);
            }else {
                this.realRotation = 0.0f;
                this.lastRotation  = deg;
                //Log.d("Rotation ~~~~~~~false~~~~~~~", "angle : "+this.lastRotation);
                touched2 = true;
            }
            //invalidate();
            return (float) Math.toDegrees(radians);
        }else if(event.getPointerCount()>=3){
            if(touched3){
                this.realRotation = 0.0f;
                //this.mScaleFactor = 1f;
                touched = false;
                touched2 = false;
                float y = event.getY(0);
                //relative to height
                float rel = y-this.last3y;
                rel = (rel/this.getHeight())*-5;
                this.Xrotation+=rel;
                if(this.Xrotation>=0 && this.Xrotation<=30)
                    this.Xrotation+=rel;
                else
                    this.Xrotation-=rel;
                //invalidate();
                return 0;
            }else{
                this.touched3 = true;
                this.last3y = event.getY(0);
                return 0;
            }
        }
        this.realRotation = 0.0f;
        //this.mScaleFactor = 1f;
        touched = false;
        touched2 = false;
        touched3 = false;
        return 0;
    }

    private class GestureListener
            extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            offsetX+=distanceX;
            offsetY+=distanceY;
            //camera.translate(-distanceX,distanceY,0);
            //Log.d("DEBUG MAP","distance x :"+offsetX+" ; distance Y : "+offsetY);
            //invalidate();
            return true;
        }
    }
    private Bitmap getBitmap(int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
