package com.example.swast.myapplication;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EDdetails extends AppCompatActivity {

    DoctorDataBase db;
    public static String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eddetails);
        db = new DoctorDataBase(this);
    }

    public void check(View view){
        Toast.makeText(this,user+" "+pass,Toast.LENGTH_LONG).show();
    }

    public void save(View v){
        Long pmobile;
        EditText e4 = (EditText) findViewById(R.id.mobile);
        EditText e5 = (EditText) findViewById(R.id.pass);
        String password = e5.getText().toString();
        pmobile = Long.parseLong(e4.getText().toString());
        if(TextUtils.isEmpty(password)){
            if(pmobile == null)
                Toast.makeText(this,"Please Enter Something to update",Toast.LENGTH_SHORT).show();
            else{
                boolean isUpdate = db.updateContactDoctor(pmobile,user,pass);
                if(isUpdate)
                    Toast.makeText(this,"Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this,"Not Updated",Toast.LENGTH_LONG).show();
            }
        }
        else{
            if(pmobile == null){
                boolean isUpdate = db.updatePasswordDoctor(password,user,pass);
                if(isUpdate)
                    Toast.makeText(this,"Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this,"Not Updated",Toast.LENGTH_LONG).show();
            }
            else{
                boolean contact = db.updateContactDoctor(pmobile,user,pass);
                boolean passwords = db.updatePasswordDoctor(password,user,pass);
                if(contact && passwords)
                    Toast.makeText(this,"Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this,"Not Updated",Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(".DoctorLogin");
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
