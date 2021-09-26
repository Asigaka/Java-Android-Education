package com.education.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceivedActivity extends AppCompatActivity {

    private TextView textViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received);

        textViewMsg = findViewById(R.id.textReceivedMsg);
        getMessage();
    }

    private void getMessage() {
        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");
        textViewMsg.setText(msg);
    }
}