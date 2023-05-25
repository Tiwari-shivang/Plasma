package com.example.plasma;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.plasma.Structure.userStructure;
import com.example.plasma.Structure.usersFirebaseRealtimeStructure;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SignIn extends AppCompatActivity {


    RadioGroup typeGroup;
    RadioButton radioButton;
    Button signUP;
    EditText emailTextBox, passwordTextBox, ConfirmPass;
    FirebaseAuth createUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signUP = findViewById(R.id.signUpButton);
        typeGroup = findViewById(R.id.typeGroup);
        emailTextBox = findViewById(R.id.usernameTextBox);
        passwordTextBox = findViewById(R.id.passwordTextBox);
        ConfirmPass = findViewById(R.id.ConfirmPasswordTextBox);
        createUser = FirebaseAuth.getInstance();

        typeGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            radioButton = findViewById(i);
        });

        signUP.setOnClickListener(view -> {
            if (TextUtils.isEmpty(emailTextBox.getText().toString()) || TextUtils.isEmpty(passwordTextBox.getText().toString())){
                if (TextUtils.isEmpty(emailTextBox.getText().toString())){
                    emailTextBox.setError("Email Required");
                }
                if(TextUtils.isEmpty(passwordTextBox.getText().toString())){
                    passwordTextBox.setError("Invalid Password");
                }
            }
            else if (!passwordTextBox.getText().toString().equals(ConfirmPass.getText().toString())){
                passwordTextBox.setError("please enter again");
                ConfirmPass.setError("please enter correctly");
                Toast.makeText(this, "Password wont matched!", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent;
                usersFirebaseRealtimeStructure userStructure = new usersFirebaseRealtimeStructure(emailTextBox.getText().toString(), radioButton.getText().toString());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.push().setValue(userStructure);
                if (radioButton.getText().toString().equals("Donors")){
                    intent = new Intent(SignIn.this, DonorsHomePage.class);
                }
                else if (radioButton.getText().toString().equals("Recipient")){
                    intent = new Intent(SignIn.this, RecipientHomePage.class);
                }
                else{
                    intent = new Intent(SignIn.this, HospitalHomePage.class);
                }
                createUser.createUserWithEmailAndPassword(emailTextBox.getText().toString(), passwordTextBox.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SignIn.this, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}