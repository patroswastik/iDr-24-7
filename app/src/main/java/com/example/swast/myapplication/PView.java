package com.example.swast.myapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PView extends AppCompatActivity {

    DoctorDataBase myDb;
    DataBase db;

    public static String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pview);
        myDb = new DoctorDataBase(this);
        db = new DataBase(this);
        TextView t1 = (TextView) findViewById(R.id.Name);
        TextView t2 = (TextView) findViewById(R.id.mobile);
        TextView t3 = (TextView) findViewById(R.id.specialization);
        TextView t4 = (TextView) findViewById(R.id.experience);
        Cursor res = myDb.getAllDoctorData();
        Cursor rs = db.getPatientData(user,pass);
        String name = null;
        while(rs.moveToNext()){
            name = rs.getString(6);
        }
        while(res.moveToNext()){
            if(name.equals(res.getString(0))){
                t1.setText(res.getString(0));
                t2.setText(res.getString(3));
                t3.setText(res.getString(6));
                t4.setText(res.getString(1));
                break;
            }
        }
    }

    public void show(View view){
//        Toast.makeText(this,user+" "+pass,Toast.LENGTH_LONG).show();
        TextView t1 = (TextView) findViewById(R.id.Name);
        TextView t2 = (TextView) findViewById(R.id.mobile);
        TextView t3 = (TextView) findViewById(R.id.specialization);
        TextView t4 = (TextView) findViewById(R.id.experience);
        Cursor res = myDb.getAllDoctorData();
        Cursor rs = db.getPatientData(user,pass);
        String name = null;
        while(rs.moveToNext()){
            name = rs.getString(6);
        }
        while(res.moveToNext()){
            if(name.equals(res.getString(0))){
                t1.setText(res.getString(0));
                t2.setText(res.getString(3));
                t3.setText(res.getString(6));
                t4.setText(res.getString(1));
                break;
            }
        }
    }

    public void showMessage(String title,String Messages){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messages);
        builder.show();
    }
}
