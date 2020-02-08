package com.ackincolor.cloudito.GeolocationService.GeolocationCache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ackincolor.cloudito.data.DatabaseController;
import com.ackincolor.cloudito.entities.AccessPoint;
import com.ackincolor.cloudito.entities.Location;
import com.ackincolor.cloudito.entities.PoI;

import java.util.ArrayList;

public class GeolocationManager {
    private DatabaseController dbm = null;
    private static SQLiteDatabase db;

    // TABLE
    public  static final String TABLE_NAME = "accessPoints";

    // ATTRIBUTE
    public static final String KEY_MAC_ADDRESS = "mac_address";
    public static final String KEY_ID_LOCATION = "id_location";
    public static final String KEY_FLOOR = "floor";
    public static final String KEY_X = "x";
    public static final String KEY_Y = "y";

    // CREATE TABLE
    public static final String CREATE_TABLE_ACCESS_POINTS = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_MAC_ADDRESS+" STRING," +
            " "+KEY_ID_LOCATION+" LONG," +
            " "+KEY_FLOOR+" INT," +
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

    // INSERT ALL ACCESS POINTS AT START
    public void insertAccessPoints(ArrayList<AccessPoint> arrayAccessPoints){
        open();
        for(AccessPoint accessPoint : arrayAccessPoints){
            ContentValues values = new ContentValues();
            values.put(KEY_MAC_ADDRESS,accessPoint.getMac());
            values.put(KEY_ID_LOCATION,accessPoint.getLocation().getId());
            values.put(KEY_FLOOR,accessPoint.getLocation().getFloor());
            values.put(KEY_X,accessPoint.getLocation().getX());
            values.put(KEY_Y,accessPoint.getLocation().getY());
            db.insert(TABLE_NAME,null,values);
        }
        close();
    }

    // GET ALL ACCESS POINTS
    public ArrayList<AccessPoint> getAccessPoints(){
        ArrayList<AccessPoint> accessPoints = new ArrayList<>();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor c = this.db.rawQuery(query,null);
        Log.d("DEBUG GEOLOCATION MANAGER"," GETTING ACCESS POINTS :"+c.getCount());

        c.moveToFirst();
        while (!c.isAfterLast()) {
            // CREATING NEW ACCESS POINT
            AccessPoint accessPoint = new AccessPoint(c.getString(0),
                    new Location(c.getLong(1),c.getInt(2),c.getDouble(3),c.getDouble((4))));

            accessPoints.add(accessPoint);
            c.moveToNext();
        }
        return accessPoints;
    }

}
