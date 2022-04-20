package com.example.sqlliteone;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "UserDataBase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table UserInfo(name TEXT primary key, " +
                "phone TEXT, date_of_birth TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists UserInfo");
    }

    public Boolean insert(String name, String phone, String date_of_bth)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("date_of_birth",date_of_bth);
        long result = db.insert("UserInfo",null,contentValues);
        return result != -1;
    }


    public Boolean Delete(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();


        int result = db.delete("UserInfo",
                "name = " + "'" + name + "'", null
        );
        db.close();

        return result > 0;
    }


    public Cursor getdata()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from UserInfo",null);
    }

}
