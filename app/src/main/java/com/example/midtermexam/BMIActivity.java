package com.example.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView textView, target;
    Global global;
    private double bmiResult;
    private Button goScoreBtn, backHomepageBtn, finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        goScoreBtn = findViewById(R.id.goScoreBtn);
        backHomepageBtn = findViewById(R.id.backHomepageBtn);
        target = findViewById(R.id.target);
        finishBtn = findViewById(R.id.finishBtn);
        global = (Global) getApplicationContext();
        bmiResult = global.bmiResult;
        textView.setText("當前BMI : " + bmiResult);

        backHomepageBtn.setOnClickListener(myListener);
        goScoreBtn.setOnClickListener(myListener);
        finishBtn.setOnClickListener(myListener);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                DecimalFormat df = new DecimalFormat("##.00");

                if (progress == 15) {
                    textView.setText("當前BMI : " + bmiResult);
                    target.setText("當前體重 : " + df.format(Math.pow(global.height, 2) * bmiResult));
                } else {
                    textView.setText(df.format(bmiResult * (1 + (float) (progress - 15) / 100)));
                    target.setText("更變後體重 : " + df.format(Math.pow(global.height, 2) * bmiResult * (1 + (float) (progress - 15) / 100)));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private Button.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backHomepageBtn:
                    Intent intent = new Intent(BMIActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.goScoreBtn:
                    Intent intent1 = new Intent(BMIActivity.this, ScoreActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.finishBtn:
                    finish();
                    break;
            }
        }
    };
}
