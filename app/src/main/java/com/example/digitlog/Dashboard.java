package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
import java.util.Locale;

public class Dashboard extends AppCompatActivity {
    TextView tv3;
    String engine;
    LinearLayout prob, charts, sheet1, sheet2, sheet3, sheet4, sheet5, sheet6;
    ArrayList<Date> name = new ArrayList<Date>();
    Auth auth;
    String general_admin = GlobalClass.general_admin;
    String current_engine_focal_name;
    ImageView im_1,im_2,im_3,im_4,im_5,im_6;
    Dialog dialog;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        dialog = new Dialog(Dashboard.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dashboard.this.finishAffinity();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
      //  drawerLayout = findViewById(R.id.mydrawerlayout);
    //    actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
    //    drawerLayout.addDrawerListener(actionBarDrawerToggle);
     //   actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
  //      this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        im_1 = (ImageView) findViewById(R.id.im_1);
        im_2 = (ImageView) findViewById(R.id.im_2);
        im_3 = (ImageView) findViewById(R.id.im_3);
        im_4 = (ImageView) findViewById(R.id.im_4);
        im_5 = (ImageView) findViewById(R.id.im_5);
        im_6 = (ImageView) findViewById(R.id.im_6);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref3;
        ref3 = firebaseDatabase.getReference("data/" + engine + "/OIC");


        //tv3.setText( engine + " Dashboard");
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        coloring_layouts();
    }

    private void coloring_layouts() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        List<String> sheets_l = Arrays.asList("GT_Log", "FO", "Generator_Board","Generation", "Mark_V", "Log_Sheet_6");
        List<ImageView> images = Arrays.asList(im_1,im_2,im_3,im_4,im_5,im_6);
        List<LinearLayout> sheets_q = Arrays.asList(sheet1, sheet2, sheet3, sheet4, sheet5, sheet6);

        String Item_l;
        LinearLayout Item_q;
        int counter = 0;
        for (int i = 0; i < sheets_l.size(); i++) {
            Item_l = sheets_l.get(i);
            Item_q = sheets_q.get(i);

            DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/" + Item_l);
            LinearLayout finalItem_q = Item_q;
            int finalCounter = counter;
            ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
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

                        Long start_long, end_long;
                        Calendar cal = Calendar.getInstance();
                        int time_now = cal.get(Calendar.HOUR_OF_DAY);
                        Date now = new Date();
                        start_long = now.getTime();
                        Date s = name.get(i - 1);
                        end_long = s.getTime();



                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
                        String sl = formatter.format(s);
                        String[] first = sl.split(" ");

                        String[] hourMin = first[1].split(":");
                        int hour = Integer.parseInt(hourMin[0]);

                        long difference = Math.abs(time_now - hour);
                        long diff2 = start_long - end_long;
                        float diff3 = diff2/1000/60/60;
 try{
                        if (diff3 <= 4) {

                            images.get(finalCounter).setBackgroundResource(R.drawable.ic_baseline_done_24);
                           //finalItem_q.setBackgroundColor(Color.GREEN);
                        } else if ((diff3 > 4) && (diff3 < 5)) {
                           images.get(finalCounter).setBackgroundResource(R.drawable.waiting);
                            //finalItem_q.setBackgroundColor(Color.YELLOW);
                        } else {
                            images.get(finalCounter).setBackgroundResource(R.drawable.time_over);
                           //finalItem_q.setBackgroundColor(Color.LTGRAY);
                        }

}catch (Exception ex) {

}
                    }
                }//onDataChange

                @Override
                public void onCancelled(DatabaseError error) {

                }//onCancelled
            });
    counter+=1;
        }

    }


    public void go_home(View view) {
        startActivity(new Intent(Dashboard.this,Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();

    }

}