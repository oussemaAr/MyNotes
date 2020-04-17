package com.orangetunisie.mynotes.ui.list;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orangetunisie.mynotes.databinding.NoteItemBinding;

class NoteViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView countdown;
    TextView description;
    ImageButton deleteButton;

    NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        NoteItemBinding binding = NoteItemBinding.bind(itemView);
        title = binding.title;
        countdown = binding.countdown;
        description = binding.description;
        deleteButton = binding.buttonDelete;
    }
}
