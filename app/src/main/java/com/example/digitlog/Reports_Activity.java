package com.example.digitlog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Reports_Activity extends AppCompatActivity {

    LinearLayout sheet1, sheet2, sheet3, sheet4, sheet5, sheet6;
    Dialog dialog;
    String engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_);
        engine = GlobalClass.engine_number;
        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        dialog = new Dialog(Reports_Activity.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Reports_Activity.this.finishAffinity();
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
        sheet4 = (LinearLayout) findViewById(R.id.sheet4);
        sheet5 = (LinearLayout) findViewById(R.id.sheet5);
        sheet6 = (LinearLayout) findViewById(R.id.sheet6);

        sheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reports_Activity.this, Excel_Export.class));
            }
        });

        sheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reports_Activity.this, Operator_Handover.class));
            }
        });

        sheet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reports_Activity.this, Faults_Reports.class));
            }
        });

        sheet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reports_Activity.this, Trips_Reports.class));
            }
        });

        sheet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reports_Activity.this, Status_Reports.class));
            }
        });
        sheet6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((engine.equals("ST_1")) | (engine.equals("ST_2"))| (engine.equals("ST_3"))| (engine.equals("ST_4"))) {
                    startActivity(new Intent(Reports_Activity.this, Mid_Night_Report_ST.class));
                } else {
                    startActivity(new Intent(Reports_Activity.this, Mid_Night_Report.class));
                }

            }
        });
    }

    public void go_home(View view) {
        startActivity(new Intent(Reports_Activity.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();

    }
}