package com.orangetunisie.mynotes.ui.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.orangetunisie.mynotes.R;
import com.orangetunisie.mynotes.databinding.ActivityListNoteBinding;
import com.orangetunisie.mynotes.ui.add.AddActivity;

import java.util.ArrayList;

public class ListNoteActivity extends AppCompatActivity {

    private NoteAdapter adapter;
    private ListNoteViewModel viewModel;
    private ActivityListNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(ListNoteViewModel.class);
        binding.fabAdd.setOnClickListener(v -> startActivity(new Intent(ListNoteActivity.this, AddActivity.class)));
        prepareRecycler();
        viewModel.getData().observe(this, notes -> adapter.setData(notes));
    }

    private void prepareRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recycler.setLayoutManager(layoutManager);

        adapter = new NoteAdapter(new ArrayList<>());
        binding.recycler.setAdapter(adapter);
        adapter.setItemClickListener(note -> {
            viewModel.deleteNote(note);
            Snackbar.make(binding.recycler, getString(R.string.note_delete), Snackbar.LENGTH_LONG).show();
        });
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());

        binding.recycler.addItemDecoration(dividerItemDecoration);
    }
}
