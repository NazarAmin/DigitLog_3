package com.example.digitlog;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Dialog;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.util.Log;
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

public class DashboardST extends AppCompatActivity {
    TextView tv3;
    String engine;
    LinearLayout sheet1, sheet2;
    ArrayList<Date> name = new ArrayList<Date>();
    String general_admin = GlobalClass.general_admin;
    String current_engine_focal_name;
    ImageView sim_1,sim_2;
    Dialog dialog;
    String actual_user;
    String arr[] = {"Nazar Amin", "Khalid Abbadi", "Tarig Eljack", "Nasreldein Elzain"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_s_t);
        dialog = new Dialog(DashboardST.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback2);

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DashboardST.this.finishAffinity();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        tv3 = (TextView) findViewById(R.id.tv1);
        //prob = (LinearLayout) findViewById(R.id.problems);
        // charts = (LinearLayout) findViewById(R.id.charts);
        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        engine = GlobalClass.engine_number;

        sim_1 = (ImageView) findViewById(R.id.sim_1);
        sim_2 = (ImageView) findViewById(R.id.sim_2);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref3;
        ref3 = firebaseDatabase.getReference("data2/" + engine + "/OIC");

      //  tv3.setText( engine + " Dashboard");
        coloring_layouts();

        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference ref3;
                ref3 = firebaseDatabase.getReference("data2/" + engine + "/OIC");

                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        actual_user = dataSnapshot.getValue(String.class);
                        //GlobalClass.current_engine_focal = actual_user;
                        if (actual_user.equals(GlobalClass.actual_user_name) | Arrays.asList(arr).contains(GlobalClass.actual_user_name)){


                    //    current_engine_focal_name = dataSnapshot.getValue(String.class);
                     //   GlobalClass.current_engine_focal = current_engine_focal_name;
                     //   if (current_engine_focal_name.equals(GlobalClass.actual_user_name) | current_engine_focal_name.equals(general_admin)){
                            startActivity(new Intent(DashboardST.this, Sheet7.class));
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
        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        actual_user = dataSnapshot.getValue(String.class);
                        //GlobalClass.current_engine_focal = actual_user;
                        if (actual_user.equals(GlobalClass.actual_user_name) | Arrays.asList(arr).contains(GlobalClass.actual_user_name)){

                      //  current_engine_focal_name = dataSnapshot.getValue(String.class);
                    //    GlobalClass.current_engine_focal = current_engine_focal_name;
                   //     if (current_engine_focal_name.equals(GlobalClass.actual_user_name) | current_engine_focal_name.equals(general_admin)){
                            startActivity(new Intent(DashboardST.this, Sheet8.class));
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
        List<String> sheets_l = Arrays.asList("LogSheet20_A", "LogSheet20_B");
        List<ImageView> images = Arrays.asList(sim_1,sim_2);

        List<LinearLayout> sheets_q = Arrays.asList(sheet1, sheet2);

        String Item_l;
        LinearLayout Item_q;
        for (int i = 0; i < sheets_l.size(); i++) {
            Item_l = sheets_l.get(i);
            Item_q = sheets_q.get(i);

            DatabaseReference ref2 = firebaseDatabase.getReference("data2/" + engine + "/" + Item_l);
            LinearLayout finalItem_q = Item_q;
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
                        System.out.println("Current Difference   ############: " + diff3);
                        if (diff3 <= 4) {
                            //images.get(i).setBackgroundResource(R.drawable.ic_baseline_done_24);

                            finalItem_q.setBackgroundColor(Color.GREEN);
                        } else if ((diff3 > 4) && (diff3 < 5)) {
                            //images.get(i).setBackgroundResource(R.drawable.warning_dashboard);

                             finalItem_q.setBackgroundColor(Color.YELLOW);
                        } else {
                           // images.get(i).setBackgroundResource(R.drawable.ic_baseline_do_disturb_24);

                            finalItem_q.setBackgroundColor(Color.LTGRAY);
                        }
                    }
                }//onDataChange

                @Override
                public void onCancelled(DatabaseError error) {

                }//onCancelled
            });

        }

    }
    public void go_home(View view) {
        startActivity(new Intent(DashboardST.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();

    }
}