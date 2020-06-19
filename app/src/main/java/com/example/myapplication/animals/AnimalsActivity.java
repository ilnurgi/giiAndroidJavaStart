package com.example.myapplication.animals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.myapplication.R;

public class AnimalsActivity extends AppCompatActivity {

    private ListView list;
    private AnimalsHelper helper;
    private SimpleCursorAdapter adapter;
    private String selection;
    private String[] selectionArgs;
    private String orderBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        list = findViewById(R.id.animals_list);
        list.setEmptyView(findViewById(R.id.animals_not_found));

        registerForContextMenu(list);

        helper = new AnimalsHelper(this);
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                null,
                new String[]{AnimalsTable.COLUMN_ANIMAL},
                new int[]{android.R.id.text1},
                0
        );
        list.setAdapter(adapter);
        updateCursor();
    }

    private void updateCursor() {
        Cursor cursor = helper.getReadableDatabase().query(
                AnimalsTable.TABLE_ANIMALS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                orderBy
        );
        adapter.swapCursor(cursor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animals_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.animals_main_search:
                return true;
            case R.id.animals_main_up:
                orderBy = AnimalsTable.COLUMN_ANIMAL + " asc ";
                updateCursor();
                return true;
            case R.id.animals_main_down:
                orderBy = AnimalsTable.COLUMN_ANIMAL + " desc ";
                updateCursor();
                return true;
            case R.id.animals_main_add:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.animals_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}