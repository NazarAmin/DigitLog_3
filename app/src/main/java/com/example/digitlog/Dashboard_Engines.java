package com.example.digitlog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Dashboard_Engines extends AppCompatActivity {
    LinearLayout sheet1, sheet2, sheet3, sheet4, sheet5, sheet6;
    ArrayList<Date> name = new ArrayList<Date>();
    List<String> sheets_l;
    List<LinearLayout> sheets_q;
    TextView enstatus1, enstatus2,enstatus3,mw1, mw2, mw3, fo1, fo2, fo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_engines);

        enstatus1 = (TextView) findViewById(R.id.enstatus1);
        enstatus2 = (TextView) findViewById(R.id.enstatus2);
        enstatus3 = (TextView) findViewById(R.id.enstatus3);
        mw1 = (TextView) findViewById(R.id.mw1);
        mw2 = (TextView) findViewById(R.id.mw2);
        mw3 = (TextView) findViewById(R.id.mw3);
        fo1 = (TextView) findViewById(R.id.fo1);
        fo2 = (TextView) findViewById(R.id.fo2);
        fo3 = (TextView) findViewById(R.id.fo3);

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        sheet3 = (LinearLayout) findViewById(R.id.sheet3);
       // sheet4 = (LinearLayout) findViewById(R.id.sheet4);
        //sheet5 = (LinearLayout) findViewById(R.id.sheet5);
        //sheet6 = (LinearLayout) findViewById(R.id.sheet6);



        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalClass.block_number.equals("one")){
                    GlobalClass.engine_number = "Engine_1";
                }else if (GlobalClass.block_number.equals("two")){
                    GlobalClass.engine_number = "Engine_4";
                }else{
                    GlobalClass.engine_number = "Engine_7";
                }
                startActivity(new Intent(Dashboard_Engines.this, New_Engine_Dash.class));
            }
        });
        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalClass.block_number.equals("one")){
                    GlobalClass.engine_number = "Engine_2";
                }else if (GlobalClass.block_number.equals("two")){
                    GlobalClass.engine_number = "Engine_5";
                }else{
                    GlobalClass.engine_number = "Engine_8";
                }
                startActivity(new Intent(Dashboard_Engines.this, New_Engine_Dash.class));
            }
        });

        sheet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalClass.block_number.equals("one")){
                    GlobalClass.engine_number = "Engine_3";
                }else if (GlobalClass.block_number.equals("two")){
                    GlobalClass.engine_number = "Engine_6";
                }else{
                    GlobalClass.engine_number = "Engine_9";
                }
                startActivity(new Intent(Dashboard_Engines.this, New_Engine_Dash.class));
            }
        });

        coloring_layouts();
        coloring_layouts2();


    }

    protected void onStart() {
        super.onStart();

        coloring_layouts();
        coloring_layouts2();
    }

    private void coloring_layouts() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (GlobalClass.block_number.equals("one")){
            sheets_l = Arrays.asList("Engine_1","Engine_2","Engine_3");
        }else if (GlobalClass.block_number.equals("two")){
            sheets_l = Arrays.asList("Engine_4","Engine_5","Engine_6");
        }else{
            sheets_l = Arrays.asList("Engine_7","Engine_8","Engine_9");
        }
        List<TextView> status = Arrays.asList(enstatus1, enstatus2,enstatus3);
        List<TextView> mw = Arrays.asList(mw1, mw2, mw3);
        List<TextView> user = Arrays.asList(fo1, fo2, fo3);
        List<LinearLayout> sheets_q = Arrays.asList(sheet1, sheet2, sheet3); //, sheet4, sheet5, sheet6

        String engine;
        //LinearLayout Item_q = null;

        for (int counter = 0; counter < sheets_l.size(); counter++) {
            engine = sheets_l.get(counter);

            DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/Generation");
            //LinearLayout finalItem_q = Item_q;

            String finalEngine = engine;
            ref2.addListenerForSingleValueEvent(new ValueEventListener(){
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
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });
            int finalCounter1 = counter;
            String finalEngine1 = engine;
            int finalCounter = counter;
            ref2.addValueEventListener(new ValueEventListener() {
                int i = 0;
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<Float> datavals = new ArrayList<Float>();
                    if (dataSnapshot.hasChildren()) {
                        for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                            Data data = mydatasnapshot.getValue(Data.class);
                            datavals.add(data.getIp1());
                            i = i + 1;
                        }

                        DatabaseReference ref3 = firebaseDatabase.getReference("data/" + finalEngine1 + "/Status");

                        //int finalCounter;
                        ref3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                status.get(finalCounter).setText(snapshot.getValue().toString());
                                //enstatus1.setText(snapshot.getValue().toString());

                                if (snapshot.getValue().toString().equals("Normal Operation")) {
                                    sheets_q.get(finalCounter).setBackgroundColor(Color.argb((float) 0.7, 255, 102, 102));
                                } else if (snapshot.getValue().toString().equals("Standby")) {
                                    sheets_q.get(finalCounter).setBackgroundColor(Color.YELLOW);
                                } else {
                                    sheets_q.get(finalCounter).setBackgroundColor(Color.argb((float) 0.7, 30, 255, 102));
                                }

                                if (snapshot.getValue().toString().equals("Standby") |
                                        snapshot.getValue().toString().equals("Forced Shutdown") |
                                        snapshot.getValue().toString().equals("Planned Shutdown") |
                                        snapshot.getValue().toString().equals("Trip")) {
                                    mw.get(finalCounter1).setText("---- MW");
                                } else {
                                    mw.get(finalCounter1).setText(datavals.get(datavals.size() - 1).toString() + " MW");

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });


                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            DatabaseReference ref4 = firebaseDatabase.getReference("data/" + engine + "/OIC");
            int finalCounter2 = counter;
            ref4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user.get(finalCounter2).setText(snapshot.getValue().toString());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }


    private void coloring_layouts2() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (GlobalClass.block_number.equals("one")){
            sheets_l = Arrays.asList("Engine_1","Engine_2","Engine_3");
        }else if (GlobalClass.block_number.equals("two")){
            sheets_l = Arrays.asList("Engine_4","Engine_5","Engine_6");
        }else{
            sheets_l = Arrays.asList("Engine_7","Engine_8","Engine_9");
        }
        List<TextView> status = Arrays.asList(enstatus1, enstatus2,enstatus3);
        List<TextView> mw = Arrays.asList(mw1, mw2, mw3);
        List<TextView> user = Arrays.asList(fo1, fo2, fo3);
        List<LinearLayout> sheets_q = Arrays.asList(sheet1, sheet2, sheet3); //, sheet4, sheet5, sheet6

        String engine;
        //LinearLayout Item_q = null;

        for (int counter = 0; counter < sheets_l.size(); counter++) {
            engine = sheets_l.get(counter);

            DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/LogSheet20_A");
            //LinearLayout finalItem_q = Item_q;

            String finalEngine = engine;
            ref2.addListenerForSingleValueEvent(new ValueEventListener(){
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
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });
            int finalCounter1 = counter;
            String finalEngine1 = engine;
            int finalCounter = counter;
            ref2.addValueEventListener(new ValueEventListener() {
                int i = 0;
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<Float> datavals = new ArrayList<Float>();
                    if (dataSnapshot.hasChildren()) {
                        for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                            DataS1 data = mydatasnapshot.getValue(DataS1.class);
                            datavals.add(data.getIp1());
                            i = i + 1;
                        }

                        DatabaseReference ref3 = firebaseDatabase.getReference("data/" + finalEngine1 + "/Status");

                        //int finalCounter;
                        ref3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                status.get(finalCounter).setText(snapshot.getValue().toString());
                                //enstatus1.setText(snapshot.getValue().toString());

                                if (snapshot.getValue().toString().equals("Normal Operation")) {
                                    sheets_q.get(finalCounter).setBackgroundColor(Color.argb((float) 0.7, 255, 102, 102));
                                } else if (snapshot.getValue().toString().equals("Standby")) {
                                    sheets_q.get(finalCounter).setBackgroundColor(Color.YELLOW);
                                } else {
                                    sheets_q.get(finalCounter).setBackgroundColor(Color.argb((float) 0.7, 30, 255, 102));
                                }

                                if (snapshot.getValue().toString().equals("Standby") |
                                        snapshot.getValue().toString().equals("Forced Shutdown") |
                                        snapshot.getValue().toString().equals("Planned Shutdown") |
                                        snapshot.getValue().toString().equals("Trip")) {
                                    mw.get(finalCounter1).setText("---- MW");
                                } else {
                                    mw.get(finalCounter1).setText(datavals.get(datavals.size() - 1).toString() + " MW");

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });


                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            DatabaseReference ref4 = firebaseDatabase.getReference("data/" + engine + "/OIC");
            int finalCounter2 = counter;
            ref4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user.get(finalCounter2).setText(snapshot.getValue().toString());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }
}