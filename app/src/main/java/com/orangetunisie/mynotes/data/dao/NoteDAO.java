package com.orangetunisie.mynotes.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.orangetunisie.mynotes.data.entity.Note;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addNote(Note note);

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();

    @Delete
    Completable deleteNote(Note note);
}
