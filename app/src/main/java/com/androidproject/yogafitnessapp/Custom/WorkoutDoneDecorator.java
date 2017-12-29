package com.androidproject.yogafitnessapp.Custom;

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

    private HashSet<CalendarDay> list;

    private ColorDrawable colorDrawable;

    private static final String COLOUR_HASH = "#e57373";

    public WorkoutDoneDecorator(HashSet<CalendarDay> list) {
        this.list = list;
        colorDrawable = new ColorDrawable(Color.parseColor(COLOUR_HASH));
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
