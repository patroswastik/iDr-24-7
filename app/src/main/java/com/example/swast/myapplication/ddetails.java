package com.example.swast.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ddetails extends AppCompatActivity {

    public static String user,pass;
    DoctorDataBase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddetails);
        myDb = new DoctorDataBase(this);
        TextView t1 = (TextView) findViewById(R.id.name);
        TextView t2 = (TextView) findViewById(R.id.age);
        TextView t3 = (TextView) findViewById(R.id.sex);
        TextView t4 = (TextView) findViewById(R.id.mobile);
        TextView t5 = (TextView) findViewById(R.id.mail);
        TextView t6 = (TextView) findViewById(R.id.specialization);
        Cursor res = myDb.getAllDoctorData();
        while(res.moveToNext()){
            if(user.equals(res.getString(4)) && pass.equals(res.getString(5))){
                t1.setText(res.getString(0));
                t2.setText(res.getString(1));
                t3.setText(res.getString(2));
                t4.setText(res.getString(3));
                t5.setText(res.getString(4));
                t6.setText(res.getString(6));
                break;
            }
        }
    }

    public void show(View view){
        TextView t1 = (TextView) findViewById(R.id.name);
        TextView t2 = (TextView) findViewById(R.id.age);
        TextView t3 = (TextView) findViewById(R.id.sex);
        TextView t4 = (TextView) findViewById(R.id.mobile);
        TextView t5 = (TextView) findViewById(R.id.mail);
        TextView t6 = (TextView) findViewById(R.id.specialization);
        Cursor res = myDb.getAllDoctorData();
        while(res.moveToNext()){
            if(user.equals(res.getString(4)) && pass.equals(res.getString(5))){
                t1.setText(res.getString(0));
                t2.setText(res.getString(1));
                t3.setText(res.getString(2));
                t4.setText(res.getString(3));
                t5.setText(res.getString(4));
                t6.setText(res.getString(6));
                break;
            }
        }
    }

    public void edit(View view){
        EDdetails.user = user;
        EDdetails.pass = pass;
        Intent intent = new Intent(".EDdetails");
        startActivity(intent);
    }
}
