package com.example.digitlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Faults_Dash extends AppCompatActivity {
    LinearLayout faulttag, triptag, sobservationtag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faults_dash);

        faulttag = (LinearLayout) findViewById(R.id.fault_tag);
        triptag = (LinearLayout) findViewById(R.id.triptag);
        sobservationtag = (LinearLayout) findViewById(R.id.sobservationtag);
        faulttag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass.Faults_Category = "Fault";
                startActivity(new Intent(Faults_Dash.this, Dashboard_problem.class));
            }
        });

        triptag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass.Faults_Category = "Trip";

                startActivity(new Intent(Faults_Dash.this, Dashboard_problem.class));
            }
        });

        sobservationtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass.Faults_Category = "Site Observation";

                startActivity(new Intent(Faults_Dash.this, Dashboard_problem.class));
            }
        });
    }
}