package com.example.myapplication.animals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
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
                handlerSearch(item);
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

    private String likeQuery = "";

    private void handlerSearch(MenuItem item) {
        SearchView searchView = (SearchView) item.getActionView();
        item.expandActionView();
        searchView.setQuery(likeQuery, false);

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if(!TextUtils.isEmpty(newText)){
                            selection = AnimalsTable.COLUMN_ANIMAL + " like ? ";
                            selectionArgs = new String[]{
                                    "%" + newText + "%"
                            };
                        } else {
                            selection = null;
                            selectionArgs = null;
                        }
                        likeQuery = newText;
                        updateCursor();
                        return true;
                    }
                }
        );
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.animals_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.animals_context_delete:
                deleteAnimal(info.position);
                return true;
            case R.id.animals_context_update:
                updateAnimalDialog(info.position);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void updateAnimalDialog(int position) {
        Cursor cursor = adapter.getCursor();
        cursor.moveToPosition(position);
        String databaseId = cursor.getString(
                cursor.getColumnIndex(AnimalsTable.COLUMN_ID)
        );
        String databaseName = cursor.getString(
                cursor.getColumnIndex(AnimalsTable.COLUMN_ANIMAL)
        );
        EditText edit = (EditText) LayoutInflater
                .from(this)
                .inflate(R.layout.animals_dialog, null);
        edit.setText(databaseName);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Animal");
        builder.setView(edit);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = edit.getText().toString();
                if (newName.equals(databaseName)) {
                    return;
                }

                ContentValues values = new ContentValues();
                values.put(AnimalsTable.COLUMN_ANIMAL, newName);

                helper.getWritableDatabase().update(
                        AnimalsTable.TABLE_ANIMALS,
                        values,
                        AnimalsTable.COLUMN_ID + " = ? ",
                        new String[] {databaseId }
                );
                updateCursor();
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void deleteAnimal(int position) {
        Cursor cursor = adapter.getCursor();
        cursor.moveToPosition(position);
        String databaseId = cursor.getString(
                cursor.getColumnIndex(AnimalsTable.COLUMN_ID)
        );
        helper.getWritableDatabase().delete(
                AnimalsTable.TABLE_ANIMALS,
                AnimalsTable.COLUMN_ID + " = ?",
                new String[]{ databaseId }
        );
        updateCursor();
    }
}