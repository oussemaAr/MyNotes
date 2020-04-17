package com.orangetunisie.mynotes.ui.list;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orangetunisie.mynotes.R;

public class NoteViewHolder  extends RecyclerView.ViewHolder {

    TextView title;
    TextView countdown;
    TextView description;
    ImageButton deleteButton;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        countdown = itemView.findViewById(R.id.countdown);
        description = itemView.findViewById(R.id.description);
        deleteButton = itemView.findViewById(R.id.button_delete);
    }
}
