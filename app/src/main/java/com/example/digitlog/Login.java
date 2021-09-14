package com.example.digitlog;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.util.Patterns;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText email, password;
    String user_check;
    String pass_check;
    String email_check;

    String name_final;
    Button login;
    TextView register;
    ProgressBar progressBar;
    Users users;
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //users = new Users();
        progressBar.setVisibility(View.INVISIBLE);
        email_check = email.getText().toString().trim();
        pass_check = password.getText().toString().trim();

        //System.out.println(users.getPassword());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                SetValidation();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity

               Intent intent = new Intent(getApplicationContext(), register.class);
               startActivity(intent);
              //Toast.makeText(getApplicationContext(), "Please call admin (0123495541 Nasreldein) for adding new user", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void SetValidation() {

        email_check = email.getText().toString().trim();
        pass_check = password.getText().toString().trim();

        int iend = email_check.indexOf("@");
        GlobalClass.user_name_string = email_check.substring(0 , iend);

        mAuth.signInWithEmailAndPassword(email_check, pass_check).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                    DatabaseReference ref3 = firebaseDatabase.getReference("data/users");
                    ref3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.hasChildren()) {
                                for (DataSnapshot mydatasnapshot : dataSnapshot.getChildren()) {
                                    Users user = mydatasnapshot.getValue(Users.class);
                                    if (user.getEmail().equals(email_check)) {
                                        GlobalClass.actual_user_name = user.getUser();
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), "Logged on as " + user.getUser() + " Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this, Blocks.class));
                                    }

                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }else{
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(), "Failed! Please check credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }