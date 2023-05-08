package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        MaterialButton registerButton = findViewById(R.id.registerButton);
        TextInputLayout nameLayout = findViewById(R.id.nameLayout);
        TextInputLayout emailLayout = findViewById(R.id.mailLayout);
        TextInputLayout passwordLayout = findViewById(R.id.passwordLayout);
        TextInputEditText nameET = findViewById(R.id.nameET);
        TextInputEditText emailET = findViewById(R.id.mailET);
        TextInputEditText passwordET= findViewById(R.id.passwordET);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(nameET.getText()).toString().isEmpty()) {
                    nameLayout.setError("Name is required");
                } else if (Objects.requireNonNull(emailET.getText()).toString().isEmpty()) {
                    emailLayout.setError("Email is required");
                } else if (Objects.requireNonNull(passwordET.getText()).toString().isEmpty()) {
                    passwordLayout.setError("Password is required");
                } else {
                    ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                    progressDialog.setMessage("Creating Account...");
                    progressDialog.show();

                    auth.createUserWithEmailAndPassword(emailET.getText().toString(), passwordET.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setDisplayName(nameET.getText().toString()).build();
                            Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).updateProfile(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    progressDialog.dismiss();
                                    Toast.makeText(RegisterActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, BaseActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(RegisterActivity.this, "There was an error creating your account. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "There was an error creating your account. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}