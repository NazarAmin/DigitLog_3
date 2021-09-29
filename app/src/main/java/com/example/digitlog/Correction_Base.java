package com.example.digitlog;

import androidx.annotation.NonNull;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Correction_Base extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button but;
    MyRecyclerViewAdapter55 adapter, adapter2, adapter3, adapter4;
    String item_o, path2, path3, path4, path5;
    ArrayList<String> animalNames;// = new ArrayList<>();
    EditText chart_search,chart_search2,chart_search3,chart_search4;
    ArrayList<String> LogSheet20_B = new ArrayList<>();
    ArrayList<String> HSRG_A = new ArrayList<>();
    ArrayList<String> HSRG_B = new ArrayList<>();
    ArrayList<String> GT_Log = new ArrayList<>();
    ArrayList<String> FO = new ArrayList<>();
    ArrayList<String> Generation = new ArrayList<>();
    ArrayList<String> Generator_Board = new ArrayList<>();
    ArrayList<String> Mark_V = new ArrayList<>();
    ArrayList<String> Log_Sheet_6 = new ArrayList<>();
    ArrayList<String> parameters3 = new ArrayList<>();
    int counter = 0;
    Map<String, String> map = new HashMap<String, String>();

    Dialog dialog;
    Spinner spinner,spinner2,spinner3,spinner4;
    FirebaseDatabase firebaseDatabase;
    EditText param, param2;
    String engine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correction__base);
        firebaseDatabase = FirebaseDatabase.getInstance();
        engine = GlobalClass.engine_number;
        prepare_recycle_view(engine);


        //  spinner = (Spinner) findViewById(R.id.rvAnimals);
        spinner2 = (Spinner) findViewById(R.id.rvAnimals2);
        spinner3 = (Spinner) findViewById(R.id.rvAnimals3);
        spinner4 = (Spinner) findViewById(R.id.rvAnimals4);



        param = (EditText) findViewById(R.id.param);
        param2 = (EditText) findViewById(R.id.param2);

        param.setText("----");

        dialog = new Dialog(Correction_Base.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Correction_Base.this.finishAffinity();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

       // spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);

        animalNames = new ArrayList<>();

        but = (Button) findViewById(R.id.but);

     //   if ((GlobalClass.engine_number.equals("ST_1")) | (GlobalClass.engine_number.equals("ST_2"))
     //           | (GlobalClass.engine_number.equals("ST_3")) | (GlobalClass.engine_number.equals("ST_4"))){
       // String shifts[] = {"GT_1","GT_2","GT_3","GT_4","GT_5","GT_6","GT_7","GT_8","ST_1","ST_2","ST_3","ST_4"};
        //ArrayAdapter<String> dataAdapter90 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, shifts);
        //dataAdapter90.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(dataAdapter90);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ref9 = firebaseDatabase.getReference(path5);
                try{
                    ref9.setValue(Float.parseFloat(param2.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Successfully Updated, screen is reset", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    try{
                        ref9.setValue(param2.getText().toString());
                        Toast.makeText(getApplicationContext(), "Successfully Updated, screen is reset", Toast.LENGTH_LONG).show();
                    }catch (Exception ex){
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



         GT_Log.add(this.getString(R.string.p1));
         GT_Log.add(this.getString(R.string.p2));
         GT_Log.add(this.getString(R.string.p3));
         GT_Log.add(this.getString(R.string.p4));
         GT_Log.add(this.getString(R.string.p5));
         GT_Log.add(this.getString(R.string.p6));
         GT_Log.add(this.getString(R.string.p7));
         GT_Log.add(this.getString(R.string.p8));
         GT_Log.add(this.getString(R.string.p9));
         GT_Log.add(this.getString(R.string.p10));
         GT_Log.add(this.getString(R.string.p11));
         GT_Log.add(this.getString(R.string.p12));
         GT_Log.add(this.getString(R.string.p13));
         GT_Log.add(this.getString(R.string.p14));
         GT_Log.add(this.getString(R.string.p15));
         GT_Log.add(this.getString(R.string.p16));
         GT_Log.add(this.getString(R.string.p17));
         GT_Log.add(this.getString(R.string.p18));
         GT_Log.add(this.getString(R.string.p19));
         GT_Log.add(this.getString(R.string.p20));
         GT_Log.add(this.getString(R.string.p21));

        FO.add(this.getString(R.string.pp1));
        FO.add(this.getString(R.string.pp2));
        FO.add(this.getString(R.string.pp3));
        FO.add(this.getString(R.string.pp4));
        FO.add(this.getString(R.string.pp5));
        FO.add(this.getString(R.string.pp6));
        FO.add(this.getString(R.string.pp7));
        FO.add(this.getString(R.string.pp8));
        FO.add(this.getString(R.string.pp9));
        FO.add(this.getString(R.string.pp10));
        FO.add(this.getString(R.string.pp11));
        FO.add(this.getString(R.string.pp12));
        FO.add(this.getString(R.string.pp13));
        FO.add(this.getString(R.string.pp14));
        FO.add(this.getString(R.string.pp15));
        FO.add(this.getString(R.string.pp16));
        FO.add(this.getString(R.string.pp17));
        FO.add(this.getString(R.string.pp18));
        FO.add(this.getString(R.string.pp19));
        FO.add(this.getString(R.string.pp20));
        FO.add(this.getString(R.string.pp21));

        Generator_Board.add(this.getString(R.string.gp1));
        Generator_Board.add(this.getString(R.string.gp2));
        Generator_Board.add(this.getString(R.string.gp3));
        Generator_Board.add(this.getString(R.string.gp4));
        Generator_Board.add(this.getString(R.string.gp5));
        Generator_Board.add(this.getString(R.string.gp6));
        Generator_Board.add(this.getString(R.string.gp7));
        Generator_Board.add(this.getString(R.string.gp8));
        Generator_Board.add(this.getString(R.string.gp9));
        Generator_Board.add(this.getString(R.string.gp10));
        Generator_Board.add(this.getString(R.string.gp11));
        Generator_Board.add(this.getString(R.string.gp12));
        Generator_Board.add(this.getString(R.string.gp13));
        Generator_Board.add(this.getString(R.string.gp14));
        Generator_Board.add(this.getString(R.string.gp15));
        Generator_Board.add(this.getString(R.string.gp16));

        Generation.add(this.getString(R.string.gg1));
        Generation.add(this.getString(R.string.gg2));
        Generation.add(this.getString(R.string.gg3));
        Generation.add(this.getString(R.string.gg4));
        Generation.add(this.getString(R.string.gg5));
        Generation.add(this.getString(R.string.gg6));
        Generation.add(this.getString(R.string.gg7));
        Generation.add(this.getString(R.string.gg8));
        Generation.add(this.getString(R.string.gg9));
        Generation.add(this.getString(R.string.gg10));
        Generation.add(this.getString(R.string.gg11));
        Generation.add(this.getString(R.string.gg12));
        Generation.add(this.getString(R.string.gg13));
        Generation.add(this.getString(R.string.gg14));
        Generation.add(this.getString(R.string.gg15));
        Generation.add(this.getString(R.string.gg16));
        Generation.add(this.getString(R.string.gg17));
        Generation.add(this.getString(R.string.gg18));
        Generation.add(this.getString(R.string.gg19));
        Generation.add(this.getString(R.string.gg20));
        Generation.add(this.getString(R.string.gg21));

        Mark_V.add(this.getString(R.string.mp1));
        Mark_V.add(this.getString(R.string.mp2));
        Mark_V.add(this.getString(R.string.mp3));
        Mark_V.add(this.getString(R.string.mp4));
        Mark_V.add(this.getString(R.string.mp5));
        Mark_V.add(this.getString(R.string.mp6));
        Mark_V.add(this.getString(R.string.mp7));
        Mark_V.add(this.getString(R.string.mp8));
        Mark_V.add(this.getString(R.string.mp9));
        Mark_V.add(this.getString(R.string.mp10));
        Mark_V.add(this.getString(R.string.mp11));
        Mark_V.add(this.getString(R.string.mp12));
        Mark_V.add(this.getString(R.string.mp13));
        Mark_V.add(this.getString(R.string.mp14));
        Mark_V.add(this.getString(R.string.mp15));
        Mark_V.add(this.getString(R.string.mp16));
        Mark_V.add(this.getString(R.string.mp17));
        Mark_V.add(this.getString(R.string.mp18));
        Mark_V.add(this.getString(R.string.mp19));

        Log_Sheet_6.add(this.getString(R.string.p61));
        Log_Sheet_6.add(this.getString(R.string.p62));
        Log_Sheet_6.add(this.getString(R.string.p63));
        Log_Sheet_6.add(this.getString(R.string.p64));
        Log_Sheet_6.add(this.getString(R.string.p65));
        Log_Sheet_6.add(this.getString(R.string.p66));
        Log_Sheet_6.add(this.getString(R.string.p67));
        Log_Sheet_6.add(this.getString(R.string.p68));
        Log_Sheet_6.add(this.getString(R.string.p69));
        Log_Sheet_6.add(this.getString(R.string.p610));
        Log_Sheet_6.add(this.getString(R.string.p611));
        Log_Sheet_6.add(this.getString(R.string.p612));
        Log_Sheet_6.add(this.getString(R.string.p613));
        Log_Sheet_6.add(this.getString(R.string.p614));
        Log_Sheet_6.add(this.getString(R.string.p615));
        Log_Sheet_6.add(this.getString(R.string.p616));
        Log_Sheet_6.add(this.getString(R.string.p617));
        Log_Sheet_6.add(this.getString(R.string.p618));

        HSRG_A.add(this.getString(R.string.sp2));
        HSRG_A.add(this.getString(R.string.sp3));
        HSRG_A.add(this.getString(R.string.sp4));
        HSRG_A.add(this.getString(R.string.sp5));
        HSRG_A.add(this.getString(R.string.sp6));
        HSRG_A.add(this.getString(R.string.sp7));
        HSRG_A.add(this.getString(R.string.sp8));
        HSRG_A.add(this.getString(R.string.sp9));
        HSRG_A.add(this.getString(R.string.sp10));
        HSRG_A.add(this.getString(R.string.sp11));
        HSRG_A.add(this.getString(R.string.sp12));
        HSRG_A.add(this.getString(R.string.sp13));


        HSRG_B.add(this.getString(R.string.qsp2));
        HSRG_B.add(this.getString(R.string.qsp3));
        HSRG_B.add(this.getString(R.string.qsp4));
        HSRG_B.add(this.getString(R.string.qsp5));
        HSRG_B.add(this.getString(R.string.qsp6));
        HSRG_B.add(this.getString(R.string.qsp7));
        HSRG_B.add(this.getString(R.string.qsp8));
        HSRG_B.add(this.getString(R.string.qsp9));
        HSRG_B.add(this.getString(R.string.qsp10));
        HSRG_B.add(this.getString(R.string.qsp11));
        HSRG_B.add(this.getString(R.string.qsp12));
        HSRG_B.add(this.getString(R.string.qsp13));


        LogSheet20_B.add(this.getString(R.string.ssp1));
        LogSheet20_B.add(this.getString(R.string.ssp2));
        LogSheet20_B.add(this.getString(R.string.ssp3));
        LogSheet20_B.add(this.getString(R.string.ssp4));
        LogSheet20_B.add(this.getString(R.string.ssp5));
        LogSheet20_B.add(this.getString(R.string.ssp6));
        LogSheet20_B.add(this.getString(R.string.ssp7));

        LogSheet20_B.add(this.getString(R.string.ssp9));
        LogSheet20_B.add(this.getString(R.string.ssp10));
        LogSheet20_B.add(this.getString(R.string.ssp11));
        LogSheet20_B.add(this.getString(R.string.ssp12));
        LogSheet20_B.add(this.getString(R.string.ssp13));
        LogSheet20_B.add(this.getString(R.string.ssp14));
        LogSheet20_B.add(this.getString(R.string.ssp15));
        LogSheet20_B.add(this.getString(R.string.ssp16));
        LogSheet20_B.add(this.getString(R.string.ssp17));
        LogSheet20_B.add(this.getString(R.string.ssp18));
        LogSheet20_B.add(this.getString(R.string.ssp19));
        LogSheet20_B.add(this.getString(R.string.ssp20));
        LogSheet20_B.add(this.getString(R.string.ssp21));
        LogSheet20_B.add(this.getString(R.string.ssp22));
        LogSheet20_B.add(this.getString(R.string.ssp23));
        LogSheet20_B.add(this.getString(R.string.ssp24));
        LogSheet20_B.add(this.getString(R.string.ssp25));
        LogSheet20_B.add(this.getString(R.string.ssp26));
        LogSheet20_B.add(this.getString(R.string.ssp27));
        LogSheet20_B.add(this.getString(R.string.ssp28));
        LogSheet20_B.add(this.getString(R.string.ssp29));
        LogSheet20_B.add(this.getString(R.string.ssp30));
        LogSheet20_B.add(this.getString(R.string.ssp31));
        LogSheet20_B.add(this.getString(R.string.ssp32));
        LogSheet20_B.add(this.getString(R.string.ssp33));
        LogSheet20_B.add(this.getString(R.string.ssp33));
        LogSheet20_B.add(this.getString(R.string.ssp34));
        LogSheet20_B.add(this.getString(R.string.ssp35));
        LogSheet20_B.add(this.getString(R.string.ssp36));
        LogSheet20_B.add(this.getString(R.string.ssp37));
        LogSheet20_B.add(this.getString(R.string.ssp38));
        LogSheet20_B.add(this.getString(R.string.ssp39));
        parameters3.add("ip1");
        parameters3.add("ip2");
        parameters3.add("ip3");
        parameters3.add("ip4");
        parameters3.add("ip5");
        parameters3.add("ip6");
        parameters3.add("ip7");
        parameters3.add("ip8");
        parameters3.add("ip9");
        parameters3.add("ip10");
        parameters3.add("ip11");
        parameters3.add("ip12");
        parameters3.add("ip13");
        parameters3.add("ip14");
        parameters3.add("ip15");
        parameters3.add("ip16");
        parameters3.add("ip17");
        parameters3.add("ip18");
        parameters3.add("ip19");
        parameters3.add("ip20");
        parameters3.add("ip21");
        parameters3.add("ip22");
        parameters3.add("ip23");
        parameters3.add("ip24");
        parameters3.add("ip25");
        parameters3.add("ip26");
        parameters3.add("ip27");
        parameters3.add("ip28");
        parameters3.add("ip29");
        parameters3.add("ip30");
        parameters3.add("ip31");
        parameters3.add("ip32");
        parameters3.add("ip33");
        parameters3.add("ip34");
        parameters3.add("ip35");
        parameters3.add("ip36");
        parameters3.add("ip37");
        parameters3.add("ip38");
        parameters3.add("ip39");



        ArrayList<ArrayList> sheets_main = new ArrayList<>();
        sheets_main.add(GT_Log);
        sheets_main.add(FO);
        sheets_main.add(Generation);
        sheets_main.add(Generator_Board);
        sheets_main.add(Mark_V);
        sheets_main.add(Log_Sheet_6);
        sheets_main.add(HSRG_A);
        sheets_main.add(HSRG_B);
        sheets_main.add(LogSheet20_B);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()){
          //  case R.id.rvAnimals:
            //    prepare_recycle_view(parent.getItemAtPosition(position).toString());
              //  break;
            case R.id.rvAnimals2:
               // prepare_recycle_view2(parent.getItemAtPosition(position).toString());
                prepare_recycle_view2(parent.getItemAtPosition(position).toString());
                counter = position;
                item_o = parent.getItemAtPosition(position).toString();
                break;
            case R.id.rvAnimals3:
                //prepare_recycle_view3(parent.getItemAtPosition(position).toString());
                System.out.println(parent.getItemAtPosition(position).toString());
                switch (item_o){
                    case "GT_Log":
                        get_alias(GT_Log, spinner4);
                        break;
                    case "FO":
                        get_alias(FO, spinner4);
                        break;
                    case "Generation":
                        get_alias(Generation, spinner4);
                        break;
                    case "Generator_Board":
                        get_alias(Generator_Board, spinner4);
                        break;
                    case "Mark_V":
                        get_alias(Mark_V, spinner4);
                        break;
                    case "Log_Sheet_6":
                        get_alias(Log_Sheet_6, spinner4);
                        break;
                    case "HSRG_A":
                        get_alias(HSRG_A, spinner4);
                        break;
                    case "HSRG_B":
                        get_alias(HSRG_B, spinner4);
                        break;
                    case "LogSheet20_B":
                        get_alias(LogSheet20_B, spinner4);
                        break;
                    default:
                        prepare_recycle_view3(parent.getItemAtPosition(position).toString());
                        break;
                }
                path4 = path3 + "/" + parent.getItemAtPosition(position).toString();
                break;
            case R.id.rvAnimals4:
                if ((item_o.equals("OIC_History")) | (item_o.equals("faults_trips")) | (item_o.equals("tips_log"))
                        | (item_o.equals("mid_night")) | (item_o.equals("Status_History"))){
                    prepare_recycle_view5(parent.getItemAtPosition(position).toString());
                }else{
                    prepare_recycle_view4(position);
                }
                break;
        } }

    private void prepare_recycle_view5(String item) {

        DatabaseReference ref9 = firebaseDatabase.getReference(path4 + "/" + item);
        path5 = path4 + "/" + item;
        ref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    param.setText(snapshot.getValue().toString());
                }catch (Exception e){

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }

    private void get_alias(ArrayList sheet, Spinner spinner) {

        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, sheet);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter4);
    }

    void prepare_recycle_view(String item){

        DatabaseReference ref9 = firebaseDatabase.getReference(GlobalClass.database + "/" + item);
        ArrayList<String> newList = new ArrayList<>();
        path2 = GlobalClass.database + "/" + item;
        ref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                        if ((mydatasnapshot.getKey().equals("OIC")) |(mydatasnapshot.getKey().equals("Status"))){
                            continue;
                        }else {
                            newList.add(mydatasnapshot.getKey());
                        }
                    }
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, newList);
                    // Drop down layout style - list view with radio button
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // attaching data adapter to spinner
                    spinner2.setAdapter(dataAdapter2);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    void prepare_recycle_view2(String item){
        DatabaseReference ref9 = firebaseDatabase.getReference(path2 +"/" +  item);

        ArrayList<String> newList2 = new ArrayList<>();
        path3 = path2 + "/" + item;
        ref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                            newList2.add(mydatasnapshot.getKey());

                    }

                    ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, newList2);
                    // Drop down layout style - list view with radio button
                    dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // attaching data adapter to spinner
                    spinner3.setAdapter(dataAdapter3);
                } }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }

    void prepare_recycle_view3(String item){
        DatabaseReference ref9 = firebaseDatabase.getReference(path3 + "/" + item);

        ArrayList<String> newList3 = new ArrayList<>();
        path4 = path3 + "/" + item;
        ref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : snapshot.getChildren()) {
                        newList3.add(mydatasnapshot.getKey());
                    }
                    ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, newList3);
                    // Drop down layout style - list view with radio button
                    dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // attaching data adapter to spinner
                    spinner4.setAdapter(dataAdapter4);
                } }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }

    void prepare_recycle_view4(int item5){

        String item = parameters3.get(item5);

        DatabaseReference ref9 = firebaseDatabase.getReference(path4 + "/" + item);
        path5 = path4 + "/" + item;
        ref9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            param.setText(snapshot.getValue().toString());
                        }catch (Exception e){

                        }
                    }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void go_home(View view) {
        startActivity(new Intent(Correction_Base.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog.show();

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}