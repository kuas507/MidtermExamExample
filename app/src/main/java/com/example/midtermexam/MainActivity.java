package com.example.midtermexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText wightEdit, heightEdit;
    private Button submitBtn;

    private SharedPreferences msharedPreferences;
    private SharedPreferences msharedPreferences1;
    private double bmiResult;
    private TextView textView;
    Global global;
    private RatingBar scoreRating;
    private SharedPreferences.Editor edit;
    private SharedPreferences.Editor edit1;
    List<String> recordList = new ArrayList<>();
    String[] recordArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        global = (Global) getApplicationContext();
        msharedPreferences = getSharedPreferences("PREF_INPUT", getApplicationContext().MODE_PRIVATE);
        msharedPreferences1 = getSharedPreferences("PREF_INPUT1", getApplicationContext().MODE_PRIVATE);
        heightEdit = findViewById(R.id.editText);
        wightEdit = findViewById(R.id.editText2);
        scoreRating = findViewById(R.id.scoreRaing);
        submitBtn = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        recordArray = new String[2];
        scoreRating.setRating(global.ratingScore);
        scoreRating.setVisibility(View.GONE);

        if (global.scoreFlag) {
            scoreRating.setVisibility(View.VISIBLE);
            scoreRating.setRating(global.ratingScore);
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    double height = Double.parseDouble(heightEdit.getText().toString().trim()) / 100;
                    double weight = Double.parseDouble(wightEdit.getText().toString().trim());
                    global.height = height;
                    bmiResult = weight / Math.pow(height, 2);
                    DecimalFormat df = new DecimalFormat("##.00");
                    bmiResult = Double.parseDouble(df.format(bmiResult));
                    Log.e("", String.valueOf(bmiResult));


                    global.bmiResult = bmiResult;

                    if (bmiResult > 30) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("過重!!")
                                .setNegativeButton("Web", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Uri uri = Uri.parse("https://zh.wikipedia.org/wiki/%E8%BA%AB%E9%AB%98%E9%AB%94%E9%87%8D%E6%8C%87%E6%95%B8");
                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                        startActivity(intent);
                                    }
                                })
                                .setNeutralButton("tel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Uri uri1 = Uri.parse("tel:123123");
                                        Intent intent1 = new Intent(Intent.ACTION_DIAL, uri1);
                                        startActivity(intent1);
                                    }
                                })
                                .setPositiveButton("繼續", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent2 = new Intent(MainActivity.this, BMIActivity.class);
                                        startActivity(intent2);

                                    }
                                })
                                .show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, BMIActivity.class);
                        startActivity(intent);
                    }


                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        savePref();
    }

    @Override
    public void onResume() {
        super.onResume();
        readPref();

    }

    private void savePref() {
        String time = new Date(System.currentTimeMillis()).toString();
        edit = msharedPreferences.edit();
        edit1 = msharedPreferences1.edit();
        edit1.putString("historyrecord", msharedPreferences.getString("historyrecord", ""));
        edit.putString("historyrecord", time + " " + bmiResult);
        edit.commit();
        edit1.commit();

    }

    private void readPref() {

        Log.e("第0", msharedPreferences.getString("historyrecord", ""));
        Log.e("第1", msharedPreferences1.getString("historyrecord", ""));
        textView.setText("新紀錄 " + msharedPreferences.getString("historyrecord", "") + "\n" + "----------" + "\n" + "上次紀錄" + msharedPreferences1.getString("historyrecord", ""));

    }
}
