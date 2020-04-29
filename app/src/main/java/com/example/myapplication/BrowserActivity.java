package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class BrowserActivity extends AppCompatActivity {

    public static final int W = ViewGroup.LayoutParams.WRAP_CONTENT;
    public static final int M = ViewGroup.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EditText edit = new EditText(this);
        edit.setLayoutParams(new ViewGroup.LayoutParams(M, W));

        Button button = new Button(this);
        button.setLayoutParams(new ViewGroup.LayoutParams(W, W));
        button.setText("Press me");

        WebView view = new WebView(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(M, M));
        view.setWebViewClient(new WebViewClient());

        LinearLayout l = new LinearLayout(this);
        l.setLayoutParams(new ViewGroup.LayoutParams(M, M));
        l.setOrientation(LinearLayout.VERTICAL);
        l.addView(edit);
        l.addView(button);
        l.addView(view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edit.getText().toString();
                view.loadUrl(url);
            }
        });

        setContentView(l);
    }
}
