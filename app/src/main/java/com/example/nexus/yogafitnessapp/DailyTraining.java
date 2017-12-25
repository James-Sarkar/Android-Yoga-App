package com.example.nexus.yogafitnessapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nexus.yogafitnessapp.Database.YogaAndroidDB;
import com.example.nexus.yogafitnessapp.Model.Exercise;
import com.example.nexus.yogafitnessapp.Utils.Common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class DailyTraining extends AppCompatActivity {

    Button startButton;

    ImageView exerciseImage;

    TextView getReadyText, countDownText, timerText, exerciseName;

    MaterialProgressBar progressBar;

    LinearLayout getReadyLayout;

    int exerciseId, timeLimit = 0;

    List<Exercise> exerciseList = new ArrayList<>();

    YogaAndroidDB yogaAndroidDB;

    // Countdowns
    CountDownTimer exerciseEasyModeCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            timerText.setText("" + (millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            if (exerciseId < exerciseList.size() - 1) {
                exerciseId++;
                progressBar.setProgress(exerciseId);
                timerText.setText("");

                setExerciseInformation(exerciseId);
                startButton.setText("Start");
            } else {
                showDone();
            }
        }
    };

    CountDownTimer exerciseMediumModeCountDown = new CountDownTimer(Common.TIME_LIMIT_MEDIUM, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            timerText.setText("" + (millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            if (exerciseId < exerciseList.size() - 1) {
                exerciseId++;
                progressBar.setProgress(exerciseId);
                timerText.setText("");

                setExerciseInformation(exerciseId);
                startButton.setText("Start");
            } else {
                showDone();
            }
        }
    };

    CountDownTimer exerciseHardModeCountDown = new CountDownTimer(Common.TIME_LIMIT_HARD, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            timerText.setText("" + (millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            if (exerciseId < exerciseList.size() - 1) {
                exerciseId++;
                progressBar.setProgress(exerciseId);
                timerText.setText("");

                setExerciseInformation(exerciseId);
                startButton.setText("Start");
            } else {
                showDone();
            }
        }
    };

    CountDownTimer restingTimeCountDown = new CountDownTimer(10000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            countDownText.setText("" + (millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            setExerciseInformation(exerciseId);
            showExercises();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);

        initData();

        yogaAndroidDB = new YogaAndroidDB(this);

        startButton = (Button) findViewById(R.id.start_button);

        exerciseImage = (ImageView) findViewById(R.id.image_detail);

        getReadyText = (TextView) findViewById(R.id.get_ready_text);
        countDownText = (TextView) findViewById(R.id.countdown_text);
        timerText = (TextView) findViewById(R.id.timer);
        exerciseName = (TextView) findViewById(R.id.title);

        progressBar = (MaterialProgressBar) findViewById(R.id.progress_bar);

        getReadyLayout = (LinearLayout) findViewById(R.id.get_ready_layout);

        // Set data
        progressBar.setMax(exerciseList.size());

        setExerciseInformation(exerciseId);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startButton.getText().toString().toLowerCase().equals("start")) {
                    showGetReady();
                    startButton.setText("Done");
                } else if (startButton.getText().toString().toLowerCase().equals("done")) {

                    if (yogaAndroidDB.getSettingsMode() == 0) {
                        exerciseEasyModeCountDown.cancel();
                    } else if (yogaAndroidDB.getSettingsMode() == 1) {
                        exerciseMediumModeCountDown.cancel();
                    } else if (yogaAndroidDB.getSettingsMode() == 2) {
                        exerciseHardModeCountDown.cancel();
                    }

                    restingTimeCountDown.cancel();

                    if (exerciseId < exerciseList.size()) {
                        showRestTime();
                        exerciseId++;
                        progressBar.setProgress(exerciseId);
                        timerText.setText("");
                    } else {
                        showDone();
                    }
                } else {
                    if (yogaAndroidDB.getSettingsMode() == 0) {
                        exerciseEasyModeCountDown.cancel();
                    } else if (yogaAndroidDB.getSettingsMode() == 1) {
                        exerciseMediumModeCountDown.cancel();
                    } else if (yogaAndroidDB.getSettingsMode() == 2) {
                        exerciseHardModeCountDown.cancel();
                    }

                    restingTimeCountDown.cancel();

                    if (exerciseId < exerciseList.size()) {
                        setExerciseInformation(exerciseId);
                    } else {
                        showDone();
                    }
                }
            }
        });
    }

    private void showRestTime() {

        exerciseImage.setVisibility(View.INVISIBLE);
        timerText.setVisibility(View.INVISIBLE);

        startButton.setVisibility(View.VISIBLE);
        getReadyLayout.setVisibility(View.VISIBLE);

        startButton.setText("Skip");

        restingTimeCountDown.start();

        getReadyText.setText("Resting Time");
    }

    private void showGetReady() {

        exerciseImage.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.INVISIBLE);

        timerText.setVisibility(View.VISIBLE);
        getReadyLayout.setVisibility(View.VISIBLE);

        getReadyText.setText("Get Ready");
        new CountDownTimer(6000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                countDownText.setText("" + ((millisUntilFinished - 1000) / 1000));
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();
    }

    private void showExercises() {

        if (exerciseId < exerciseList.size()) {
            exerciseImage.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.VISIBLE);

            getReadyLayout.setVisibility(View.INVISIBLE);

            if (yogaAndroidDB.getSettingsMode() == 0) {
                exerciseEasyModeCountDown.start();
            } else if (yogaAndroidDB.getSettingsMode() == 1) {
                exerciseMediumModeCountDown.start();
            } else if (yogaAndroidDB.getSettingsMode() == 2) {
                exerciseHardModeCountDown.start();
            }

            exerciseImage.setImageResource(exerciseList.get(exerciseId).getImageId());
            exerciseName.setText(exerciseList.get(exerciseId).getName());
        } else {
            showDone();
        }
    }

    private void showDone() {

        exerciseImage.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        timerText.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        getReadyLayout.setVisibility(View.VISIBLE);

        getReadyText.setText("Done!");
        countDownText.setText("Congratulations! \n You are done with today's exercise");
        countDownText.setTextSize(20);

        // Save completed workout days to db
        yogaAndroidDB.saveWorkoutDay("" + Calendar.getInstance().getTimeInMillis());
    }

    private void setExerciseInformation(int exerciseId) {

        exerciseImage.setImageResource(exerciseList.get(exerciseId).getImageId());
        exerciseName.setText(exerciseList.get(exerciseId).getName());

        startButton.setText("Start");

        exerciseImage.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.VISIBLE);
        timerText.setVisibility(View.VISIBLE);

        getReadyLayout.setVisibility(View.INVISIBLE);
    }

    private void initData() {

        exerciseList.add(new Exercise(R.drawable.easy_pose, "Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose, "Cobra Pose"));
        exerciseList.add(new Exercise(R.drawable.downward_facing_dog, "Downward Facing Dog"));
        exerciseList.add(new Exercise(R.drawable.boat_pose, "Boat Pose"));
        exerciseList.add(new Exercise(R.drawable.half_pigeon, "Half Pigeon"));
        exerciseList.add(new Exercise(R.drawable.low_lunge, "Low Lunge"));
        exerciseList.add(new Exercise(R.drawable.upward_bow, "Upward Bow"));
        exerciseList.add(new Exercise(R.drawable.crescent_lunge, "Crescent Lunge"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose, "Warrior Pose I"));
        exerciseList.add(new Exercise(R.drawable.bow_pose, "Bow Pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose_2, "Warrior Pose II"));
    }
}
