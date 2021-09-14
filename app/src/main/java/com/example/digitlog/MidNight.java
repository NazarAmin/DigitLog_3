package com.example.digitlog;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MidNight extends AppCompatActivity {
    Dialog dialog;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText generation_MN, fuel_MN, hp_MN, lp_MN, df_MN, generation_MV, fuel_MV, hp_efs;
    Button save;
    String engine;
    Mid_Night_Class mid_night_class;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
    String currentdateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_night);

        radioGroup = (RadioGroup) findViewById(R.id.rg);
        save = (Button) findViewById(R.id.sub_btn);

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        dialog = new Dialog(MidNight.this);
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
        fuel_MV = (EditText) findViewById(R.id.fuel_MV);
        //hp_efs = (EditText) findViewById(R.id.hp_efs);

        fuel_MN = (EditText) findViewById(R.id.fuel_MN);
        hp_MN = (EditText) findViewById(R.id.hp_MN);
        lp_MN = (EditText) findViewById(R.id.lp_MN);
        df_MN = (EditText) findViewById(R.id.df_MN);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    public void save_function(){

        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        engine = GlobalClass.engine_number;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/mid_night");

        mid_night_class = new Mid_Night_Class(generation_MN.getText().toString(),
                fuel_MN.getText().toString(),
                hp_MN.getText().toString(),
                lp_MN.getText().toString(),
                df_MN.getText().toString(),
                radioButton.getText().toString(),
                generation_MV.getText().toString(),
                fuel_MV.getText().toString());
               // hp_efs.getText().toString());

        ref2.child(sdf.format(new Date()).trim()).setValue(mid_night_class);
        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void go_home(View view) {
        startActivity(new Intent(MidNight.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();

    }
}