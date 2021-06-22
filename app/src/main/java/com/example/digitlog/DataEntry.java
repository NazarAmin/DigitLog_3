package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataEntry extends AppCompatActivity {
    LinearLayout status, handover, sheet1, sheet2, sheet9;
    TextView engine_pic;
    String general_admin = "Khalid Abbadi";
    String actual_user;
    String engine = GlobalClass.engine_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet9 = (LinearLayout) findViewById(R.id.sheet9);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        status = (LinearLayout) findViewById(R.id.status);
        handover = (LinearLayout) findViewById(R.id.handover);
        engine_pic = (TextView) findViewById(R.id.engine_pic);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref3;
        ref3 = firebaseDatabase.getReference("data/" + engine + "/OIC");

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                engine_pic.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref3.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        actual_user = dataSnapshot.getValue(String.class);
                        //GlobalClass.current_engine_focal = actual_user;
                        if (actual_user.equals(GlobalClass.actual_user_name) | GlobalClass.actual_user_name.equals(general_admin)) {
                            startActivity(new Intent(DataEntry.this, EngineStatus.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                    "enter data to " + engine, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
                handover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ref3.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                actual_user = dataSnapshot.getValue(String.class);
                                //GlobalClass.current_engine_focal = actual_user;
                                if (actual_user.equals(GlobalClass.actual_user_name) | GlobalClass.actual_user_name.equals(general_admin)) {
                                    startActivity(new Intent(DataEntry.this, Handover_Activity.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                            "enter data to " + engine, Toast.LENGTH_LONG).show();
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

                                    if ((engine.equals("Engine_3")) | (engine.equals("Engine_6"))) {
                                        startActivity(new Intent(DataEntry.this, DashboardST.class));
                                    } else {
                                        startActivity(new Intent(DataEntry.this, Dashboard.class));
                                    }
                                }

                });
                sheet2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ref3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                actual_user = dataSnapshot.getValue(String.class);
                                //GlobalClass.current_engine_focal = actual_user;
                                if (actual_user.equals(GlobalClass.actual_user_name) | GlobalClass.actual_user_name.equals(general_admin)) {
                                    startActivity(new Intent(DataEntry.this, Dashboard_problem.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                            "enter data to " + engine, Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                });


        sheet9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        actual_user = dataSnapshot.getValue(String.class);
                        //GlobalClass.current_engine_focal = actual_user;
                        if (actual_user.equals(GlobalClass.actual_user_name) | GlobalClass.actual_user_name.equals(general_admin)) {
                            startActivity(new Intent(DataEntry.this, Trip_Activity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                    "enter data to " + engine, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
            }

}
