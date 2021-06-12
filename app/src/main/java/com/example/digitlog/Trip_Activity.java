
package com.example.digitlog;

import android.app.Activity;
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

public class Trip_Activity extends AppCompatActivity implements TimePicker.OnTimeChangedListener, DatePickerDialog.OnDateSetListener {
    EditText category, comment;
    TextView title, engine_n, datetimetv, alarms, description5;
    private StorageReference storageRef;
    String Categoy, description, cat;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Dialog dialog;
    String conseq;
    String day, monthe, yeare, houre, minutee;


    Button save;
    CheckBox ldo, hcgo;
    String engine, f_co, t_co, user_2, category_type;
    Trip_Class trip_class;
    ImageView image;
    String checkchoise;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
    String currentdateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_);

        radioGroup = (RadioGroup) findViewById(R.id.rg);

        ldo = (CheckBox) findViewById(R.id.ldo);
        hcgo = (CheckBox) findViewById(R.id.hcgo);

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


        try {
            Categoy = getIntent().getStringExtra("Categoy");
            description = getIntent().getStringExtra("description");
            String comment5 = getIntent().getStringExtra("comment");
            comment.setText(comment5);

        }catch (Exception ex){

        }

        trip_class = new Trip_Class();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

            }
        });

    }

    public void save_function(){
        String dateformat5;
        dateformat5 = yeare + "_" + monthe + "_" + day + " " + houre + ":" + minutee + ":00";
        datetimetv.setText(dateformat5);

        int selectedId=radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedId);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/tips_log");
/*
        if (comment.getText().toString() != null && !comment.getText().toString().isEmpty()) {
            t_co = comment.getText().toString();
        } else {
            t_co = "NSet";
        }
**/
        // Trip_Class(String load, String fuel, String user_2, String comment,String datetime, String alarms)
        trip_class = new Trip_Class(radioButton.getText().toString(), checkchoise,user_2, description5.getText().toString(), dateformat5, alarms.getText().toString());

        ref2.child(sdf.format(new Date()).trim()).setValue(trip_class);
        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
        this.finish();

    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.ldo:
                if (checked) {
                    checkchoise = "LDO";
                }
            else {
                    // Remove the meat
                }
                break;
            case R.id.hcgo:
                if (checked) {
                    checkchoise = "HCGO";
                }
            else {
                    // I'm lactose intolerant
                }
                break;
            // TODO: Veggie sandwich
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        day = String.valueOf(dayOfMonth);
        monthe = String.valueOf(month);
        yeare = String.valueOf(year);


    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        houre = String.valueOf(hourOfDay);
        minutee = String.valueOf(minute);
    }
}
