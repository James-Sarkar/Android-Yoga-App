package com.example.nexus.yogafitnessapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by James Sarkar.
 */

public class YogaAndroidDB extends SQLiteAssetHelper {

    private static final String DB_NAME = "YogaAndroid.db";

    private static final String TABLE_NAME = "Settings";

    private static final String MODE_COLUMN_NAME = "Mode";

    private static final int DB_VERSION = 1;

    public YogaAndroidDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Retrieve settings information from db
    public int getSettingsMode() {

        SQLiteDatabase database = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {MODE_COLUMN_NAME};

        queryBuilder.setTables(TABLE_NAME);

        Cursor cursor = queryBuilder.query(database, sqlSelect, null, null, null, null, null);
        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(MODE_COLUMN_NAME));
    }

    // Save settings to database, 0 = Easy, 1 = Medium, 2 = Hard
    public void saveSettingsMode(int value) {

        SQLiteDatabase database = getReadableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + MODE_COLUMN_NAME + " = "  + value;
        database.execSQL(query);
    }
}
