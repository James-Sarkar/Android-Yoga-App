package com.example.nexus.yogafitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nexus.yogafitnessapp.Custom.WorkoutDoneDecorator;
import com.example.nexus.yogafitnessapp.Database.YogaAndroidDB;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Calendar extends AppCompatActivity {

    MaterialCalendarView materialCalendarView;

    YogaAndroidDB yogaAndroidDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        yogaAndroidDB = new YogaAndroidDB(this);

        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendar);

        List<String> workoutDays = yogaAndroidDB.getWorkOutDays();

        HashSet<CalendarDay> convertedWorkoutDaysList = new HashSet<>();

        for (String workoutDay : workoutDays) {
            convertedWorkoutDaysList.add(CalendarDay.from(new Date(Long.parseLong(workoutDay))));
        }

        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedWorkoutDaysList));
    }
}
