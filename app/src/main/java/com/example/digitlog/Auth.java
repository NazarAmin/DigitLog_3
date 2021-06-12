package com.example.digitlog;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Auth {
    boolean a = false;
    String engine = GlobalClass.engine_number;
    String current_user = GlobalClass.actual_user_name;
    String general_admin = GlobalClass.general_admin;
    String current_engine_focal_name;


    public Auth() {
    }

    public boolean authentication(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");

        DatabaseReference ref3;
        ref3 = firebaseDatabase.getReference("data/" + engine + "/OIC");
        ref3.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                current_engine_focal_name = dataSnapshot.getValue(String.class);
                GlobalClass.current_engine_focal = current_engine_focal_name;

                if (current_engine_focal_name.equals(GlobalClass.actual_user_name) | current_engine_focal_name.equals(general_admin)){
                    //GlobalClass.current_engine_focal = engine_focal;
                   a =  true;
                }else{

                    a = false;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    return a;
    }
}
