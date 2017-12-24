package com.example.nexus.yogafitnessapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewExercise extends AppCompatActivity {

    int imageId;

    String name;

    TextView title, timer;

    ImageView imageDetail;

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        timer = (TextView) findViewById(R.id.timer);
        timer.setText("");
        title = (TextView) findViewById(R.id.title);
        imageDetail = (ImageView) findViewById(R.id.image_detail);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(20000, 1000) {

                    @Override
                    public void onTick(long l) {
                        timer.setText(""+1/1000);
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(ViewExercise.this, "Done!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }.start();
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
