package com.example.eps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.security.MessageDigest;
import java.util.ArrayList;

public class DataBase extends SQLiteAssetHelper {
    public static final String DB_NAME = "CmDb.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Modules";
    public static final String ID ="ID";
    public static final String OTI = "OTI";
    public static final String CHECK = "CHECK";
    public static final String BASKHOND = "BASKHOND";
    public static final String VOLLEY = "VOLLEY";
    public static final String GYM = "GYM";
    public static final String FOUR = "FOUR";


    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    // It's not working it needs extra works
    public Modules getModule (int moduleIndex){
        Modules m = null;
        SQLiteDatabase db = getReadableDatabase();
        String[] args = {String.valueOf(moduleIndex)};
        Cursor cu = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ID+" = "+args[0],args,null);
        while (cu.moveToNext()){
            int a = cu.getInt(0);
            String b = cu.getString(1);
            String c = cu.getString(2);
            String d = cu.getString(3);
            String e = cu.getString(4);
            String f = cu.getString(5);
            String j = cu.getString(6);
            m = new Modules(a,b,c,d,e,f,j);
        }
        return m;
    }

    public ArrayList<Modules> getAllModule(){
        ArrayList<Modules> modules = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cu = db.rawQuery("SELECT * FROM "+TABLE_NAME,null,null);
        while (cu.moveToNext()){
            int a = cu.getInt(0);
            String b = cu.getString(1);
            String c = cu.getString(2);
            String d = cu.getString(3);
            String e = cu.getString(4);
            String f = cu.getString(5);
            String j = cu.getString(6);
            modules.add(new Modules(a,b,c,d,e,f,j));
        }
        return modules;
    }

    public long getCount(){
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db,TABLE_NAME);
    }
}
