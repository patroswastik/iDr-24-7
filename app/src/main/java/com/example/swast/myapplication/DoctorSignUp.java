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

public class DoctorSignUp extends AppCompatActivity {

    DoctorDataBase Mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);
        Mydb = new DoctorDataBase(this);
    }

    public void signUP(View view){
        try{
            EditText e1 = (EditText) findViewById(R.id.name);
            EditText e2 = (EditText) findViewById(R.id.age);
            EditText e3 = (EditText) findViewById(R.id.sex);
            EditText e4 = (EditText) findViewById(R.id.mobile);
            EditText e5 = (EditText) findViewById(R.id.uname);
            EditText e6 = (EditText) findViewById(R.id.password);
            EditText e7 = (EditText) findViewById(R.id.specialization);
            String pname,psex,user,pass,specialization;
            int page;
            Long pmobile;
            pname = e1.getText().toString();
            page = Integer.parseInt(e2.getText().toString());
            psex = e3.getText().toString();
            pmobile = Long.parseLong(e4.getText().toString());
            user = e5.getText().toString();
            pass = e6.getText().toString();
            specialization = e7.getText().toString();
            boolean numeric = true;
            try{
                Double num = Double.parseDouble(pname);
                Double num1 = Double.parseDouble(psex);
                Double num2 = Double.parseDouble(user);
                Double num3 = Double.parseDouble(specialization);
            }catch (Exception e){
                numeric = false;
            }
            if(!TextUtils.isEmpty(pname) && pname.length()>3 && user.length()>10 && pass.length()>=8 && !numeric ){
                boolean inserted = Mydb.insertDoctorData(pname, page, psex, pmobile, user, pass, specialization);
                if (inserted) {
                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(this, "Cannot be Stored in Database", Toast.LENGTH_LONG).show();
                Cursor res = Mydb.getAllDoctorData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name: " + res.getString(0) + "\n");
                    buffer.append("Age: " + res.getString(1) + "\n");
                    buffer.append("Sex: " + res.getString(2) + "\n");
                    buffer.append("Mobile: " + res.getString(3) + "\n");
                    buffer.append("Username: " + res.getString(4) + "\n");
                    buffer.append("Password: " + res.getString(5) + "\n");
                    buffer.append("Specialization: " + res.getString(6) + "\n\n");
                }
                showMessage("Data", buffer.toString());
                Intent intent = new Intent(".DoctorLogin");
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Type Something",Toast.LENGTH_SHORT).show();
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
