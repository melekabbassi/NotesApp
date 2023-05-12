package com.example.notesapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {
    private DatabaseHelper databaseHelper;
    private Activity activity;
    public NoteAdapter(@NonNull Context context, Activity activity, ArrayList<Note> notes) {
        super(context, 0, notes);
        this.activity = (Activity) activity;
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        Note note = getItem(position);

        TextView titleTV = convertView.findViewById(R.id.titleTV);
        titleTV.setText(note.getTitle());

        MaterialButton editButton = convertView.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditNoteActivity.class);
                intent.putExtra("id", note.getId());
                intent.putExtra("title", note.getTitle());
                intent.putExtra("content", note.getContent());
                activity.startActivityForResult(intent, 1);
            }
        });

        MaterialButton deleteButton = convertView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                databaseHelper.delete(note.getId());
                remove(note);
                notifyDataSetChanged();
                Toast.makeText(getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
