package com.ackincolor.cloudito.CourseService.CourseCache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ackincolor.cloudito.data.DatabaseController;
import com.ackincolor.cloudito.entities.Course;
import com.ackincolor.cloudito.entities.Node;

import java.util.ArrayList;
import java.util.UUID;

public class CourseManager {
    private DatabaseController dbm = null;
    private SQLiteDatabase db;
    public  static final String TABLE_NAME = "noeuds";
    public static final String KEY_ID_PARCOURS = "Id_parcours";
    public static final String KEY_ID_NOEUD = "Id_noeud";
    public static final String KEY_ORDER = "orderNoeud";
    public static final String KEY_TYPE = "type";
    public static final String KEY_POI = "Id_poi";
    public  static final String TABLE_NAME1 = "coursenoeuds";
    public static final String KEY_ID_COURSENOEUDS = "Id_parcours_noeuds";
    public static final String COORDINATE_X = "x";
    public static final String COORDINATE_Y = "y";
    public static final String TABLE_NAME2 = "map";
    public static final String KEY_ID_MAP = "id_map";
    public static final String KEY_MAP_DATA = "map_data";
    public static final String CREATE_TABLE_NOEUDS = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_PARCOURS+" STRING," +
            " "+KEY_ID_NOEUD+" TEXT," +
            " "+KEY_ORDER+" INT," +
            " "+KEY_TYPE+" TEXT," +
            " "+KEY_POI+" TEXT" +
            ");";
    public static final String CREATE_TABLE_COURSENOEUDS = "CREATE TABLE "+TABLE_NAME1+
            " (" +
            " "+KEY_ID_COURSENOEUDS+" STRING," +
            " "+COORDINATE_X+" INT," +
            " "+COORDINATE_Y+" INT" +
            ");";
    public static final String CREATE_TABLE_MAP = "CREATE TABLE "+TABLE_NAME2+
            " ("+
            " "+KEY_ID_MAP+" INT," +
            " "+KEY_MAP_DATA+" BLOB" +
            ");";
    public CourseManager(Context context){
        this.dbm = DatabaseController.getInstance(context);
    }
    public void open(){
        this.db = this.dbm.getWritableDatabase();
    }

    public void saveParcours(Course p){
    }
    public void close(){
        this.db.close();
    }
    public String getParcours(UUID id){
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor c = this.db.rawQuery(query,null);
        Log.d("DEBUG","recuperation des parcours :"+c.getCount());
        ArrayList<Course> liste = new ArrayList<>();
        c.moveToNext();
        String str = "";
        while(!c.isAfterLast()){
            str+=c.getString(1);
            c.moveToNext();
        }
        return str;
    }
    public void saveMap(byte[] data){
        String query = "Select *  from "+TABLE_NAME2;
        Cursor c = this.db.rawQuery(query,null);
        if(c.getCount()==0) {
            ContentValues values = new ContentValues();
            values.put(KEY_ID_MAP, 1);
            values.put(KEY_MAP_DATA, data);
            // Inserting Row
            this.db.insert(TABLE_NAME2, null, values);
        }
    }
    public byte[] getMap(){
        String query = "Select *  from "+TABLE_NAME2;
        Cursor c = this.db.rawQuery(query,null);
        Log.d("DEBUG Cache","recuperation de la map");
        c.moveToNext();
        while(!c.isAfterLast()){
            return c.getBlob(1);
        }
        return new byte[0];
    }
}
