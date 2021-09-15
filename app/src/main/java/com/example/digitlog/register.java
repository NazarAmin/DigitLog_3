package com.example.digitlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;

import android.content.Intent;
import android.util.Patterns;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import java.util.Date;

public class register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText name, admin, phone, password, email5;
    String user_name;
    String password2;
    String phone_number;
    Button register;
    TextView login;
    String email;
    Users users;
    Dialog dialog;
    Users user22;
    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid;
    TextInputLayout nameError, emailError, phoneError, passError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.name);
        admin = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        login = (TextView) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        phoneError = (TextInputLayout) findViewById(R.id.phoneError);
        passError = (TextInputLayout) findViewById(R.id.passError);
        email5 = (EditText) findViewById(R.id.email99);

        dialog = new Dialog(register.this);
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

        users = new Users();
        user22 = new Users();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }

public void save_function() {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    DatabaseReference ref2 = firebaseDatabase.getReference("data2/users");
    if (admin.getText().toString().trim().equals("admin_dig_123")) {

        user_name = name.getText().toString();
        password2 = password.getText().toString().trim();
        phone_number = phone.getText().toString().trim();
        email = email5.getText().toString().trim();

        if (user_name.isEmpty()){
            name.setError("Please enter user name");
            name.requestFocus();
            isNameValid = false;
        }else{
            isNameValid = true;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email5.setError("Please enter valid email address");
            email5.requestFocus();
            isEmailValid = false;
        }else{
            isEmailValid = true;
        }
        if (password2.length() < 6){
            password.setError("Password is less than 6 charchters");
            password.requestFocus();
            isPasswordValid = false;
        }else{
            isPasswordValid = true;
        }

        user22.setUser(user_name);
        user22.setPhone_number(phone_number);
        user22.setEmail(email);
        user22.setPassword(password2);

        if (isPasswordValid & isNameValid & isNameValid){


            ref2.child(user_name).setValue(user22);



            users = new Users(user_name, password2, phone_number, email);

            mAuth.createUserWithEmailAndPassword(email, password2)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                users = new Users(user_name, password2, phone_number, email);
                                ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(getApplicationContext(), "User Added Successfully", Toast.LENGTH_SHORT).show();
                                            register.this.finish();

                                        }else{
                                            Toast.makeText(getApplicationContext(), "Failed to register", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(), "Failed to register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(getApplicationContext(), "Wrong Fields Entry", Toast.LENGTH_SHORT).show();

        }

    }else{
        Toast.makeText(getApplicationContext(), "Wrong Admin Password", Toast.LENGTH_SHORT).show();
    }


}

}