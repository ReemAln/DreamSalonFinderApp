package com.example.dreamsalonfinderapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLENAME = "Userdetails";
    public DBHelper(@Nullable Context context) {
        super(context, TABLENAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create Table " + TABLENAME + "(fullName TEXT, email EMAIL, phone INT, password VARCHAR ,id INT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("drop table if exists Userdetails.db");
    }
}
