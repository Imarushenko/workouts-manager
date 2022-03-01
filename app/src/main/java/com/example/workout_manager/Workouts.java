package com.example.workout_manager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    // properties
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AdapterForRecycleView adapterForRecycleView;
    ArrayList<KindOfWorkouts> list;

    // logout
    Button logout_btn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        // logout settings & button
        mAuth = FirebaseAuth.getInstance();
        logout_btn = findViewById(R.id.sign_out_w);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();
            }
        });

        // recycle view settings
        // find recycle list by id
        recyclerView = findViewById(R.id.list_of_workouts_w);
        // firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("workouts");
        // recycle view settings
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // array list & adapter
        list = new ArrayList<>();
        adapterForRecycleView = new AdapterForRecycleView(this, list);
        recyclerView.setAdapter(adapterForRecycleView);

        // firebase function
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    KindOfWorkouts kindOfWorkouts = dataSnapshot.getValue(KindOfWorkouts.class);
                    // add to list
                    list.add(kindOfWorkouts);
                }
                adapterForRecycleView.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // click the button functions
        Button createWorkout_btn = findViewById(R.id.create_activity_btn_w);
        createWorkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointToCreateWorkout();
            }
        });

    }

    public void pointToCalculations(View view) {
        Intent point_to_calculations = new Intent(Workouts.this, Calculates.class);
        startActivity(point_to_calculations);
    }

    private void signOutUser() {
        Intent mainActivity = new Intent(Workouts.this, MainActivity.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
        finish();
    }

    public void pointToCreateWorkout() {
        Intent point_to_createWorkout_screen = new Intent(this, CreateWorkout.class);
        startActivity(point_to_createWorkout_screen);
    }



}