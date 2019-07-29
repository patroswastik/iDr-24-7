package com.example.swast.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.Long;

public class PatientSignUp extends AppCompatActivity {

    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);
        db = new DataBase(this);
    }

    public void signUP(View view){
        try{
            EditText e1 = (EditText) findViewById(R.id.name);
            EditText e2 = (EditText) findViewById(R.id.age);
            EditText e3 = (EditText) findViewById(R.id.sex);
            EditText e4 = (EditText) findViewById(R.id.mobile);
            EditText e5 = (EditText) findViewById(R.id.uname);
            EditText e6 = (EditText) findViewById(R.id.password);
            String pname,psex,user,pass;
            int page;
            Long pmobile;
            pname = e1.getText().toString();
            page = Integer.parseInt(e2.getText().toString());
            psex = e3.getText().toString();
            pmobile = Long.parseLong(e4.getText().toString());
            user = e5.getText().toString();
            pass = e6.getText().toString();
            boolean numeric = true;
            try{
                Double num = Double.parseDouble(pname);
                Double num1 = Double.parseDouble(psex);
                Double num2 = Double.parseDouble(user);
            }catch (Exception e){
                numeric = false;
            }
            if(!TextUtils.isEmpty(pname) && pname.length()>=3 && user.length()>10 && pass.length()>=8 && !numeric){
                boolean inserted = db.insertPatientData(pname,page,psex,pmobile,user,pass);
                if(inserted) {
                    Toast.makeText(PatientSignUp.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this,"Cannot be Stored in Database",Toast.LENGTH_SHORT).show();
                Cursor res = db.getAllPatientData();
                if(res.getCount()==0){
                    showMessage("Error","Nothing Found");
                    return ;
                }
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
                Intent intent = new Intent(".PatientLogin");
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Fill Information Correctly",Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this,"There is Some Error",Toast.LENGTH_SHORT).show();
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