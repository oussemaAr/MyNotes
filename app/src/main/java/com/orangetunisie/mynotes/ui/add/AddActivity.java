package com.orangetunisie.mynotes.ui.add;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.orangetunisie.mynotes.R;
import com.orangetunisie.mynotes.data.entity.Note;
import com.orangetunisie.mynotes.databinding.AddActivityBinding;

public class AddActivity extends AppCompatActivity {


    private Note note = new Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AddActivityBinding binding = AddActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AddViewModel viewModel = new ViewModelProvider(this).get(AddViewModel.class);

        findViewById(R.id.button_set_date).setOnClickListener(v -> {
            MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
            builder.setTitleText(getString(R.string.picker_title));
            MaterialDatePicker<Long> dialog = builder.build();
            dialog.addOnPositiveButtonClickListener(selection -> {
                binding.buttonSetDate.setText(dialog.getHeaderText());
                note.setTimestamp(selection);
            });
            dialog.show(getSupportFragmentManager(), "PICKER");
        });

        findViewById(R.id.button_add).setOnClickListener(v -> {
            note.setTitle(binding.title.getText().toString());
            note.setDescription(binding.description.getText().toString());
            viewModel.insert(note);
            finish();
        });
    }
}
