package com.example.digitlog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class New_Engine_Dash extends AppCompatActivity {
    LinearLayout sheet1, sheet2;
    FirebaseDatabase firebaseDatabase;
    String engine;
    ImageView home;
    Dialog dialog;
    String current_user;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__engine__dash);

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
        //ref3 = firebaseDatabase.getReference("data/" + engine + "/OIC");

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        home = (ImageView) findViewById(R.id.home_image);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(New_Engine_Dash.this, Blocks.class));
            }
        });
        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(New_Engine_Dash.this, Dashboard_chart.class));
            }
        });
        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(New_Engine_Dash.this, DataEntry.class));

 /**
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String current_engine_focal_name = dataSnapshot.getValue(String.class);
                        GlobalClass.current_engine_focal = current_engine_focal_name;

                        if (GlobalClass.current_engine_focal.equals(GlobalClass.actual_user_name)) {//| GlobalClass.current_engine_focal.equals(general_admin)){



                        }else{
                            Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                    "handover " + engine + " this can be done by " + GlobalClass.current_engine_focal, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
**/
                    }
                });



            }
            public void go_home(){
                startActivity(new Intent(New_Engine_Dash.this, Dashboard_Engines.class));
            }


    public void go_out(View view) {
        dialog.show();

    }

    }

