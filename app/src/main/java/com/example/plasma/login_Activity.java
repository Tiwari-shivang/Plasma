package com.example.plasma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class login_Activity extends AppCompatActivity {

    TextView signInButton, forgotPassword;
    TextView allInone;
    EditText username, Password, Type;
    Button login;
    Intent intent, SignInIntent, forGotIntent;
    FirebaseAuth loginAuth;
    RadioGroup typeGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.loginButton);
        signInButton = findViewById(R.id.signInButton);
        username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        SignInIntent = new Intent(this, SignIn.class);
        loginAuth = FirebaseAuth.getInstance();
        typeGroup = findViewById(R.id.typeGroup);
        forgotPassword = findViewById(R.id.forgotPass);

        typeGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            radioButton = findViewById(i);
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(Password.getText().toString())){
                    if (TextUtils.isEmpty(username.getText().toString())){
                        username.setError("Email can not be empty");
                    }
                    if (TextUtils.isEmpty(Password.getText().toString())){
                        Password.setError("Password can not be empty");
                    }
                }
                else {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String Email = "";
                            for (DataSnapshot snapshot1:snapshot.getChildren()){
                                for (DataSnapshot snapshot2:snapshot1.getChildren()){
                                    if (Objects.equals(snapshot2.getKey(), "email")){
                                        Email = Objects.requireNonNull(snapshot2.getValue()).toString();
                                    }
                                    else{
                                        if (Email.equals(username.getText().toString())){
                                            if (Objects.requireNonNull(snapshot2.getValue()).toString().equals(radioButton.getText().toString())){
                                                loginAuth.signInWithEmailAndPassword(username.getText().toString(), Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()){
                                                            Toast.makeText(login_Activity.this, "Logged in", Toast.LENGTH_SHORT).show();
                                                            if (radioButton.getText().toString().equals("Donors")){
                                                                intent = new Intent(login_Activity.this, DonorsHomePage.class);
                                                            }
                                                            else if (radioButton.getText().toString().equals("Recipient")){
                                                                intent = new Intent(login_Activity.this, RecipientHomePage.class);
                                                            }
                                                            else {
                                                                intent = new Intent(login_Activity.this, HospitalActivity.class);
                                                            }
                                                            startActivity(intent);
                                                        }
                                                        else{
                                                            Toast.makeText(login_Activity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }
                                            else{
                                                Toast.makeText(login_Activity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        signInButton.setOnClickListener(view -> startActivity(SignInIntent));
        forgotPassword.setOnClickListener(view -> { forGotIntent = new Intent(login_Activity.this, ForgotPassword.class); startActivity(forGotIntent); });


    }
}
