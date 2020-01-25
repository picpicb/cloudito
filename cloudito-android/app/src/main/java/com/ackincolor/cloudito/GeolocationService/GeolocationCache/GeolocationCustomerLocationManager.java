package com.ackincolor.cloudito.GeolocationService.GeolocationCache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ackincolor.cloudito.data.DatabaseController;
import com.ackincolor.cloudito.entities.AccessPoint;
import com.ackincolor.cloudito.entities.Location;

public class GeolocationCustomerLocationManager {
    private DatabaseController dbm = null;
    private static SQLiteDatabase db;

    // TABLE
    public  static final String TABLE_NAME = "customerLocation";

    // ATTRIBUTES
    public static final String KEY_ID_LOCATION = "id_location";
    public static final String KEY_FLOOR = "floor";
    public static final String KEY_X = "x";
    public static final String KEY_Y = "y";

    // CREATE TABLE
    public static final String CREATE_TABLE_CUSTOMER_LOCATION = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_LOCATION+" LONG," +
            " "+KEY_FLOOR+" INT," +
            " "+KEY_X+" DOUBLE,"+
            " "+KEY_Y+" DOUBLE );";

    // CONSTRUCTOR
    public GeolocationCustomerLocationManager(Context context) {
        this.dbm = DatabaseController.getInstance(context);
    }
    public void open(){
        this.db = this.dbm.getWritableDatabase();
    }
    public void close(){
        this.db.close();
    }

    public void insertCustomerLocation(Location location){
        db.execSQL("delete from "+ TABLE_NAME+" ;");

        // PREPARE INSERT
        ContentValues values = new ContentValues();
        values.put(KEY_ID_LOCATION,location.getId());
        values.put(KEY_FLOOR,location.getFloor());
        values.put(KEY_X,location.getX());
        values.put(KEY_Y,location.getY());
        db.insert(TABLE_NAME,null,values);
    }

    public Location getCustomerLocation(){
        String request = "Select * from "+TABLE_NAME+" ; ";
        Cursor c = this.db.rawQuery(request,null);
        Log.d("DEBUG GEOLOCATION CUSTOMER LOCATION MANAGER"," GETTING CUSTOMER LOCATION :"+c.getCount());
        Location location = null;
        c.moveToFirst();
        while (!c.isAfterLast()) {
            // CREATING NEW LOCATION
            location = new Location(c.getLong(0),c.getInt(1),c.getDouble(2),c.getDouble(3));
            c.moveToNext();
        }
        return location;
    }
}
