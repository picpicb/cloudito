package com.ackincolor.cloudito.ui.components;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
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

public class Map extends View implements MapInterface{
    private com.ackincolor.cloudito.entities.Map map;
    public ArrayList<CourseNode> courseNode;
    public ArrayList<Path> magasins;
    public ArrayList<Path> courses;
    private Paint p, p2, p3,p4;
    private float offsetX,offsetY,offsetXtotal,offsetYtotal;
    private float zoomRatio = 1f;
    private ScaleGestureDetector mScaleDetector;
    private GestureDetector mGestureListener;
    public float mScaleFactor = 1.f;
    private float lastRotation = 0.0f;
    private float realRotation = 0.0f;
    public Location center;
    private int zoom = 0;
    private float northAngle = 0.0f;
    private Bitmap boussole,userLocation;
    private double distance;
    private boolean touched = false;
    private boolean touched2 = false;
    private boolean touched3 = false;
    private Camera camera;
    private float last3y;
    private float Xrotation;

    public Map(Context context, AttributeSet attrs){
        super(context,attrs);
        this.p = new Paint();
        this.p.setColor(Color.CYAN);
        this.p2 = new Paint();
        this.p2.setColor(Color.argb(255,140,140,140));
        this.p3 = new Paint();
        this.p3.setColor(Color.GREEN);
        this.p3.setStyle(Paint.Style.STROKE);
        this.p3.setStrokeWidth(10);
        this.p4 = new Paint();
        this.p4.setColor(Color.argb(255,255, 255, 255));
        //this.p4.setColor(Color.BLUE);

        //demarage de la recuperation de la carte
        this.offsetX = 0;
        this.offsetY = 0;
        this.zoomRatio = 1.0f;
        if(attrs!=null){
            mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
            mGestureListener = new GestureDetector(context, new GestureListener());
        }
        this.center = new Location(0,0,200,300);
        this.magasins = new ArrayList<>();
        this.courses = new ArrayList<>();

        this.boussole = getBitmap(R.drawable.boussolearrow);

        if(this.boussole == null) {
            //Log.d("DEBUG MAP","image non trouvé");
        }
        this.camera = new Camera();
        this.Xrotation = 30;
        //this.camera.rotateY(-20);

    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);
        mGestureListener.onTouchEvent(ev);
        rotation(ev);
        calculNewCoord2();
        calculZoom();
        invalidate();
        //Log.d("DEBUG MAP","angle rotation : "+ this.realRotation);

        return true;
    }
    //fonction qui permet de definire la position de l'utilisateur'
    public void setCenter(Location l){
        this.center = l;
        invalidate();
    }
    //fonction qui permet de faire touerner la carte
    public void setAngle(float deg){
        this.realRotation = deg;
        calculNewCoord2();
        invalidate();
    }
    public float getAngle(){
        return this.realRotation;
    }
    private float rotation(MotionEvent event) {
        //Log.d("DEBUG MAP"," angle nord :"+this.northAngle);
        if(event.getPointerCount() == 2) {
            double delta_x = (event.getX(0) - event.getX(1));
            double delta_y = (event.getY(0) - event.getY(1));
            double radians = Math.atan2(delta_y, delta_x);
            //Log.d("Rotation ~~~~~~~~~~~~~~~~~", delta_x + " ## " + delta_y + " ## " + radians + " ## "
            //        + Math.toDegrees(radians));
            if(touched) {
                this.mScaleFactor = (float) -((this.distance - (Math.sqrt(Math.abs((Math.pow(delta_x, 2) - Math.pow(delta_y, 2))))))/this.getWidth()) +1;
                this.zoomRatio*=this.mScaleFactor;
                this.distance = Math.sqrt(Math.abs((Math.pow(delta_x, 2) - Math.pow(delta_y, 2))));
            }else{
                this.distance = Math.sqrt(Math.abs((Math.pow(delta_x, 2) - Math.pow(delta_y, 2))));
                this.mScaleFactor = 1f;
                touched = true;
            }
            float deg = (float) Math.toDegrees(radians);
            if(deg!=0 && touched2){
                this.realRotation = this.lastRotation -deg;
                this.northAngle += this.realRotation;
                this.lastRotation  = deg;
                //Log.d("Rotation ~~~~~~~true~~~~~~~", "angle : "+this.realRotation);
            }else {
                this.realRotation = 0.0f;
                this.lastRotation  = deg;
                //Log.d("Rotation ~~~~~~~false~~~~~~~", "angle : "+this.lastRotation);
                touched2 = true;
            }
            invalidate();
            return (float) Math.toDegrees(radians);
        }else if(event.getPointerCount()>=3){
            if(touched3){
                this.realRotation = 0.0f;
                this.mScaleFactor = 1f;
                touched = false;
                touched2 = false;
                float y = event.getY(0);
                //relative to height
                float rel = y-this.last3y;
                rel = (rel/this.getHeight())*-5;
                Log.d("DEBUG MAP","point y : "+rel);
                this.Xrotation+=rel;
                if(this.Xrotation>=0 && this.Xrotation<=30)
                    this.camera.rotateX(rel);
                else
                    this.Xrotation-=rel;
                invalidate();
                return 0;
            }else{
                this.touched3 = true;
                this.last3y = event.getY(0);
                return 0;
            }
        }
        this.realRotation = 0.0f;
        this.mScaleFactor = 1f;
        touched = false;
        touched2 = false;
        touched3 = false;
        return 0;
    }

    //fonction qui permet de zommer Plus ou Moins
    public void zoom(boolean more){
        if(more){
            this.mScaleFactor=1.1f;
        }
        else{
            this.mScaleFactor=0.9f;
        }
        calculNewCoord2();
    }

    //fonction qui permet de calculer les nouvelles coordonées de la carte
    public void calculNewCoord2(){
        //calcul des nouvelle coordonée
        this.center.setX((this.center.getX()-offsetX));
        this.center.setY((this.center.getY()-offsetY));

        if(this.magasins!=null)
        {

            for(Path listeLocation : this.magasins){
                Matrix rotateMatrix = new Matrix();
                Matrix translateMatrix = new Matrix();
                RectF rectF = new RectF();
                listeLocation.computeBounds(rectF, true);
                //scaleMatrix.setScale(this.mScaleFactor, this.mScaleFactor,(float)this.center.getX(),(float)this.center.getY());
                translateMatrix.setTranslate(-offsetX,-offsetY);
                rotateMatrix.setRotate(-this.realRotation,(float)this.center.getX(),(float)this.center.getY());
                //listeLocation.transform(scaleMatrix);
                listeLocation.transform(translateMatrix);
                listeLocation.transform(rotateMatrix);
            }
            for(Path listeLocation : this.courses){
                Matrix rotateMatrix = new Matrix();
                Matrix translateMatrix = new Matrix();
                RectF rectF = new RectF();
                listeLocation.computeBounds(rectF, true);
                //scaleMatrix.setScale(this.mScaleFactor, this.mScaleFactor,(float)this.center.getX(),(float)this.center.getY());
                translateMatrix.setTranslate(-offsetX,-offsetY);
                rotateMatrix.setRotate(-this.realRotation,(float)this.center.getX(),(float)this.center.getY());
                //listeLocation.transform(scaleMatrix);
                listeLocation.transform(translateMatrix);
                listeLocation.transform(rotateMatrix);
            }

        }
        //this.mScaleFactor = 1;
        offsetXtotal+=offsetX;
        offsetYtotal+=offsetY;
        offsetX = 0;
        offsetY = 0;
    }
    private void calculZoom() {
        if(this.magasins!=null)
        {
            for(Path listeLocation : this.magasins){
                Matrix scaleMatrix = new Matrix();
                scaleMatrix.setScale(this.mScaleFactor, this.mScaleFactor,(float)this.center.getX(),(float)this.center.getY());
                listeLocation.transform(scaleMatrix);
            }
            for(Path listeLocation : this.courses){
                Matrix scaleMatrix = new Matrix();
                scaleMatrix.setScale(this.mScaleFactor, this.mScaleFactor,(float)this.center.getX(),(float)this.center.getY());
                listeLocation.transform(scaleMatrix);
            }
            /*System.out.println("mScaleFactor"+mScaleFactor);
            System.out.println("this.center.getX()"+this.center.getX());
            System.out.println("this.center.getY()"+this.center.getY());*/

        }
    }
    //permet de recalculer les coordonée en fonction d'un angle
    private Location rotateLocation(Location a,Location o,float angle){
        angle*=Math.PI/180;
        double tempX = a.getX()-o.getX();
        double tempY = a.getY()-o.getY();
        return new Location(0,0,tempX * Math.cos (angle) + tempY * Math.sin (angle) + o.getX(),
                - tempX * Math.sin (angle) + tempY * Math.cos (angle) + o.getY());
    }

    protected void onDraw(Canvas canvas){
        canvas.save();
        Matrix m = new Matrix();
        this.camera.getMatrix(m);
        canvas.drawRect(new Rect(0,0,this.getWidth(),this.getHeight()),this.p4);
        canvas.translate(this.getWidth()/2,this.getHeight()/2);
        canvas.concat(m);
        for(Path p : this.magasins)
            canvas.drawPath(p,p2);
        for(Path p : this.courses)
            canvas.drawPath(p,p3);
        canvas.drawCircle((float)this.center.getX(),(float)this.center.getY(),10,p);
        //dessin de la boussole
        canvas.restore();
        if(boussole!=null) {
            /*canvas.save(); //Saving the canvas and later restoring it so only this image will be rotated.
            canvas.rotate(-this.northAngle);
            canvas.drawBitmap(boussole, 50, 50, null);
            canvas.restore();*/

            Matrix matrix = new Matrix();
            matrix.setRotate(-this.northAngle, boussole.getHeight() / 2, boussole.getWidth() / 2);
            canvas.drawBitmap(boussole, matrix, null);
        }
    }

    //permet de definir un parcours sur la map
    public void setCourse(ArrayList<CourseNode> liste){
        this.courseNode = liste;
        for(CourseNode cn : this.courseNode){
            cn.setX(cn.getLocation().getX()-900);
            cn.setY(cn.getLocation().getY()-700);
        }
        this.center.setX(this.courseNode.get(liste.size()-1).getLocation().getX()-offsetXtotal/zoomRatio);
        this.center.setY(this.courseNode.get(liste.size()-1).getLocation().getY()-offsetYtotal/zoomRatio);
        //System.out.println(this.courseNode);
        settingPath();
        invalidate();
    }

    //permet de definir les magasins à desiner
    public void setMap(com.ackincolor.cloudito.entities.Map map){

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
        this.map = map;
        if(this.map.getListe()!=null){
            for(ArrayList<Location> listeLocation : this.map.getListe()){
                for(Location location : listeLocation){
                    Location ltemp = rotateLocation(location,this.center,this.realRotation);
                    location.setX((ltemp.getX()-900));
                    location.setY((ltemp.getY()-700));
                }
            }
        }
        //Log.d("DEBUG","setting map into view : minX :"+minX+",minY : "+ minY+", MaxX:"+maxX+", maxY"+maxY);
        // dessin de la carte
        this.invalidate();
        settingPath();
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //Log.d("DEBUG MAP", )
            //
            //mScaleFactor *= detector.getScaleFactor();
            //mScaleFactor = Math.max(0.9f, Math.min(mScaleFactor, 1.2f));

            return false;
        }
    }
    private class GestureListener
            extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            offsetX=distanceX;
            offsetY=distanceY;

            return true;
        }
    }
    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }
    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public com.ackincolor.cloudito.entities.Map getMap() {
        return map;
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
            for(Path listeLocation : this.magasins){
                Matrix rotateMatrix = new Matrix();
                Matrix translateMatrix = new Matrix();
                Matrix scaleMatrix = new Matrix();
                RectF rectF = new RectF();
                listeLocation.computeBounds(rectF, true);
                scaleMatrix.setScale(this.zoomRatio, this.zoomRatio,(float)this.center.getX(),(float)this.center.getY());
                translateMatrix.setTranslate(-offsetXtotal,-offsetYtotal);
                rotateMatrix.setRotate(-this.northAngle,(float)this.center.getX(),(float)this.center.getY());
                listeLocation.transform(scaleMatrix);
                listeLocation.transform(translateMatrix);
                listeLocation.transform(rotateMatrix);
            }
            for(Path listeLocation : this.courses){
                Matrix rotateMatrix = new Matrix();
                Matrix translateMatrix = new Matrix();
                Matrix scaleMatrix = new Matrix();
                RectF rectF = new RectF();
                listeLocation.computeBounds(rectF, true);
                scaleMatrix.setScale(this.zoomRatio, this.zoomRatio,(float)this.center.getX(),(float)this.center.getY());
                translateMatrix.setTranslate(-offsetXtotal,-offsetYtotal);
                rotateMatrix.setRotate(-this.northAngle,(float)this.center.getX(),(float)this.center.getY());
                listeLocation.transform(scaleMatrix);
                listeLocation.transform(translateMatrix);
                listeLocation.transform(rotateMatrix);
            }
        }
    }

    public void simulateForward(ArrayList<CourseNode> liste) {
            this.courseNode = liste;
            for(CourseNode cn : this.courseNode){
                cn.setX(cn.getLocation().getX()-900);
                cn.setY(cn.getLocation().getY()-700);
                this.center.setX(this.courseNode.get(0).getLocation().getX());
                this.center.setY(this.courseNode.get(0).getLocation().getY());

                invalidate();
                settingPath();
            }

    }

    public ArrayList<CourseNode> getCourseNode(){
        return this.courseNode;
    }
}