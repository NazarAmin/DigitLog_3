package com.example.digitlog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Blocks extends AppCompatActivity {
    LinearLayout sheet1, sheet2, sheet3;
    float total = 0;
    ArrayList<Float> datavals = new ArrayList<Float>();

    TextView enstatus1, enstatus2,enstatus3,mw1, mw2, mw3, fo1, fo2, fo3;

    DecimalFormat df = new DecimalFormat("#.#");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocks);



        mw1 = (TextView) findViewById(R.id.mw1);
        mw2 = (TextView) findViewById(R.id.mw2);
        mw3 = (TextView) findViewById(R.id.mw3);

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        //sheet2 = (LinearLayout) findViewById(R.id.sheet2);
       // sheet3 = (LinearLayout) findViewById(R.id.sheet3);

        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass.block_number = "one";
                startActivity(new Intent(Blocks.this, Dashboard_Engines.class));
            }
        });
      /*
        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass.block_number = "two";

                startActivity(new Intent(Blocks.this, Dashboard_Engines.class));
            }
        });

        sheet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass.block_number = "three";

                startActivity(new Intent(Blocks.this, Dashboard_Engines.class));
            }
        });

**/
       coloring_layouts("Engine_1", mw1);
       coloring_layouts("Engine_2", mw1); //,"Engine_2","Engine_3"
       coloring_layouts("Engine_3", mw1); //,"Engine_2","Engine_3"
/*
        coloring_layouts2("Engine_4", mw2); //,"Engine_2","Engine_3"
        coloring_layouts2("Engine_5", mw2); //,"Engine_2","Engine_3"
        coloring_layouts2("Engine_6", mw2); //,"Engine_2","Engine_3"

        coloring_layouts3("Engine_7", mw3); //,"Engine_2","Engine_3"
        coloring_layouts3("Engine_8", mw3); //,"Engine_2","Engine_3"
        coloring_layouts3("Engine_9", mw3); //,"Engine_2","Engine_3"
**/

        //coloring_layouts(Arrays.asList("Engine_4","Engine_5","Engine_6"), mw2);//,"Engine_5","Engine_6"
      // coloring_layouts(Arrays.asList("Engine_7","Engine_8","Engine_9"), mw3);


    }

    protected void onStart() {
        super.onStart();

       // coloring_layouts();
    }


    private void coloring_layouts(String engine, TextView mw) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/Generation");
            //LinearLayout finalItem_q = Item_q;
            ref2.addValueEventListener(new ValueEventListener() {
                int i = 0;
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    datavals.clear();
                    if (dataSnapshot.hasChildren()) {
                        for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                            Data data = mydatasnapshot.getValue(Data.class);
                            datavals.add(data.getIp1());
                            i = i + 1;
                        }
                        //noinspection UnusedAssignment
                        try {
                            total = Float.parseFloat(mw.getText().toString()) + datavals.get(i - 1);
                            mw.setText(df.format(total));
                            //mw.setText(Float.toString(total));
                        } catch (Exception e) {
                        total = datavals.get(i - 1);
                        mw.setText(df.format(total));
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

    private void coloring_layouts2(String engine, TextView mw) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/Generation");
        //LinearLayout finalItem_q = Item_q;
        ref2.addValueEventListener(new ValueEventListener() {
            int i = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datavals.clear();
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                        Data data = mydatasnapshot.getValue(Data.class);
                        datavals.add(data.getIp1());
                        i = i + 1;
                    }
                    //noinspection UnusedAssignment
                    try {
                        total = Float.parseFloat(mw.getText().toString()) + datavals.get(i - 1);
                        mw.setText(df.format(total));
                    } catch (Exception e) {
                        total = datavals.get(i - 1);
                        mw.setText(df.format(total));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void coloring_layouts3(String engine, TextView mw) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/Generation");
        //LinearLayout finalItem_q = Item_q;
        ref2.addValueEventListener(new ValueEventListener() {
            int i = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datavals.clear();
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                        Data data = mydatasnapshot.getValue(Data.class);
                        datavals.add(data.getIp1());
                        i = i + 1;
                    }
                    //noinspection UnusedAssignment
                    try {
                        total = Float.parseFloat(mw.getText().toString()) + datavals.get(i - 1);
                        mw.setText(df.format(total));
                    } catch (Exception e) {
                        total = datavals.get(i - 1);
                        mw.setText(df.format(total));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    }
