package com.example.digitlog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
        ArrayList<String> images = new ArrayList<>();
        private StorageReference storageRef;
        Dialog dialog;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faults__list);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals2);

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        dialog = new Dialog(Faults_List.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Faults_List.this.finishAffinity();
                }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        dialog.dismiss();
                }
        });

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
                                images.add(faults_trips.getImage_name());
                        }

                        ArrayList<String> name_string = new ArrayList<String>();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);

                        for (Date dateString : name) {
                                name_string.add(sdf.format(dateString));
                        }

                        mExampleList = new ArrayList<>();

                        for (i = (category.size() - 1); i>=0 ; i--){
                                mExampleList.add(new Faults_Trips(category.get(i), urgency.get(i), user.get(i), comment.get(i), name_string.get(i), images.get(i)));
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

                String image_location = adapter.getItem4(images, images.size()-1 - position);
                System.out.println(image_location + "############################################# ////////");
                System.out.println(position + "############################################# ////////");
                Intent i = new Intent(Faults_List.this, Fault_Trip_Image.class);
                i.putExtra("Loc", image_location + ".jpeg");
                startActivity(i);

               /**
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference ref2 = firebaseDatabase.getReference("data2/" + engine + "/faults_trips");


                storageRef = FirebaseStorage.getInstance().getReference();

                StorageReference pathReference = storageRef.child(image_location + ".JPEG");
**/
                }
        public void go_home(View view) {
                startActivity(new Intent(Faults_List.this, Blocks.class));
        }

        public void go_out(View view) {
                dialog.show();

        }
        }