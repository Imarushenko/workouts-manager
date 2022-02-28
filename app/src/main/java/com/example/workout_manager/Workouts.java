package com.example.workout_manager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Workouts extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        Button createWorkout_btn = findViewById(R.id.create_activity_btn_w);
        createWorkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointToLogin();
            }
        });

    }

    public void pointToLogin() {
        Intent point_to_createWorkout_screen = new Intent(this, CreateWorkout.class);
        startActivity(point_to_createWorkout_screen);
    }


}