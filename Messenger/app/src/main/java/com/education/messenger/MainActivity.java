package com.education.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMsg = findViewById(R.id.editSendText);
    }

    public void onClickSendMsg(View view) {
        sendMsgWithAction();
    }

    private void sendMsgWithAction() {
        String msg = editTextMsg.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        Intent chosenIntent = Intent.createChooser(intent, "");
        startActivity(chosenIntent);
    }

    private void sendMsgToOtherActivity() {
        String msg = editTextMsg.getText().toString();
        Intent intent = new Intent(this, ReceivedActivity.class);
        //Отправляем в интент значения через ключ
        intent.putExtra("msg", msg);
        startActivity(intent);
    }
}