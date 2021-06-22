package com.example.digitlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard_chart extends AppCompatActivity {
    LinearLayout sheet1, sheet2, sheet3, sheet9, sheet10, sheet6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_chart);

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

                startActivity(new Intent(Dashboard_chart.this, Excel_Export.class));
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

}