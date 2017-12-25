package com.example.nexus.yogafitnessapp.Custom;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;

/**
 * Created by James Sarkar.
 */

public class WorkoutDoneDecorator implements DayViewDecorator {

    HashSet<CalendarDay> list;

    ColorDrawable colorDrawable;

    public WorkoutDoneDecorator(HashSet<CalendarDay> list) {
        this.list = list;
        colorDrawable = new ColorDrawable(Color.parseColor("#e57373"));
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return list.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(colorDrawable);
    }
}
