package com.orangetunisie.mynotes.ui.list;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.orangetunisie.mynotes.data.entity.Note;
import com.orangetunisie.mynotes.repository.NoteRepository;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListNoteViewModel extends AndroidViewModel {

    private NoteRepository repository;

    public ListNoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application.getBaseContext());
    }

    public LiveData<List<Note>> getData() {
        return repository.getNotes();
    }

    public void deleteNote(Note note) {
        repository.deleteNote(note)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "onComplete: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: ", e);
                    }
                });
    }
}
