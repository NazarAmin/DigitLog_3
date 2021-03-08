package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class chart extends AppCompatActivity {
    LineChart Data_of_Temp;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref2 = firebaseDatabase.getReference("data");
    LineDataSet lineDataSet = new LineDataSet(null, "flow rate");
    LineDataSet plineDataSet1 = new LineDataSet(null, "pressure");
    LineDataSet vlineDataSet2 = new LineDataSet(null, "vibration");
    LineDataSet tlineDataSet3 = new LineDataSet(null, "tempe");

    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;
    ValueEventListener valueEventListener;
    ArrayList<Date> name = new ArrayList<Date>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Utils.init(this);

        Data_of_Temp = (LineChart) findViewById(R.id.Data_of_Temp);
        Data_of_Temp.animateX(1000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // test -----------

        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
                if (dataSnapshot.exists()) {
                    int i = 0;
                    for(DataSnapshot d : dataSnapshot.getChildren()) {
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

        // test2 -------



        ref2.addValueEventListener(new ValueEventListener() {
            int i = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> datavals = new ArrayList<Entry>();
                ArrayList<Entry> pressurel = new ArrayList<Entry>();
                ArrayList<Entry> templ = new ArrayList<Entry>();
                ArrayList<Entry> vibl = new ArrayList<Entry>();

                //ArrayList<Integer> ys = new ArrayList<Integer>();

                if (dataSnapshot.hasChildren()) {

                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {

                        Data data = mydatasnapshot.getValue(Data.class);
                      //  ys.add(data.getFlowrate());

                        //datavals.add(new Entry(data.getPressure(), data.getFlowrate()));
                       datavals.add(new Entry(name.get(i).getTime(), data.getIp1()));
                        pressurel.add(new Entry(name.get(i).getTime(), data.getIp2()));
                        templ.add(new Entry(name.get(i).getTime(), data.getIp3()));
                        vibl.add(new Entry(name.get(i).getTime(), data.getIp4()));

                        i = i + 1;
                    }
                    showchart(datavals, pressurel,vibl,templ);
                } else {
                    Data_of_Temp.clear();
                    Data_of_Temp.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showchart(ArrayList<Entry> datavals,ArrayList<Entry> pressurel,ArrayList<Entry> vibl,ArrayList<Entry> templ) {

        lineDataSet.setValues(datavals);

        lineDataSet.setColor(Color.GREEN);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setLineWidth(3f);

        plineDataSet1.setValues(pressurel);
        plineDataSet1.setColor(Color.BLUE);
        plineDataSet1.setDrawCircleHole(false);
        plineDataSet1.setLineWidth(3f);

        tlineDataSet3.setValues(templ);
        tlineDataSet3.setColor(Color.RED);
        tlineDataSet3.setDrawCircleHole(false);
        tlineDataSet3.setLineWidth(3f);

        vlineDataSet2.setValues(vibl);
        vlineDataSet2.setColor(Color.DKGRAY);
        vlineDataSet2.setDrawCircleHole(false);
        vlineDataSet2.setLineWidth(3f);

        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        iLineDataSets.add(plineDataSet1);
        iLineDataSets.add(tlineDataSet3);
        iLineDataSets.add(vlineDataSet2);
        lineData = new LineData(iLineDataSets);
        long referenceTimestamp = name.get(0).getTime();
        AxisValueFormatter xAxisFormatter = new HourAxisValueFormatter(referenceTimestamp);
        XAxis xAxis = Data_of_Temp.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);
       // MyMarkerView myMarkerView= new MyMarkerView(getApplicationContext(), R.layout.my_marker_view_layout, referenceTimestamp);
        //mChart.setMarkerView(myMarkerView);
        Data_of_Temp.clear();
        Data_of_Temp.setData(lineData);
        Legend legend = Data_of_Temp.getLegend();
        legend.setEnabled(true);
        Data_of_Temp.invalidate();
    }
}


