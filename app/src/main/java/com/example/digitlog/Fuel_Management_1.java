package com.example.digitlog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fuel_Management_1 extends AppCompatActivity {

    EditText consum, krc, stock;
    Button sub_btn;
    Fuel_Mgt fuel_mgt;
    Dialog dialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel__management_1);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/Plant_1_Fuel");

        dialog2 = new Dialog(Fuel_Management_1.this);


        dialog2.setContentView(R.layout.custom_dialoge_feedback2);
        dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok2 = dialog2.findViewById(R.id.save);
        Button cancel2 = dialog2.findViewById(R.id.cancel);
        ok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fuel_Management_1.this.finishAffinity();
            }
        });
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });



        fuel_mgt = new Fuel_Mgt();

        consum = (EditText) findViewById(R.id.consum);
        krc = (EditText) findViewById(R.id.krc);
        stock = (EditText) findViewById(R.id.stock);

        sub_btn = (Button) findViewById(R.id.sub_btn);

        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuel_mgt = new Fuel_Mgt(krc.getText().toString(),consum.getText().toString(),stock.getText().toString(), GlobalClass.actual_user_name);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
                ref2.child(sdf.format(new Date()).trim()).setValue(fuel_mgt);
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void go_home(View view) {
        startActivity(new Intent(Fuel_Management_1.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog2.show();

    }
}