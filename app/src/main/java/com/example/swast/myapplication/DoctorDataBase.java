package com.example.swast.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DoctorDataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Doctor.db";
    public static final String DOCTOR_TABLE_NAME = "doctor_table";

    //Doctor Details
    public static final String DCOL_1 = "NAME";
    public static final String DCOL_2 = "EXPERIENCE";
    public static final String DCOL_3 = "SEX";
    public static final String DCOL_4 = "MOBILE";
    public static final String DCOL_5 = "MAIL";
    public static final String DCOL_6 = "PASSWORD";
    public static final String DCOL_7 = "SPECIALIZATION";

    public DoctorDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DOCTOR_TABLE_NAME+"("+DCOL_1+" text , "+DCOL_2+" integer, "+DCOL_3+" text, "+DCOL_4+" number(10), "+DCOL_5+" text, "+DCOL_6+" text, "+DCOL_7+" text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+DOCTOR_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDoctorData(String name, int experience, String sex, Long mobile, String mail, String password, String specialization){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_1,name);
        contentValues.put(DCOL_2,experience);
        contentValues.put(DCOL_3,sex);
        contentValues.put(DCOL_4,mobile);
        contentValues.put(DCOL_5,mail);
        contentValues.put(DCOL_6,password);
        contentValues.put(DCOL_7,specialization);
        long result = db.insert(DOCTOR_TABLE_NAME, null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllDoctorData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+DOCTOR_TABLE_NAME,null);
        return res;
    }

    public Cursor getDoctorData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+DOCTOR_TABLE_NAME+" where mail = ? and password = ?",new String[] {username,password});
        return res;
    }

    public boolean updateContactDoctor(Long mobile, String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_4,mobile);
        db.update(DOCTOR_TABLE_NAME, contentValues, "MAIL = ? and PASSWORD = ?",new String[] {user,pass});
        return true;
    }

    public boolean updatePasswordDoctor(String password, String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL_6,password);
        db.update(DOCTOR_TABLE_NAME, contentValues, "MAIL = ? and PASSWORD = ?",new String[] {user,pass});
        return true;
    }

    public Integer deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DOCTOR_TABLE_NAME,"MAIL = ?",new String[] {"spatro@mitaoe.ac.in"});
    }
}
