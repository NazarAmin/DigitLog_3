package com.example.digitlog;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Mid_Night_Report_ST extends AppCompatActivity {

    //private EditText editTextExcel;
    ArrayList<String> name = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd", Locale.ENGLISH);
    String engine = GlobalClass.engine_number;

    private File filePath2 = new File(Environment.getExternalStorageDirectory() + "/Digit Log/Reports/Mid Night/" + engine + " Mid_Night Report " + sdf.format(new Date()) + ".xls");
    private File filePath = new File(Environment.getExternalStorageDirectory(), "Digit Log/Reports/Mid Night");
    String file_path_string = Environment.getExternalStorageDirectory().toString() + "Digit Log/Reports/Mid Night";

    HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel__export);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    && (getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                getDatawork();
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
                getDatawork();
            }else{
                Toast.makeText(getApplicationContext(),"Permissions Denied", Toast.LENGTH_LONG).show();
                this.finish();
            }
        }
    }

    private void getDatawork(){

        HSSFSheet hssfSheet = hssfWorkbook.createSheet("MidNight Report");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/mid_night");
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

                HSSFRow hssfRow2 = hssfSheet.createRow(0);
                HSSFCell hssfCell1 = hssfRow2.createCell(0);
                hssfCell1.setCellValue("Date");
                HSSFCell hssfCell2 = hssfRow2.createCell(1);
                hssfCell2.setCellValue("Cumulative Generation MWH");
                HSSFCell hssfCell3 = hssfRow2.createCell(2);
                hssfCell3.setCellValue("Cumulative Generation MVAR");
                HSSFCell hssfCell4 = hssfRow2.createCell(3);
                hssfCell4.setCellValue("Number of Starts");
                HSSFCell hssfCell5 = hssfRow2.createCell(4);
                hssfCell5.setCellValue("Dem Water Tons");


                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {

                        Mid_Night_Class_ST data = mydatasnapshot.getValue(Mid_Night_Class_ST.class);

                        HSSFRow hssfRow = hssfSheet.createRow(i + 1);
                        HSSFCell hssfCell = hssfRow.createCell(0);
                        hssfCell.setCellValue(name.get(i));


                        HSSFCell col2 = hssfRow.createCell(1);
                        col2.setCellValue(String.valueOf(data.getGen()));

                        HSSFCell col3 = hssfRow.createCell(2);
                        col3.setCellValue(String.valueOf(data.getGen_mv()));


                        HSSFCell col4 = hssfRow.createCell(3);
                        col4.setCellValue(String.valueOf(data.getStarts()));

                        HSSFCell col5 = hssfRow.createCell(4);
                        col5.setCellValue(String.valueOf(data.getDem_w()));




                        i = i + 1;

                    }

                    try {
                        if (!filePath.exists()) {

                            filePath.mkdirs();
                            File f1 = new File(Environment.getExternalStorageDirectory() + "/Digit Log/Reports", "Mid Night");
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
                        Toast.makeText(getApplicationContext(), "Problem", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                        startActivity(new Intent(Mid_Night_Report_ST.this, Dashboard_chart.class));
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void openFolder(){

        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), Mid_Night_Report_ST.this.getApplicationContext().getPackageName() + ".provider", filePath2);
        Toast.makeText(getApplicationContext(), "Excel file saved in: " + file_path_string, Toast.LENGTH_LONG).show();

        intent.setDataAndType(photoURI,"application/vnd.ms-excel");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);


    }


}

