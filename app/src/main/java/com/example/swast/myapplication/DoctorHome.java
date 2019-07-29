package com.example.swast.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DoctorHome extends AppCompatActivity {

    public static String user;
    public static String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
    }

    public void details(View view){
        ddetails.user = user;
        ddetails.pass = pass;
        Intent intent = new Intent(".ddetails");
        startActivity(intent);
    }

    public void check(View view){
        Toast.makeText(this, user+" "+pass, Toast.LENGTH_LONG).show();
    }

    public void dview(View view){
        DView.user = user;
        DView.pass = pass;
        Intent intent = new Intent(".DView");
        startActivity(intent);
    }

    public void psearch(View view){
        Intent intent = new Intent(".DSearch");
        startActivity(intent);
    }

    public void logout(View view){
        Intent intent = new Intent(".homePage");
        startActivity(intent);
    }
}
