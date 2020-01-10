package com.ackincolor.cloudito.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ackincolor.cloudito.entities.Course;
import com.ackincolor.cloudito.entities.Node;

import java.util.ArrayList;
import java.util.UUID;

public class ParcoursManager {
    private DatabaseController dbm = null;
    private SQLiteDatabase db;
    public  static final String TABLE_NAME = "noeuds";
    public static final String KEY_ID_PARCOURS = "Id_parcours";
    public static final String KEY_ID_NOEUD = "Id_noeud";
    public static final String KEY_ORDER = "orderNoeud";
    public static final String KEY_TYPE = "type";
    public static final String KEY_POI = "Id_poi";
    public static final String CREATE_TABLE_NOEUDS = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_PARCOURS+" STRING," +
            " "+KEY_ID_NOEUD+" TEXT," +
            " "+KEY_ORDER+" INT," +
            " "+KEY_TYPE+" TEXT," +
            " "+KEY_POI+" TEXT" +
            ");";
    public ParcoursManager(Context context){
        this.dbm = DatabaseController.getInstance(context);
    }
    public void open(){
        this.db = this.dbm.getWritableDatabase();
    }

    public void saveParcours(Course p){
        for(Node n : p.getListe()){
            ContentValues values = new ContentValues();
            values.put(KEY_ID_NOEUD,n.getId().toString());
            values.put(KEY_ID_PARCOURS,p.getId().toString());
            values.put(KEY_ORDER,n.getOrder());
            values.put(KEY_POI,n.getPoi().toString());
            db.insert(TABLE_NAME,null,values);
        }
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
}
