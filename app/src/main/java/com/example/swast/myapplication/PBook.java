package com.example.swast.myapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PBook extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DataBase db;
    DoctorDataBase myDb;
    public static String user,pass;
    public static String special;
    public static String names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbook);
        db = new DataBase(this);
        myDb = new DoctorDataBase(this);
        EditText e1 = (EditText) findViewById(R.id.name);
        e1.setText(names);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.specializations,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void book(View view){
        EditText e1 = (EditText) findViewById(R.id.name);
        String dname = e1.getText().toString();
        Cursor res = myDb.getAllDoctorData();
        boolean found = false;
        while(res.moveToNext()){
            if(dname.equals(res.getString(0)))
                found = true;
        }
        if(found){
            boolean isUpdate = db.updatePatient(dname,user,pass);
            if(isUpdate){
                Toast.makeText(this,"Booked",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"Not Booked",Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String title,String Messages){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messages);
        builder.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        special = adapterView.getItemAtPosition(i).toString();
        if(special.equals("Category")){

        }else{
            StringBuffer buffer = new StringBuffer();
            Cursor res = myDb.getAllDoctorData();
            while(res.moveToNext()){
                if(special.equals(res.getString(6))){
                    buffer.append("Name: "+res.getString(0)+"\n");
                    buffer.append("Experience: "+res.getString(1)+"\n");
                    buffer.append("Sex: "+res.getString(2)+"\n");
                    buffer.append("Mobile: "+res.getString(3)+"\n");
                    buffer.append("Username: "+res.getString(4)+"\n");
                    buffer.append("Specialization: "+res.getString(6)+"\n\n");
                }
            }
            showMessage("Data",buffer.toString());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
