package com.androidproject.yogafitnessapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Sarkar.
 */

public class YogaAndroidDB extends SQLiteAssetHelper {

    private static final String DB_NAME = "YogaAndroid.db";

    private static final String SETTINGS_TABLE_NAME = "SettingsActivity";

    private static final String WORKOUT_DAYS_TABLE_NAME = "WorkoutDays";

    private static final String MODE_COLUMN_NAME = "Mode";

    private static final String DAY_COLUMN_NAME = "Day";

    private static final int DB_VERSION = 1;

    public YogaAndroidDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Retrieve settings information from db
    public int getSettingsMode() {
        SQLiteDatabase database = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {MODE_COLUMN_NAME};

        queryBuilder.setTables(SETTINGS_TABLE_NAME);

        Cursor cursor = queryBuilder.query(database, sqlSelect, null, null, null, null, null);
        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(MODE_COLUMN_NAME));
    }

    // Save settings to database, 0 = Easy, 1 = Medium, 2 = Hard
    public void saveSettingsMode(int value) {
        SQLiteDatabase database = getReadableDatabase();

        String query = "UPDATE " + SETTINGS_TABLE_NAME + " SET " + MODE_COLUMN_NAME + " = "  + value;

        database.execSQL(query);
    }

    // Retrieve workout days from db
    public List<String> getWorkOutDays() {
        SQLiteDatabase database = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {DAY_COLUMN_NAME};

        queryBuilder.setTables(WORKOUT_DAYS_TABLE_NAME);

        Cursor cursor = queryBuilder.query(database, sqlSelect, null, null, null, null, null);

        List<String> workoutDays = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                workoutDays.add(cursor.getString(cursor.getColumnIndex(DAY_COLUMN_NAME)));
            } while (cursor.moveToNext());
        }

        return workoutDays;
    }

    // Save workout day to db
    public void saveWorkoutDay(String value) {
        SQLiteDatabase database = getReadableDatabase();

        String query = String.format("INSERT INTO " + WORKOUT_DAYS_TABLE_NAME + "(" + DAY_COLUMN_NAME + ")"
                + " VALUES(%s)", value);

        database.execSQL(query);
    }
}
