package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class New_Engine_Dash extends AppCompatActivity {
    LinearLayout sheet1, sheet2, sheet5, sheet20;
    FirebaseDatabase firebaseDatabase;
    String engine;
    ImageView home;
    Dialog dialog;
    ArrayList<String> arr;
    String current_user;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__engine__dash);

        firebaseDatabase = FirebaseDatabase.getInstance();

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        arr = new ArrayList<String>();

        dialog = new Dialog(New_Engine_Dash.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                New_Engine_Dash.this.finishAffinity();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //engine = GlobalClass.engine_number;
        //current_user = GlobalClass.actual_user_name;

        //DatabaseReference ref3;
        //ref3 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/OIC");

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        sheet5 = (LinearLayout) findViewById(R.id.sheet5);
        sheet20 = (LinearLayout) findViewById(R.id.sheet20);

        if ((GlobalClass.engine_number.equals("ST_1")) |(GlobalClass.engine_number.equals("ST_2")) |
                (GlobalClass.engine_number.equals("ST_3")) |(GlobalClass.engine_number.equals("ST_4"))){
            sheet20.setVisibility(View.INVISIBLE);
        }

        home = (ImageView) findViewById(R.id.home_image);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(New_Engine_Dash.this, Blocks.class));
            }
        });

        sheet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arr.clear();
                DatabaseReference ref9 = firebaseDatabase.getReference(GlobalClass.database + "/Admins");
                ref9.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()) {
                            for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                                //Admins admin = mydatasnapshot.getValue(Admins.class);
                                arr.add(mydatasnapshot.getValue(String.class));
                            }

                        }
                        if (arr.contains(GlobalClass.actual_user_name)) {

                            startActivity(new Intent(New_Engine_Dash.this, Correction_Base.class));
                        } else {

                            startActivity(new Intent(New_Engine_Dash.this, Correction_Base2.class));

                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(New_Engine_Dash.this, Dashboard_chart.class));
            }
        });

        sheet20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(New_Engine_Dash.this, Troubleshooting.class));
            }
        });

        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(New_Engine_Dash.this, DataEntry.class));

                    }
                });



            }
            public void go_home(){
                startActivity(new Intent(New_Engine_Dash.this, Blocks.class));
            }


    public void go_out(View view) {
        dialog.show();

    }

    }

