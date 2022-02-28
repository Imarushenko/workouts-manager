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

    // TODO: move this function to other activity afterwards
    // Read from the database
//    public void readWorkoutsFromRealTimeDatabase(String id) {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("users").child(id);
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                User value = dataSnapshot.getValue(User.class);
//
//                // which info we want to get?
//                value.getFull_name();
//                value.getEmail();
//                value.getPhone_number();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });
//    }


}