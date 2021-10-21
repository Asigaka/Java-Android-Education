package com.asigaka.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    public static final ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);

        if (notes.isEmpty()) {
            notes.add(new Note("Парикмахер", "Сделать причёску", "Понедельник", 2));
            notes.add(new Note("Парикмахер", "Сделать причёску", "Понедельник", 3));
            notes.add(new Note("Парикмахер", "Сделать причёску", "Понедельник", 1));
            notes.add(new Note("Парикмахер", "Сделать причёску", "Понедельник", 2));
            notes.add(new Note("Парикмахер", "Сделать причёску", "Понедельник", 1));
            notes.add(new Note("Парикмахер", "Сделать причёску", "Понедельник", 2));
            notes.add(new Note("Парикмахер", "Сделать причёску", "Понедельник", 1));
        }

        NotesAdapter adapter = new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.onNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
            }

            @Override
            public void onLongClick(int position) {

            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                notes.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);
    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}