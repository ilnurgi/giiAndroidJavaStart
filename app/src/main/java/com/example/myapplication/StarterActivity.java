package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StarterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        btnStart = findViewById(R.id.btn_starter);
        edit = findViewById(R.id.edit);

        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String data = edit.getText().toString();
        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(data)
        );
        PackageManager manager = getPackageManager();
        if (intent.resolveActivity(manager) != null) {
            startActivity(intent);
        }
    }
}
