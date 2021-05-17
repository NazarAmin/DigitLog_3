package com.example.digitlog;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.Base64;
import java.util.Date;

public class Dashboard_problem extends AppCompatActivity {
    EditText category, comment;
    TextView title, engine_n;
    private StorageReference storageRef;
    String categy, description;

    private Uri mImageUri = null;

    private static final int GALLERY_REQUEST = 1;

    private static final int CAMERA_REQUEST_CODE = 1;

    Button capture, save;
    String engine, f_co, t_co, user_2, category_type;
    Faults_Trips faults_trips;
    ImageView image;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
    String currentdateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_problems);

        //categy = getIntent().getStringExtra("Categoy");
        //description = getIntent().getStringExtra("Description");

        //category.setText(categy);
        //comment.setText(description);

        storageRef = FirebaseStorage.getInstance().getReference();

        title = (TextView) findViewById(R.id.title);
        engine_n = (TextView) findViewById(R.id.engine_n);

        category = (EditText) findViewById(R.id.c_code);
        comment = (EditText) findViewById(R.id.comment_code);
        capture = (Button) findViewById(R.id.photo_but);
        save = (Button) findViewById(R.id.sub_btn);
        image = (ImageView) findViewById(R.id.im_v);

        engine = GlobalClass.engine_number;
        user_2 = GlobalClass.user_name_string;
        category_type = GlobalClass.Faults_Category;


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/faults_trips");


        //title.setText(category_type);

        SpannableString content = new SpannableString(category_type);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        title.setText(content);

        engine_n.setText(engine);

        faults_trips = new Faults_Trips();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (category.getText().toString() != null && !category.getText().toString().isEmpty()) {
                    f_co = category.getText().toString();
                }
                if (comment.getText().toString() != null && !comment.getText().toString().isEmpty()) {
                    t_co = comment.getText().toString();
                } else {
                    t_co = "NSet";
                }

                faults_trips = new Faults_Trips(f_co,user_2, t_co,category_type);
               /**
                faults_trips.setUrgency(f_co);
                faults_trips.setCategory(category_type);
                faults_trips.setComment(t_co);
                faults_trips.setUser_2(user_2);
                faults_trips.setDatetime(sdf.format(new Date()).trim());
                **/
                ref2.child(sdf.format(new Date()).trim()).setValue(faults_trips);
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
    public void Upload_image(View v) {

        final String title_val = "Test";//mPostTitle.getText().toString().trim();
        final String desc_val = "Trial"; // mPostDesc.getText().toString().trim();
        if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && mImageUri != null) {

            StorageReference filepath = storageRef.child("Faults_Trips/" + engine + "/" + sdf.format(new Date()).trim()).child(mImageUri.getLastPathSegment());

            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/faults_trips");

                    DatabaseReference newPost = ref2.child("Category").push();
                    newPost.child(GlobalClass.Faults_Category + "/" + sdf.format(new Date()).trim()).setValue(downloadUrl.toString());


                    //startActivity(new Intent(simpleblog2.emily.example.com.simpleblog2.PostActivity.this, MainActivity.class));
                }
            });
        }

    }
**/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            onCaptureImageResult(data);

            //mImageUri = data.getData();
            //image.setImageURI(mImageUri);

          //  CropImage.activity(mImageUri)
         //           .setGuidelines(CropImageView.Guidelines.ON)
         //           .setAspectRatio(1, 1)
         //           .start(this);

        }
/**
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                image.setImageURI(resultUri);
                mImageUri = resultUri;

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
*/
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
        StorageReference filepath = storageRef.child("Faults_Trips/" + engine + "/" +
                GlobalClass.Faults_Category + "/" + sdf.format(new Date()).trim());

        filepath.putBytes(bb).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                /**Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference ref2 = firebaseDatabase.getReference("data/" + engine + "/faults_trips");

                DatabaseReference newPost = ref2.child("blog").push();
                newPost.child("title").setValue(title_val);
                newPost.child("desc").setValue(desc_val);
                newPost.child("image").setValue(downloadUrl.toString());
                 //startActivity(new Intent(simpleblog2.emily.example.com.simpleblog2.PostActivity.this, MainActivity.class));
            */
                Toast.makeText(Dashboard_problem.this, "Successfully !", Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Dashboard_problem.this, "Failed !", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void select_image(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        }
    }
}

/**
 *
 * Get the images from Database
 StorageReference pathReference = storageRef.child("images/stars.jpg");

 // Create a reference to a file from a Cloud Storage URI
 StorageReference gsReference = storage.getReferenceFromUrl("gs://bucket/images/stars.jpg");

 // Create a reference from an HTTPS URL
 // Note that in the URL, characters are URL escaped!
 StorageReference httpsReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg");
 */