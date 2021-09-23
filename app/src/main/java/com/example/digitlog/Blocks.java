package com.example.digitlog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Blocks extends AppCompatActivity implements LifecycleObserver {
    LinearLayout sheet1, sheet2, sheet3, sheet4;
    float total, total_f, total_f_2 = 0;
    float total2 = 0;
    ImageView arr_1, arr_2, im1234, im123;
    ProgressBar simpleProgressBar, simpleProgressBar2, pbblocks;
    float tot;
    float gen_1 = 0;
    float gen_2 = 0;
    String engine;
    ArrayList<Float> datavals = new ArrayList<Float>();
    BarChart chart1;
    ArrayList<String> arr;


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

        pbblocks = (ProgressBar) findViewById(R.id.pbblocks);
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

        im1234 = (ImageView) findViewById(R.id.im1234);
        im123 = (ImageView) findViewById(R.id.im123);

        arr = new ArrayList<String>();

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

        coloring_layouts(); // LogSheet20_A
        coloring_generation("GT_1", "Generation");
        coloring_layouts2(); // LogSheet20_A
        coloring_generation2("GT_5", "Generation");

        im1234.setOnClickListener(new View.OnClickListener() {
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

                        DatabaseReference ref9 = firebaseDatabase.getReference(GlobalClass.database + "/Plant_1");
                        ref9.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (dataSnapshot.hasChildren()) {
                                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                                        //Admins admin = mydatasnapshot.getValue(Admins.class);
                                        arr.add(mydatasnapshot.getValue(String.class));
                                    }

                                    if (arr.contains(GlobalClass.actual_user_name)) {

                                        startActivity(new Intent(Blocks.this, Fuel_Management_1.class));
                                    } else {
                                       // Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                             //   "enter data to " + engine, Toast.LENGTH_LONG).show();
                                    }

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

        im123.setOnClickListener(new View.OnClickListener() {
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

                        DatabaseReference ref9 = firebaseDatabase.getReference(GlobalClass.database + "/Plant_2");
                        ref9.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (dataSnapshot.hasChildren()) {
                                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                                        //Admins admin = mydatasnapshot.getValue(Admins.class);
                                        arr.add(mydatasnapshot.getValue(String.class));
                                    }

                                    if (arr.contains(GlobalClass.actual_user_name)) {

                                        startActivity(new Intent(Blocks.this, Fuel_Management_2.class));
                                    } else {
                                       // Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                            //    "enter data to " + engine, Toast.LENGTH_LONG).show();
                                    }

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


    private void coloring_layouts() {

    DatabaseReference ref99 = firebaseDatabase.getReference(GlobalClass.database + "/Plant_1_Fuel");
    ref99.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            ArrayList<Float> datavals2 = new ArrayList();
            datavals2.clear();
            int i = 0;
            if (snapshot.hasChildren()) {
                for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                    Fuel_Mgt data = mydatasnapshot.getValue(Fuel_Mgt.class);
                    try {
                        datavals2.add(Float.parseFloat(data.getStock()));
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

            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    });
}

public void coloring_generation(String engine, String sheet) {
    DatabaseReference ref3 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/Status");

    //int finalCounter;
    ref3.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {


            String status = snapshot.getValue().toString();

            DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/" + sheet);
            //LinearLayout finalItem_q = Item_q;
            ref2.addValueEventListener(new ValueEventListener() {
                int i = 0;

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    datavals.clear();
                    if (engine.equals("GT_1")) {
                        total = 0;
                    }
                    if (dataSnapshot.hasChildren()) {
                        for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                            if (engine.substring(0, 2).equals("ST")) {
                                DataS2 data = mydatasnapshot.getValue(DataS2.class);
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
                        coloring_generation("ST_1", "LogSheet20_B");
                    } else if (engine.equals("ST_1")){
                        mw1.setText(String.valueOf(df.format(total)));
                        coloring_generation("GT_3", "Generation");
                    } else if (engine.equals("GT_3")) {
                        coloring_generation("GT_4", "Generation");
                    } else if (engine.equals("GT_4")){
                        coloring_generation("ST_2", "LogSheet20_B");
                    } else if (engine.equals("ST_2")) {

                        try {
                            mw2.setText(df.format(total - Float.parseFloat(mw1.getText().toString())));
                        }catch (Exception e){

                            Locale EASTERN_ARABIC_NUMBERS_LOCALE = new Locale.Builder()
                                    .setLanguage("ar")
                                    .setExtension('u', "nu-arab")
                                    .build();
                            float f = 0;
                            try {
                                f = NumberFormat.getInstance(EASTERN_ARABIC_NUMBERS_LOCALE)
                                        .parse(mw1.getText().toString())
                                        .floatValue();
                            } catch (ParseException parseException) {
                                parseException.printStackTrace();
                            }


                            mw2.setText(df.format(total - f));
                        }
                        tot_gen.setText(df.format(total));
                        simpleProgressBar.setProgress((int) total);
                        temp1.setText(df.format(total/180 * 100) + " %   of");
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

    private void coloring_layouts2() {

        DatabaseReference ref99 = firebaseDatabase.getReference(GlobalClass.database + "/Plant_2_Fuel");
        ref99.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Float> datavals2 = new ArrayList();
                datavals2.clear();
                int i = 0;
                if (snapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                        Fuel_Mgt data = mydatasnapshot.getValue(Fuel_Mgt.class);
                        try {
                            datavals2.add(Float.parseFloat(data.getStock()));
                        } catch (Exception e) {
                            datavals2.add((float) 0);
                        }
                        i = i + 1;
                    }
                    try {
                        total2 = datavals2.get(i - 1);
                        fue_l2.setText(df.format(total2));
                    } catch (Exception ex) {
                        fue_l2.setText("___");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void coloring_generation2(String engine, String sheet) {
        DatabaseReference ref3 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/Status");

        //int finalCounter;
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String status = snapshot.getValue().toString();
                DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/" + sheet);
                //LinearLayout finalItem_q = Item_q;
                ref2.addValueEventListener(new ValueEventListener() {
                    int i = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (engine.equals("GT_5")) {
                            total_f = 0;
                        }
                        datavals.clear();
                        if (dataSnapshot.hasChildren()) {
                            for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                                if (engine.substring(0, 2).equals("ST")) {
                                    DataS2 data = mydatasnapshot.getValue(DataS2.class);
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
                            coloring_generation2("ST_3", "LogSheet20_B");
                        } else if (engine.equals("ST_3")){
                            mw12.setText(df.format(total_f));
                            coloring_generation2("GT_7", "Generation");
                        } else if (engine.equals("GT_7")) {
                            coloring_generation2("GT_8", "Generation");
                        } else if (engine.equals("GT_8")){
                            coloring_generation2("ST_4", "LogSheet20_B");
                        } else if (engine.equals("ST_4")) {
                            try{
                                mw23.setText(df.format(total_f - Float.parseFloat(mw12.getText().toString())));
                            }catch (Exception e){

                                Locale EASTERN_ARABIC_NUMBERS_LOCALE = new Locale.Builder()
                                        .setLanguage("ar")
                                        .setExtension('u', "nu-arab")
                                        .build();
                                float f = 0;
                                try {
                                    f = NumberFormat.getInstance(EASTERN_ARABIC_NUMBERS_LOCALE)
                                            .parse(mw12.getText().toString())
                                            .floatValue();
                                } catch (ParseException parseException) {
                                    parseException.printStackTrace();
                                }


                                mw23.setText(df.format(total_f - f));
                            }
                            tot_gen_2.setText(df.format(total_f));
                            simpleProgressBar2.setProgress((int) total_f);
                            temp2.setText(df.format(total_f/180 * 100) + " %   of");
                            pbblocks.setVisibility(View.INVISIBLE);
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

