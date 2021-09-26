package com.education.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerInfo;
    private TextView textViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Присваиваем айди 
        spinnerInfo = findViewById(R.id.spinnerInfo);
        textViewItems = findViewById(R.id.textViewItems);
    }

    public void showItem(View view) {
        int index = spinnerInfo.getSelectedItemPosition();
        String item = getItemByIndex(index);
        textViewItems.setText(item);
    }

    private String getItemByIndex(int index) {
        String[] items = getResources().getStringArray(R.array.text_view_info);
        return items[index];
    }
}