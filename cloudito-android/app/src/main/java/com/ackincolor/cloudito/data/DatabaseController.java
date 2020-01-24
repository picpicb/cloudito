package com.ackincolor.cloudito.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ackincolor.cloudito.GeolocationService.GeolocationCache.GeolocationCustomerLocationManager;
import com.ackincolor.cloudito.GeolocationService.GeolocationCache.GeolocationManager;

public class DatabaseController extends SQLiteOpenHelper {
    private static DatabaseController sInstance;
    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 5;

    public static synchronized DatabaseController getInstance(Context context) {
        if (sInstance == null) { sInstance = new DatabaseController(context); }
        return sInstance;
    }
    private DatabaseController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // PARCOURS
        db.execSQL(ParcoursManager.CREATE_TABLE_NOEUDS);

        // ACCESS POINTS
        db.execSQL(GeolocationManager.CREATE_TABLE_ACCESS_POINTS);

        // CUSTOMER LOCATION
        db.execSQL(GeolocationCustomerLocationManager.CREATE_TABLE_CUSTOMER_LOCATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ParcoursManager.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+GeolocationManager.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ GeolocationCustomerLocationManager.TABLE_NAME);
        onCreate(db);
    }
}
