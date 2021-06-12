package com.example.digitlog;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button button2, button;
    EditText etpress, ettemp, etvib, etflow, etminpress, etmaxpress;
    TextView tvdatetime, tvuser;
    Data data;
   @SuppressLint("MissingPermission")

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
       DatabaseReference ref2 = firebaseDatabase.getReference("data");

       button2 = (Button) findViewById(R.id.button2);
        button = (Button) findViewById(R.id.button);
        etpress = (EditText) findViewById(R.id.etpressure);
        ettemp = (EditText) findViewById(R.id.ettemp);
        etvib = (EditText) findViewById(R.id.etvib);
        etflow = (EditText) findViewById(R.id.etflow);
        etminpress = (EditText) findViewById(R.id.etminpress);
        etmaxpress = (EditText) findViewById(R.id.etmaxpress);
        tvdatetime = (TextView) findViewById(R.id.tvdatetime);
        tvuser = (TextView) findViewById(R.id.tvuser);

        //Work = new work();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), chart.class);
                startActivity(intent);
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
        String currentdateandTime = sdf.format(new Date());
        tvdatetime.setText(currentdateandTime);

        data = new Data();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pressure = Integer.parseInt(etpress.getText().toString().trim());
                int temperature = Integer.parseInt(ettemp.getText().toString().trim());
                int flow = Integer.parseInt(etflow.getText().toString().trim());
                int vib = Integer.parseInt(etvib.getText().toString().trim());
                int min = Integer.parseInt(etminpress.getText().toString().trim());
                int max = Integer.parseInt(etmaxpress.getText().toString().trim());
                String user = tvuser.getText().toString().trim();

                data.setIp1(pressure);
                data.setIp2(flow);
                data.setIp3(temperature);
                data.setIp4(vib);
                data.setIp5(max);
                data.setIp6(min);
                data.setUser(user);

                ref2.child(sdf.format(new Date()).toString().trim()).setValue(data);

            }
        });

    }


    public void showTimePickerDialog(View view) {
    }
}