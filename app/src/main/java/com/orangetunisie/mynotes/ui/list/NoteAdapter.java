package com.orangetunisie.mynotes.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orangetunisie.mynotes.R;
import com.orangetunisie.mynotes.data.entity.Note;
import com.orangetunisie.mynotes.utils.ItemClickListener;
import com.orangetunisie.mynotes.utils.Utils;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<Note> data;
    private ItemClickListener itemClickListener;

    public NoteAdapter(List<Note> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = data.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        holder.countdown.setText(Utils.timetogo(holder.itemView.getContext(), note.getTimestamp()));
        holder.deleteButton.setOnClickListener(v -> {
            itemClickListener.onItemClicked(position);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    void setData(List<Note> data) {
        this.data = data;
    }
}
