package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.myapplication.contacts.ContactsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private CheckBox check;
    private PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);
        check = findViewById(R.id.check);
        Button buttonState = findViewById(R.id.btn_state);
        Button btnStarter = findViewById(R.id.btn_starter);
        Button btnBrowser = findViewById(R.id.btn_browser);
        Button btnMetro = findViewById(R.id.btn_metro);
        Button btnContacts = findViewById(R.id.btn_contacts);

        findViewById(R.id.btn_metro2).setOnClickListener(this);
        findViewById(R.id.btn_dialog).setOnClickListener(this);

        button.setOnClickListener(this);
        buttonState.setOnClickListener(this);
        button1.setOnClickListener(this);
        btnStarter.setOnClickListener(this);
        btnBrowser.setOnClickListener(this);
        btnMetro.setOnClickListener(this);
        btnContacts.setOnClickListener(this);
//        button.setOnClickListener(view -> {});

        registerForContextMenu(findViewById(R.id.hello));

        popupMenu = new PopupMenu(this, findViewById(R.id.hello));
        popupMenu.inflate(R.menu.popup);
        popupMenu.setOnMenuItemClickListener(this);

        findViewById(R.id.hello).setOnClickListener(this);

        Log.d("ilnurgi", "MainActivity onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ilnurgi", "MainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ilnurgi", "MainActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ilnurgi", "MainActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ilnurgi", "MainActivity onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ilnurgi", "MainActivity onDestroy");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id)
        {
            case R.id.button:
                check.setChecked(true);
                break;
            case R.id.button1:
                check.setChecked(false);
                break;
            case R.id.hello:
                popupMenu.show();
                break;
            case R.id.btn_state:
                Intent intent = new Intent(this, StateActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_starter:
                Intent intent1 = new Intent(this, StarterActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_browser:
                Intent intent2 = new Intent(this, BrowserActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_metro:
                Intent intent3 = new Intent(this, MetroActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_metro2:
                Intent intent4 = new Intent(this, Metro2Activity.class);
                startActivity(intent4);
                break;
            case R.id.btn_contacts:
                Intent intent5 = new Intent(this, ContactsActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_dialog:
                showDialog();
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.hello) {
            getMenuInflater().inflate(R.menu.context, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.context_exit) {
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_add:
            case R.id.main_delete:
            case R.id.main_create:
                Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.popup_quit) {
            Toast.makeText(this, "Quit", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NavigationActivity.class);
            intent.putExtra("NAME", "dima");
//            intent.putExtra("USER", new User());

//            startActivity(intent);
            startActivityForResult(intent, 444);
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 444) {
            if (data != null) {
                String name = data.getStringExtra("NAME");
                if (name != null) {
                    Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                }
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showDialog() {
        View dialog = getLayoutInflater().inflate(R.layout.dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setView(dialog)
                .setTitle("TITLEGI")
                .setPositiveButton("окей", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("нету", null)
                .show();
    }
}
