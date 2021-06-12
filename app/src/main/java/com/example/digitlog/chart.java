package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

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

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class chart extends AppCompatActivity {
    LineChart Data_of_Temp;
    ArrayList<String> items;
    ArrayList<LineDataSet> items_line_dataset;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    String engine = GlobalClass.engine_number;
    int outer_counter = 0;
   // String sheet = GlobalClass.sheet_number;

    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData = new LineData(iLineDataSets);
    ValueEventListener valueEventListener;
    ArrayList<Date> name = new ArrayList<Date>();
    List<Integer> colors = new ArrayList<Integer>();

    int k = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        String[] colorsTxt = getApplicationContext().getResources().getStringArray(R.array.colors55);

        for(int i = 0; i < colorsTxt.length; i++) {
            int newColor = Color.parseColor(colorsTxt[i]);
            colors.add(newColor);
        }

        ArrayList<String> sheet1 = new ArrayList<>();
        sheet1.add(this.getString(R.string.p1));
        sheet1.add(this.getString(R.string.p2));
        sheet1.add(this.getString(R.string.p3));
        sheet1.add(this.getString(R.string.p4));
        sheet1.add(this.getString(R.string.p5));
        sheet1.add(this.getString(R.string.p6));
        sheet1.add(this.getString(R.string.p7));
        sheet1.add(this.getString(R.string.p8));
        sheet1.add(this.getString(R.string.p9));
        sheet1.add(this.getString(R.string.p10));
        sheet1.add(this.getString(R.string.p11));
        sheet1.add(this.getString(R.string.p12));
        sheet1.add(this.getString(R.string.p13));
        sheet1.add(this.getString(R.string.p14));
        sheet1.add(this.getString(R.string.p15));
        sheet1.add(this.getString(R.string.p16));
        sheet1.add(this.getString(R.string.p17));
        sheet1.add(this.getString(R.string.p18));
        sheet1.add(this.getString(R.string.p19));
        sheet1.add(this.getString(R.string.p20));
        sheet1.add(this.getString(R.string.p21));

        ArrayList<String> sheet2 = new ArrayList<>();

        sheet2.add(this.getString(R.string.pp1));
        sheet2.add(this.getString(R.string.pp2));
        sheet2.add(this.getString(R.string.pp3));
        sheet2.add(this.getString(R.string.pp4));
        sheet2.add(this.getString(R.string.pp5));
        sheet2.add(this.getString(R.string.pp6));
        sheet2.add(this.getString(R.string.pp7));
        sheet2.add(this.getString(R.string.pp8));
        sheet2.add(this.getString(R.string.pp9));
        sheet2.add(this.getString(R.string.pp10));
        sheet2.add(this.getString(R.string.pp11));
        sheet2.add(this.getString(R.string.pp12));
        sheet2.add(this.getString(R.string.pp13));
        sheet2.add(this.getString(R.string.pp14));
        sheet2.add(this.getString(R.string.pp15));
        sheet2.add(this.getString(R.string.pp16));
        sheet2.add(this.getString(R.string.pp17));
        sheet2.add(this.getString(R.string.pp18));
        sheet2.add(this.getString(R.string.pp19));
        sheet2.add(this.getString(R.string.pp20));
        sheet2.add(this.getString(R.string.pp21));

        ArrayList<String> sheet3 = new ArrayList<>();

        sheet3.add(this.getString(R.string.gp1));
        sheet3.add(this.getString(R.string.gp2));
        sheet3.add(this.getString(R.string.gp3));
        sheet3.add(this.getString(R.string.gp4));
        sheet3.add(this.getString(R.string.gp5));
        sheet3.add(this.getString(R.string.gp6));
        sheet3.add(this.getString(R.string.gp7));
        sheet3.add(this.getString(R.string.gp8));
        sheet3.add(this.getString(R.string.gp9));
        sheet3.add(this.getString(R.string.gp10));
        sheet3.add(this.getString(R.string.gp11));
        sheet3.add(this.getString(R.string.gp12));
        sheet3.add(this.getString(R.string.gp13));
        sheet3.add(this.getString(R.string.gp14));
        sheet3.add(this.getString(R.string.gp15));
        sheet3.add(this.getString(R.string.gp16));

        ArrayList<String> sheet4 = new ArrayList<>();

        sheet4.add(this.getString(R.string.gg1));
        sheet4.add(this.getString(R.string.gg2));
        sheet4.add(this.getString(R.string.gg3));
        sheet4.add(this.getString(R.string.gg4));
        sheet4.add(this.getString(R.string.gg5));
        sheet4.add(this.getString(R.string.gg6));
        sheet4.add(this.getString(R.string.gg7));
        sheet4.add(this.getString(R.string.gg8));
        sheet4.add(this.getString(R.string.gg9));
        sheet4.add(this.getString(R.string.gg10));
        sheet4.add(this.getString(R.string.gg11));
        sheet4.add(this.getString(R.string.gg12));
        sheet4.add(this.getString(R.string.gg13));
        sheet4.add(this.getString(R.string.gg14));
        sheet4.add(this.getString(R.string.gg15));
        sheet4.add(this.getString(R.string.gg16));
        sheet4.add(this.getString(R.string.gg17));
        sheet4.add(this.getString(R.string.gg18));
        sheet4.add(this.getString(R.string.gg19));
        sheet4.add(this.getString(R.string.gg20));
        sheet4.add(this.getString(R.string.gg21));


        ArrayList<String> sheet5 = new ArrayList<>();

        sheet5.add(this.getString(R.string.mp1));
        sheet5.add(this.getString(R.string.mp2));
        sheet5.add(this.getString(R.string.mp3));
        sheet5.add(this.getString(R.string.mp4));
        sheet5.add(this.getString(R.string.mp5));
        sheet5.add(this.getString(R.string.mp6));
        sheet5.add(this.getString(R.string.mp7));
        sheet5.add(this.getString(R.string.mp8));
        sheet5.add(this.getString(R.string.mp9));
        sheet5.add(this.getString(R.string.mp10));
        sheet5.add(this.getString(R.string.mp11));
        sheet5.add(this.getString(R.string.mp12));
        sheet5.add(this.getString(R.string.mp13));
        sheet5.add(this.getString(R.string.mp14));
        sheet5.add(this.getString(R.string.mp15));
        sheet5.add(this.getString(R.string.mp16));
        sheet5.add(this.getString(R.string.mp17));
        sheet5.add(this.getString(R.string.mp18));
        sheet5.add(this.getString(R.string.mp19));

        ArrayList<String> sheet6 = new ArrayList<>();

        sheet6.add(this.getString(R.string.p61));
        sheet6.add(this.getString(R.string.p62));
        sheet6.add(this.getString(R.string.p63));
        sheet6.add(this.getString(R.string.p64));
        sheet6.add(this.getString(R.string.p65));
        sheet6.add(this.getString(R.string.p66));
        sheet6.add(this.getString(R.string.p67));
        sheet6.add(this.getString(R.string.p68));
        sheet6.add(this.getString(R.string.p69));
        sheet6.add(this.getString(R.string.p610));
        sheet6.add(this.getString(R.string.p611));
        sheet6.add(this.getString(R.string.p612));
        sheet6.add(this.getString(R.string.p613));
        sheet6.add(this.getString(R.string.p614));
        sheet6.add(this.getString(R.string.p615));
        sheet6.add(this.getString(R.string.p616));
        sheet6.add(this.getString(R.string.p617));
        sheet6.add(this.getString(R.string.p618));

        ArrayList<String> sheet7 = new ArrayList<>();
        sheet7.add(this.getString(R.string.sp1));
        sheet7.add(this.getString(R.string.sp2));
        sheet7.add(this.getString(R.string.sp3));
        sheet7.add(this.getString(R.string.sp4));
        sheet7.add(this.getString(R.string.sp5));
        sheet7.add(this.getString(R.string.sp6));
        sheet7.add(this.getString(R.string.sp7));
        sheet7.add(this.getString(R.string.sp8));
        sheet7.add(this.getString(R.string.sp9));
        sheet7.add(this.getString(R.string.sp10));
        sheet7.add(this.getString(R.string.sp11));
        sheet7.add(this.getString(R.string.sp12));
        sheet7.add(this.getString(R.string.sp13));
        sheet7.add(this.getString(R.string.sp14));
        sheet7.add(this.getString(R.string.sp15));
        sheet7.add(this.getString(R.string.sp16));
        sheet7.add(this.getString(R.string.sp17));
        sheet7.add(this.getString(R.string.sp18));
        sheet7.add(this.getString(R.string.sp19));
        sheet7.add(this.getString(R.string.sp20));
        sheet7.add(this.getString(R.string.sp21));
        sheet7.add(this.getString(R.string.sp22));


        ArrayList<String> sheet8 = new ArrayList<>();
        sheet8.add(this.getString(R.string.ssp2));
        sheet8.add(this.getString(R.string.ssp3));
        sheet8.add(this.getString(R.string.ssp4));
        sheet8.add(this.getString(R.string.ssp5));
        sheet8.add(this.getString(R.string.ssp6));
        sheet8.add(this.getString(R.string.ssp7));
        sheet8.add(this.getString(R.string.ssp8));
        sheet8.add(this.getString(R.string.ssp9));
        sheet8.add(this.getString(R.string.ssp10));
        sheet8.add(this.getString(R.string.ssp11));
        sheet8.add(this.getString(R.string.ssp12));
        sheet8.add(this.getString(R.string.ssp13));
        sheet8.add(this.getString(R.string.ssp14));
        sheet8.add(this.getString(R.string.ssp15));
        sheet8.add(this.getString(R.string.ssp16));
        sheet8.add(this.getString(R.string.ssp17));
        sheet8.add(this.getString(R.string.ssp18));
        sheet8.add(this.getString(R.string.ssp19));
        sheet8.add(this.getString(R.string.ssp20));
        sheet8.add(this.getString(R.string.ssp21));
        sheet8.add(this.getString(R.string.ssp22));
        sheet8.add(this.getString(R.string.ssp23));
        sheet8.add(this.getString(R.string.ssp24));
        sheet8.add(this.getString(R.string.ssp25));
        sheet8.add(this.getString(R.string.ssp26));
        sheet8.add(this.getString(R.string.ssp27));
        sheet8.add(this.getString(R.string.ssp28));
        sheet8.add(this.getString(R.string.ssp29));
        sheet8.add(this.getString(R.string.ssp30));

        ArrayList<ArrayList> sheets_main = new ArrayList<>();
        sheets_main.add(sheet1);
        sheets_main.add(sheet2);
        sheets_main.add(sheet3);
        sheets_main.add(sheet4);
        sheets_main.add(sheet5);
        sheets_main.add(sheet6);
        sheets_main.add(sheet7);
        sheets_main.add(sheet8);

        int counter;
        int inner_counter;
        items = GlobalClass.chart_params;
        ArrayList<String> final_sheets = new ArrayList<>();
        ArrayList<Integer> inner_counts = new ArrayList<>();

        for (String item : items) {
            counter = 1;
            //items_line_dataset.add(new LineDataSet(null, item));
            outerloop:
            for (ArrayList sheet_i : sheets_main) {
                inner_counter = 0;
                for (Object item_i : sheet_i) {

                    if (item_i.toString().equals(item)) {

                        switch (counter) {
                            case 1:
                                final_sheets.add("GT_Log");
                                inner_counts.add(inner_counter);
                                inner_counter = 0;
                                break;
                            case 2:
                                final_sheets.add("FO");
                                inner_counts.add(inner_counter);
                                inner_counter = 0;
                                break;
                            case 3:
                                final_sheets.add("Generator_Board");
                                inner_counts.add(inner_counter);
                                inner_counter = 0;
                                break;
                            case 4:
                                final_sheets.add("Generation");
                                inner_counts.add(inner_counter);
                                inner_counter = 0;
                                break;
                            case 5:
                                final_sheets.add("Mark_V");
                                inner_counts.add(inner_counter);
                                inner_counter = 0;
                                break;
                            case 6:
                                final_sheets.add("Log_Sheet_6");
                                inner_counts.add(inner_counter);
                                inner_counter = 0;
                                break;
                            case 7:
                                final_sheets.add("LogSheet20_A");
                                inner_counts.add(inner_counter);
                                inner_counter = 0;
                                break;
                            case 8:
                                final_sheets.add("LogSheet20_B");
                                inner_counts.add(inner_counter);
                                inner_counter = 0;
                                break;
                        }
                        break outerloop;
                    }
                    inner_counter = inner_counter + 1;
                }
                counter = counter + 1;
            }
        }

        Set<String> set = new HashSet<>(final_sheets);
        //final_sheets.clear();

        ArrayList<String> unique_sheets = new ArrayList<>();
        unique_sheets.addAll(set);

        // Working to link any label in items arraylist with its parent sheet: --- to put it in get reference path:
        System.out.println("Number of items are: " + final_sheets.size());
        for (String sheet_i2 : final_sheets) {
            DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/" + sheet_i2);

            for (String item : items) {
                draw_chart(ref2, item, inner_counts.get(k));}
        }

          /*
        LineDataSet lineDataSet = new LineDataSet(null, "Phase A Current Amp");
        LineDataSet plineDataSet1 = new LineDataSet(null, "Phase B Current Amp");
        LineDataSet vlineDataSet2 = new LineDataSet(null, "Phase C Current Amp");
        LineDataSet tlineDataSet3 = new LineDataSet(null, "Excitation Voltage V");




        testing = GlobalClass.chart_params;

        for (int i = 0; i < testing.size(); i++) {
            System.out.println(testing.get(i));

            DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/" + sheet);
            LineDataSet lineDataSet = new LineDataSet(null, testing.get(i));

        }**/

        Utils.init(this);

        Data_of_Temp = (LineChart) findViewById(R.id.Data_of_Temp);
        Data_of_Temp.animateX(1000);
    }

    @Override
    protected void onStart() {
        super.onStart();

}
    public void draw_chart(DatabaseReference ref2, String item, Integer integer){
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


        ref2.addValueEventListener(new ValueEventListener() {
            int i = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> datavals = new ArrayList<Entry>();

                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                        DataS2 data = mydatasnapshot.getValue(DataS2.class);
                        switch (integer){
                            case 0:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp1()));
                                i = i + 1;
                                break;
                            case 1:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp2()));
                                i = i + 1;
                                break;
                            case 2:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp3()));
                                i = i + 1;
                                break;
                            case 3:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp4()));
                                i = i + 1;
                                break;
                            case 4:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp5()));
                                i = i + 1;
                                break;
                            case 5:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp6()));
                                i = i + 1;
                                break;
                            case 6:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp7()));
                                i = i + 1;
                                break;
                            case 7:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp8()));
                                i = i + 1;
                                break;
                            case 8:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp9()));
                                i = i + 1;
                                break;
                            case 9:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp10()));
                                i = i + 1;
                                break;
                            case 10:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp11()));
                                i = i + 1;
                                break;
                            case 11:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp12()));
                                i = i + 1;
                                break;
                            case 12:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp13()));
                                i = i + 1;
                                break;
                            case 13:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp14()));
                                i = i + 1;
                                break;
                            case 14:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp15()));
                                i = i + 1;
                                break;
                            case 15:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp16()));
                                i = i + 1;
                                break;
                            case 16:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp17()));
                                i = i + 1;
                                break;
                            case 17:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp18()));
                                i = i + 1;
                                break;
                            case 18:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp19()));
                                i = i + 1;
                                break;
                            case 19:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp20()));
                                i = i + 1;
                                break;
                            case 20:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp21()));
                                i = i + 1;
                                break;
                            case 21:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp22()));
                                i = i + 1;
                                break;
                            case 22:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp23()));
                                i = i + 1;
                                break;
                            case 23:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp24()));
                                i = i + 1;
                                break;
                            case 24:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp25()));
                                i = i + 1;
                                break;
                            case 25:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp26()));
                                i = i + 1;
                                break;
                            case 26:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp27()));
                                i = i + 1;
                                break;
                            case 27:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp28()));
                                i = i + 1;
                                break;
                            case 28:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp29()));
                                i = i + 1;
                                break;
                            case 29:
                                datavals.add(new Entry(name.get(i).getTime(), data.getIp30()));
                                i = i + 1;
                                break;
                        }
                    }

                    LineDataSet lineDataSet = new LineDataSet(null, item);
                    lineDataSet.setValues(datavals);
                    lineDataSet.setDrawCircleHole(false);
                    lineDataSet.setLineWidth(3f);

                    //int a = getRandomNumberUsingInts(10,230);
                    //int b = getRandomNumberUsingInts(10,230);
                    //int c = getRandomNumberUsingInts(10,230);
                    k = k + 1;

                    //int rand = new Random().nextInt(colors.size());
                    try {
                        lineDataSet.setColor(colors.get(k));
                    }catch (Exception e){}
                    outer_counter = outer_counter + 1;
                    iLineDataSets.add(lineDataSet);
                    lineData = new LineData(iLineDataSets);

                    long referenceTimestamp = 1451660400; //name.get(0).getTime();
                    AxisValueFormatter xAxisFormatter = new HourAxisValueFormatter(referenceTimestamp);
                    XAxis xAxis = Data_of_Temp.getXAxis();
                    xAxis.setValueFormatter(xAxisFormatter);

                    //MyMarkerView myMarkerView= new MyMarkerView(getApplicationContext(), R.layout.my_marker_view_layout, referenceTimestamp);
                    //mChart.setMarkerView(myMarkerView);
                    Data_of_Temp.clear();
                    Data_of_Temp.setData(lineData);

                    Legend legend = Data_of_Temp.getLegend();
                    legend.setEnabled(true);
                    Data_of_Temp.invalidate();

                }else{
                    //Data_of_Temp.clear();
                    Data_of_Temp.invalidate();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}


