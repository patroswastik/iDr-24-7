package com.example.swast.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class homePage extends AppCompatActivity {

    DataBase db;
    DoctorDataBase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        db = new DataBase(this);
        myDb = new DoctorDataBase(this);
    }

    public void patient(View v){
        Intent intent = new Intent(".PatientLogin");
        startActivity(intent);
    }

    public void doctor(View v){
        Intent intent = new Intent(".DoctorLogin");
        startActivity(intent);
    }

    public void del(View view){
        Integer patient_rows = db.deleteData();
        Integer doctor_rows = myDb.deleteData();
        if(patient_rows>0)
            Toast.makeText(this,"Data Deleted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();
    }
}
