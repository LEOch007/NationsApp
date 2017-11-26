package com.gy.linjliang.nationsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lenovo on 2017/11/19.
 */

public class MyDataBase extends SQLiteOpenHelper {
    private  static final int DB_VERSION = 1;
    private  static final String DB_NAME = "t_db";

    public MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MyDataBase(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table if not exists t_table" +
                "(id integer primary key autoincrement,name varchar(20) unique,sex varchar(5),live varchar(10)," +
                "place varchar(10),nation varchar(10),information varchar(800),image int,imagebitmap varchar(800),flag int)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
