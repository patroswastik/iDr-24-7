package com.example.swast.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PatientLogin extends AppCompatActivity{

    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        db = new DataBase(this);
    }

//    public void login(View view){
//        try{
//            EditText e1 = (EditText) findViewById(R.id.username);
//            EditText e2 = (EditText) findViewById(R.id.password);
//            String user,pass;
//            user = e1.getText().toString();
//            pass = e2.getText().toString();
//            Cursor res = db.getAllPatientData();
//            while(res.moveToNext()){
//                if(user.equals(res.getString(4)) && pass.equals(res.getString(5))){
////                    Toast.makeText(this,"Logged in",Toast.LENGTH_SHORT).show();
//                    PatientHome.user = user;
//                    PatientHome.pass = pass;
//                    Toast.makeText(this,PatientHome.user+" "+PatientHome.pass,Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(".PatientHome");
//                    startActivity(intent);
//                    break;
//                }
//            }
//        }catch(Exception e){
//            Toast.makeText(this,"Exception "+e,Toast.LENGTH_LONG).show();
//        }
//    }

    public void signin(View view){
        EditText e1 = (EditText) findViewById(R.id.username);
        EditText e2 = (EditText) findViewById(R.id.password);
        String user,pass;
        user = e1.getText().toString();
        pass = e2.getText().toString();
        Cursor res = db.getAllPatientData();
        boolean login = false;
        while(res.moveToNext()){
            if(user.equals(res.getString(4)) && pass.equals(res.getString(5))){
                PatientHome.user = user;
                PatientHome.pass = pass;
                login = true;
                Intent intent = new Intent(".PatientHome");
                startActivity(intent);
            }
        }
        if(login)
            Toast.makeText(this,"Logged In",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Enter Correct Username and Password",Toast.LENGTH_SHORT).show();
    }

    public void check(View view){
        Cursor res = db.getAllPatientData();
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Name: "+res.getString(0)+"\n");
            buffer.append("Age: "+res.getString(1)+"\n");
            buffer.append("Sex: "+res.getString(2)+"\n");
            buffer.append("Mobile: "+res.getString(3)+"\n");
            buffer.append("Username: "+res.getString(4)+"\n");
            buffer.append("Password: "+res.getString(5)+"\n");
            buffer.append("Appointment: "+res.getString(6)+"\n\n");
        }
        showMessage("Data",buffer.toString());
    }

    public void signUp(View view){
        Intent intent = new Intent(".PatientSignUp");
        startActivity(intent);
        Toast.makeText(this,"Entering Sign up",Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String title,String Messages){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messages);
        builder.show();
    }
}