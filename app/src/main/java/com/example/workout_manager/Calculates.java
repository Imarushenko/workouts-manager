package com.example.workout_manager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Calculates extends AppCompatActivity {
    // properties
    // BMI
    EditText height;
    EditText weight;
    TextView result;
    // BMR
    EditText age;
    EditText heightBMR;
    EditText weightBMR;
    TextView resultBMR;
    Button BMR_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculates);
        String res = "";
        // get fields by id from the activity
        // BMI
        height = (EditText) findViewById(R.id.weight_field);
        weight = (EditText) findViewById(R.id.height_field);
        result = (TextView) findViewById(R.id.result_bmi);
        // BMR
        age = (EditText) findViewById(R.id.age_bmr_field);
        heightBMR = (EditText) findViewById(R.id.height_bmr_field);
        weightBMR = (EditText) findViewById(R.id.weight_bmr_field);
        resultBMR = (TextView) findViewById(R.id.result_bmr);
        BMR_btn = (Button) findViewById(R.id.calculate_BMR_btn);
        // click button " calculate bmr "
        BMR_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalculateBmiBmr().execute();
            }
        });
    }

    public void calculateBMI(View v) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = (weightValue / (heightValue * heightValue) / 10);
            result.setText(Float.toString(bmi));

    }

//    public void calculateBMR(View v) {
//
//    }

    // inner class of AsyncTask
    public class CalculateBmiBmr extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
            Toast.makeText(Calculates.this, "BMR calculated successfully!!", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String ageStr = age.getText().toString();
                String hStr = heightBMR.getText().toString();
                String wStr = weightBMR.getText().toString();

                float ageValue = Float.parseFloat(ageStr);
                float hValue = Float.parseFloat(hStr) / 100;
                float wValue = Float.parseFloat(wStr);

                float bmr = (float) (((ageValue * 5) - (hValue * 6.25) + (wValue * 10)) * 1.5);
                resultBMR.setText((Float.toString(bmr)));
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }
}