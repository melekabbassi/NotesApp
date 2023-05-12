package com.example.notesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private GridView gridView;
    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;
    private DatabaseHelper databaseHelper;

    private static final int EDIT_NOTE_REQUEST_CODE = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = view.findViewById(R.id.gridView);
        notes = databaseHelper.getAllNotes();
        noteAdapter = new NoteAdapter(requireContext(), getActivity() ,notes);
        gridView.setAdapter(noteAdapter);

        return view;

//        GridView gridView = view.findViewById(R.id.gridView);
//        ArrayList<Note> notes = new ArrayList<>();
        // Add notes to the ArrayList
//        notes.add(new Note ("Note 1", "This is note 1"));
//        notes.add(new Note ("Note 2", "This is note 2"));
//        notes.add(new Note ("Note 3", "This is note 3"));
//        notes.add(new Note ("Note 4", "This is note 4"));
//        notes.add(new Note ("Note 5", "This is note 5"));
//        notes.add(new Note ("Note 6", "This is note 6"));
//        notes.add(new Note ("Note 7", "This is note 7"));
//        notes.add(new Note ("Note 8", "This is note 8"));
//        notes.add(new Note ("Note 9", "This is note 9"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            notes = databaseHelper.getAllNotes();
            noteAdapter.notifyDataSetChanged();
        }
    }
}