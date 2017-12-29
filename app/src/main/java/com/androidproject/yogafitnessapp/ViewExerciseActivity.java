package com.androidproject.yogafitnessapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.yogafitnessapp.Database.YogaAndroidDB;
import com.androidproject.yogafitnessapp.Utils.Common;

public class ViewExerciseActivity extends AppCompatActivity {

    private int imageId;

    private String name;

    private TextView title, timer;

    private ImageView imageDetail;

    private Button startButton;

    private boolean timerIsRunning = false;

    private YogaAndroidDB yogaAndroidDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        yogaAndroidDB = new YogaAndroidDB(this);

        timer = (TextView) findViewById(R.id.timer);
        timer.setText("");
        title = (TextView) findViewById(R.id.title);
        imageDetail = (ImageView) findViewById(R.id.image_detail);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timerIsRunning) {
                    startButton.setText("STOP");

                    int timeLimit = 0;

                    if (yogaAndroidDB.getSettingsMode() == 0) {
                        timeLimit = Common.TIME_LIMIT_EASY;
                    } else if (yogaAndroidDB.getSettingsMode() == 1) {
                        timeLimit = Common.TIME_LIMIT_MEDIUM;
                    } else if (yogaAndroidDB.getSettingsMode() == 2) {
                        timeLimit = Common.TIME_LIMIT_HARD;
                    }

                    new CountDownTimer(timeLimit, 1000) {
                        @Override
                        public void onTick(long l) {
                            timer.setText("" + (l / 1000));
                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(ViewExerciseActivity.this, "Done!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                } else {
                    Toast.makeText(ViewExerciseActivity.this, "Done!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                timerIsRunning = !timerIsRunning;
            }
        });

        if (getIntent() != null) {
            imageId = getIntent().getIntExtra("imageId", -1);
            name = getIntent().getStringExtra("name");

            imageDetail.setImageResource(imageId);
            title.setText(name);
        }
    }
}
