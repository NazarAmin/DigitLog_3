package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class DataEntry extends AppCompatActivity {
    LinearLayout status, handover, sheet1, sheet2, sheet9, sheet10;
    TextView engine_pic;
    String actual_user;
    Dialog dialog;
    String engine = GlobalClass.engine_number;
    String arr[] = {"Nazar Amin", "Khalid Abbadi", "Tarig Eljack", "Nasreldein Elzain"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);



        dialog = new Dialog(DataEntry.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataEntry.this.finishAffinity();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet9 = (LinearLayout) findViewById(R.id.sheet9);
        sheet10 = (LinearLayout) findViewById(R.id.sheet10);
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
                        if (actual_user.equals(GlobalClass.actual_user_name) | Arrays.asList(arr).contains(GlobalClass.actual_user_name)) {


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
                                if (actual_user.equals(GlobalClass.actual_user_name) | Arrays.asList(arr).contains(GlobalClass.actual_user_name)) {
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

                                    if ((engine.equals("Engine_3")) | (engine.equals("Engine_6"))| (engine.equals("Engine_9"))) {
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
                                if (actual_user.equals(GlobalClass.actual_user_name) | Arrays.asList(arr).contains(GlobalClass.actual_user_name)) {
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
                        if (actual_user.equals(GlobalClass.actual_user_name) | Arrays.asList(arr).contains(GlobalClass.actual_user_name)) {
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

        sheet10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        actual_user = dataSnapshot.getValue(String.class);
                        //GlobalClass.current_engine_focal = actual_user;
                        if (actual_user.equals(GlobalClass.actual_user_name) | Arrays.asList(arr).contains(GlobalClass.actual_user_name)) {
                            startActivity(new Intent(DataEntry.this, MidNight.class));
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

    public void go_home(View view) {
        startActivity(new Intent(DataEntry.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();

    }


}
