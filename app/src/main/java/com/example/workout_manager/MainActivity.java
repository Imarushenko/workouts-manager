package com.example.workout_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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