package com.orangetunisie.mynotes.ui.add;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.orangetunisie.mynotes.R;
import com.orangetunisie.mynotes.data.entity.Note;
import com.orangetunisie.mynotes.repository.NoteRepository;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private TextView date;
    private Note note = new Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        findViewById(R.id.button_set_date).setOnClickListener(v -> {
            MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
            builder.setTitleText(getString(R.string.picker_title));
            MaterialDatePicker<Long> dialog = builder.build();
            dialog.addOnPositiveButtonClickListener(selection -> {
                date.setText(dialog.getHeaderText());
                note.setTimestamp(selection);
            });
            dialog.show(getSupportFragmentManager(), "PICKER");
        });

        findViewById(R.id.button_add).setOnClickListener(v -> {
            note.setTitle(title.getText().toString());
            note.setDescription(description.getText().toString());
            NoteRepository repository = new NoteRepository(this);
            repository.addNote(note)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onComplete() {
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("TAG", "onError: ", e);
                        }
                    });

        });
    }
}
