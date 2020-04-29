package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StateActivity extends AppCompatActivity implements View.OnClickListener {

    private int counter;
    private TextView text;
    private Button button;

    private static final String KEY_COUNTER = "COUNTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(KEY_COUNTER);
        }

        text = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(this);
        text.setText("" + counter);
    }

    @Override
    public void onClick(View v) {
        counter++;
        text.setText(""+counter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNTER, counter);
    }
}
