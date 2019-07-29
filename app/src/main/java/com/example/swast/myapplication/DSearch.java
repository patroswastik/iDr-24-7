package com.example.swast.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DSearch extends AppCompatActivity {

    DataBase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsearch);
        myDb = new DataBase(this);
    }

    public void search(View view){
        EditText e1 = (EditText) findViewById(R.id.searchD);
        String name = e1.getText().toString();
        Cursor res = myDb.getAllPatientData();
        boolean present = false;
        while(res.moveToNext()){
            if(name.equals(res.getString(0))){
                showMessage("Data Found","Name: "+res.getString(0)+"\nAge: "+res.getString(1)+"\nSex: "+res.getString(2)+"\nMobile: "+res.getString(3)+"\n");
                present = true;
            }
        }
        if(!present)
            Toast.makeText(this,"No Data Found",Toast.LENGTH_LONG).show();
    }

    public void back(View view){
        Intent intent = new Intent(".DoctorHome");
        startActivity(intent);
    }

    public void showMessage(String title,String Messages){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messages);
        builder.show();
    }
}
