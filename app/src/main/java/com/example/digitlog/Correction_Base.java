package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.util.ArrayList;
import java.util.List;

public class Correction_Base extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button but;
    MyRecyclerViewAdapter55 adapter, adapter2, adapter3, adapter4;
    String item_o, path2, path3, path4, path5;
    ArrayList<String> animalNames;// = new ArrayList<>();
    EditText chart_search,chart_search2,chart_search3,chart_search4;
    ArrayList<String> mExampleList = new ArrayList<>();
    ArrayList<String> mExampleList2 = new ArrayList<>();
    ArrayList<String> mExampleList3 = new ArrayList<>();
    ArrayList<String> mExampleList4 = new ArrayList<>();

    ArrayList<String> chart_params2 = new ArrayList<>();
    Dialog dialog;
    Spinner spinner,spinner2,spinner3,spinner4;
    FirebaseDatabase firebaseDatabase;
    EditText param, param2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correction__base);


        spinner = (Spinner) findViewById(R.id.rvAnimals);
        spinner2 = (Spinner) findViewById(R.id.rvAnimals2);
        spinner3 = (Spinner) findViewById(R.id.rvAnimals3);
        spinner4 = (Spinner) findViewById(R.id.rvAnimals4);

        firebaseDatabase = FirebaseDatabase.getInstance();

        param = (EditText) findViewById(R.id.param);
        param2 = (EditText) findViewById(R.id.param2);


        dialog = new Dialog(Correction_Base.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Correction_Base.this.finishAffinity();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);

        animalNames = new ArrayList<>();

        but = (Button) findViewById(R.id.but);

     //   if ((GlobalClass.engine_number.equals("ST_1")) | (GlobalClass.engine_number.equals("ST_2"))
     //           | (GlobalClass.engine_number.equals("ST_3")) | (GlobalClass.engine_number.equals("ST_4"))){
        String shifts[] = {"GT_1","GT_2","GT_3","GT_4","GT_5","GT_6","GT_7","GT_8","ST_1","ST_2","ST_3","ST_4"};
        ArrayAdapter<String> dataAdapter90 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, shifts);
        dataAdapter90.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter90);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ref9 = firebaseDatabase.getReference(path5);
                try{
                    ref9.setValue(Float.parseFloat(param2.getText().toString()));
                }catch (Exception e){
                    ref9.setValue(param2.getText().toString());
                }
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()){
            case R.id.rvAnimals:
                prepare_recycle_view(parent.getItemAtPosition(position).toString());
                break;
            case R.id.rvAnimals2:
                prepare_recycle_view2(parent.getItemAtPosition(position).toString());
                break;
            case R.id.rvAnimals3:
                prepare_recycle_view3(parent.getItemAtPosition(position).toString());
                break;
            case R.id.rvAnimals4:
                prepare_recycle_view4(parent.getItemAtPosition(position).toString());
                break;
        } }

    void prepare_recycle_view(String item){
        DatabaseReference ref9 = firebaseDatabase.getReference("data/" + item);
        ArrayList<String> newList = new ArrayList<>();
        path2 = "data/" + item;
        ref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                        if ((mydatasnapshot.getKey().equals("OIC")) |(mydatasnapshot.getKey().equals("Status"))){
                            continue;
                        }else {
                            newList.add(mydatasnapshot.getKey());
                        }
                    }
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, newList);
                    // Drop down layout style - list view with radio button
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // attaching data adapter to spinner
                    spinner2.setAdapter(dataAdapter2);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    void prepare_recycle_view2(String item){
        System.out.println(path2 + item);
        DatabaseReference ref9 = firebaseDatabase.getReference(path2 +"/" +  item);

        ArrayList<String> newList2 = new ArrayList<>();
        path3 = path2 + "/" + item;
        ref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                        if ((mydatasnapshot.getKey().equals("OIC")) |(mydatasnapshot.getKey().equals("Status"))){
                            continue;
                        }else {
                            newList2.add(mydatasnapshot.getKey());
                        }
                    }
                    ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, newList2);
                    // Drop down layout style - list view with radio button
                    dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // attaching data adapter to spinner
                    spinner3.setAdapter(dataAdapter3);
                } }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }

    void prepare_recycle_view3(String item){
        DatabaseReference ref9 = firebaseDatabase.getReference(path3 + "/" + item);

        ArrayList<String> newList3 = new ArrayList<>();
        path4 = path3 + "/" + item;
        ref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                        newList3.add(mydatasnapshot.getKey());
                    }
                    ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, newList3);
                    // Drop down layout style - list view with radio button
                    dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // attaching data adapter to spinner
                    spinner4.setAdapter(dataAdapter4);
                } }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }

    void prepare_recycle_view4(String item){

        DatabaseReference ref9 = firebaseDatabase.getReference(path4 + "/" + item);
        path5 = path4 + "/" + item;
        ref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            param.setText(snapshot.getValue().toString());
                        }catch (Exception e){

                        }
                    }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void go_home(View view) {
        startActivity(new Intent(Correction_Base.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}