package com.example.swast.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PatientHome extends AppCompatActivity {

    public static String user;
    public static String pass;

    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        db = new DataBase(this);
    }

    public void detailed(View view){
        Pdetails.users = user;
        Pdetails.passes = pass;
        Intent intent = new Intent(".Pdetails");
        startActivity(intent);
    }

    public void check(View view){
        Toast.makeText(this,user+" "+pass,Toast.LENGTH_LONG).show();
    }

    public void booked(View view){
        PBook.user = user;
        PBook.pass = pass;
        Intent intent = new Intent(".PBook");
        startActivity(intent);
    }

    public void viewApp(View view){
        PView.user = user;
        PView.pass = pass;
        Intent intent = new Intent(".PView");
        startActivity(intent);
    }

    public void search(View view){
        Intent intent = new Intent(".PSearch");
        startActivity(intent);
    }

    public void cancel(View view){
        Cursor rs = db.getPatientData(user,pass);
        boolean not_found = false;
        while(rs.moveToNext()){
            if((rs.getString(6)).equals("null"))
                not_found = true;
        }
        if(!not_found){
            boolean isUpdate = db.updateAppointmentPatient(user,pass);
            if(isUpdate)
                Toast.makeText(this,"Appointment Cancelled",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"No Appointments",Toast.LENGTH_SHORT).show();
    }

    public void logout(View view){
        Intent intent = new Intent(".homePage");
        startActivity(intent);
    }
}
