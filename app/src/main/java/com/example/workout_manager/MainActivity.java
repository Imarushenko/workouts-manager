package com.example.workout_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    // firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // firebase
        mAuth = FirebaseAuth.getInstance();
    }

    // login function
    public void login(View view) {
        // get email address & password
        String email = ((EditText)findViewById(R.id.field_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.field_password)).getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("result" , "login OK");
                        } else {
                            Log.d("result" , "login FAILED");
                        }
                    }
                });
    }

    public void checkValidation(View view) {
        Toast popUp = Toast.makeText(this, "Email or Password is wrong", Toast.LENGTH_LONG);
        popUp.show();
    }

    public void pointToRegister(View view) {
        Intent point_to_reg_screen = new Intent(this, AppRegister.class);
        startActivity(point_to_reg_screen);
    }
}