package com.orangetunisie.mynotes.ui.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.orangetunisie.mynotes.R;
import com.orangetunisie.mynotes.ui.add.AddActivity;

import java.util.ArrayList;

public class ListNoteActivity extends AppCompatActivity {

    private NoteAdapter adapter;
    private ListNoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_note);
        viewModel = new ViewModelProvider(this).get(ListNoteViewModel.class);
        findViewById(R.id.fab_add).setOnClickListener(v -> startActivity(new Intent(ListNoteActivity.this, AddActivity.class)));
        prepareRecycler();
        viewModel.getData().observe(this, notes -> adapter.setData(notes));
    }

    private void prepareRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoteAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(note -> {
            viewModel.deleteNote(note);
            Snackbar.make(recyclerView, getString(R.string.note_delete), Snackbar.LENGTH_LONG).show();
        });
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
