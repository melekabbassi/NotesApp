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

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        MaterialButton loginButton = findViewById(R.id.loginButton);
        MaterialButton registerButton = findViewById(R.id.registerButton);
        TextInputLayout emailLayout = findViewById(R.id.mailLayout);
        TextInputLayout passwordLayout = findViewById(R.id.passwordLayout);
        TextInputEditText emailET = findViewById(R.id.mailET);
        TextInputEditText passwordET= findViewById(R.id.passwordET);

        loginButton.setOnClickListener(v -> {
            if (Objects.requireNonNull(emailET.getText()).toString().isEmpty()) {
                emailLayout.setError("Email is required");
                return;
            } else if (Objects.requireNonNull(passwordET.getText()).toString().isEmpty()) {
                passwordLayout.setError("Password is required");
                return;
            } else {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Logging in...");
                progressDialog.show();

                auth.signInWithEmailAndPassword(emailET.getText().toString(), passwordET.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivity.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, BaseActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "There was an error while logging in", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
}