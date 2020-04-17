package com.orangetunisie.mynotes.ui.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.orangetunisie.mynotes.R;
import com.orangetunisie.mynotes.repository.NoteRepository;
import com.orangetunisie.mynotes.ui.add.AddActivity;

import java.util.ArrayList;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListNoteActivity extends AppCompatActivity {

    private NoteAdapter adapter;
    private NoteRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_note);

        findViewById(R.id.fab_add).setOnClickListener(v -> startActivity(new Intent(ListNoteActivity.this, AddActivity.class)));

        prepareRecycler();

        repository = new NoteRepository(this);
        repository.getNotes().observe(this, notes -> adapter.setData(notes));
    }

    private void prepareRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoteAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(note -> {
            repository.deleteNote(note)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onComplete() {
                            Snackbar.make(recyclerView, getString(R.string.note_delete), Snackbar.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        });
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
