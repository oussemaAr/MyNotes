package com.orangetunisie.mynotes.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.orangetunisie.mynotes.data.db.NoteDB;
import com.orangetunisie.mynotes.data.entity.Note;

import java.util.List;

import io.reactivex.Completable;

public class NoteRepository {

    private Context context;

    public NoteRepository(Context context) {
        this.context = context;
    }

    public Completable addNote(Note note) {
        return NoteDB.getInstance(context).noteDAO().addNote(note);
    }

    public LiveData<List<Note>> getNotes() {
        return NoteDB.getInstance(context).noteDAO().getAllNotes();
    }

    public Completable deleteNote(Note note) {
        return NoteDB.getInstance(context).noteDAO().deleteNote(note);
    }
}
