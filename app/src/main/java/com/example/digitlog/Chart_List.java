package com.example.digitlog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import com.example.digitlog.R;

import static com.example.digitlog.GlobalClass.chart_params;

public class Chart_List extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
        Button but;
        MyRecyclerViewAdapter adapter;
        String item_o;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart__list);


        //GlobalClass.chart_params.clear();

        but = (Button) findViewById(R.id.but);
        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add(this.getString(R.string.p1));
        animalNames.add(this.getString(R.string.p2));
        animalNames.add(this.getString(R.string.p3));
        animalNames.add(this.getString(R.string.p4));
        animalNames.add(this.getString(R.string.p5));
        animalNames.add(this.getString(R.string.p6));
        animalNames.add(this.getString(R.string.p7));
        animalNames.add(this.getString(R.string.p8));
        animalNames.add(this.getString(R.string.p9));
        animalNames.add(this.getString(R.string.p10));
        animalNames.add(this.getString(R.string.p11));
        animalNames.add(this.getString(R.string.p12));
        animalNames.add(this.getString(R.string.p13));
        animalNames.add(this.getString(R.string.p14));
        animalNames.add(this.getString(R.string.p15));
        animalNames.add(this.getString(R.string.p16));
        animalNames.add(this.getString(R.string.p17));
        animalNames.add(this.getString(R.string.p18));
        animalNames.add(this.getString(R.string.p19));
        animalNames.add(this.getString(R.string.p20));
        animalNames.add(this.getString(R.string.p21));


        animalNames.add(this.getString(R.string.pp1));
        animalNames.add(this.getString(R.string.pp2));
        animalNames.add(this.getString(R.string.pp3));
        animalNames.add(this.getString(R.string.pp4));
        animalNames.add(this.getString(R.string.pp5));
        animalNames.add(this.getString(R.string.pp6));
        animalNames.add(this.getString(R.string.pp7));
        animalNames.add(this.getString(R.string.pp8));
        animalNames.add(this.getString(R.string.pp9));
        animalNames.add(this.getString(R.string.pp10));
        animalNames.add(this.getString(R.string.pp11));
        animalNames.add(this.getString(R.string.pp12));
        animalNames.add(this.getString(R.string.pp13));
        animalNames.add(this.getString(R.string.pp14));
        animalNames.add(this.getString(R.string.pp15));
        animalNames.add(this.getString(R.string.pp16));
        animalNames.add(this.getString(R.string.pp17));
        animalNames.add(this.getString(R.string.pp18));
        animalNames.add(this.getString(R.string.pp19));
        animalNames.add(this.getString(R.string.pp20));
        animalNames.add(this.getString(R.string.pp21));


        animalNames.add(this.getString(R.string.gp1));
        animalNames.add(this.getString(R.string.gp2));
        animalNames.add(this.getString(R.string.gp3));
        animalNames.add(this.getString(R.string.gp4));
        animalNames.add(this.getString(R.string.gp5));
        animalNames.add(this.getString(R.string.gp6));
        animalNames.add(this.getString(R.string.gp7));
        animalNames.add(this.getString(R.string.gp8));
        animalNames.add(this.getString(R.string.gp9));
        animalNames.add(this.getString(R.string.gp10));
        animalNames.add(this.getString(R.string.gp11));
        animalNames.add(this.getString(R.string.gp12));
        animalNames.add(this.getString(R.string.gp13));
        animalNames.add(this.getString(R.string.gp14));
        animalNames.add(this.getString(R.string.gp15));
        animalNames.add(this.getString(R.string.gp16));


        animalNames.add(this.getString(R.string.gg1));
        animalNames.add(this.getString(R.string.gg2));
        animalNames.add(this.getString(R.string.gg3));
        animalNames.add(this.getString(R.string.gg4));
        animalNames.add(this.getString(R.string.gg5));
        animalNames.add(this.getString(R.string.gg6));
        animalNames.add(this.getString(R.string.gg7));
        animalNames.add(this.getString(R.string.gg8));
        animalNames.add(this.getString(R.string.gg9));
        animalNames.add(this.getString(R.string.gg10));
        animalNames.add(this.getString(R.string.gg11));
        animalNames.add(this.getString(R.string.gg12));
        animalNames.add(this.getString(R.string.gg13));
        animalNames.add(this.getString(R.string.gg14));
        animalNames.add(this.getString(R.string.gg15));
        animalNames.add(this.getString(R.string.gg16));
        animalNames.add(this.getString(R.string.gg17));
        animalNames.add(this.getString(R.string.gg18));
        animalNames.add(this.getString(R.string.gg19));
        animalNames.add(this.getString(R.string.gg20));
        animalNames.add(this.getString(R.string.gg21));

        animalNames.add(this.getString(R.string.mp1));
        animalNames.add(this.getString(R.string.mp2));
        animalNames.add(this.getString(R.string.mp3));
        animalNames.add(this.getString(R.string.mp4));
        animalNames.add(this.getString(R.string.mp5));
        animalNames.add(this.getString(R.string.mp6));
        animalNames.add(this.getString(R.string.mp7));
        animalNames.add(this.getString(R.string.mp8));
        animalNames.add(this.getString(R.string.mp9));
        animalNames.add(this.getString(R.string.mp10));
        animalNames.add(this.getString(R.string.mp11));
        animalNames.add(this.getString(R.string.mp12));
        animalNames.add(this.getString(R.string.mp13));
        animalNames.add(this.getString(R.string.mp14));
        animalNames.add(this.getString(R.string.mp15));
        animalNames.add(this.getString(R.string.mp16));
        animalNames.add(this.getString(R.string.mp17));
        animalNames.add(this.getString(R.string.mp18));
        animalNames.add(this.getString(R.string.mp19));


        animalNames.add(this.getString(R.string.p61));
        animalNames.add(this.getString(R.string.p62));
        animalNames.add(this.getString(R.string.p63));
        animalNames.add(this.getString(R.string.p64));
        animalNames.add(this.getString(R.string.p65));
        animalNames.add(this.getString(R.string.p66));
        animalNames.add(this.getString(R.string.p67));
        animalNames.add(this.getString(R.string.p68));
        animalNames.add(this.getString(R.string.p69));
        animalNames.add(this.getString(R.string.p610));
        animalNames.add(this.getString(R.string.p611));
        animalNames.add(this.getString(R.string.p612));
        animalNames.add(this.getString(R.string.p613));
        animalNames.add(this.getString(R.string.p614));
        animalNames.add(this.getString(R.string.p615));
        animalNames.add(this.getString(R.string.p616));
        animalNames.add(this.getString(R.string.p617));
        animalNames.add(this.getString(R.string.p618));



        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        GlobalClass.chart_params = new ArrayList<String>();

                        GlobalClass.chart_params = chart_params2;
                        startActivity(new Intent(Chart_List.this, chart.class));
                }
        });
        }

        private ArrayList<String> chart_params2 = new ArrayList<>();

        @Override
        public void onItemClick(View view, int position) {
        item_o = adapter.getItem(position);
        chart_params2.add(item_o);

        Toast.makeText(this, adapter.getItem(position), Toast.LENGTH_SHORT).show();
        }

        }