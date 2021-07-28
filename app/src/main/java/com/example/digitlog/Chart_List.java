package com.example.digitlog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import com.example.digitlog.R;

import static com.example.digitlog.GlobalClass.chart_params;

public class Chart_List extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, MyRecyclerViewAdapter55.ItemClickListener {
        Button but;
        MyRecyclerViewAdapter55 adapter;
        String item_o;
        ArrayList<String> animalNames;// = new ArrayList<>();
        EditText chart_search;
        ArrayList<String> mExampleList = new ArrayList<>();
        ArrayList<String> chart_params2 = new ArrayList<>();
        Dialog dialog;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart__list);

        chart_search = (EditText) findViewById(R.id.chart_search);

                dialog = new Dialog(Chart_List.this);
                dialog.setContentView(R.layout.custom_dialoge_feedback2);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                Button ok = dialog.findViewById(R.id.save);
                Button cancel = dialog.findViewById(R.id.cancel);
                ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                Chart_List.this.finishAffinity();
                        }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                dialog.dismiss();
                        }
                });

        animalNames = new ArrayList<>();

        but = (Button) findViewById(R.id.but);
        try{
                if (GlobalClass.engine_number.equals("Engine_3")){

                }
        }catch (Exception exception){
                startActivity(new Intent(Chart_List.this, Blocks.class));
        }
        if (GlobalClass.engine_number.equals("Engine_3")){
                mExampleList.add(this.getString(R.string.sp1));
                mExampleList.add(this.getString(R.string.sp2));
                mExampleList.add(this.getString(R.string.sp3));
                mExampleList.add(this.getString(R.string.sp4));
                mExampleList.add(this.getString(R.string.sp5));
                mExampleList.add(this.getString(R.string.sp6));
                mExampleList.add(this.getString(R.string.sp7));
                mExampleList.add(this.getString(R.string.sp8));
                mExampleList.add(this.getString(R.string.sp9));
                mExampleList.add(this.getString(R.string.sp10));
                mExampleList.add(this.getString(R.string.sp11));
                mExampleList.add(this.getString(R.string.sp12));
                mExampleList.add(this.getString(R.string.sp13));
                mExampleList.add(this.getString(R.string.sp14));
                mExampleList.add(this.getString(R.string.sp15));
                mExampleList.add(this.getString(R.string.sp16));
                mExampleList.add(this.getString(R.string.sp17));
                mExampleList.add(this.getString(R.string.sp18));
                mExampleList.add(this.getString(R.string.sp19));
                mExampleList.add(this.getString(R.string.sp20));
                mExampleList.add(this.getString(R.string.sp21));
                mExampleList.add(this.getString(R.string.sp22));

                mExampleList.add(this.getString(R.string.ssp2));
                mExampleList.add(this.getString(R.string.ssp3));
                mExampleList.add(this.getString(R.string.ssp4));
                mExampleList.add(this.getString(R.string.ssp5));
                mExampleList.add(this.getString(R.string.ssp6));
                mExampleList.add(this.getString(R.string.ssp7));
                mExampleList.add(this.getString(R.string.ssp8));
                mExampleList.add(this.getString(R.string.ssp9));
                mExampleList.add(this.getString(R.string.ssp10));
                mExampleList.add(this.getString(R.string.ssp11));
                mExampleList.add(this.getString(R.string.ssp12));
                mExampleList.add(this.getString(R.string.ssp13));
                mExampleList.add(this.getString(R.string.ssp14));
                mExampleList.add(this.getString(R.string.ssp15));
                mExampleList.add(this.getString(R.string.ssp16));
                mExampleList.add(this.getString(R.string.ssp17));
                mExampleList.add(this.getString(R.string.ssp18));
                mExampleList.add(this.getString(R.string.ssp19));
                mExampleList.add(this.getString(R.string.ssp20));
                mExampleList.add(this.getString(R.string.ssp21));
                mExampleList.add(this.getString(R.string.ssp22));
                mExampleList.add(this.getString(R.string.ssp23));
                mExampleList.add(this.getString(R.string.ssp24));
                mExampleList.add(this.getString(R.string.ssp25));
                mExampleList.add(this.getString(R.string.ssp26));
                mExampleList.add(this.getString(R.string.ssp27));
                mExampleList.add(this.getString(R.string.ssp28));
                mExampleList.add(this.getString(R.string.ssp29));
                mExampleList.add(this.getString(R.string.ssp30));
        }else {
                // data to populate the RecyclerView with
                mExampleList.add(this.getString(R.string.p1));
                mExampleList.add(this.getString(R.string.p2));
                mExampleList.add(this.getString(R.string.p3));
                mExampleList.add(this.getString(R.string.p4));
                mExampleList.add(this.getString(R.string.p5));
                mExampleList.add(this.getString(R.string.p6));
                mExampleList.add(this.getString(R.string.p7));
                mExampleList.add(this.getString(R.string.p8));
                mExampleList.add(this.getString(R.string.p9));
                mExampleList.add(this.getString(R.string.p10));
                mExampleList.add(this.getString(R.string.p11));
                mExampleList.add(this.getString(R.string.p12));
                mExampleList.add(this.getString(R.string.p13));
                mExampleList.add(this.getString(R.string.p14));
                mExampleList.add(this.getString(R.string.p15));
                mExampleList.add(this.getString(R.string.p16));
                mExampleList.add(this.getString(R.string.p17));
                mExampleList.add(this.getString(R.string.p18));
                mExampleList.add(this.getString(R.string.p19));
                mExampleList.add(this.getString(R.string.p20));
                mExampleList.add(this.getString(R.string.p21));


                mExampleList.add(this.getString(R.string.pp1));
                mExampleList.add(this.getString(R.string.pp2));
                mExampleList.add(this.getString(R.string.pp3));
                mExampleList.add(this.getString(R.string.pp4));
                mExampleList.add(this.getString(R.string.pp5));
                mExampleList.add(this.getString(R.string.pp6));
                mExampleList.add(this.getString(R.string.pp7));
                mExampleList.add(this.getString(R.string.pp8));
                mExampleList.add(this.getString(R.string.pp9));
                mExampleList.add(this.getString(R.string.pp10));
                mExampleList.add(this.getString(R.string.pp11));
                mExampleList.add(this.getString(R.string.pp12));
                mExampleList.add(this.getString(R.string.pp13));
                mExampleList.add(this.getString(R.string.pp14));
                mExampleList.add(this.getString(R.string.pp15));
                mExampleList.add(this.getString(R.string.pp16));
                mExampleList.add(this.getString(R.string.pp17));
                mExampleList.add(this.getString(R.string.pp18));
                mExampleList.add(this.getString(R.string.pp19));
                mExampleList.add(this.getString(R.string.pp20));
                mExampleList.add(this.getString(R.string.pp21));


                mExampleList.add(this.getString(R.string.gp1));
                mExampleList.add(this.getString(R.string.gp2));
                mExampleList.add(this.getString(R.string.gp3));
                mExampleList.add(this.getString(R.string.gp4));
                mExampleList.add(this.getString(R.string.gp5));
                mExampleList.add(this.getString(R.string.gp6));
                mExampleList.add(this.getString(R.string.gp7));
                mExampleList.add(this.getString(R.string.gp8));
                mExampleList.add(this.getString(R.string.gp9));
                mExampleList.add(this.getString(R.string.gp10));
                mExampleList.add(this.getString(R.string.gp11));
                mExampleList.add(this.getString(R.string.gp12));
                mExampleList.add(this.getString(R.string.gp13));
                mExampleList.add(this.getString(R.string.gp14));
                mExampleList.add(this.getString(R.string.gp15));
                mExampleList.add(this.getString(R.string.gp16));


                mExampleList.add(this.getString(R.string.gg1));
                mExampleList.add(this.getString(R.string.gg2));
                mExampleList.add(this.getString(R.string.gg3));
                mExampleList.add(this.getString(R.string.gg4));
                mExampleList.add(this.getString(R.string.gg5));
                mExampleList.add(this.getString(R.string.gg6));
                mExampleList.add(this.getString(R.string.gg7));
                mExampleList.add(this.getString(R.string.gg8));
                mExampleList.add(this.getString(R.string.gg9));
                mExampleList.add(this.getString(R.string.gg10));
                mExampleList.add(this.getString(R.string.gg11));
                mExampleList.add(this.getString(R.string.gg12));
                mExampleList.add(this.getString(R.string.gg13));
                mExampleList.add(this.getString(R.string.gg14));
                mExampleList.add(this.getString(R.string.gg15));
                mExampleList.add(this.getString(R.string.gg16));
                mExampleList.add(this.getString(R.string.gg17));
                mExampleList.add(this.getString(R.string.gg18));
                mExampleList.add(this.getString(R.string.gg19));
                mExampleList.add(this.getString(R.string.gg20));
                mExampleList.add(this.getString(R.string.gg21));

                mExampleList.add(this.getString(R.string.mp1));
                mExampleList.add(this.getString(R.string.mp2));
                mExampleList.add(this.getString(R.string.mp3));
                mExampleList.add(this.getString(R.string.mp4));
                mExampleList.add(this.getString(R.string.mp5));
                mExampleList.add(this.getString(R.string.mp6));
                mExampleList.add(this.getString(R.string.mp7));
                mExampleList.add(this.getString(R.string.mp8));
                mExampleList.add(this.getString(R.string.mp9));
                mExampleList.add(this.getString(R.string.mp10));
                mExampleList.add(this.getString(R.string.mp11));
                mExampleList.add(this.getString(R.string.mp12));
                mExampleList.add(this.getString(R.string.mp13));
                mExampleList.add(this.getString(R.string.mp14));
                mExampleList.add(this.getString(R.string.mp15));
                mExampleList.add(this.getString(R.string.mp16));
                mExampleList.add(this.getString(R.string.mp17));
                mExampleList.add(this.getString(R.string.mp18));
                mExampleList.add(this.getString(R.string.mp19));


                mExampleList.add(this.getString(R.string.p61));
                mExampleList.add(this.getString(R.string.p62));
                mExampleList.add(this.getString(R.string.p63));
                mExampleList.add(this.getString(R.string.p64));
                mExampleList.add(this.getString(R.string.p65));
                mExampleList.add(this.getString(R.string.p66));
                mExampleList.add(this.getString(R.string.p67));
                mExampleList.add(this.getString(R.string.p68));
                mExampleList.add(this.getString(R.string.p69));
                mExampleList.add(this.getString(R.string.p610));
                mExampleList.add(this.getString(R.string.p611));
                mExampleList.add(this.getString(R.string.p612));
                mExampleList.add(this.getString(R.string.p613));
                mExampleList.add(this.getString(R.string.p614));
                mExampleList.add(this.getString(R.string.p615));
                mExampleList.add(this.getString(R.string.p616));
                mExampleList.add(this.getString(R.string.p617));
                mExampleList.add(this.getString(R.string.p618));

        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter55(mExampleList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

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
                                filter(s.toString());
                        }
                });

        but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        GlobalClass.chart_params = new ArrayList<String>();

                        GlobalClass.chart_params = chart_params2;
                        startActivity(new Intent(Chart_List.this, chart.class));
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
                adapter.filterList(filteredList);
        }


        @Override
        public void onItemClick(View view, int position) {
        item_o = adapter.getItem(position);
        System.out.println("item_o is    ###############################");
        System.out.println(item_o);

        chart_params2.add(item_o);

        Toast.makeText(this, adapter.getItem(position), Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(Chart_List.this, Dashboard_Engines.class));
        }

        public void go_out(View view) {
                dialog.show();

        }
}