package com.example.digitlog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
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

        ArrayList<Faults_Trips> mExampleList;
        MyRecyclerViewAdapter2 adapter;
        String engine;
        Post post;
        DatabaseReference ref2;
        ArrayList<Date> name;
        EditText editText;
        RecyclerView recyclerView;
        ArrayList<String> urgency = new ArrayList<>();
        ArrayList<String> user = new ArrayList<>();
        ArrayList<String> comment = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faults__list);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals2);

        editText = (EditText) findViewById(R.id.edittext);

        post = new Post();
        name = new ArrayList<Date>();

        engine = GlobalClass.engine_number;

        ref2 = post.getref(engine, "test", false);
        name = post.retrieve_dates(ref2);



        ref2.addValueEventListener(new ValueEventListener() {
        int i = 0;

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
                        for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {

                                Faults_Trips faults_trips = mydatasnapshot.getValue(Faults_Trips.class);

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

                        mExampleList = new ArrayList<>();

                        for (i = 0; i<category.size();i++){
                                mExampleList.add(new Faults_Trips(category.get(i), urgency.get(i), user.get(i), comment.get(i), name_string.get(i)));
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        try {
                               // adapter = new MyRecyclerViewAdapter2(Faults_List.this, category, urgency, user, comment, name_string);
                                adapter = new MyRecyclerViewAdapter2(mExampleList);
                                adapter.setClickListener(Faults_List.this);
                                recyclerView.setAdapter(adapter);
                        }catch(Exception e){

                                }

                        editText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                        filter(s.toString());
                                }
                        });

                }


        }

        @Override
        public void onCancelled(DatabaseError error) {

        }
        });

        }
        private void filter(String text) {

                ArrayList<Faults_Trips> filteredList = new ArrayList<>();

                for (Faults_Trips item : mExampleList) {
                        try {
                                if ((item.getUrgency().toLowerCase().contains(text.toLowerCase())) | (item.getComment().toLowerCase().contains(text.toLowerCase()))){
                                        filteredList.add(item);
                                }
                        }catch (Exception ex){

                        }
                }
                adapter.filterList(filteredList);
        }

        @Override
        public void onItemClick(View view, int position) {

        /*
        Intent secondActivity = new Intent(Faults_List.this, Dashboard_problem.class);

                secondActivity.putExtra("Categoy", adapter.getItem(category, position));
                secondActivity.putExtra("description", adapter.getItem2(urgency, position));
                secondActivity.putExtra("comment", adapter.getItem3(comment, position));

                startActivity(secondActivity);
*/
                }
        }