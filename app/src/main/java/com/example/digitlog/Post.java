package com.example.digitlog;

import androidx.annotation.NonNull;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Post {

    String engine;
    String category;
    String datetime;

    public Post() {

    }

    public Post(String engine, String category, String datetime) {
        this.engine = engine;
        this.category = category;
        this.datetime = datetime;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getcategory() {
        return category;
    }

    public void setcategory(String category) {
        this.category = category;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public DatabaseReference getref(String engine, String datetime, boolean check) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2;

        if (check) {
            ref2 = firebaseDatabase.getReference("data/" + engine + "/faults_trips/" + datetime);
        } else {
            ref2 = firebaseDatabase.getReference("data/" + engine + "/faults_trips/");
        }
        return ref2;
    }


    public ArrayList<Date> retrieve_dates(DatabaseReference ref2) {
        ArrayList<Date> name = new ArrayList<>();
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
                if (dataSnapshot.exists()) {
                    int i = 0;
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        try {
                            System.out.println("#########################################################################");
                            name.add(sdf.parse(d.getKey()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }
                }
            }//onDataChange

            @Override
            public void onCancelled(DatabaseError error) {

            }//onCancelled
        });

        return name;}
        // test2 -------


        }

