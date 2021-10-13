package com.education.stringadvanced;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private String url = "https://bodysize.org/ru/top100/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String nameStr = "Никита, Максим, Виктор, Пётр";
//        String[] names = nameStr.split(", ");
//        for (String name : names) {
//            Log.i("name", name);
//        }

//        String geometry = "Геометрия";
//        Log.i("geometry", geometry.substring(3));

        DownloadWeb downloadWeb = new DownloadWeb();
        String result = null;
        try {
            result = downloadWeb.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        Pattern patternName = Pattern.compile("target=\"_blank\">(.*?)</a>");
        Matcher matcherName = patternName.matcher(result);
        while (matcherName.find()) {
            Log.i("web", matcherName.group(1));
        }

        Pattern patternImage = Pattern.compile("<div class=\"photo\" style=\"background-image: url((.*?));\">");
        Matcher matcherImage = patternImage.matcher(result);
        while (matcherImage.find()) {
            Log.i("web", matcherImage.group(1).substring(1, matcherImage.group(1).toCharArray().length - 1));
        }
    }

    private static class DownloadWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            URL url = null;
            HttpURLConnection urlConnection = null; //Открывает браузер и берёт оттуда данные
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream input = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    result.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            Log.i("URL", strings[0]);
            return result.toString();
        }
    }
}