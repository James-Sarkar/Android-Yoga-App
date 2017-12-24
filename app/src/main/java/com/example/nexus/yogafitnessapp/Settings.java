package com.example.nexus.yogafitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.nexus.yogafitnessapp.Database.YogaAndroidDB;

public class Settings extends AppCompatActivity {

    Button saveButton;

    RadioButton easyButton, mediumButton, hardButton;

    RadioGroup radioGroup;

    YogaAndroidDB yogaAndroidDB;

    ToggleButton switchAlarm;

    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize view
        saveButton = (Button) findViewById(R.id.save_button);

        easyButton = (RadioButton) findViewById(R.id.easy_button);
        mediumButton = (RadioButton) findViewById(R.id.medium_button);
        hardButton = (RadioButton) findViewById(R.id.hard_button);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        switchAlarm = (ToggleButton) findViewById(R.id.switch_alarm);

        timePicker = (TimePicker) findViewById(R.id.time_picker);

        yogaAndroidDB = new YogaAndroidDB(this);

        int mode = yogaAndroidDB.getSettingsMode();

        setRadioButton(mode);

        // Events
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveDifficultyMode();

                saveAlarm(switchAlarm.isChecked());
                
                Toast.makeText(Settings.this, "Difficulty Level Saved!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void saveAlarm(boolean checked) {

        if (checked) {

        }
    }

    private void saveDifficultyMode() {

        int selectId = radioGroup.getCheckedRadioButtonId();

        if (selectId == easyButton.getId()) {
            yogaAndroidDB.saveSettingsMode(0);
        } else if (selectId == mediumButton.getId()) {
            yogaAndroidDB.saveSettingsMode(1);
        } else if (selectId == hardButton.getId()) {
            yogaAndroidDB.saveSettingsMode(2);
        }
    }

    private void setRadioButton(int mode) {

        if (mode == 0) {
            radioGroup.check(R.id.easy_button);
        } else if (mode == 1) {
            radioGroup.check(R.id.medium_button);
        } else if (mode == 2) {
            radioGroup.check(R.id.hard_button);
        }
    }
}
