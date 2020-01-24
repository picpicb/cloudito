package com.ackincolor.cloudito.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ackincolor.cloudito.entities.AccessPoint;
import com.ackincolor.cloudito.entities.Coordinate;
import com.ackincolor.cloudito.entities.Node;
import com.ackincolor.cloudito.entities.PoI;

import java.util.ArrayList;

public class GeolocationManager {
    private DatabaseController dbm = null;
    private static SQLiteDatabase db;

    // TABLE
    public  static final String TABLE_NAME = "accessPoints";

    // ATTRIBUTE
    public static final String KEY_MAC_ADDRESS = "Id_mac_address";
    public static final String KEY_X = "Id_x";
    public static final String KEY_Y = "Id_y";

    // CREATE TABLE
    public static final String CREATE_TABLE_ACCESS_POINTS = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_MAC_ADDRESS+" STRING," +
            " "+KEY_X+" DOUBLE,"+
            " "+KEY_Y+" DOUBLE );";

    // CONSTRUCTOR
    public GeolocationManager(Context context) {
        this.dbm = DatabaseController.getInstance(context);
    }
    public void open(){
        this.db = this.dbm.getWritableDatabase();
    }
    public void close(){
        this.db.close();
    }
    public static void insertAccessPoints(){
        ArrayList<AccessPoint> arrayAccessPoints = new ArrayList<>();
        //AccessPoint a1 = new AccessPoint("",new PoI("AccessPoint","AccessPoint",new Coordinate(x,y)));
        //arrayAccessPoints.add(a1);
        for(AccessPoint accessPoint : arrayAccessPoints){
            ContentValues values = new ContentValues();
            values.put(KEY_MAC_ADDRESS,accessPoint.getMacAdress());
            values.put(KEY_X,accessPoint.getNode().getEmplacement().getX());
            values.put(KEY_Y,accessPoint.getNode().getEmplacement().getY());
            db.insert(TABLE_NAME,null,values);
        }
    }

    public ArrayList<AccessPoint> getAccessPoints(){

        return null;
    }

}
