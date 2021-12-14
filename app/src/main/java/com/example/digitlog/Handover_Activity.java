package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Handover_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner, spinner2;
    String engine, engine_focal, current_user, datetime, current_engine_focal_name, shift_name;
    EditText comment_hand;
    Button update_hand;
    TextView PIC;
    Handover_c handover_c;
    String general_admin = "admin";
    Dialog dialog, dialog2;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handover_);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        dialog2 = new Dialog(Handover_Activity.this);
        dialog2.setContentView(R.layout.custom_dialoge_feedback2);
        dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok2 = dialog2.findViewById(R.id.save);
        Button cancel2 = dialog2.findViewById(R.id.cancel);
        ok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Handover_Activity.this.finishAffinity();
            }
        });
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        PIC = (TextView) findViewById(R.id.pic);
        comment_hand = (EditText) findViewById(R.id.comment_hand);
        update_hand = (Button) findViewById(R.id.update_hand);

        dialog = new Dialog(Handover_Activity.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save_function();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);

        datetime = sdf.format(new Date());
        engine = GlobalClass.engine_number;
        current_user = GlobalClass.actual_user_name;

        DatabaseReference ref2, ref3;
        ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/OIC_History/" + datetime);
        ref3 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/OIC");
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                current_engine_focal_name = dataSnapshot.getValue(String.class);
                PIC.setText(current_engine_focal_name);
                GlobalClass.current_engine_focal = current_engine_focal_name;

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        update_hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        String shifts[] = {"A", "B", "C", "D", "E"};
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(Handover_Activity.this, android.R.layout.simple_spinner_item, shifts);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);
        
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        DatabaseReference ref9 = firebaseDatabase.getReference(GlobalClass.database + "/users");
        ref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                        Users user = mydatasnapshot.getValue(Users.class);
                        categories.add(user.getUser());
                    }

                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Handover_Activity.this, android.R.layout.simple_spinner_item, categories);
                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // attaching data adapter to spinner
                    spinner.setAdapter(dataAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void save_function() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);

        datetime = sdf.format(new Date());
        engine = GlobalClass.engine_number;
        current_user = GlobalClass.actual_user_name;

        DatabaseReference ref2, ref3;
        ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/OIC_History/" + datetime);
        ref3 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/OIC");

        handover_c = new Handover_c(current_user, engine_focal, datetime, comment_hand.getText().toString(), shift_name);


    //    if (actual_user.equals(GlobalClass.actual_user_name) | Arrays.asList(arr).contains(GlobalClass.actual_user_name)) {



     //   if (GlobalClass.current_engine_focal.equals(GlobalClass.actual_user_name) | GlobalClass.current_engine_focal.equals(general_admin)){
            GlobalClass.current_engine_focal = engine_focal;
            ref2.setValue(handover_c);
            ref3.setValue(engine_focal);
    //    }else{
    //        Toast.makeText(getApplicationContext(), "You are not authorized to " +
    //               "handover " + engine + " this can be done by " + engine_focal, Toast.LENGTH_LONG).show();
     //   }
        dialog.dismiss();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item

        switch(parent.getId()){
            case R.id.spinner:
                engine_focal = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner2:
                shift_name = parent.getItemAtPosition(position).toString();
        }

        //GlobalClass.engine_focal = engine_focal;

        // Showing selected spinner item
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void go_home(View view) {
        startActivity(new Intent(Handover_Activity.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog2.show();

    }
}