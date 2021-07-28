
package com.example.digitlog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Trip_Activity extends AppCompatActivity  {
    EditText category, comment;
    TextView title, engine_n, datetimetv, alarms, description5;
    private StorageReference storageRef;
    String Categoy, description, cat;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Dialog dialog;
    String conseq;
    String day, monthe, yeare, houre, minutee;
    String dateformat5;
    int ho, mi, ye, mo, da;

    Button save;
    CheckBox ldo, hcgo;
    String engine, f_co, t_co, user_2, category_type;
    Trip_Class trip_class;
    ImageView image;
    String checkchoise;
    Dialog dialog2;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
    String currentdateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_);


        dialog2 = new Dialog(Trip_Activity.this);
        dialog2.setContentView(R.layout.custom_dialoge_feedback2);
        dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok2 = dialog2.findViewById(R.id.save);
        Button cancel2 = dialog2.findViewById(R.id.cancel);
        ok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Trip_Activity.this.finishAffinity();
            }
        });
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });


        radioGroup = (RadioGroup) findViewById(R.id.rg);

        ldo = (CheckBox) findViewById(R.id.ldo);
        hcgo = (CheckBox) findViewById(R.id.hcgo);

        try{
            if (GlobalClass.engine_number.equals("Engine_3")){

            }
        }catch (Exception exception){
            startActivity(new Intent(Trip_Activity.this, Blocks.class));
        }

        if (GlobalClass.engine_number.equals("Engine_3")) {

            ldo.setText("HSRG_1");
            hcgo.setText("HSRG_2");
        }

        description5 = (TextView) findViewById(R.id.description);
        alarms = (TextView) findViewById(R.id.alarms);
        datetimetv = (TextView) findViewById(R.id.datetimetv);



        dialog = new Dialog(Trip_Activity.this);
        dialog.setContentView(R.layout.custom_dialoge_feedback);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.save);
        Button cancel = dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save_function();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        //spinner.setId();
        //spinner.setText(categy);
        //comment.setText(description);

        title = (TextView) findViewById(R.id.title);
        engine_n = (TextView) findViewById(R.id.engine_n);

        //category = (EditText) findViewById(R.id.c_code);
        comment = (EditText) findViewById(R.id.comment_code);
        save = (Button) findViewById(R.id.sub_btn);

        engine = GlobalClass.engine_number;
        user_2 = GlobalClass.actual_user_name;
        //category_type = GlobalClass.Faults_Category;
        engine_n.setText(engine);

/*
        try {
            Categoy = getIntent().getStringExtra("Categoy");
            description = getIntent().getStringExtra("description");
            String comment5 = getIntent().getStringExtra("comment");
            comment.setText(comment5);

        }catch (Exception ex){

        }
**/
        trip_class = new Trip_Class();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                dialog.show();

            }
        });

    }

    public void save_function() {
        //new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH)
        dateformat5 = yeare + "_" + monthe + "_" + day + " " + houre + ":" + minutee + ":00";

        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/tips_log");

        // Trip_Class(String load, String fuel, String user_2, String comment,String datetime, String alarms)

        try{
            if (GlobalClass.engine_number.equals("Engine_3")){

            }
        }catch (Exception exception){
            startActivity(new Intent(Trip_Activity.this, Blocks.class));
        }

        if (GlobalClass.engine_number.equals("Engine_3")) {

            if (ldo.isChecked() & hcgo.isChecked()) {
                checkchoise = "HSRG_1, HSRG_2";
            } else if (ldo.isChecked() & !hcgo.isChecked()) {
                checkchoise = "HSRG_1";
            } else {
                checkchoise = "HSRG_2";
            }
        } else {

            if (ldo.isChecked() & hcgo.isChecked()) {
                checkchoise = "LDO, HCGO";
            } else if (ldo.isChecked() & !hcgo.isChecked()) {
                checkchoise = "LDO";
            } else {
                checkchoise = "HCGO";
            }
        }



        trip_class = new Trip_Class(radioButton.getText().toString(), checkchoise,user_2, description5.getText().toString(), dateformat5, alarms.getText().toString());

        ref2.child(sdf.format(new Date()).trim()).setValue(trip_class);
        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
        this.finish();

    }

    public void showTimePickerDialog(View v) {
       // DialogFragment newFragment = new TimePickerFragment();
        //newFragment.show(getSupportFragmentManager(), "timePicker");

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                houre = String.valueOf(hourOfDay);
                ho = hourOfDay;
                mi = minute;
                minutee = String.valueOf(minute);
                try {
                    datetimetv.setText(yeare + "_" + monthe + "_" + day + " " + houre + ":" + minutee + ":00");
                }catch (Exception ex){

                }
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this, AlertDialog.THEME_DEVICE_DEFAULT_DARK, onTimeSetListener,
                ho, mi,true);

        timePickerDialog.setTitle("Select Time:");
        timePickerDialog.show();

    }
    public void showDatePickerDialog(View v) {
        //DialogFragment newFragment = new DatePickerFragment();
        //newFragment.show(getSupportFragmentManager(), "datePicker");

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                yeare = String.valueOf(year);
                day = String.valueOf(dayOfMonth);
                monthe = String.valueOf(month);
                ye = year;
                mo = month;
                da = dayOfMonth;
            }
        };

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, mYear, mMonth, mDay);
        datePickerDialog.setTitle("Select Date:");
        datePickerDialog.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



    public void go_home(View view) {
        startActivity(new Intent(Trip_Activity.this, Dashboard_Engines.class));
    }

    public void go_out(View view) {
        dialog2.show();

    }


}



