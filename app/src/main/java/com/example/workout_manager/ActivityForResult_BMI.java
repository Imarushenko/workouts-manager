package com.example.workout_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityForResult_BMI extends AppCompatActivity {
    // key name that connects between the activities values
    public static final String KEY_NAME = "RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result_bmi);

        // get the values from the previous screen
        Intent intent = getIntent();
        final float h = intent.getFloatExtra("h", 0);
        final float w = intent.getFloatExtra("w", 0);

        // show the BMI values on the activity_for_result_bmi.xml layout
        TextView bmi_result = findViewById(R.id.values_AFR);
        bmi_result.setText("BMI Values:  Height = " + h + ",  Weight = " + w);

        Button calc_bmi_btn = findViewById(R.id.values_btn_AFR);

        calc_bmi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calculations of the BMI
                float hDiv = h / 100;
                float bmi_res = w / (hDiv * hDiv) ;

                // send the data back
                Intent bmi_result_intent = new Intent();

                // validations of the BMI values
                if(h == 0) {
                    bmi_result_intent.putExtra(KEY_NAME, "Height cannot be 0");
                    setResult(RESULT_OK, bmi_result_intent);
                    finish();
                }

                else if(w == 0) {
                    bmi_result_intent.putExtra(KEY_NAME, "Weight cannot be 0");
                    setResult(RESULT_OK, bmi_result_intent);
                    finish();
                }

                else {
                    bmi_result_intent.putExtra(KEY_NAME, Float.toString(bmi_res));
                    setResult(RESULT_OK, bmi_result_intent);
                    finish();
                }
            }
        });
    }
}



