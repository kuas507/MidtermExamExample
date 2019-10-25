package com.example.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private Button backHomeBtn, backBMI;
    Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ratingBar = findViewById(R.id.ratingBar);
        backHomeBtn = findViewById(R.id.backHomeBtn);
        backBMI = findViewById(R.id.backBMI);
        global = (Global) getApplicationContext();
        backHomeBtn.setOnClickListener(myListener);
        backBMI.setOnClickListener(myListener);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), "Rating" + rating, Toast.LENGTH_SHORT).show();
                global.ratingScore = rating;
                global.scoreFlag = true;
            }
        });

    }

    private Button.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backHomeBtn:
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.backBMI:
                    Intent intent1 = new Intent(getApplicationContext(), BMIActivity.class);
                    startActivity(intent1);
            }
        }
    };
}
