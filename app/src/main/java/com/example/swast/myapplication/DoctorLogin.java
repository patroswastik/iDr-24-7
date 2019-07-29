package com.example.swast.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorLogin extends AppCompatActivity {

    DoctorDataBase Mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        Mydb = new DoctorDataBase(this);
    }

    public void signin(View view){
        EditText e1 = (EditText) findViewById(R.id.username);
        EditText e2 = (EditText) findViewById(R.id.password);
        String user,pass;
        user = e1.getText().toString();
        pass = e2.getText().toString();
        Cursor res = Mydb.getAllDoctorData();
        boolean login = false;
        while(res.moveToNext()){
            if(user.equals(res.getString(4)) && pass.equals(res.getString(5))){
                DoctorHome.user = user;
                DoctorHome.pass = pass;
                login = true;
                Intent intent = new Intent(".DoctorHome");
                startActivity(intent);
            }
            if(login)
                Toast.makeText(this,"Logged In",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Enter Correct Username and Password",Toast.LENGTH_SHORT).show();
        }
    }

    public void signUp(View view){
        Intent intent = new Intent(".DoctorSignUp");
        startActivity(intent);
    }

    public void check(View view){
        Cursor res = Mydb.getAllDoctorData();
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Name: "+res.getString(0)+"\n");
            buffer.append("Experience: "+res.getString(1)+"\n");
            buffer.append("Sex: "+res.getString(2)+"\n");
            buffer.append("Mobile: "+res.getString(3)+"\n");
            buffer.append("Username: "+res.getString(4)+"\n");
            buffer.append("Password: "+res.getString(5)+"\n");
            buffer.append("Specialization: "+res.getString(6)+"\n\n");
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
