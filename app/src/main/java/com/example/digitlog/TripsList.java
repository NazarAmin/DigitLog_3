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

        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.Locale;

public class TripsList extends AppCompatActivity implements MyRecyclerViewAdapter3.ItemClickListener {

    ArrayList<Trip_Class> mExampleList = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    ArrayList<String> is_images = new ArrayList<>();
    MyRecyclerViewAdapter3 adapter;
    String engine;
    Post post;
    DatabaseReference ref2;
    EditText editText;
    RecyclerView recyclerView;




    Dialog dialog;
    ArrayList<String> name_string = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_list);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals9);

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        dialog = new Dialog(TripsList.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TripsList.this.finishAffinity();
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
       // name = new ArrayList<Date>();

        engine = GlobalClass.engine_number;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2;


        ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/tips_log");


        ref2.addValueEventListener(new ValueEventListener() {
            int i = 0;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mExampleList.clear();
                name_string.clear();
                images.clear();
                is_images.clear();


                ArrayList<String> fuel = new ArrayList<>();
                ArrayList<String> user = new ArrayList<>();
                ArrayList<String> comment = new ArrayList<>();
                ArrayList<String> load = new ArrayList<>();
                ArrayList<String> alarms = new ArrayList<>();
                ArrayList<Date> name = new ArrayList<>();
                ArrayList<String> name_2 = new ArrayList<>();


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);

                if (dataSnapshot.hasChildren()) {

                    try {

                        for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {


                            if ((sdf.parse(mydatasnapshot.getKey()).before(GlobalClass.start_date)) ||  //sdf.parse(String.valueOf(
                                    (sdf.parse(mydatasnapshot.getKey()).after(GlobalClass.end_date))) {
                                System.out.println("Continued !!!!");
                                continue;
                            } else {
                                System.out.println("   |||||    Loop 1");
                                Trip_Class trip_class = mydatasnapshot.getValue(Trip_Class.class);

                                fuel.add(trip_class.getFuel());
                                user.add(trip_class.getUser_2());
                                comment.add(trip_class.getComment());
                                load.add(trip_class.getLoad());
                                alarms.add(trip_class.getAlarms());
                                name_2.add(trip_class.getDatetime());
                                images.add(trip_class.getImage_name());
                                is_images.add(trip_class.getUrl());
                                name.add(sdf.parse(mydatasnapshot.getKey()));
                            }
                        }
                        for (java.util.Date dateString : name) {
                            name_string.add(sdf.format(dateString));
                            System.out.println(dateString);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    try {
                        for (i = (name_string.size()); i>0 ; i--){
                            System.out.println(i + "   |||||    counter is working...");
                            mExampleList.add(new Trip_Class(load.get(i-1), fuel.get(i-1), user.get(i-1),
                                    comment.get(i-1), name_2.get(i-1), alarms.get(i-1), images.get(i-1), is_images.get(i-1)));
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
                        System.out.println(e.getCause());
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
                if ((item.getFuel().toLowerCase().contains(text.toLowerCase())) |
                        (item.getComment().toLowerCase().contains(text.toLowerCase()))){

                    filteredList.add(item);
                }
            }catch (Exception ex){

            }
        }
        adapter.filterList(filteredList);
    }

    @Override
    public void onItemClick(View view, int position) {

        try{

            if (is_images.get(is_images.size()-1-position).equals("No image Attached")) {
                Toast.makeText(getApplicationContext(), "No Image to Show", Toast.LENGTH_SHORT).show();
            }else{
                String image_location = adapter.getItem4(images, images.size()-1 - position);
                Intent i = new Intent(TripsList.this, Fault_Trip_Image.class);
                i.putExtra("Loc", image_location + ".jpeg");
                startActivity(i);
            }

        }catch (Exception e){

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void go_home(View view) {
        startActivity(new Intent(TripsList.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();
    }
}