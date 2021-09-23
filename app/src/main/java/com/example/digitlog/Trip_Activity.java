
package com.example.digitlog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.media.Image;
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
    ImageView help;
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
    TextView fuel_nasr;
    String current_date;
    private static final int CAMERA_REQUEST_CODE = 1;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
    String currentdateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_);
        fuel_nasr = (TextView) findViewById(R.id.fuel_nasr);

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);

        help = (ImageView) findViewById(R.id.help);

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

        current_date = sdf.format(new Date()).trim();
        radioGroup = (RadioGroup) findViewById(R.id.rg);

        ldo = (CheckBox) findViewById(R.id.ldo);
        hcgo = (CheckBox) findViewById(R.id.hcgo);

        if ((GlobalClass.engine_number.equals("ST_1")) |(GlobalClass.engine_number.equals("ST_2")) |
                (GlobalClass.engine_number.equals("ST_3")) |(GlobalClass.engine_number.equals("ST_4"))){
            help.setVisibility(View.INVISIBLE);
        }


        try{
            if (GlobalClass.engine_number.equals("ST_1")){

            }
        }catch (Exception exception){
            startActivity(new Intent(Trip_Activity.this, Blocks.class));
        }

        if (GlobalClass.engine_number.equals("ST_1")) {

            ldo.setText("HSRG_1");
            hcgo.setText("HSRG_2");
            fuel_nasr.setText("Run with");
        }
        if (GlobalClass.engine_number.equals("ST_2")) {

            ldo.setText("HSRG_3");
            hcgo.setText("HSRG_4");
            fuel_nasr.setText("Run with");
        }
        if (GlobalClass.engine_number.equals("ST_3")) {

            ldo.setText("HSRG_5");
            hcgo.setText("HSRG_6");
            fuel_nasr.setText("Run with");
        }
        if (GlobalClass.engine_number.equals("ST_4")) {

            ldo.setText("HSRG_7");
            hcgo.setText("HSRG_8");
            fuel_nasr.setText("Run with");
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

        title = (TextView) findViewById(R.id.title);
        engine_n = (TextView) findViewById(R.id.engine_n);

        comment = (EditText) findViewById(R.id.comment_code);
        save = (Button) findViewById(R.id.sub_btn);
        image = (ImageView) findViewById(R.id.im_v);

        engine = GlobalClass.engine_number;
        user_2 = GlobalClass.actual_user_name;
        engine_n.setText(engine);

        trip_class = new Trip_Class();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        storageRef = FirebaseStorage.getInstance().getReference();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        byte bb[] = bytes.toByteArray();
        image.setImageBitmap(thumbnail);
        uploadToFirebase(bb);
    }


    private void uploadToFirebase(byte[] bb) {
        StorageReference filepath = storageRef.child("Trips/" + engine + "/" + "/" + current_date + ".jpeg");

        filepath.putBytes(bb).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(Trip_Activity.this, "Successfully !", Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Trip_Activity.this, "Failed !", Toast.LENGTH_LONG).show();
            }
        });
        String image_url = filepath.getDownloadUrl().toString();
        System.out.println(image_url);


    }

    public void select_image(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        }
    }

    public void save_function() {
        //new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH)
        dateformat5 = yeare + "_" + monthe + "_" + day + " " + houre + ":" + minutee + ":00";

        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference("data2/" + engine + "/tips_log");

        // Trip_Class(String load, String fuel, String user_2, String comment,String datetime, String alarms)

        try{
            if (GlobalClass.engine_number.equals("ST_1")){

            }
        }catch (Exception exception){
            startActivity(new Intent(Trip_Activity.this, Blocks.class));
        }

        if ((GlobalClass.engine_number.equals("ST_1"))){

            if (ldo.isChecked() & hcgo.isChecked()) {
                checkchoise = "HSRG_1, HSRG_2";
            } else if (ldo.isChecked() & !hcgo.isChecked()) {
                checkchoise = "HSRG_1";
            } else {
                checkchoise = "HSRG_2";}
            } else if (GlobalClass.engine_number.equals("ST_2")){
                if (ldo.isChecked() & hcgo.isChecked()) {
                    checkchoise = "HSRG_3, HSRG_4";
                } else if (ldo.isChecked() & !hcgo.isChecked()) {
                    checkchoise = "HSRG_3";
                } else {
                    checkchoise = "HSRG_4";
            }
        } else if (GlobalClass.engine_number.equals("ST_3")){
            if (ldo.isChecked() & hcgo.isChecked()) {
                checkchoise = "HSRG_5, HSRG_6";
            } else if (ldo.isChecked() & !hcgo.isChecked()) {
                checkchoise = "HSRG_5";
            } else {
                checkchoise = "HSRG_6";
            }
        } else if (GlobalClass.engine_number.equals("ST_4")){
            if (ldo.isChecked() & hcgo.isChecked()) {
                checkchoise = "HSRG_7, HSRG_8";
            } else if (ldo.isChecked() & !hcgo.isChecked()) {
                checkchoise = "HSRG_7";
            } else {
                checkchoise = "HSRG_8";
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



        trip_class = new Trip_Class(radioButton.getText().toString(), checkchoise,user_2, description5.getText().toString(), dateformat5, alarms.getText().toString(),
                "Trips/" + engine + "/" + current_date);

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
        startActivity(new Intent(Trip_Activity.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog2.show();

    }


    public void go_to_manual(View view) {
        startActivity(new Intent(Trip_Activity.this, Troubleshooting.class));
    }
}



