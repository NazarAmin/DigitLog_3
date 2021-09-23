package com.example.digitlog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Date;
import java.util.Locale;

public class Dashboard_problem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText category, comment;
    TextView title, engine_n;
    private StorageReference storageRef;
    String Categoy, description, cat;
    Spinner spinner;
    Spinner spinner2;
    Dialog dialog, dialog2;
    String conseq;
    String current_date;
    ImageView help;


    private Uri mImageUri = null;

    private static final int GALLERY_REQUEST = 1;

    private static final int CAMERA_REQUEST_CODE = 1;

    Button capture, save;
    String engine, f_co, t_co, user_2, category_type;
    Faults_Trips faults_trips;
    ImageView image;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
    String currentdateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_problems);
        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);
        dialog2 = new Dialog(Dashboard_problem.this);
        dialog2.setContentView(R.layout.custom_dialoge_feedback2);

        help = (ImageView) findViewById(R.id.help2);

        if ((GlobalClass.engine_number.equals("ST_1")) |(GlobalClass.engine_number.equals("ST_2")) |
                (GlobalClass.engine_number.equals("ST_3")) |(GlobalClass.engine_number.equals("ST_4"))){
            help.setVisibility(View.INVISIBLE);
        }

        dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button ok2 = dialog2.findViewById(R.id.save);
        Button cancel2 = dialog2.findViewById(R.id.cancel);
        ok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dashboard_problem.this.finishAffinity();
            }
        });
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner_c);

        current_date = sdf.format(new Date()).trim();

        dialog = new Dialog(Dashboard_problem.this);
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

        storageRef = FirebaseStorage.getInstance().getReference();

        title = (TextView) findViewById(R.id.title);
        engine_n = (TextView) findViewById(R.id.engine_n);

        //category = (EditText) findViewById(R.id.c_code);
        comment = (EditText) findViewById(R.id.comment_code);
        capture = (Button) findViewById(R.id.photo_but);
        save = (Button) findViewById(R.id.sub_btn);
        image = (ImageView) findViewById(R.id.im_v);

        engine = GlobalClass.engine_number;
        user_2 = GlobalClass.actual_user_name;
        //category_type = GlobalClass.Faults_Category;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/faults_trips");

        engine_n.setText(engine);

        ArrayList<String> categories = new ArrayList<>();
        categories.add("Trip");
        categories.add("Forced Shutdown");
        categories.add("Planned Shutdown");
        categories.add("Not Significant");

        ArrayList<String> categories2 = new ArrayList<>();
        categories2.add("Fault");
        categories2.add("Site Observation");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);
        spinner2.setOnItemSelectedListener(this);

          try {
        Categoy = getIntent().getStringExtra("Categoy");
        description = getIntent().getStringExtra("description");
        String comment5 = getIntent().getStringExtra("comment");
        setSpinText(spinner2, Categoy);
        setSpinText(spinner, description);
        comment.setText(comment5);

        //Toast.makeText(getApplicationContext(), Categoy, Toast.LENGTH_LONG).show();
         }catch (Exception ex){

           }

        faults_trips = new Faults_Trips();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            dialog.show();

            }
        });

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


    public void save_function(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/faults_trips");

        if (comment.getText().toString() != null && !comment.getText().toString().isEmpty()) {
            t_co = comment.getText().toString();
        } else {
            t_co = "NSet";
        }

        faults_trips = new Faults_Trips(cat, conseq,user_2, t_co, sdf.format(new Date()).trim(), "Faults_Trips/" + engine + "/" +
                cat + "/" + current_date);

        ref2.child(sdf.format(new Date()).trim()).setValue(faults_trips);
        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
        this.finish();

    }

    private void uploadToFirebase(byte[] bb) {
        StorageReference filepath = storageRef.child("Faults_Trips/" + engine + "/" +
                cat + "/" + current_date + ".jpeg");

        filepath.putBytes(bb).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(Dashboard_problem.this, "Successfully !", Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Dashboard_problem.this, "Failed !", Toast.LENGTH_LONG).show();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner)parent;
        Spinner spinner2 = (Spinner)parent;
        if(spinner.getId() == R.id.spinner)
        {
            conseq = parent.getItemAtPosition(position).toString();
        }
        if(spinner2.getId() == R.id.spinner_c)
        {
            cat = parent.getItemAtPosition(position).toString();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setSpinText(Spinner spin, String text)
    {
        for(int i= 0; i < spin.getAdapter().getCount(); i++)
        {
             if(spin.getAdapter().getItem(i).toString().contains(text))
            {
                spin.setSelection(i);
            }
        }

    }
    public void go_home(View view) {
        startActivity(new Intent(Dashboard_problem.this, Blocks.class));
    }

    public void go_out(View view) {
        dialog2.show();

    }

    public void go_to_manual(View view) {
        startActivity(new Intent(Dashboard_problem.this, Troubleshooting.class));
    }

}
