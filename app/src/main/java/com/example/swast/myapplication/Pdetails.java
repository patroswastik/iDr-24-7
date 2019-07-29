package com.example.swast.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Pdetails extends AppCompatActivity {

    public static String users;
    public static String passes;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdetails);
        db = new DataBase(this);
        TextView t1 = (TextView) findViewById(R.id.name);
        TextView t2 = (TextView) findViewById(R.id.age);
        TextView t3 = (TextView) findViewById(R.id.sex);
        TextView t4 = (TextView) findViewById(R.id.mobile);
        TextView t5 = (TextView) findViewById(R.id.mail);
        Cursor res = db.getAllPatientData();
        while(res.moveToNext()){
            if(users.equals(res.getString(4)) && passes.equals(res.getString(5))){
                t1.setText(res.getString(0));
                t2.setText(res.getString(1));
                t3.setText(res.getString(2));
                t4.setText(res.getString(3));
                t5.setText(res.getString(4));
                break;
            }
        }
    }

    public void change(View view){
        EPdetails.user = users;
        EPdetails.pass = passes;
        Intent intent = new Intent(".EPdetails");
        startActivity(intent);
    }

    public void show(View view){
        TextView t1 = (TextView) findViewById(R.id.name);
        TextView t2 = (TextView) findViewById(R.id.age);
        TextView t3 = (TextView) findViewById(R.id.sex);
        TextView t4 = (TextView) findViewById(R.id.mobile);
        TextView t5 = (TextView) findViewById(R.id.mail);
        Cursor res = db.getAllPatientData();
        while(res.moveToNext()){
            if(users.equals(res.getString(4)) && passes.equals(res.getString(5))){
                t1.setText(res.getString(0));
                t2.setText(res.getString(1));
                t3.setText(res.getString(2));
                t4.setText(res.getString(3));
                t5.setText(res.getString(4));
                break;
            }
        }
    }
}