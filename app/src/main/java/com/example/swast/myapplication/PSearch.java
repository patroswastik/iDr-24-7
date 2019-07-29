package com.example.swast.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PSearch extends AppCompatActivity {

    DoctorDataBase myDb;
    DataBase db;
    public static String names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psearch);
        myDb = new DoctorDataBase(this);
        db = new DataBase(this);
    }

    public void search(View view){
        EditText e1 = (EditText) findViewById(R.id.searchD);
        String name = e1.getText().toString();
        Cursor res = myDb.getAllDoctorData();
        boolean present = false;
        while(res.moveToNext()){
            if(name.equals(res.getString(0))){
                showMessage("Data Found","Name: "+res.getString(0)+"\nSpecialization: "+res.getString(6));
                present = true;
                names = name;
            }
        }
        if(!present)
            Toast.makeText(this,"No Data Found",Toast.LENGTH_LONG).show();
    }

    public void back(View view){
        Intent intent = new Intent(".PatientHome");
        startActivity(intent);
    }

    public void next(View view){
        if(names.equals("")){
            Intent intent = new Intent(".PBook");
            startActivity(intent);
        }else{
            PBook.names = names;
            Intent intent = new Intent(".PBook");
            startActivity(intent);
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
