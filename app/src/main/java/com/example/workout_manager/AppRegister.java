package com.example.workout_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppRegister extends AppCompatActivity {
    // firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_register);
        // firebase
        mAuth = FirebaseAuth.getInstance();

        // call 2 (or more) functions once click on 1 button
        Button reg_btn = findViewById(R.id.submit_btn_reg);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // functions of Firebase bellow
                register();
                addDataToRealTimeDataBase();
                registeredSuccessfully();
            }
        });
    }

    // register function = Firebase
    public void register() {
        // email & password - take from the activity
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

    // add data to real-time database (& User class)
    public void addDataToRealTimeDataBase() {
        // what we gonna save in the database
        String first_name = ((EditText) findViewById(R.id.full_name_txt_reg)).getText().toString();
        String id = ((EditText) findViewById(R.id.id_txt_reg)).getText().toString();
        String email = ((EditText) findViewById(R.id.email_field_reg)).getText().toString();
        String phone_number = ((EditText) findViewById(R.id.phone_field_reg)).getText().toString();

        // new User object
        User user = new User(first_name, id, phone_number, email);

        // Write to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // manager - access the database
        // location where to add the data - if the location isn't exists - it will be created by the "path"
        DatabaseReference myRef = database.getReference("users").child(user.getId());

        myRef.setValue(user);
    }

    // go back to login screen (Activity) function
    public void pointToLogin(View view) {
        Intent point_to_login_screen = new Intent(this, MainActivity.class);
        startActivity(point_to_login_screen);
    }

    // popup - user registered successfully
    public void registeredSuccessfully() {
        Toast popUp = Toast.makeText(this, "Successfully Registered!!!", Toast.LENGTH_LONG);
        popUp.show();
    }
}