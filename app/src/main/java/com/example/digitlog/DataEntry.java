package com.example.digitlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DataEntry extends AppCompatActivity {
    LinearLayout status, handover, sheet1, sheet2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        status = (LinearLayout) findViewById(R.id.status);
        handover = (LinearLayout) findViewById(R.id.handover);


        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DataEntry.this, EngineStatus.class));
            }
        });

        handover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DataEntry.this, Handover_Activity.class));
            }
        });

        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DataEntry.this, Dashboard.class));
            }
        });
        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DataEntry.this, Faults_Dash.class));
            }
        });
    }
}