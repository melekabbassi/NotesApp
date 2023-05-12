package com.example.notesapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static AddFragment newInstance(int id, String title, String content) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putInt(DatabaseHelper.COLUMN_ID, id);
        args.putString(DatabaseHelper.COLUMN_TITLE, title);
        args.putString(DatabaseHelper.COLUMN_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ExtendedFloatingActionButton extendedFloatingActionButton = view.findViewById(R.id.extended_fab);
        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
        return view;
    }

    private void saveNote() {
        TextInputEditText noteET = requireView().findViewById(R.id.noteET);
        TextInputEditText titleET = requireView().findViewById(R.id.titleET);
        String title = Objects.requireNonNull(titleET.getText()).toString().trim();
        String note = Objects.requireNonNull(noteET.getText()).toString().trim();

        databaseHelper = new DatabaseHelper(requireContext());

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, title);
        values.put(DatabaseHelper.COLUMN_CONTENT, note);

        long result = db.insert(DatabaseHelper.TABLE_NAME, null, values);

        db.close();

        if (result != -1) {
            Toast.makeText(requireContext(), "Note saved", Toast.LENGTH_SHORT).show();
            titleET.setText("");
            noteET.setText("");
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new HomeFragment()).commit();
        } else {
            Toast.makeText(requireContext(), "Failed to save note", Toast.LENGTH_SHORT).show();
        }

    }
}