package com.example.nexus.yogafitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nexus.yogafitnessapp.Adapter.RecyclerViewAdapter;
import com.example.nexus.yogafitnessapp.Utils.DataInitializer;
import com.example.nexus.yogafitnessapp.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercises extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();

    RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;

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
