package com.example.digitlog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.Locale;

public class Dashboard_Engines extends AppCompatActivity {
    LinearLayout sheet1, sheet2, sheet3, sheet4, sheet5, sheet6;
    ImageView gt_image1, gt_image2,gt_image3;
    ArrayList<Date> name = new ArrayList<Date>();
    List<String> sheets_l;
    List<LinearLayout> sheets_q;
    TextView enstatus1, enstatus2,enstatus3,mw1, mw2, mw3, fo1, fo2, fo3, st2, gt3, gt4, ani, ani2, ani3;
    Dialog dialog;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref20;
    String engine;
    ArrayList<String> datavals = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_engines);

        gt_image1 = (ImageView) findViewById(R.id.gt_image1);
        gt_image2 = (ImageView) findViewById(R.id.gt_image2);
        gt_image3 = (ImageView) findViewById(R.id.gt_image3);

        ani = (TextView) findViewById(R.id.ani);
        ani2 = (TextView) findViewById(R.id.ani2);
        ani3 = (TextView) findViewById(R.id.ani3);


        enstatus1 = (TextView) findViewById(R.id.enstatus1);
        enstatus2 = (TextView) findViewById(R.id.enstatus2);
        enstatus3 = (TextView) findViewById(R.id.enstatus3);
        mw1 = (TextView) findViewById(R.id.mw1);
        mw2 = (TextView) findViewById(R.id.mw2);
        mw3 = (TextView) findViewById(R.id.mw3);
        fo1 = (TextView) findViewById(R.id.fo1);
        fo2 = (TextView) findViewById(R.id.fo2);
        fo3 = (TextView) findViewById(R.id.fo3);

        gt3 = (TextView) findViewById(R.id.gt3);
        gt4 = (TextView) findViewById(R.id.gt4);
        st2 = (TextView) findViewById(R.id.st2);

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        sheet3 = (LinearLayout) findViewById(R.id.sheet3);
       // sheet4 = (LinearLayout) findViewById(R.id.sheet4);
        //sheet5 = (LinearLayout) findViewById(R.id.sheet5);
        //sheet6 = (LinearLayout) findViewById(R.id.sheet6);

        TranslateAnimation animation = new TranslateAnimation(650.0f, -650.0f, 0.0f, 0.0f); // new TranslateAnimation (float fromXDelta,float toXDelta, float fromYDelta, float toYDelta)

        animation.setDuration(10000); // animation duration, change accordingly
        animation.setRepeatCount(-1); // animation repeat count
        animation.setFillAfter(false);

        try {
            if (GlobalClass.block_number.equals("one")) {
                gt3.setText("GT1");
                get_text_ani(ani, "GT_1", 0);
                gt4.setText("GT2");
                get_text_ani(ani2, "GT_2", 0);
                st2.setText("ST1");
                get_text_ani(ani3, "ST_1", 0);
            }else if (GlobalClass.block_number.equals("two")){
                gt3.setText("GT3");
                get_text_ani(ani, "GT_3", 0);
                gt4.setText("GT4");
                get_text_ani(ani2, "GT_4", 0);
                st2.setText("ST2");
                get_text_ani(ani3, "ST_2", 0);
            }else if (GlobalClass.block_number.equals("three")){
                gt3.setText("GT5");
                get_text_ani(ani, "GT_5", 0);
                gt4.setText("GT6");
                get_text_ani(ani2, "GT_6", 0);
                st2.setText("ST3");
                get_text_ani(ani3, "ST_3", 0);
            }else {
                gt3.setText("GT7");
                get_text_ani(ani, "GT_7", 0);
                gt4.setText("GT8");
                get_text_ani(ani2, "GT_8", 0);
                st2.setText("ST4");
                get_text_ani(ani3, "ST_4", 0);
            }
        }catch (Exception ex){

        }

        ani.startAnimation(animation);//your_view for which you need animation
        ani2.startAnimation(animation);//your_view for which you need animation
        ani3.startAnimation(animation);//your_view for which you need animation

        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalClass.block_number.equals("one")){
                    GlobalClass.engine_number = "GT_1";
                }else if (GlobalClass.block_number.equals("two")){
                    GlobalClass.engine_number = "GT_3";
                }else if (GlobalClass.block_number.equals("three")){
                    GlobalClass.engine_number = "GT_5";
                }else {
                    GlobalClass.engine_number = "GT_7";
                }
                startActivity(new Intent(Dashboard_Engines.this, New_Engine_Dash.class));
            }
        });
        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalClass.block_number.equals("one")){
                    GlobalClass.engine_number = "GT_2";
                }else if (GlobalClass.block_number.equals("two")){
                    GlobalClass.engine_number = "GT_4";
                }else if (GlobalClass.block_number.equals("three")){
                    GlobalClass.engine_number = "GT_6";
                }else {
                    GlobalClass.engine_number = "GT_8";
                }
                startActivity(new Intent(Dashboard_Engines.this, New_Engine_Dash.class));
            }
        });

        sheet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalClass.block_number.equals("one")){
                    GlobalClass.engine_number = "ST_1";
                }else if (GlobalClass.block_number.equals("two")){
                    GlobalClass.engine_number = "ST_2";
                }else if (GlobalClass.block_number.equals("three")){
                    GlobalClass.engine_number = "ST_3";
                }else {
                    GlobalClass.engine_number = "ST_4";
                }
                startActivity(new Intent(Dashboard_Engines.this, New_Engine_Dash.class));
            }
        });
        coloring_layouts();
        coloring_layouts2();

    }

    private void get_text_ani(TextView ani, String engine, int i) {
        datavals.clear();
        ref20 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/Status_History");
        ref20.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                        try {
                            E_Status data = mydatasnapshot.getValue(E_Status.class);
                            datavals.add(data.getDescription());
                        } catch (Exception e) {
                        }
                    }
                }
              //  try{
                System.out.println("||||||||||||||\n");

                System.out.println(datavals.get(datavals.size()-1));
                System.out.println(datavals.get(1));


                ani.setText(datavals.get(datavals.size()-1));

             //   }catch (Exception e){
                 //   ani.setText("No Error");

               // }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    protected void onStart() {
        super.onStart();

        coloring_layouts();
        coloring_layouts2();
    }

    private void coloring_layouts() {

        if (GlobalClass.block_number.equals("one")){
            sheets_l = Arrays.asList("GT_1","GT_2","ST_1");
        }else if (GlobalClass.block_number.equals("two")) {
            sheets_l = Arrays.asList("GT_3", "GT_4", "ST_2");
        }else if (GlobalClass.block_number.equals("three")) {
            sheets_l = Arrays.asList("GT_5", "GT_6", "ST_3");
        }else {
            sheets_l = Arrays.asList("GT_7", "GT_8", "ST_4");
        }
     //   }else{
  //          sheets_l = Arrays.asList("GT_5","GT_6","ST_3");
   //     }
        List<TextView> status = Arrays.asList(enstatus1, enstatus2,enstatus3);
        List<TextView> mw = Arrays.asList(mw1, mw2, mw3);
        List<TextView> user = Arrays.asList(fo1, fo2, fo3);
        List<LinearLayout> sheets_q = Arrays.asList(sheet1, sheet2, sheet3); //, sheet4, sheet5, sheet6
        List<ImageView> images = Arrays.asList(gt_image1,gt_image2,gt_image3);


        String engine;
        //LinearLayout Item_q = null;

        for (int counter = 0; counter < sheets_l.size(); counter++) {
            engine = sheets_l.get(counter);

            DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/Generation");
            //LinearLayout finalItem_q = Item_q;

            String finalEngine = engine;
            ref2.addListenerForSingleValueEvent(new ValueEventListener(){
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
                            try{
                                Data data = mydatasnapshot.getValue(Data.class);
                                datavals.add(data.getIp1());
                                i = i + 1;
                            }catch (Exception e){

                            }

                        }

                        DatabaseReference ref3 = firebaseDatabase.getReference(GlobalClass.database + "/" + finalEngine1 + "/Status");

                        //int finalCounter;
                        ref3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                status.get(finalCounter).setText(snapshot.getValue().toString());
                                //enstatus1.setText(snapshot.getValue().toString());
   //         try {


                                if (snapshot.getValue().toString().equals("Normal Operation")) {
                                    images.get(finalCounter).setBackgroundResource(R.drawable.on);

                                    //  sheets_q.get(finalCounter).setBackgroundColor(Color.GREEN);
                                } else if (snapshot.getValue().toString().equals("Reserve Shutdown")) {
                                    images.get(finalCounter).setBackgroundResource(R.drawable.standby);

                                    //  sheets_q.get(finalCounter).setBackgroundColor(Color.YELLOW);

                                } else {
                                    images.get(finalCounter).setBackgroundResource(R.drawable.off);

                                    // sheets_q.get(finalCounter).setBackgroundColor(Color.LTGRAY);
                                }


                if (snapshot.getValue().toString().equals("Reserve Shutdown") |
                        snapshot.getValue().toString().equals("Forced Shutdown") |
                        snapshot.getValue().toString().equals("Planned Shutdown")) {
                    mw.get(finalCounter1).setText("0.0 MW");
                } else {
                    mw.get(finalCounter1).setText(datavals.get(datavals.size() - 1).toString() + " MW");

                }
        //    }catch (Exception exception){

         //   }
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

            DatabaseReference ref4 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/OIC");
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
            sheets_l = Arrays.asList("GT_1","GT_2","ST_1");
        }else if (GlobalClass.block_number.equals("two")) {
            sheets_l = Arrays.asList("GT_3", "GT_4", "ST_2");
        }else if (GlobalClass.block_number.equals("three")) {
            sheets_l = Arrays.asList("GT_5", "GT_6", "ST_3");
        }else {
            sheets_l = Arrays.asList("GT_7", "GT_8", "ST_4");
        }
        List<TextView> status = Arrays.asList(enstatus1, enstatus2,enstatus3);
        List<TextView> mw = Arrays.asList(mw1, mw2, mw3);
        List<TextView> user = Arrays.asList(fo1, fo2, fo3);
        List<LinearLayout> sheets_q = Arrays.asList(sheet1, sheet2, sheet3); //, sheet4, sheet5, sheet6
        List<ImageView> images = Arrays.asList(gt_image1,gt_image2,gt_image3);


        String engine;
        //LinearLayout Item_q = null;

        for (int counter = 0; counter < sheets_l.size(); counter++) {
            engine = sheets_l.get(counter);

            DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/LogSheet20_B");
            //LinearLayout finalItem_q = Item_q;

            String finalEngine = engine;
            ref2.addListenerForSingleValueEvent(new ValueEventListener(){
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
                            DataS2 data = mydatasnapshot.getValue(DataS2.class);
                            datavals.add(data.getIp1());
                            i = i + 1;
                        }

                        DatabaseReference ref3 = firebaseDatabase.getReference(GlobalClass.database + "/" + finalEngine1 + "/Status");

                        //int finalCounter;
                        ref3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                status.get(finalCounter).setText(snapshot.getValue().toString());
                                //enstatus1.setText(snapshot.getValue().toString());

                           //      try {

                                if (snapshot.getValue().toString().equals("Normal Operation")) {
                                    images.get(finalCounter).setBackgroundResource(R.drawable.on);

                                  //  sheets_q.get(finalCounter).setBackgroundColor(Color.GREEN);
                                } else if (snapshot.getValue().toString().equals("Reserve Shutdown")) {
                                    images.get(finalCounter).setBackgroundResource(R.drawable.standby);

                                  //  sheets_q.get(finalCounter).setBackgroundColor(Color.YELLOW);

                                } else {
                                     images.get(finalCounter).setBackgroundResource(R.drawable.off);

                                   // sheets_q.get(finalCounter).setBackgroundColor(Color.LTGRAY);
                                }

                                if (snapshot.getValue().toString().equals("Reserve Shutdown") |
                                        snapshot.getValue().toString().equals("Forced Shutdown") |
                                        snapshot.getValue().toString().equals("Planned Shutdown")) {
                                    mw.get(finalCounter1).setText("0.0 MW");
                                } else {
                                    mw.get(finalCounter1).setText(datavals.get(datavals.size() - 1).toString() + " MW");

                                }
                        //    }catch (Exception exception){

                       //     }
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

            DatabaseReference ref4 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/OIC");
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