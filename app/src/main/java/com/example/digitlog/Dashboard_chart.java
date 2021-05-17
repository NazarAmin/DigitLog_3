package com.example.digitlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard_chart extends AppCompatActivity {
    LinearLayout sheet1, sheet2, sheet3, sheet4, sheet5, sheet6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_chart);

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        sheet3 = (LinearLayout) findViewById(R.id.sheet3);


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
                GlobalClass.sheet_number = "Generator_Board";

                startActivity(new Intent(Dashboard_chart.this, Chart_List.class));
            }
        });

    }

}