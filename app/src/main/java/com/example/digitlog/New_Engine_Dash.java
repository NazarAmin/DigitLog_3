package com.example.digitlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class New_Engine_Dash extends AppCompatActivity {
    LinearLayout sheet1, sheet2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__engine__dash);

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);

        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(New_Engine_Dash.this, Dashboard_chart.class));
            }
        });
        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(New_Engine_Dash.this, DataEntry.class));
            }
        });
    }
}