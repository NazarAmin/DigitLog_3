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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DataEntry extends AppCompatActivity {
    LinearLayout status, handover, sheet1, sheet2, sheet9, sheet10;
    TextView engine_pic, shift;
    String actual_user;
    Dialog dialog;
    String engine = GlobalClass.engine_number;
    ArrayList<Date> name = new ArrayList<java.util.Date>();
    ArrayList<String> arr; //= {"Nazar Amin", "Khalid Abbadi", "Tarig Eljack", "Nasreldein Elzain", "Imad Hussein"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        arr = new ArrayList<String>();


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

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
        shift = (TextView) findViewById(R.id.shift);


        DatabaseReference ref3, ref99;
        ref3 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/OIC");

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                engine_pic.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//////////////////////////////////////////////////////////////////
        ref99 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/OIC_History");

        ref99.addValueEventListener(new ValueEventListener() {
            int i = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
                if (snapshot.hasChildren()) {
                    int i = 0;
                    for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                        Handover_c data = mydatasnapshot.getValue(Handover_c.class);
                        shift.setText(data.getShift());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
///////////////////////////////////////////////////////////////////////////
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.clear();
                ref3.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        actual_user = dataSnapshot.getValue(String.class);
                        //GlobalClass.current_engine_focal = actual_user;

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
                                if (actual_user.equals(GlobalClass.actual_user_name) | arr.contains(GlobalClass.actual_user_name)) {


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

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
                handover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arr.clear();
                        ref3.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                actual_user = dataSnapshot.getValue(String.class);
                                //GlobalClass.current_engine_focal = actual_user;

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
                                        if (actual_user.equals(GlobalClass.actual_user_name) | arr.contains(GlobalClass.actual_user_name)) {
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
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                });

                sheet1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arr.clear();
                                    if ((engine.equals("ST_1")) | (engine.equals("ST_2"))| (engine.equals("ST_3"))| (engine.equals("ST_4"))) {
                                        startActivity(new Intent(DataEntry.this, DashboardST.class));
                                    } else {
                                        startActivity(new Intent(DataEntry.this, Dashboard.class));
                                    }
                                }

                });
                sheet2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arr.clear();
                        ref3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                actual_user = dataSnapshot.getValue(String.class);



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
                                        if (actual_user.equals(GlobalClass.actual_user_name) | arr.contains(GlobalClass.actual_user_name)) {
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

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                });


        sheet9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.clear();
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        actual_user = dataSnapshot.getValue(String.class);

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
                                if (actual_user.equals(GlobalClass.actual_user_name) | arr.contains(GlobalClass.actual_user_name)) {
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

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        sheet10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.clear();
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        actual_user = dataSnapshot.getValue(String.class);

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
                                //GlobalClass.current_engine_focal = actual_user;
                                if ((actual_user.equals(GlobalClass.actual_user_name) ) | (arr.contains(GlobalClass.actual_user_name))) {

                                    if ((engine.equals("ST_1")) | (engine.equals("ST_2"))| (engine.equals("ST_3"))| (engine.equals("ST_4"))) {
                                        startActivity(new Intent(DataEntry.this, MidNight_ST.class));
                                    } else {
                                        startActivity(new Intent(DataEntry.this, MidNight.class));
                                    }

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
