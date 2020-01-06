package com.ackincolor.cloudito.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseController extends SQLiteOpenHelper {
    private static DatabaseController sInstance;
    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 4;

    public static synchronized DatabaseController getInstance(Context context) {
        if (sInstance == null) { sInstance = new DatabaseController(context); }
        return sInstance;
    }
    private DatabaseController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ParcoursManager.CREATE_TABLE_NOEUDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ParcoursManager.TABLE_NAME);
        onCreate(db);
    }
}
