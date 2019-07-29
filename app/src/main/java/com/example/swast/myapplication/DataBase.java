package com.example.swast.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Patient.db";
    public static final String PATIENT_TABLE_NAME = "patient_table";

    //Patient Details
    public static final String PCOL_1 = "NAME";
    public static final String PCOL_2 = "AGE";
    public static final String PCOL_3 = "SEX";
    public static final String PCOL_4 = "MOBILE";
    public static final String PCOL_5 = "MAIL";
    public static final String PCOL_6 = "PASSWORD";
    public static final String PCOL_7 = "APPOINTMENT";

    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+PATIENT_TABLE_NAME+"("+PCOL_1+" text , "+PCOL_2+" integer, "+PCOL_3+" text, "+PCOL_4+" number(10), "+PCOL_5+" text, "+PCOL_6+" text,"+PCOL_7+" text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+PATIENT_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertPatientData(String name, int age, String sex, Long mobile, String mail, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String nope="null";
        contentValues.put(PCOL_1,name);
        contentValues.put(PCOL_2,age);
        contentValues.put(PCOL_3,sex);
        contentValues.put(PCOL_4,mobile);
        contentValues.put(PCOL_5,mail);
        contentValues.put(PCOL_6,password);
        contentValues.put(PCOL_7,nope);
        long result = db.insert(PATIENT_TABLE_NAME, null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllPatientData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+PATIENT_TABLE_NAME,null);
        return res;
    }

    public Cursor getPatientData(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+PATIENT_TABLE_NAME+" where mail = ? and password = ?",new String[]{username,password});
        return res;
    }

    public boolean updatePatient(String name, String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PCOL_7,name);
        db.update(PATIENT_TABLE_NAME, contentValues, "MAIL = ? and PASSWORD = ?",new String[] {user,pass});
        return true;
    }

    public boolean updateContactPatient(Long mobile, String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PCOL_4,mobile);
        db.update(PATIENT_TABLE_NAME, contentValues, "MAIL = ? and PASSWORD = ?",new String[] {user,pass});
        return true;
    }

    public boolean updatePasswordPatient(String password, String user,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PCOL_6,password);
        db.update(PATIENT_TABLE_NAME, contentValues, "MAIL = ? and PASSWORD = ?",new String[] {user,pass});
        return true;
    }

    public boolean updateAppointmentPatient(String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PCOL_7,"null");
        db.update(PATIENT_TABLE_NAME, contentValues, "MAIL = ? and PASSWORD = ?",new String[] {user,pass});
        return true;
    }

    public Integer deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PATIENT_TABLE_NAME,"appointment = ?",new String[]{"null"});
    }
}
