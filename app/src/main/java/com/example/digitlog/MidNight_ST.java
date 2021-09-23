package com.example.digitlog;

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

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MidNight_ST extends AppCompatActivity {
    Dialog dialog;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText generation_MN, generation_MV, dem, starts;
    Button save;
    String engine;
    Mid_Night_Class_ST mid_night_class;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
    String currentdateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_night_st);

        radioGroup = (RadioGroup) findViewById(R.id.rg);
        save = (Button) findViewById(R.id.sub_btn);

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        dialog = new Dialog(MidNight_ST.this);
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

        generation_MN = (EditText) findViewById(R.id.generation_MN);
        generation_MV = (EditText) findViewById(R.id.generation_MV);
        starts = (EditText) findViewById(R.id.starts);
        dem = (EditText) findViewById(R.id.dem);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    public void save_function(){


        engine = GlobalClass.engine_number;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/mid_night");

        mid_night_class = new Mid_Night_Class_ST(generation_MN.getText().toString(),
                starts.getText().toString(),
                generation_MV.getText().toString(),
                dem.getText().toString());


        ref2.child(sdf.format(new Date()).trim()).setValue(mid_night_class);
        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void go_home(View view) {
        startActivity(new Intent(MidNight_ST.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();

    }
}