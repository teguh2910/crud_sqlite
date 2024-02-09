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
        DB.execSQL("create table userdetails(name TEXT primary key, contact TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists userdetails");
    }
    public boolean insertuserdata(String name,String contact, String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        long results=DB.insert("userdetails",null,contentValues);
        return results != -1;
    }
    public boolean updateuserdata(String name,String contact, String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        Cursor cursor = DB.rawQuery("select * from userdetails where name = ?", new String[]{name});

        if (cursor.getCount() > 0) {
            long results = DB.update("userdetails", contentValues, "name=?", new String[]{name});
            return results != -1;
        }else{
            return  false;
            }
        }

    public boolean deleteuserdata(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        Cursor cursor = DB.rawQuery("select * from userdetails where name = ?", new String[]{name});

        if (cursor.getCount() > 0) {
            long results = DB.delete("userdetails", "name=?", new String[]{name});
            return results != -1;
        }else{
            return  false;
           }
       }
    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("select * from userdetails", null);
    }
    public boolean checklogin(String name, String contact){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        Cursor cursor = DB.rawQuery("SELECT * FROM userdetails WHERE name=? AND contact=?", new String[]{name, contact});
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
