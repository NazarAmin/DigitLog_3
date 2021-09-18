package com.example.digitlog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Troubleshooting extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, MyRecyclerViewAdapter55.ItemClickListener {
        MyRecyclerViewAdapter55 adapter;
        String item_o;
        ArrayList<String> animalNames;// = new ArrayList<>();
        EditText chart_search;
        ArrayList<String> mExampleList = new ArrayList<>();
        ArrayList<String> chart_params2 = new ArrayList<>();
        Dialog dialog;
        TextView t_cause, t_actions, signal_selected, short_name;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference("data2/Alarms");
        DatabaseReference ref3;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_troubleshooting);
                RecyclerView recyclerView = findViewById(R.id.rvAnimals);

                TextView eng = (TextView) findViewById(R.id.eng);
                eng.setText(GlobalClass.engine_number);
                chart_search = (EditText) findViewById(R.id.chart_search);

                t_cause = (TextView) findViewById(R.id.t_alarm);
                t_actions = (TextView) findViewById(R.id.t_actions);
                signal_selected = (TextView) findViewById(R.id.signal_selected);
                short_name = (TextView) findViewById(R.id.short_name);

                dialog = new Dialog(Troubleshooting.this);
                dialog.setContentView(R.layout.custom_dialoge_feedback2);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                Button ok = dialog.findViewById(R.id.save);
                Button cancel = dialog.findViewById(R.id.cancel);
                ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                Troubleshooting.this.finishAffinity();
                        }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                dialog.dismiss();
                        }
                });

        animalNames = new ArrayList<>();


        ref2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                                for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                                        Manual data = mydatasnapshot.getValue(Manual.class);

                                        mExampleList.add(data.getSignal());
                                }
                        }

                        // set up the RecyclerView
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        adapter = new MyRecyclerViewAdapter55(mExampleList);
                        adapter.setClickListener(Troubleshooting.this);
                        recyclerView.setAdapter(adapter);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
        });




        chart_search.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                //filter(s.toString());
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                                try {
                                        filter(s.toString());

                                }catch (Exception e){

                                }
                        }
                });


        }

        private void filter(String text) {

                ArrayList<String> filteredList = new ArrayList<>();

                for (String item : mExampleList){
                        try {
                                if (item.toLowerCase().contains(text.toLowerCase())){
                                        filteredList.add(item);
                                }
                        }catch (Exception ex){

                        }
                }
try{
        adapter.filterList(filteredList);

}catch (Exception e){

}
        }


        @Override
        public void onItemClick(View view, int position) {
        item_o = adapter.getItem(position);
        signal_selected.setText(item_o);
        ref3 = firebaseDatabase.getReference("data2/Alarms/" + item_o);
        ref3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Manual data = snapshot.getValue(Manual.class);
                        t_cause.setText(data.getCause());
                        t_actions.setText(data.getAction());
                        short_name.setText(data.getAlarm());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
        });

        }

        @Override
        protected void onStart() {
                super.onStart();
                chart_params2.clear();
        }

        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {

        }
        public void go_home(View view) {
                startActivity(new Intent(Troubleshooting.this, Blocks.class));
        }

        public void go_out(View view) {
                dialog.show();

        }
}