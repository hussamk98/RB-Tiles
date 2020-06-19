package com.tinyapps.rbtiles.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String dbname = "rbtiles.db";
    public static String createtable1 ="CREATE TABLE " + attributes.properties.tbl3x1 + "(" +
            attributes.properties.id + " INTEGER PRIMARY KEY NOT NULL, "+
            attributes.properties.hscore3x1 + " TEXT  )";

    public static String createtable2 ="CREATE TABLE " + attributes.properties.tbl3x2 + "(" +
            attributes.properties.id + " INTEGER PRIMARY KEY NOT NULL, " +
            attributes.properties.hscore3x2 + " TEXT  )";

    public static String createtable3 ="CREATE TABLE " + attributes.properties.tbl3x3 + "(" +
            attributes.properties.id + " INTEGER PRIMARY KEY NOT NULL, " +
            attributes.properties.hscore3x3 + " TEXT  )";


    public DatabaseHelper(Context context) {
        super(context, dbname, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(createtable1);
            db.execSQL(createtable2);
            db.execSQL(createtable3);

            this.addSetting1(db, "1", "0");
            this.addSetting2(db, "1", "0");
            this.addSetting3(db, "1", "0");
    }

    public void addSetting1(SQLiteDatabase db, String key, String value) {
        ContentValues values = new ContentValues();
        values.put(attributes.properties.id, key);
        values.put(attributes.properties.hscore3x1, value);
        db.insert(attributes.properties.tbl3x1, null, values);
    }
    public void addSetting2(SQLiteDatabase db, String key, String value) {
        ContentValues values = new ContentValues();
        values.put(attributes.properties.id, key);
        values.put(attributes.properties.hscore3x2, value);
        db.insert(attributes.properties.tbl3x2, null, values);
    }
    public void addSetting3(SQLiteDatabase db, String key, String value) {
        ContentValues values = new ContentValues();
        values.put(attributes.properties.id, key);
        values.put(attributes.properties.hscore3x3, value);
        db.insert(attributes.properties.tbl3x3, null, values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor retrieve3x1() {



        String Query = "SELECT * FROM " + attributes.properties.tbl3x1 + " WHERE " + attributes.properties.id + " = " + 1   ;
        SQLiteDatabase db = this.getReadableDatabase();



        return db.rawQuery(Query, null);
    }
    public Cursor retrieve3x2() {



        String Query = "SELECT * FROM " + attributes.properties.tbl3x2 + " WHERE " + attributes.properties.id + " = "  + 1 ;
        SQLiteDatabase db = this.getReadableDatabase();



        return db.rawQuery(Query, null);
    }
    public Cursor retrieve3x3() {



        String Query = "SELECT * FROM " + attributes.properties.tbl3x3 + " WHERE " + attributes.properties.id + " = "  + 1 ;
        SQLiteDatabase db = this.getReadableDatabase();



        return db.rawQuery(Query, null);
    }
    public boolean update3x1(String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cc = new ContentValues();
        cc.put(attributes.properties.hscore3x1, score);

        db.update(attributes.properties.tbl3x1, cc,  attributes.properties.id + " = " + 1,null);


        return true;
    }

    public boolean update3x2(String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cc = new ContentValues();
        cc.put(attributes.properties.hscore3x2, score);

        db.update(attributes.properties.tbl3x2, cc,  attributes.properties.id + " = " + 1,null);


        return true;
    }

    public boolean update3x3(String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cc = new ContentValues();
        cc.put(attributes.properties.hscore3x3, score);

        db.update(attributes.properties.tbl3x3, cc,  attributes.properties.id + " = " + 1,null);


        return true;
    }
}
