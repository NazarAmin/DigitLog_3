package com.example.digitlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    TextView tv3;
    String engine;
    LinearLayout prob, charts, sheet1, sheet2, sheet3, sheet4, sheet5, sheet6;
    ArrayList<Date> name = new ArrayList<Date>();
    Auth auth;
    String general_admin = GlobalClass.general_admin;
    String current_engine_focal_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tv3 = (TextView) findViewById(R.id.tv1);
        //prob = (LinearLayout) findViewById(R.id.problems);
       // charts = (LinearLayout) findViewById(R.id.charts);
        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        sheet3 = (LinearLayout) findViewById(R.id.sheet3);
        sheet4 = (LinearLayout) findViewById(R.id.sheet4);
        sheet5 = (LinearLayout) findViewById(R.id.sheet5);
        sheet6 = (LinearLayout) findViewById(R.id.sheet6);
        engine = GlobalClass.engine_number;
        auth = new Auth();


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref3;
        ref3 = firebaseDatabase.getReference("data/" + engine + "/OIC");


        tv3.setText( engine + " Dashboard");
        coloring_layouts();
/**
        charts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Dashboard_chart.class));
            }
        });
        prob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Dashboard_problem.class));
            }
        });
**/
        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        current_engine_focal_name = dataSnapshot.getValue(String.class);
                        GlobalClass.current_engine_focal = current_engine_focal_name;
                        if (current_engine_focal_name.equals(GlobalClass.actual_user_name) | current_engine_focal_name.equals(general_admin)){
                            startActivity(new Intent(Dashboard.this, Sheet_1.class));
                        }else{
                             Toast.makeText(getApplicationContext(), "You are not authorized to " +
                             "enter data to " + engine, Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });

            }
        });
        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        current_engine_focal_name = dataSnapshot.getValue(String.class);
                        GlobalClass.current_engine_focal = current_engine_focal_name;
                        if (current_engine_focal_name.equals(GlobalClass.actual_user_name) | current_engine_focal_name.equals(general_admin)){
                            startActivity(new Intent(Dashboard.this, Sheet_2.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                    "enter data to " + engine, Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });            }
        });

        sheet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        current_engine_focal_name = dataSnapshot.getValue(String.class);
                        GlobalClass.current_engine_focal = current_engine_focal_name;
                        if (current_engine_focal_name.equals(GlobalClass.actual_user_name) | current_engine_focal_name.equals(general_admin)){
                            startActivity(new Intent(Dashboard.this, Sheet_3.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                    "enter data to " + engine, Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });            }
        });

        sheet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        current_engine_focal_name = dataSnapshot.getValue(String.class);
                        GlobalClass.current_engine_focal = current_engine_focal_name;
                        if (current_engine_focal_name.equals(GlobalClass.actual_user_name) | current_engine_focal_name.equals(general_admin)){
                            startActivity(new Intent(Dashboard.this, Sheet_4.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                    "enter data to " + engine, Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });            }
        });

        sheet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        current_engine_focal_name = dataSnapshot.getValue(String.class);
                        GlobalClass.current_engine_focal = current_engine_focal_name;
                        if (current_engine_focal_name.equals(GlobalClass.actual_user_name) | current_engine_focal_name.equals(general_admin)){
                            startActivity(new Intent(Dashboard.this, Sheet_5.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                    "enter data to " + engine, Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });            }
        });

        sheet6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        current_engine_focal_name = dataSnapshot.getValue(String.class);
                        GlobalClass.current_engine_focal = current_engine_focal_name;
                        if (current_engine_focal_name.equals(GlobalClass.actual_user_name) | current_engine_focal_name.equals(general_admin)){
                            startActivity(new Intent(Dashboard.this, Sheet_6.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                    "enter data to " + engine, Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        coloring_layouts();
    }

    private void coloring_layouts() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        List<String> sheets_l = Arrays.asList("GT_Log", "FO", "Generator_Board","Generation", "Mark_V", "Log_Sheet_6");
        List<LinearLayout> sheets_q = Arrays.asList(sheet1, sheet2, sheet3, sheet4, sheet5, sheet6);

        String Item_l;
        LinearLayout Item_q;
        for (int i = 0; i < sheets_l.size(); i++) {
            Item_l = sheets_l.get(i);
            Item_q = sheets_q.get(i);

            DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/" + Item_l);
            LinearLayout finalItem_q = Item_q;
            ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
                    //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                    if (dataSnapshot.exists()) {
                        name.clear();
                        int i = 0;
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            try {
                                name.add(sdf.parse(d.getKey()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            i++;
                        }

                        Calendar cal = Calendar.getInstance();
                        int time_now = cal.get(Calendar.HOUR_OF_DAY);
                        Date s = name.get(i - 1);

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
                        String sl = formatter.format(s);
                        String[] first = sl.split(" ");

                        String[] hourMin = first[1].split(":");
                        int hour = Integer.parseInt(hourMin[0]);

                        long difference = Math.abs(time_now - hour);


                        if (difference <= 1) {
                            finalItem_q.setBackgroundColor(Color.argb((float) 0.7,255,102,102));
                        } else if ((difference > 1) && (difference < 3)) {
                            finalItem_q.setBackgroundColor(Color.YELLOW);
                        } else {
                            finalItem_q.setBackgroundColor(Color.argb((float) 0.7,102,255,102));
                        }
                    }
                }//onDataChange

                @Override
                public void onCancelled(DatabaseError error) {

                }//onCancelled
            });

        }

    }


}