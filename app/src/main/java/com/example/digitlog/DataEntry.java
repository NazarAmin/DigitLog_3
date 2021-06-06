package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataEntry extends AppCompatActivity {
    LinearLayout status, handover, sheet1, sheet2;
    TextView engine_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        sheet1 = (LinearLayout) findViewById(R.id.sheet1);
        sheet2 = (LinearLayout) findViewById(R.id.sheet2);
        status = (LinearLayout) findViewById(R.id.status);
        handover = (LinearLayout) findViewById(R.id.handover);
        engine_pic = (TextView) findViewById(R.id.engine_pic);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference ref2, ref3;

        ref3 = firebaseDatabase.getReference("data/" + GlobalClass.engine_number + "/OIC");
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                engine_pic.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                if ((GlobalClass.engine_number.equals("Engine_3")) | (GlobalClass.engine_number.equals("Engine_6"))){
                    startActivity(new Intent(DataEntry.this, DashboardST.class));
                }else{
                startActivity(new Intent(DataEntry.this, Dashboard.class));
            }
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

