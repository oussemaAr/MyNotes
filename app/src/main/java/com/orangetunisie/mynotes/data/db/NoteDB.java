package com.orangetunisie.mynotes.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.orangetunisie.mynotes.data.dao.NoteDAO;
import com.orangetunisie.mynotes.data.entity.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDB extends RoomDatabase {

    public abstract NoteDAO noteDAO();

    private static NoteDB instance;

    public static NoteDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDB.class, "db")
                    .build();
        }
        return instance;
    }


}
