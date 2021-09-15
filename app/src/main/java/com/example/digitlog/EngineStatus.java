package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EngineStatus extends AppCompatActivity {
Post post;
RadioGroup radioGroup;
RadioButton radioButton;
Button update;
String engine, user;
String datetime;
EditText esdescription;
E_Status e_status;
Dialog dialog, dialog2;
String Current_Status, radioButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engine_status);


        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        dialog2 = new Dialog(EngineStatus.this);
        dialog2.setContentView(R.layout.custom_dialoge_feedback2);
        dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok2 = dialog2.findViewById(R.id.save);
        Button cancel2 = dialog2.findViewById(R.id.cancel);
        ok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EngineStatus.this.finishAffinity();
            }
        });
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        update = (Button) findViewById(R.id.update);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        esdescription = (EditText) findViewById(R.id.esdescription);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference ref3 = firebaseDatabase.getReference("data2/" + GlobalClass.engine_number + "/Status");
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              Current_Status = snapshot.getValue().toString();
                if(Current_Status.equals("Normal Operation")){
                    radioGroup.check(R.id.no);
                }else if(Current_Status.equals("Forced Shutdown")){
                    radioGroup.check(R.id.fs);
                }else if(Current_Status.equals("Planned Shutdown")){
                    radioGroup.check(R.id.ps);
                }else{
                    radioGroup.check(R.id.sb);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /**DatabaseReference ref3 = firebaseDatabase.getReference("data2/" + finalEngine1 + "/Status");

        //int finalCounter;
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                status.get(finalCounter).setText(snapshot.getValue().toString());

        */


        dialog = new Dialog(EngineStatus.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save_function();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        e_status = new E_Status();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

            }
        });

    }

    private void save_function() {

        int selectedId=radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
        datetime = sdf.format(new Date());

        engine = GlobalClass.engine_number;
        user = GlobalClass.actual_user_name;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2, ref3;
        ref2 = firebaseDatabase.getReference("data2/" + engine + "/Status_History/" + datetime);
        ref3 = firebaseDatabase.getReference("data2/" + engine + "/Status");
        if (esdescription.getText().toString().isEmpty()){
            Toast.makeText(EngineStatus.this,
                    "Please enter the description",
                    Toast.LENGTH_LONG).show();
        }else {
            e_status = new E_Status(radioButton.getText().toString(), esdescription.getText().toString(), user,datetime );
            ref2.setValue(e_status);
            ref3.setValue(radioButton.getText().toString());
            Toast.makeText(EngineStatus.this,
                    "Engine Status has changed to " + radioButton.getText().toString(),
                    Toast.LENGTH_LONG).show();

            dialog.dismiss();
            this.finish();
        }
    }

    public void go_home(View view) {
        startActivity(new Intent(EngineStatus.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();

    }
}