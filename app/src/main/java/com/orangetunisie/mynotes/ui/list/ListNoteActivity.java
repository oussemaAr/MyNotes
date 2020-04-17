package com.orangetunisie.mynotes.ui.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orangetunisie.mynotes.R;
import com.orangetunisie.mynotes.ui.add.AddActivity;
import com.orangetunisie.mynotes.utils.Utils;

public class ListNoteActivity extends AppCompatActivity {

    private NoteAdapter adapter;
    private final int ADD_TAG = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_note);

        findViewById(R.id.fab_add).setOnClickListener(v -> startActivityForResult(new Intent(ListNoteActivity.this, AddActivity.class), ADD_TAG));

        prepareRecycler();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.setData(Utils.data);
        adapter.notifyDataSetChanged();
    }

    private void prepareRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoteAdapter(Utils.data);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(pos -> {
            Utils.data.remove(pos);
            adapter.setData(Utils.data);
            adapter.notifyDataSetChanged();
        });
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
