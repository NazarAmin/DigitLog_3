
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Status_List extends AppCompatActivity implements MyRecyclerViewAdapter4.ItemClickListener {

    //Status_Activity(String status, String user_2, String comment, String datetime)

    ArrayList<E_Status> mExampleList;
    MyRecyclerViewAdapter4 adapter;
    String engine;
    Post post;
    DatabaseReference ref2;
    EditText editText;
    RecyclerView recyclerView;
    ArrayList<String> status = new ArrayList<>();
    ArrayList<String> user = new ArrayList<>();
    ArrayList<String> comment = new ArrayList<>();
    ArrayList<Date> name = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status__list);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals4);

        editText = (EditText) findViewById(R.id.edittext);

        post = new Post();
        name = new ArrayList<Date>();

        engine = GlobalClass.engine_number;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2;


        ref2 = firebaseDatabase.getReference("data/" + engine + "/Status_History");

        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
                if (dataSnapshot.exists()) {
                    int i = 0;
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        try {
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



        ref2.addValueEventListener(new ValueEventListener() {
            int i = 0;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {

                        E_Status status_activity = mydatasnapshot.getValue(E_Status.class);

                        status.add(status_activity.getStatus());
                        user.add(status_activity.getUser());
                        comment.add(status_activity.getDescription());

                    }

                    ArrayList<String> name_string = new ArrayList<String>();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);

                    for (Date dateString : name) {
                        name_string.add(sdf.format(dateString));
                    }

                    mExampleList = new ArrayList<E_Status>();

                    for (i = (status.size() - 1); i>=0 ; i--){

                        //Status_Activity(String status, String user_2, String comment, String datetime)
                        try {
                            mExampleList.add(new E_Status(status.get(i), comment.get(i), user.get(i), name_string.get(i)));
                        }catch (Exception exception){

                        }
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    try {
                        // adapter = new MyRecyclerViewAdapter2(Faults_List.this, category, urgency, user, comment, name_string);
                        adapter = new MyRecyclerViewAdapter4(mExampleList);
                        adapter.setClickListener(Status_List.this);
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

        ArrayList<E_Status> filteredList = new ArrayList<>();

        for (E_Status item : mExampleList) {
            try {
                if ((item.getStatus().toLowerCase().contains(text.toLowerCase())) | (item.getDescription().toLowerCase().contains(text.toLowerCase()))){
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}