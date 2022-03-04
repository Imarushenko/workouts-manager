package com.example.workout_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityForResult_BMI extends AppCompatActivity {
    public static final String KEY_NAME = "RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result_bmi);

        Intent intent = getIntent();
        final float h = intent.getFloatExtra("h", 0);
        final float w = intent.getFloatExtra("w", 0);

        TextView bmi_result = findViewById(R.id.values_AFR);
        bmi_result.setText("BMI Values:  Height = " + h + ",  Weight = " + w);

        Button calc_bmi_btn = findViewById(R.id.values_btn_AFR);

        calc_bmi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float hDiv = h / 100;
                float bmi_res = w / (hDiv * hDiv) ;

                if(h == 0) {
                    Intent bmi_result_intent = new Intent();
                    bmi_result_intent.putExtra(KEY_NAME, "Height cannot be 0");
                    setResult(RESULT_OK, bmi_result_intent);
                    finish();
                }

                // send the data back
                Intent bmi_result_intent = new Intent();
                bmi_result_intent.putExtra(KEY_NAME, Float.toString(bmi_res));
                setResult(RESULT_OK, bmi_result_intent);
                finish();
            }
        });
    }
}



