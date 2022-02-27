package com.example.workout_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AppRegister extends AppCompatActivity {
    // firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_register);
        // firebase
        mAuth = FirebaseAuth.getInstance();
    }

    // register function
    public void register(View view) {
        String email = ((EditText) findViewById(R.id.email_field_reg)).getText().toString();
        String password = ((EditText) findViewById(R.id.pass_field_reg)).getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("result" , "registry - OK");
                        } else {
                            Log.d("result" , "registry - Failed");
                        }
                    }
                });
    }
}