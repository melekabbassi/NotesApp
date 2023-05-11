package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(@NonNull Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        Note note = getItem(position);

        TextView titleTV = convertView.findViewById(R.id.titleTV);
        titleTV.setText(note.getTitle());

        return convertView;
    }
}
