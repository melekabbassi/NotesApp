package com.example.notesapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String
                .format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                        TABLE_NAME, COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP IF TABLE EXISTS %s", TABLE_NAME));
        onCreate(db);
    }

    public void insert(String title, String content) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(String.format("INSERT INTO %s (%s, %s) VALUES ('%s', '%s')",
                TABLE_NAME, COLUMN_TITLE, COLUMN_CONTENT, title, content));
        db.close();
    }

    @SuppressLint("DefaultLocale")
    public void update(int id, String title, String content) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(String.format("UPDATE %s SET %s = '%s', %s = '%s' WHERE %s = %d",
                TABLE_NAME, COLUMN_TITLE, title, COLUMN_CONTENT, content, COLUMN_ID, id));
        db.close();
    }

    @SuppressLint("DefaultLocale")
    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(String.format("DELETE FROM %s WHERE %s = %d", TABLE_NAME, COLUMN_ID, id));
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(String.format("DELETE FROM %s", TABLE_NAME));
        db.close();
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s", TABLE_NAME), null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT));
                notes.add(new Note(id, title, content));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }
}
