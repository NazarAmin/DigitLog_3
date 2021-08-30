package com.example.digitlog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Blocks extends AppCompatActivity implements LifecycleObserver {
    LinearLayout sheet1, sheet2, sheet3, sheet4;
    float total, total_f, total_f_2 = 0;
    float total2 = 0;
    ImageView arr_1, arr_2;
    ProgressBar simpleProgressBar, simpleProgressBar2;
    float tot;
    float gen_1 = 0;
    float gen_2 = 0;
    String engine;
    ArrayList<Float> datavals = new ArrayList<Float>();
    BarChart chart1;

    TextView enstatus1, enstatus2,enstatus3,mw1, mw2, mw3, fo1, fo2, fo3, tot_gen, tot_fuel, tot_gen_2, fue_l2, mw12, mw23, temp1, temp2;

    DecimalFormat df = new DecimalFormat("#.#");
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @SuppressLint("SetTextI18n")


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void recreate()
    {
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            super.recreate();
        }
        else
        {
            startActivity(getIntent());
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocks);


        simpleProgressBar=(ProgressBar) findViewById(R.id.chart_1);// initiate the progress bar
        simpleProgressBar2=(ProgressBar) findViewById(R.id.chart_2); // initiate the progress bar

        temp1 = (TextView) findViewById(R.id.temp99_1);
        temp2 = (TextView) findViewById(R.id.temp99_2);

        arr_1 = (ImageView) findViewById(R.id.arr_1);
        arr_2 = (ImageView) findViewById(R.id.arr_2);

        mw1 = (TextView) findViewById(R.id.mw1);
        mw2 = (TextView) findViewById(R.id.mw2);
        mw3 = (TextView) findViewById(R.id.mw3);
        mw12 = (TextView) findViewById(R.id.mw12);
        mw23 = (TextView) findViewById(R.id.mw23);

        tot_gen = (TextView) findViewById(R.id.tot_gen);
        tot_fuel = (TextView) findViewById(R.id.tot_fue);

        tot_gen_2 = (TextView) findViewById(R.id.tot_gen2);
        fue_l2 = (TextView) findViewById(R.id.tot_fue2);

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        sheet3 = (LinearLayout) findViewById(R.id.sheet3);
        sheet4 = (LinearLayout) findViewById(R.id.sheet4);


        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass.block_number = "one";
                startActivity(new Intent(Blocks.this, Dashboard_Engines.class));
            }
        });

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
        sheet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass.block_number = "four";

                startActivity(new Intent(Blocks.this, Dashboard_Engines.class));
            }
        });

        total = 0;
        total_f = 0;
        coloring_layouts("GT_1"); // LogSheet20_A
        coloring_generation("GT_1", "Generation");

        coloring_layouts2("GT_5"); // LogSheet20_A
        coloring_generation2("GT_5", "Generation");

    }



    private void coloring_layouts(String engine) {

    DatabaseReference ref99 = firebaseDatabase.getReference("data/" + engine + "/mid_night");
    ref99.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            ArrayList<Float> datavals2 = new ArrayList();
            datavals2.clear();
            int i = 0;
            if (snapshot.hasChildren()) {
                for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                    Mid_Night_Class data = mydatasnapshot.getValue(Mid_Night_Class.class);
                    try {
                        datavals2.add(Float.parseFloat(data.getEfs()));
                    } catch (Exception e) {
                        datavals2.add((float) 0);
                    }
                    i = i + 1;
                }
                try {
                    total2 = datavals2.get(i - 1);
                    tot_fuel.setText(df.format(total2));
                } catch (Exception ex) {
                    tot_fuel.setText("___");
                }
                if (engine.equals("GT_1")) {
                    coloring_layouts("GT_2");
                } else if (engine.equals("GT_2")) {
                    coloring_layouts("ST_1");
                }
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    });
}

public void coloring_generation(String engine, String sheet) {
    DatabaseReference ref3 = firebaseDatabase.getReference("data/" + engine + "/Status");

    //int finalCounter;
    ref3.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {


            String status = snapshot.getValue().toString();

            DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/" + sheet);
            //LinearLayout finalItem_q = Item_q;
            ref2.addValueEventListener(new ValueEventListener() {
                int i = 0;

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    datavals.clear();
                    if (dataSnapshot.hasChildren()) {
                        for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                            if (engine.substring(0, 2).equals("ST")) {
                                DataS1 data = mydatasnapshot.getValue(DataS1.class);
                                datavals.add(data.getIp1());
                                i = i + 1;
                            } else {
                                Data data = mydatasnapshot.getValue(Data.class);
                                datavals.add(data.getIp1());
                                i = i + 1;
                            }
                        }
                        if (status.equals("Normal Operation")) {
                            try {
                                total = total + datavals.get(i - 1);
                            } catch (Exception e) {
                            }
                        }
                    }

                    if (engine.equals("GT_1")) {
                        coloring_generation("GT_2", "Generation");
                    } else if (engine.equals("GT_2")) {
                        coloring_generation("ST_1", "LogSheet20_A");
                    } else if (engine.equals("ST_1")){
                        mw1.setText(String.valueOf(total));
                        coloring_generation("GT_3", "Generation");
                    } else if (engine.equals("GT_3")) {
                        coloring_generation("GT_4", "Generation");
                    } else if (engine.equals("GT_4")){
                        coloring_generation("ST_2", "LogSheet20_A");
                    } else if (engine.equals("ST_2")) {
                        mw2.setText(String.valueOf(total - Float.parseFloat(mw1.getText().toString())));
                        tot_gen.setText(String.valueOf(total));
                        simpleProgressBar.setProgress((int) total);
                        temp1.setText(String.valueOf(df.format(total/240 * 100)) + " %   of");
                    }


/**
                    ArrayList<BarEntry> visitors = new ArrayList<>();
                    visitors.add(new BarEntry(0, total));
                    BarDataSet barDataSet = new BarDataSet(visitors, "Generation");
                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    BarData barData = new BarData(barDataSet);
                    chart1.setFitBars(true);
                    chart1.setData(barData);
                    chart1.animateY(2000);
**/
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

    private void coloring_layouts2(String engine) {

        DatabaseReference ref99 = firebaseDatabase.getReference("data/" + engine + "/mid_night");
        ref99.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Float> datavals2 = new ArrayList();
                datavals2.clear();
                int i = 0;
                if (snapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                        Mid_Night_Class data = mydatasnapshot.getValue(Mid_Night_Class.class);
                        try {
                            datavals2.add(Float.parseFloat(data.getEfs()));
                        } catch (Exception e) {
                            datavals2.add((float) 0);
                        }
                        i = i + 1;
                    }
                    try {
                        total_f_2 = datavals2.get(i - 1);
                        fue_l2.setText(df.format(total_f_2));
                    } catch (Exception ex) {
                        fue_l2.setText("___");
                    }
                    if (engine.equals("GT_5")) {
                        coloring_layouts2("GT_6");
                    } else if (engine.equals("GT_6")) {
                        coloring_layouts2("ST_3");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void coloring_generation2(String engine, String sheet) {
        DatabaseReference ref3 = firebaseDatabase.getReference("data/" + engine + "/Status");

        //int finalCounter;
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String status = snapshot.getValue().toString();
                DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/" + sheet);
                //LinearLayout finalItem_q = Item_q;
                ref2.addValueEventListener(new ValueEventListener() {
                    int i = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        datavals.clear();
                        if (dataSnapshot.hasChildren()) {
                            for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                                if (engine.substring(0, 2).equals("ST")) {
                                    DataS1 data = mydatasnapshot.getValue(DataS1.class);
                                    datavals.add(data.getIp1());
                                    i = i + 1;
                                } else {
                                    Data data = mydatasnapshot.getValue(Data.class);
                                    datavals.add(data.getIp1());
                                    i = i + 1;
                                }
                            }
                            if (status.equals("Normal Operation")) {
                                try {
                                    total_f = total_f + datavals.get(i - 1);
                                } catch (Exception e) {
                                }
                            }
                        }

                        if (engine.equals("GT_5")) {
                            coloring_generation2("GT_6", "Generation");
                        } else if (engine.equals("GT_6")) {
                            coloring_generation2("ST_3", "LogSheet20_A");
                        } else if (engine.equals("ST_3")){
                            mw12.setText(String.valueOf(total_f));
                            coloring_generation2("GT_7", "Generation");
                        } else if (engine.equals("GT_7")) {
                            coloring_generation2("GT_8", "Generation");
                        } else if (engine.equals("GT_8")){
                            coloring_generation2("ST_4", "LogSheet20_A");
                        } else if (engine.equals("ST_4")) {
                            mw23.setText(String.valueOf(total_f - Float.parseFloat(mw12.getText().toString())));
                            tot_gen_2.setText(String.valueOf(total_f));
                            simpleProgressBar2.setProgress((int) total_f);
                            temp2.setText(String.valueOf(df.format(total_f/240 * 100)) + " %   of");
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

    }

