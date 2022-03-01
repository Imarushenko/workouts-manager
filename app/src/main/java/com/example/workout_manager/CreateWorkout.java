package com.example.workout_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateWorkout extends AppCompatActivity {
    // firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);
        // firebase
        mAuth = FirebaseAuth.getInstance();

        Button addWorkoutsData_btn = findViewById(R.id.addActivity_btn_create_workout);
        addWorkoutsData_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add the workout to the database
                addWorkoutsDataToRealTimeDataBase();
                // popup
                workOutAdded();
            }
        });

    }

    // add data to real-time database (& User class)
    public void addWorkoutsDataToRealTimeDataBase() {
        // what we gonna save in the database
        String id = ((EditText) findViewById(R.id.id_field_create_workout)).getText().toString();
        String title = ((EditText) findViewById(R.id.workout_field_create_workout)).getText().toString();
        String typeOfWorkout = ((EditText) findViewById(R.id.type_field_create_workout)).getText().toString();
        String workoutDetails = ((EditText) findViewById(R.id.details_field_create_workout)).getText().toString();
        String sets = ((EditText) findViewById(R.id.sets_field_create_workout)).getText().toString();

        // new User object
        KindOfWorkouts createWorkout = new KindOfWorkouts(id, title, typeOfWorkout, workoutDetails, sets);

        // Write to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // manager - access the database
        // location where to add the data - if the location isn't exists - it will be created by the "path"
        DatabaseReference myRef = database.getReference("workouts").child(createWorkout.getId());

        myRef.setValue(createWorkout);
    }

    // popup
    public void workOutAdded() {
        Toast popUp = Toast.makeText(this, "Workout Was Added Successfully!", Toast.LENGTH_LONG);
        popUp.show();
    }
}