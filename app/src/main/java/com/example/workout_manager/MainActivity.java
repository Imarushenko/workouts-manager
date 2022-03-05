package com.example.workout_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    // firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // firebase
        mAuth = FirebaseAuth.getInstance();

        // onClick function
        Button login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    // login auth - firebase
    public void signIn() {
        String email = ((EditText)findViewById(R.id.field_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.field_password)).getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            pointToLogin();

                            // show the activity layout (workouts) in the workouts page
                            // go to workouts INTENT
                            Intent showAllWorkouts = new Intent(MainActivity.this, Workouts.class);
                            startActivity(showAllWorkouts);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            checkSignInValidation();
                        }
                    }
                });
    }

    // popup - points to if user's details is wrong
    public void checkSignInValidation() {
        Toast popUp = Toast.makeText(this, "Email or Password is wrong", Toast.LENGTH_LONG);
        popUp.show();
    }

    // go to register INTENT
    public void pointToRegister(View view) {
        Intent point_to_reg_screen = new Intent(this, AppRegister.class);
        startActivity(point_to_reg_screen);
    }

    // go to login INTENT
    public void pointToLogin() {
        Intent point_to_login_screen = new Intent(this, Workouts.class);
        startActivity(point_to_login_screen);
    }
}