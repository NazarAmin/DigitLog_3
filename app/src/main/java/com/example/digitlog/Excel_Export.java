package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.github.mikephil.charting.data.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.grpc.util.GracefulSwitchLoadBalancer;

public class Excel_Export extends AppCompatActivity {

    //private EditText editTextExcel;
    ArrayList<String> name = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd", Locale.ENGLISH);
    String engine = GlobalClass.engine_number;
    private File filePath2 = new File(Environment.getExternalStorageDirectory() + "/Digit Log/Reports/Engine Parameters/" + engine + " Parameters Report " + sdf.format(new Date()) + ".xls");

    private File filePath = new File(Environment.getExternalStorageDirectory(), "Digit Log/Reports/Engine Parameters");
    String file_path_string = Environment.getExternalStorageDirectory().toString() + "Digit Log/Reports/Engine Parameters";
    HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel__export);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            && (getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            prepare_work();
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)){
                prepare_work();
            }else{
                Toast.makeText(getApplicationContext(),"Permissions Denied", Toast.LENGTH_LONG).show();
                this.finish();
            }
        }
    }


    public void prepare_work(){
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
        sheet8.add(this.getString(R.string.ssp1));
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


        ArrayList<String> sheet9 = new ArrayList<>();
        sheet9.add(this.getString(R.string.qsp2));
        sheet9.add(this.getString(R.string.qsp3));
        sheet9.add(this.getString(R.string.qsp4));
        sheet9.add(this.getString(R.string.qsp5));
        sheet9.add(this.getString(R.string.qsp6));
        sheet9.add(this.getString(R.string.qsp7));
        sheet9.add(this.getString(R.string.qsp8));
        sheet9.add(this.getString(R.string.qsp9));
        sheet9.add(this.getString(R.string.qsp10));
        sheet9.add(this.getString(R.string.qsp11));
        sheet9.add(this.getString(R.string.qsp12));
        sheet9.add(this.getString(R.string.qsp13));
        sheet9.add(this.getString(R.string.qsp14));
        sheet9.add(this.getString(R.string.qsp15));
        sheet9.add(this.getString(R.string.qsp16));
        sheet9.add(this.getString(R.string.qsp17));
        sheet9.add(this.getString(R.string.qsp18));
        sheet9.add(this.getString(R.string.qsp19));
        sheet9.add(this.getString(R.string.qsp20));
        sheet9.add(this.getString(R.string.qsp21));
        sheet9.add(this.getString(R.string.qsp22));

        ArrayList<ArrayList> sheets_main = new ArrayList<>();
        sheets_main.add(sheet1);
        sheets_main.add(sheet2);
        sheets_main.add(sheet3);
        sheets_main.add(sheet4);
        sheets_main.add(sheet5);
        sheets_main.add(sheet6);
        sheets_main.add(sheet7);
        sheets_main.add(sheet9);
        sheets_main.add(sheet8);

        List<String> sheets = Arrays.asList("GT_Log", "FO","Generator_Board","Generation", "Mark_V","Log_Sheet_6");
        List<String> sheets2 = Arrays.asList("HSRG_A","HSRG_B","LogSheet20_B");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        int kn = 0;
        int kso = 6;

        // String engine = GlobalClass.engine_number;
        if ((engine.equals("ST_1")) | (engine.equals("ST_2"))|
                (engine.equals("ST_3")) | (engine.equals("ST_4"))){
            for (String item : sheets2) {
                DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/" + item);

                getDatawork(item, hssfWorkbook, ref2, kn, 0, sheets_main.get(kso));
                kso++;
                kn++;
            }
        }else{
            for (String item : sheets) {
                DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/" + item);
                getDatawork(item, hssfWorkbook, ref2, kn, 1, sheets_main.get(kn));
                kn++;
            }
        }
    }
    private void getDatawork(String item, HSSFWorkbook hssfWorkbook, DatabaseReference ref2, int kn, int type, ArrayList<ArrayList> sheet_nsme) {

            HSSFSheet hssfSheet = hssfWorkbook.createSheet(item);
            int kkl, length5;
            switch (kn){
                case 0:
                    if (type == 0) {
                        length5 = 23;
                    }else{
                        length5 = 22;
                    }
                    break;
                case 1:
                    if (type == 0) {
                        length5 = 31;
                    }else{
                        length5 = 22;
                    }
                    break;
                case 2:
                    length5 = 22;
                    break;
                case 3:
                    length5 = 21;
                    break;
                case 4:
                    length5 = 19;
                    break;
                case 5:
                    length5 = 19;
                    break;
                default:
                    length5 = 16;
                    break;

            }

            HSSFRow hssfRow = hssfSheet.createRow(0);

            for (kkl = 0; kkl<length5; kkl++){

                HSSFCell col = hssfRow.createCell(kkl+1);
               try{
                    col.setCellValue(String.valueOf(sheet_nsme.get(kkl)));

                }catch (Exception e){

                }
            }

            ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
                    name.clear();
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            try {
                                name.add(d.getKey());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

            ref2.addValueEventListener(new ValueEventListener() {
                int i = 0;


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {

                            DataS2 data = mydatasnapshot.getValue(DataS2.class);

                            HSSFRow hssfRow = hssfSheet.createRow(i+1);
                            HSSFCell hssfCell = hssfRow.createCell(0);
                            HSSFCell col = hssfRow.createCell(1);
                            hssfCell.setCellValue(name.get(i));
                            col.setCellValue(String.valueOf(data.getIp1()));

                            HSSFCell col2 = hssfRow.createCell(2);
                            col2.setCellValue(String.valueOf(data.getIp2()));

                            HSSFCell col3 = hssfRow.createCell(3);
                            col3.setCellValue(String.valueOf(data.getIp3()));


                            HSSFCell col4 = hssfRow.createCell(4);
                            col4.setCellValue(String.valueOf(data.getIp4()));


                            HSSFCell col5 = hssfRow.createCell(5);
                            col5.setCellValue(String.valueOf(data.getIp5()));

                            HSSFCell col6 = hssfRow.createCell(6);
                            col6.setCellValue(String.valueOf(data.getIp6()));

                            HSSFCell col7 = hssfRow.createCell(7);
                            col7.setCellValue(String.valueOf(data.getIp7()));

                            HSSFCell col8 = hssfRow.createCell(8);
                            col8.setCellValue(String.valueOf(data.getIp8()));

                            HSSFCell col9 = hssfRow.createCell(9);
                            col9.setCellValue(String.valueOf(data.getIp9()));

                            HSSFCell col10 = hssfRow.createCell(10);
                            col10.setCellValue(String.valueOf(data.getIp10()));

                            HSSFCell col11 = hssfRow.createCell(11);
                            col11.setCellValue(String.valueOf(data.getIp11()));

                            HSSFCell col12 = hssfRow.createCell(12);
                            col12.setCellValue(String.valueOf(data.getIp12()));

                            HSSFCell col13 = hssfRow.createCell(13);
                            col13.setCellValue(String.valueOf(data.getIp13()));

                            HSSFCell col14 = hssfRow.createCell(14);
                            col14.setCellValue(String.valueOf(data.getIp14()));

                            HSSFCell col15 = hssfRow.createCell(15);
                            col15.setCellValue(String.valueOf(data.getIp15()));

                            HSSFCell col16 = hssfRow.createCell(16);
                            if(String.valueOf(data.getIp16()).equals("0.0")){
                                col16.setCellValue("");
                            }else{
                                col16.setCellValue(String.valueOf(data.getIp16()));
                            }

                            HSSFCell col17 = hssfRow.createCell(17);
                            if(String.valueOf(data.getIp17()).equals("0.0")){
                                col17.setCellValue("");
                            }else {
                                col17.setCellValue(String.valueOf(data.getIp17()));
                            }

                            HSSFCell col18 = hssfRow.createCell(18);
                            if(String.valueOf(data.getIp18()).equals("0.0")){
                                col18.setCellValue("");
                            }else {
                                col18.setCellValue(String.valueOf(data.getIp18()));
                            }

                            HSSFCell col19 = hssfRow.createCell(19);
                            if(String.valueOf(data.getIp19()).equals("0.0")){
                                col19.setCellValue("");
                            }else {
                                col19.setCellValue(String.valueOf(data.getIp19()));
                            }

                            HSSFCell col20 = hssfRow.createCell(20);

                            if(String.valueOf(data.getIp20()).equals("0.0")){
                                col20.setCellValue("");
                            }else {
                                col20.setCellValue(String.valueOf(data.getIp20()));
                            }
                            HSSFCell col21 = hssfRow.createCell(21);
                            if(String.valueOf(data.getIp21()).equals("0.0")) {
                                col21.setCellValue("");
                            }else {
                                col21.setCellValue(String.valueOf(data.getIp21()));
                            }
                            HSSFCell col22 = hssfRow.createCell(22);
                            if(String.valueOf(data.getIp22()).equals("0.0")) {
                                col22.setCellValue("");
                            }else {
                                col22.setCellValue(String.valueOf(data.getIp22()));
                            }
                            HSSFCell col23 = hssfRow.createCell(23);
                            if(String.valueOf(data.getIp23()).equals("0.0")) {
                                col23.setCellValue("");
                            }else {
                                col23.setCellValue(String.valueOf(data.getIp23()));
                            }
                            HSSFCell col24 = hssfRow.createCell(24);
                            if(String.valueOf(data.getIp24()).equals("0.0")) {
                                col24.setCellValue("");
                            }else {
                                col24.setCellValue(String.valueOf(data.getIp24()));
                            }
                            HSSFCell col25 = hssfRow.createCell(25);
                            if(String.valueOf(data.getIp25()).equals("0.0")) {
                                col25.setCellValue("");
                            }else {
                                col25.setCellValue(String.valueOf(data.getIp25()));
                            }
                            HSSFCell col26 = hssfRow.createCell(26);
                            if(String.valueOf(data.getIp26()).equals("0.0")) {
                                col26.setCellValue("");
                            }else {
                                col26.setCellValue(String.valueOf(data.getIp26()));
                            }
                            HSSFCell col27 = hssfRow.createCell(27);
                            if(String.valueOf(data.getIp27()).equals("0.0")) {
                                col27.setCellValue("");
                            }else {
                                col27.setCellValue(String.valueOf(data.getIp27()));
                            }
                            HSSFCell col28 = hssfRow.createCell(28);
                            if(String.valueOf(data.getIp28()).equals("0.0")) {
                                col28.setCellValue("");}else{
                                col28.setCellValue(String.valueOf(data.getIp28()));

                            }

                            HSSFCell col29 = hssfRow.createCell(29);
                            if(String.valueOf(data.getIp29()).equals("0.0")) {
                                col29.setCellValue("");
                            }else {
                                col29.setCellValue(String.valueOf(data.getIp29()));
                            }
                            HSSFCell col30 = hssfRow.createCell(30);
                            if(String.valueOf(data.getIp30()).equals("0.0")) {
                                col30.setCellValue("");
                            }else{

                                col30.setCellValue(String.valueOf(data.getIp30()));

                            }

                            int index_o = 0;
                            if (item.equals("FO")) {
                                index_o = 22;}
                            else if (item.equals("GT_Log")) {
                                index_o = 22;}
                            else if (item.equals("Generation")) {
                                index_o = 22;}
                            else if (item.equals("Mark_V")) {
                                index_o = 20;}
                            else if (item.equals("Generator_Board")) {
                                index_o = 17;}
                            else if (item.equals("Log_Sheet_6")) {
                                index_o = 19;}
                            else if (item.equals("HSRG_A")) {
                                index_o = 22;}
                            else if (item.equals("HSRG_B")) {
                                index_o = 22;}
                            else if (item.equals("LogSheet20_B")) {
                                index_o = 31;}

                            HSSFCell col99 = hssfRow.createCell(index_o);
                            col99.setCellValue(String.valueOf(data.getUser()));
                            i++;
                        }

                            if (type == 0 && kn == 1) {
                                try {
                                    if (!filePath.exists()) {

                                        filePath.mkdirs();
                                        File f1 = new File(Environment.getExternalStorageDirectory() + "/Digit Log/Reports", "Engine Parameters");
                                        if (!f1.exists()) {
                                            f1.mkdirs();
                                            FileOutputStream fileOutputStream = new FileOutputStream(filePath2);

                                            hssfWorkbook.write(fileOutputStream);


                                            if (fileOutputStream != null) {

                                                fileOutputStream.flush();
                                                fileOutputStream.close();
                                            }
                                            Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                                            openFolder();
                                        }
                                    }

                                    FileOutputStream fileOutputStream = new FileOutputStream(filePath2);

                                    hssfWorkbook.write(fileOutputStream);


                                    if (fileOutputStream != null) {

                                        fileOutputStream.flush();
                                        fileOutputStream.close();
                                    }
                                    Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                                    openFolder();

                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Problem, excel might not installed", Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                    startActivity(new Intent(Excel_Export.this, Dashboard_chart.class));
                                }

                            }else if (type == 1 && kn == 5) {
                                try {

                                    if (!filePath.exists()) {

                                        filePath.mkdirs();
                                        File f1 = new File(Environment.getExternalStorageDirectory() + "/Digit Log/Reports", "Engine Parameters");
                                        if (!f1.exists()) {
                                            f1.mkdirs();
                                            FileOutputStream fileOutputStream = new FileOutputStream(filePath2);

                                            hssfWorkbook.write(fileOutputStream);


                                            if (fileOutputStream != null) {

                                                fileOutputStream.flush();
                                                fileOutputStream.close();
                                            }
                                            Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                                            openFolder();
                                        }
                                    }

                                    FileOutputStream fileOutputStream = new FileOutputStream(filePath2);

                                    hssfWorkbook.write(fileOutputStream);


                                    if (fileOutputStream != null) {

                                        fileOutputStream.flush();
                                        fileOutputStream.close();
                                    }
                                    Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                                    openFolder();

                                    //startActivity(new Intent(Excel_Export.this, Dashboard_chart.class));
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Problem", Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                    startActivity(new Intent(Excel_Export.this, Dashboard_chart.class));
                                }

                            }
                        }
                    }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            // System.out.println(i + " is done ######################");



        }

    public void openFolder(){
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

 //       File TEST = new File(Environment.getExternalStorageDirectory(), "TEST");
   //     TEST.mkdir(); // make directory may want to check return value
     //   String path = TEST.getAbsolutePath();

   //     File file = new File(path);
        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), Excel_Export.this.getApplicationContext().getPackageName() + ".provider", filePath2);
        Toast.makeText(getApplicationContext(), "Excel file saved in: " + file_path_string, Toast.LENGTH_LONG).show();

        intent.setDataAndType(photoURI,"application/vnd.ms-excel");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);


        /**
        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri uri = Uri.parse(String.valueOf(Environment.getExternalStorageDirectory()));
              //  + "/Digit Log Data " + engine + "_" + sdf.format(new Date()) + ".xls");
         intent.setType("application/vnd.ms-excel");
         intent.setData(uri);
        startActivity(Intent.createChooser(intent, "Open folder"));

*/
    }


        }

