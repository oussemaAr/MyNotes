package com.orangetunisie.mynotes.ui.add;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.orangetunisie.mynotes.data.entity.Note;
import com.orangetunisie.mynotes.repository.NoteRepository;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddViewModel extends AndroidViewModel {

    private NoteRepository repository;

    public AddViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application.getBaseContext());
    }

    public void insert(Note note) {

        repository.addNote(note)
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
