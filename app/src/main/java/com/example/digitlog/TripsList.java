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

public class TripsList extends AppCompatActivity implements MyRecyclerViewAdapter3.ItemClickListener {

    ArrayList<Trip_Class> mExampleList;
    MyRecyclerViewAdapter3 adapter;
    String engine;
    Post post;
    DatabaseReference ref2;
    EditText editText;
    RecyclerView recyclerView;
    ArrayList<String> fuel = new ArrayList<>();
    ArrayList<String> user = new ArrayList<>();
    ArrayList<String> comment = new ArrayList<>();
    ArrayList<String> load = new ArrayList<>();
    ArrayList<String> alarms = new ArrayList<>();
    ArrayList<Date> name = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_list);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals9);

        editText = (EditText) findViewById(R.id.edittext);

        post = new Post();
        name = new ArrayList<Date>();

        engine = GlobalClass.engine_number;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2;


        ref2 = firebaseDatabase.getReference("data/" + engine + "/tips_log");

        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
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



        ref2.addValueEventListener(new ValueEventListener() {
            int i = 0;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {

                        Trip_Class Trip_Class = mydatasnapshot.getValue(Trip_Class.class);

                        fuel.add(Trip_Class.getFuel());
                        user.add(Trip_Class.getUser_2());
                        comment.add(Trip_Class.getComment());
                        load.add(Trip_Class.getLoad());
                        alarms.add(Trip_Class.getAlarms());
                    }

                    ArrayList<String> name_string = new ArrayList<String>();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");

                    for (Date dateString : name) {
                        name_string.add(sdf.format(dateString));
                    }

                    mExampleList = new ArrayList<>();
                    try {
                        for (i = 0; i < name_string.size(); i++) {
                            mExampleList.add(new Trip_Class(load.get(i), fuel.get(i), user.get(i), comment.get(i), name_string.get(i), alarms.get(i)));
                        }
                    }catch (Exception e){

                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    try {
                        // adapter = new MyRecyclerViewAdapter2(Faults_List.this, category, urgency, user, comment, name_string);
                        adapter = new MyRecyclerViewAdapter3(mExampleList);
                        adapter.setClickListener(TripsList.this);
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

        ArrayList<Trip_Class> filteredList = new ArrayList<>();

        for (Trip_Class item : mExampleList) {
            try {
                if ((item.getFuel().toLowerCase().contains(text.toLowerCase())) | (item.getComment().toLowerCase().contains(text.toLowerCase()))){
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