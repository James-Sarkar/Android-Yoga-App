package com.androidproject.yogafitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidproject.yogafitnessapp.Adapter.RecyclerViewAdapter;
import com.androidproject.yogafitnessapp.Utils.DataInitializer;
import com.androidproject.yogafitnessapp.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercisesActivity extends AppCompatActivity {

    private List<Exercise> exerciseList = new ArrayList<>();

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

        DataInitializer.initData(exerciseList);

        recyclerView = (RecyclerView) findViewById(R.id.list_exercises);

        recyclerViewAdapter = new RecyclerViewAdapter(exerciseList, getBaseContext());

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
