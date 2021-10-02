package com.example.digitlog;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Patterns;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.digitlog.Data;
        import com.example.digitlog.R;
        import com.google.android.material.textfield.TextInputEditText;
        import com.google.android.material.textfield.TextInputLayout;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.Locale;

public class Sheet7 extends AppCompatActivity {
    EditText p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13;
    Button button3;
    DataS1 data;
    TextView textView3;
    String engine;
    Dialog dialog;
    boolean isp1,isp2,isp3,isp4,isp5,isp6,isp7,isp8,isp9,isp10,isp11,
            isp12,isp13, isp14, isp15,isp16, isp17, isp18,isp19, isp20, isp21;
    TextInputLayout emailError, emailError2, emailError3, emailError4, emailError5, emailError6,
            emailError7, emailError8, emailError9, emailError10, emailError11, emailError12, emailError13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet7);
        engine = GlobalClass.engine_number;
        textView3 = (TextView) findViewById(R.id.textView3);

        TextView eng = (TextView) findViewById(R.id.eng);
        eng.setText(GlobalClass.engine_number);
        textView3.setText(GlobalClass.hsrg);

        dialog = new Dialog(Sheet7.this);
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


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/" + GlobalClass.hsrg);


        emailError2 = (TextInputLayout) findViewById(R.id.emailError2);
        emailError3 = (TextInputLayout) findViewById(R.id.emailError3);
        emailError4 = (TextInputLayout) findViewById(R.id.emailError4);
        emailError5 = (TextInputLayout) findViewById(R.id.emailError5);
        emailError6 = (TextInputLayout) findViewById(R.id.emailError6);
        emailError7 = (TextInputLayout) findViewById(R.id.emailError7);
        emailError8 = (TextInputLayout) findViewById(R.id.emailError8);
        emailError9 = (TextInputLayout) findViewById(R.id.emailError9);
        emailError10 = (TextInputLayout) findViewById(R.id.emailError10);
        emailError11 = (TextInputLayout) findViewById(R.id.emailError11);
        emailError12 = (TextInputLayout) findViewById(R.id.emailError12);
        emailError13 = (TextInputLayout) findViewById(R.id.emailError13);

        p2 = (EditText) findViewById(R.id.p2);
        p3 = (EditText) findViewById(R.id.p3);
        p4 = (EditText) findViewById(R.id.p4);
        p5 = (EditText) findViewById(R.id.p5);
        p6 = (EditText) findViewById(R.id.p6);
        p7 = (EditText) findViewById(R.id.p7);
        p8 = (EditText) findViewById(R.id.p8);
        p9 = (EditText) findViewById(R.id.p9);
        p10 = (EditText) findViewById(R.id.p10);

        p11 = (EditText) findViewById(R.id.p11);
        p12 = (EditText) findViewById(R.id.p12);
        p13 = (EditText) findViewById(R.id.p13);

        if ((GlobalClass.hsrg.equals("HSRG_A"))){

           emailError2.setHint(this.getString(R.string.sp2));
           emailError3.setHint(this.getString(R.string.sp3));
           emailError4.setHint(this.getString(R.string.sp4));
           emailError5.setHint(this.getString(R.string.sp5));
           emailError6.setHint(this.getString(R.string.sp6));
           emailError7.setHint(this.getString(R.string.sp7));
           emailError8.setHint(this.getString(R.string.sp8));
           emailError9.setHint(this.getString(R.string.sp9));
           emailError10.setHint(this.getString(R.string.sp10));
           emailError11.setHint(this.getString(R.string.sp11));
           emailError12.setHint(this.getString(R.string.sp12));
           emailError13.setHint(this.getString(R.string.sp13));



        }else{

            emailError2.setHint(this.getString(R.string.qsp2));
            emailError3.setHint(this.getString(R.string.qsp3));
           emailError4.setHint(this.getString(R.string.qsp4));
           emailError5.setHint(this.getString(R.string.qsp5));
           emailError6.setHint(this.getString(R.string.qsp6));
           emailError7.setHint(this.getString(R.string.qsp7));
           emailError8.setHint(this.getString(R.string.qsp8));
           emailError9.setHint(this.getString(R.string.qsp9));
           emailError10.setHint(this.getString(R.string.qsp10));
           emailError11.setHint(this.getString(R.string.qsp11));
           emailError12.setHint(this.getString(R.string.qsp12));
           emailError13.setHint(this.getString(R.string.qsp13));


        }





        button3 = (Button) findViewById(R.id.button11);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
        String currentdateandTime = sdf.format(new Date());

        data = new DataS1();


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SetValidation();

                dialog.show();

            }




        });

    }

    public void SetValidation() {

        if ((ParseDouble(p1.getText().toString() )> 20) || (ParseDouble(p1.getText().toString()) < 3)){
            p1.setError("value entered out of range!, should be (5 - 15 MPa)");
            p1.requestFocus();
        }

        // Check for a valid email address.
        if (p1.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isp1 = false;
        } else if (ParseDouble(p1.getText().toString()) < 0.083) {
            emailError.setError(getResources().getString(R.string.pmin));
            isp1 = false;
        } else if (ParseDouble(p1.getText().toString()) < 0.0055) {
            emailError.setError(getResources().getString(R.string.pll));
            isp1 = false;
        } else  {
            isp1 = true;
            emailError.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        if (p2.getText().toString().isEmpty()) {
            emailError2.setError(getResources().getString(R.string.email_error));
            isp2 = false;
        } else if (ParseDouble(p1.getText().toString()) < 0.042) {
            emailError2.setError(getResources().getString(R.string.pmin));
            isp2 = false;
        } else  {
            isp2 = true;
            emailError.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        if (p3.getText().toString().isEmpty()) {
            emailError3.setError(getResources().getString(R.string.email_error));
            isp3 = false;
        } else if (ParseDouble(p1.getText().toString()) < 0.0055) {
            emailError3.setError(getResources().getString(R.string.pmin));
            isp3 = false;
        } else  {
            isp3 = true;
            emailError3.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        if (p4.getText().toString().isEmpty()) {
            emailError4.setError(getResources().getString(R.string.email_error));
            isp4 = false;
        } else if (ParseDouble(p1.getText().toString()) < 0.083) {
            emailError4.setError(getResources().getString(R.string.pmin));
            isp4 = false;
        } else  {
            isp4 = true;
            emailError4.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        if (p6.getText().toString().isEmpty()) {
            emailError6.setError(getResources().getString(R.string.email_error));
            isp6 = false;
        } else if (ParseDouble(p1.getText().toString()) < 7.24) {
            emailError6.setError(getResources().getString(R.string.pmin));
            isp6 = false;
        } else  {
            isp6 = true;
            emailError6.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        if (p7.getText().toString().isEmpty()) {
            emailError7.setError(getResources().getString(R.string.email_error));
            isp7 = false;
        } else if (ParseDouble(p7.getText().toString()) < 0.165) {
            emailError7.setError(getResources().getString(R.string.pmin));
            isp7 = false;
        } else if (ParseDouble(p7.getText().toString()) < 0.1379) {
            emailError7.setError(getResources().getString(R.string.pll));
            isp7 = false;
        } else  {
            isp7 = true;
            emailError7.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        if (p8.getText().toString().isEmpty()) {
            emailError8.setError(getResources().getString(R.string.email_error));
            isp8 = false;
        } else if (ParseDouble(p8.getText().toString()) < 0.165) {
            emailError8.setError(getResources().getString(R.string.pmin));
            isp8 = false;
        } else if (ParseDouble(p8.getText().toString()) < 0.1379) {
            emailError8.setError(getResources().getString(R.string.pll));
            isp8 = false;
        } else  {
            isp8 = true;
            emailError8.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        if (p10.getText().toString().isEmpty()) {
            emailError10.setError(getResources().getString(R.string.email_error));
            isp10 = false;
        } else if (ParseDouble(p10.getText().toString()) < 0.2) {
            emailError10.setError(getResources().getString(R.string.pmin));
            isp10 = false;
        } else if (ParseDouble(p10.getText().toString()) > 0.4) {
            emailError10.setError(getResources().getString(R.string.pmax));
            isp10 = false;
        } else  {
            isp10 = true;
            emailError10.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        if (p11.getText().toString().isEmpty()) {
            emailError11.setError(getResources().getString(R.string.email_error));
            isp11 = false;
        } else if (ParseDouble(p11.getText().toString()) > 1.2) {
            emailError11.setError(getResources().getString(R.string.pmax));
            isp11 = false;
        } else  {
            isp11 = true;
            emailError11.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        if (p12.getText().toString().isEmpty()) {
            emailError12.setError(getResources().getString(R.string.email_error));
            isp12 = false;
        } else if (ParseDouble(p12.getText().toString()) > 1.2) {
            emailError12.setError(getResources().getString(R.string.pmin));
            isp12 = false;
        } else  {
            isp12 = true;
            emailError12.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////

        if (p13.getText().toString().isEmpty()) {
            emailError13.setError(getResources().getString(R.string.email_error));
            isp13 = false;
        } else if (ParseDouble(p13.getText().toString()) > 4) {
            emailError13.setError(getResources().getString(R.string.pmax));
            isp13 = false;

        } else  {
            isp13 = true;
            emailError13.setErrorEnabled(false);
        }

        ////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////////////////////
        //  if (isEmailValid && isPasswordValid) {

        //    Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        //  startActivity(new Intent(Login.this, Sheet_1.class));}

    }

    public void save_function(){
        try{
            float iip2 = ParseDouble(p2.getText().toString().trim());
            float iip3 = ParseDouble(p3.getText().toString().trim());
            float iip4 = ParseDouble(p4.getText().toString().trim());
            float iip5 = ParseDouble(p5.getText().toString().trim());
            float iip6 = ParseDouble(p6.getText().toString().trim());
            float iip7 = ParseDouble(p7.getText().toString().trim());
            float iip8 = ParseDouble(p8.getText().toString().trim());
            float iip9 = ParseDouble(p9.getText().toString().trim());
            float iip10 = ParseDouble(p10.getText().toString().trim());

            float iip11 = ParseDouble(p11.getText().toString().trim());
            float iip12 = ParseDouble(p12.getText().toString().trim());
            float iip13 = ParseDouble(p13.getText().toString().trim());

            String user = GlobalClass.actual_user_name;



            data.setIp2(iip2);
            data.setIp3(iip3);
            data.setIp4(iip4);
            data.setIp5(iip5);
            data.setIp6(iip6);
            data.setIp7(iip7);
            data.setIp8(iip8);
            data.setIp9(iip9);
            data.setIp10(iip10);

            data.setIp11(iip11);
            data.setIp12(iip12);
            data.setIp13(iip13);

            data.setLogsheet(GlobalClass.hsrg);
            data.setUser(user);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref2 = firebaseDatabase.getReference(GlobalClass.database + "/" + engine + "/" + GlobalClass.hsrg);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);
            String currentdateandTime = sdf.format(new Date());

            ref2.child(sdf.format(new Date()).toString().trim()).setValue(data);
            Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
            this.finish();
        }catch (Exception exception){
            Toast.makeText(getApplicationContext(), "Failed! ensure all data are entered", Toast.LENGTH_SHORT).show();

        }
    }
    float ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Float.parseFloat(strNumber);
            } catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        else return -1;
    }
}