package com.example.crud_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table userdetails(username TEXT primary key, nim TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists userdetails");
    }
    public boolean insertuserdata(String username,String nim, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("nim", nim);
        contentValues.put("password", password);
        long results=DB.insert("userdetails",null,contentValues);
        return results != -1;
    }
    public boolean updateuserdata(String username,String nim, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("nim", nim);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("select * from userdetails where username = ?", new String[]{username});

        if (cursor.getCount() > 0) {
            cursor.close();
            long results = DB.update("userdetails", contentValues, "username=?", new String[]{username});
            return results != -1;
        }else{
            return  false;
            }
        }

    public boolean deleteuserdata(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        Cursor cursor = DB.rawQuery("select * from userdetails where username = ?", new String[]{username});

        if (cursor.getCount() > 0) {
            cursor.close();
            long results = DB.delete("userdetails", "username=?", new String[]{username});
            return results != -1;
        }else{
            return  false;
           }
       }
    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("select * from userdetails", null);
    }
    public boolean checklogin(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("SELECT * FROM userdetails WHERE username=? AND password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            // User with the provided name exists, you can check contact if needed
            // For now, let's assume contact checking is not necessary
            cursor.close();
            return true;
        } else {
            // User does not exist or some other issue occurred
            cursor.close();
            return false;
        }
    }
}
