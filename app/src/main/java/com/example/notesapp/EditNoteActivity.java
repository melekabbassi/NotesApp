package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class EditNoteActivity extends AppCompatActivity {
    private TextInputEditText editTitleET, editContentET;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editTitleET = findViewById(R.id.editTitleET);
        editContentET = findViewById(R.id.editNoteET);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        editTitleET.setText(title);
        editContentET.setText(content);

        FloatingActionButton editFAB = findViewById(R.id.edit_extended_fab);
        editFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTitle = Objects.requireNonNull(editTitleET.getText()).toString();
                String updatedContent = Objects.requireNonNull(editContentET.getText()).toString();
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                databaseHelper.update(id, updatedTitle, updatedContent);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}