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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Dashboard_chart extends AppCompatActivity {
    LinearLayout sheet1, sheet2, sheet3, sheet9, sheet10, sheet6;
    Dialog dialog;
    Button date_from, date_to;
    String day, monthe, yeare, houre, minutee;
    String dateformat5;
    int ho, mi, ye, mo, da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_chart);

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

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

                startActivity(new Intent(Dashboard_chart.this, Reports_Activity.class));
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
                day = String.valueOf(dayOfMonth);
                monthe = String.valueOf(month);
                ye = year;
                mo = month;
                da = dayOfMonth;
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

}