package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView action;
    private BottomNavigationView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        action = findViewById(R.id.action);
        bottom = findViewById(R.id.navigation);

        bottom.setOnNavigationItemSelectedListener(this);
        Log.d("ilnurgi", "Navigation onCreate");

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        if (name != null) {
            action.setText(name);
        }
        Intent intent1 = new Intent();
        intent1.putExtra("NAME", "name");
        setResult(RESULT_OK, intent1);
//        onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ilnurgi", "Navigation onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ilnurgi", "Navigation onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ilnurgi", "Navigation onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ilnurgi", "Navigation onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ilnurgi", "Navigation onDestroy");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.bottom_git:
                action.setText("GIT");
                return true;
            case R.id.bottom_home:
                action.setText("HOME");
                return true;
            case R.id.bottom_svn:
                action.setText("SVN");
                return true;
        }
        return false;
    }
}
