package com.example.digitlog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Dashboard_chart extends AppCompatActivity {
    LinearLayout sheet1, sheet2, sheet3, sheet9, sheet10, sheet6;
    Dialog dialog;
    Button date_from, date_to;
    String day, monthe, yeare, houre, minutee, actual_user, engine;
    String dateformat5;
    int ho, mi, ye, mo, da;
    DatabaseReference ref3;
    ArrayList<String> arr;
    TextView from, to;
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_chart);
        engine = GlobalClass.engine_number;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        ref3 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/OIC");

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        arr = new ArrayList<String>();

        dialog = new Dialog(Dashboard_chart.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dashboard_chart.this.finishAffinity();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        from = (TextView) findViewById(R.id.tv_from);
        to = (TextView) findViewById(R.id.tv_to);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        from.setText(formatter2.format(GlobalClass.start_date));
        to.setText(formatter2.format(GlobalClass.end_date));

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        sheet3 = (LinearLayout) findViewById(R.id.sheet3);
        sheet9 = (LinearLayout) findViewById(R.id.sheet9);
        sheet10 = (LinearLayout) findViewById(R.id.sheet10);

        sheet1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard_chart.this, Faults_List.class));
            }
        });

        sheet2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard_chart.this, Chart_List.class));
            }
        });

        sheet3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Reports are under design with management", Toast.LENGTH_SHORT).show();
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

                                DatabaseReference ref9 = firebaseDatabase.getReference(GlobalClass.database + "/Plant_1");
                                ref9.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if (dataSnapshot.hasChildren()) {
                                            for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                                                arr.add(mydatasnapshot.getValue(String.class));
                                            }
                                        }

                                        DatabaseReference ref10 = firebaseDatabase.getReference(GlobalClass.database + "/Plant_2");

                                        ref10.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChildren()) {
                                                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                                                        arr.add(mydatasnapshot.getValue(String.class));
                                                    }
                                                }

                                                if ((actual_user.equals(GlobalClass.actual_user_name)) | (arr.contains(GlobalClass.actual_user_name))) {
                                                    startActivity(new Intent(Dashboard_chart.this, Reports_Activity.class));
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "You are not authorized to " +
                                                            "download reports", Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(Dashboard_chart.this, TripsList.class));

                //startActivity(new Intent(Dashboard_chart.this, Chart_List.class));
            }
        });

        sheet10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard_chart.this, Status_List.class));

                //startActivity(new Intent(Dashboard_chart.this, Chart_List.class));
            }
        });
    }
    public void go_home(View view) {
        startActivity(new Intent(Dashboard_chart.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();

    }

    public void showDatePickerDialog(View v) {
        //DialogFragment newFragment = new DatePickerFragment();
        //newFragment.show(getSupportFragmentManager(), "datePicker");

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                yeare = String.valueOf(year);

                if (dayOfMonth<10) {
                    day = "0" + String.valueOf(dayOfMonth);
                }else{
                    day = String.valueOf(dayOfMonth);
                }

                if (month+1<10) {
                    monthe = "0" + String.valueOf(month+1);
                }else{
                    monthe = String.valueOf(month + 1);
                }

                String temp_date_2 = yeare + "_" + monthe + "_" + day + " 00:00:00";

                try {
                    GlobalClass.end_date = formatter2.parse(temp_date_2);
                } catch (ParseException e) {
                    System.out.println("Not Parsed Successfully Date line233");
                    e.printStackTrace();
                }
                to.setText(yeare + "-" + monthe + "-" + day);
            }
        };

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, mYear, mMonth, mDay);
        datePickerDialog.setTitle("Select Date:");
        datePickerDialog.show();
    }


    public void showDatePickerDialog_From(View v) {
        //DialogFragment newFragment = new DatePickerFragment();
        //newFragment.show(getSupportFragmentManager(), "datePicker");

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                yeare = String.valueOf(year);
                day = String.valueOf(dayOfMonth);
                monthe = String.valueOf(month + 1);

                String temp_date = yeare + "_" + monthe + "_" + day + " 00:00:00";

                try {
                    GlobalClass.start_date = formatter2.parse(temp_date);
                    System.out.println(temp_date);
                    System.out.println(GlobalClass.start_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                from.setText(yeare + "-" + monthe + "-" + day);
            }
        };


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, 2021, 5, 18);
        datePickerDialog.setTitle("Select Date:");
        datePickerDialog.show();
    }
}