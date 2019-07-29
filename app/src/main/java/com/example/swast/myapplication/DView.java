package com.example.swast.myapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DView extends AppCompatActivity {

    public static String user,pass;
    DataBase db;
    DoctorDataBase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dview);
        db = new DataBase(this);
        myDb = new DoctorDataBase(this);
    }

    public void show(View view){
        TextView t1 = (TextView) findViewById(R.id.Name);
        TextView t2 = (TextView) findViewById(R.id.mobile);
        TextView t3 = (TextView) findViewById(R.id.specialization);
        TextView t4 = (TextView) findViewById(R.id.experience);
        Cursor res = db.getAllPatientData();
        Cursor rs = myDb.getDoctorData(user,pass);
        String name = null;
        while(rs.moveToNext()){
            name = rs.getString(0);
            break;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            if(name.equals(res.getString(6))){
                buffer.append("Name: "+res.getString(0)+"\n");
                buffer.append("Age: "+res.getString(1)+"\n");
                buffer.append("Sex: "+res.getString(2)+"\n");
                buffer.append("Mobile: "+res.getString(3)+"\n\n");
            }
        }
        showMessage("Data",buffer.toString());
    }

    public void showMessage(String title,String Messages){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messages);
        builder.show();
    }
}