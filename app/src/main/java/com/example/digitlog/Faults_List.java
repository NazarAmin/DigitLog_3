package com.example.digitlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Faults_List extends AppCompatActivity implements MyRecyclerViewAdapter2.ItemClickListener {
        MyRecyclerViewAdapter2 adapter;
        String item_o, engine, category;
        Post post;
        DatabaseReference ref2;
        ArrayList<Date> name;
       // ArrayList<String> urgency, user, comment, cats;


@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faults__list);

        post = new Post();
        name = new ArrayList<Date>();

        engine = GlobalClass.engine_number;
        //category = GlobalClass.Faults_Category;

        //String[] cats = {"Trip"};//, "Fault", "Site Observation"};

        //for (String categoryn : cats) {

                ref2 = post.getref(engine, "test", false);
                name = post.retrieve_dates(ref2);

                ArrayList<String> urgency = new ArrayList<>();
                ArrayList<String> user = new ArrayList<>();
                ArrayList<String> comment = new ArrayList<>();
                ArrayList<String> category = new ArrayList<>();


                ref2.addValueEventListener(new ValueEventListener() {
                        int i = 0;

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                //ArrayList<Integer> ys = new ArrayList<Integer>();

                                if (dataSnapshot.hasChildren()) {

                                        for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {

                                                Faults_Trips faults_trips = mydatasnapshot.getValue(Faults_Trips.class);
                                                //  ys.add(data.getFlowrate());

                                                //datavals.add(new Entry(data.getPressure(), data.getFlowrate()));

                                                urgency.add(faults_trips.getUrgency());

                                                user.add(faults_trips.getUser_2());

                                                comment.add(faults_trips.getComment());

                                                category.add(faults_trips.getCategory());


                                        }

                                        ArrayList<String> name_string = new ArrayList<String>();


                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");

                                        for (Date dateString : name) {
                                                name_string.add(sdf.format(dateString));
                                        }

                                        // set up the RecyclerView
                                        RecyclerView recyclerView = findViewById(R.id.rvAnimals2);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        try {
                                                adapter = new MyRecyclerViewAdapter2(Faults_List.this, category, urgency, user, comment, name_string);
                                                adapter.setClickListener(Faults_List.this);
                                                recyclerView.setAdapter(adapter);
                                        }catch(Exception e){

                                                }
                                }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }//onCancelled
                });


        }


        //}

        //private ArrayList<String> chart_params2 = new ArrayList<>();

        @Override
        public void onItemClick(View view, int position) {

        //item_o = adapter.getItem(position);
        //chart_params2.add(item_o);

        Intent secondActivity = new Intent(Faults_List.this, Dashboard_problem.class);

        //secondActivity.putExtra("Categoy", adapter.getItem(position));
        //secondActivity.putExtra("Description", adapter.getItem(position + 1));

        startActivity(secondActivity);

              //  Toast.makeText(this, adapter.getItem(position), Toast.LENGTH_SHORT).show();
        }

        }