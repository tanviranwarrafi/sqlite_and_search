package com.example.sqlitedatabaserecyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "User.db";
    private static String TABLE_NAME = "User";
    public static String COL_ID = "Id";
    public static String COL_NAME = "Name";
    public static String COL_EMAIL = "Email";
    private static int VERSION = 1;

    private String createTable = "create table User(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Email TEXT)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String name, String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_EMAIL,email);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME, null,contentValues);
        sqLiteDatabase.close();
        return id;
    }

    public Cursor showData(){
        String show_all = "select * From "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(show_all, null);
        return cursor;
    }
}
