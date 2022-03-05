package com.example.workout_manager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AsyncTask_Calculates extends AppCompatActivity {
    // firebase
    private FirebaseAuth mAuth;
    // properties
    // BMI
    EditText height;
    EditText weight;
    TextView bmi_result;
    Button bmi_btn;
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
        // firebase
        mAuth = FirebaseAuth.getInstance();
        // get fields by id from the activity
        // BMI
        height = (EditText) findViewById(R.id.height_field);
        weight = (EditText) findViewById(R.id.weight_field);
        bmi_result = (TextView) findViewById(R.id.result_bmi);
        bmi_btn = (Button) findViewById(R.id.BMI_btn);

        bmi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float h = Integer.parseInt(height.getText().toString());
                float w = Integer.parseInt(weight.getText().toString());
                // INTENT - activity for result
                Intent intent = new Intent(AsyncTask_Calculates.this, ActivityForResult_BMI.class);
                intent.putExtra("h", h);
                intent.putExtra("w", w);
                startForResult.launch(intent);
            }
        });

        // BMR - get fields by id
        age = (EditText) findViewById(R.id.age_bmr_field);
        heightBMR = (EditText) findViewById(R.id.height_bmr_field);
        weightBMR = (EditText) findViewById(R.id.weight_bmr_field);
        resultBMR = (TextView) findViewById(R.id.result_bmr);
        BMR_btn = (Button) findViewById(R.id.calculate_BMR_btn);
        // click button " calculate bmr "
        BMR_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // execute AsyncTask class bellow
                new CalculateBmiBmr().execute();
            }
        });
    }

    // activityForResult method using activity for result launcher
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK) {
                if(result.getData() != null && result.getData().getStringExtra(ActivityForResult_BMI.KEY_NAME) != null) {
                    bmi_result.setText(result.getData().getStringExtra(ActivityForResult_BMI.KEY_NAME));
                }
            }
        }
    });

    // inner class of AsyncTask
    public class CalculateBmiBmr extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
        }

        // a function that runs after we clicked the button (post execute)
        @Override
        protected void onPostExecute(String s) {

            if(isValidBmr) {
                // popup
                Toast.makeText(AsyncTask_Calculates.this, "BMR calculated successfully!!", Toast.LENGTH_LONG).show();

                // what we gonna save in the database
                String weight_bmr = ((EditText) findViewById(R.id.weight_bmr_field)).getText().toString();
                String height_bmr = ((EditText) findViewById(R.id.height_bmr_field)).getText().toString();
                String age_bmr = ((EditText) findViewById(R.id.age_bmr_field)).getText().toString();
                String bmr_result = ((TextView) findViewById(R.id.result_bmr)).getText().toString();

                // new UserDetails object
                UserDetails_BMI_BMR user_details = new UserDetails_BMI_BMR(weight_bmr, height_bmr, age_bmr, bmr_result);

                // Write to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance(); // manager - access the database
                // location where to add the data - if the location isn't exists - it will be created by the "path"
                DatabaseReference myRef = database.getReference("BMR Details").child(user_details.getAge());

                myRef.setValue(user_details);
            }
        }

        // helper boolean property to check if the BMR parameters are valid. if it is valid: add it to the database & show the popup
        boolean isValidBmr = false;

        // a function that runs in the background - calculate BMR
        @Override
        protected String doInBackground(String... strings) {
            try {
                // stringify the values
                String ageStr = age.getText().toString();
                String hStr = heightBMR.getText().toString();
                String wStr = weightBMR.getText().toString();

                // parse string values to float
                float ageValue = Float.parseFloat(ageStr);
                float hValue = Float.parseFloat(hStr) / 100;
                float wValue = Float.parseFloat(wStr);

                // BMI validations
                if(hValue == 0) {
                    resultBMR.setText("Height cannot be 0");
                }

                if(ageValue < 0 || ageValue > 120) {
                    resultBMR.setText("Please enter a valid age");
                }

                else if(wValue == 0) {
                    resultBMR.setText("Weight cannot be 0");
                }

                // BMI calculations
                else {
                    float bmr = (float) (((ageValue * 5) - (hValue * 6.25) + (wValue * 10)) * 1.5);
                    resultBMR.setText((Float.toString(bmr)));
                    isValidBmr = true;
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }
    }
}