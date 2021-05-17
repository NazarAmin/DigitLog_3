package com.example.digitlog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EngineStatus extends AppCompatActivity {
Post post;
RadioGroup radioGroup;
RadioButton radioButton;
Button update;
String engine, user;
String datetime;
EditText esdescription;
    E_Status e_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engine_status);

        update = (Button) findViewById(R.id.update);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        esdescription = (EditText) findViewById(R.id.esdescription);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
        datetime = sdf.format(new Date());

        engine = GlobalClass.engine_number;
        user = GlobalClass.user_name_string;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2, ref3;
        ref2 = firebaseDatabase.getReference("data/" + engine + "/Status_History/" + datetime);
        ref3 = firebaseDatabase.getReference("data/" + engine + "/Status");

        e_status = new E_Status();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId=radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selectedId);

                e_status = new E_Status(radioButton.getText().toString(),esdescription.getText().toString(), user);
                ref2.setValue(e_status);
                ref3.setValue(radioButton.getText().toString());
                Toast.makeText(EngineStatus.this,
                        "Engine Status has changed to " + radioButton.getText().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });



    }
}