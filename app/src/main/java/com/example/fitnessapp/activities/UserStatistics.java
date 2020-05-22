package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.UserStatsResponse;
import com.example.fitnessapp.storage.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserStatistics extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener   {

    private EditText editTextName, editTextWeight,editTextHeight, editTextAge;
    private Spinner genSpinner, goalSpinner, activitySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView ( R.layout.activity_user_statistics );
         genSpinner = findViewById(R.id.spinGender);
        genSpinner.setOnItemSelectedListener(this);
         goalSpinner = findViewById(R.id.spinGoals);
        goalSpinner.setOnItemSelectedListener(this);
         activitySpinner = findViewById(R.id.spinActivity);
        activitySpinner.setOnItemSelectedListener(this);
        editTextName = findViewById ( R.id.nameInput );
        editTextWeight = findViewById ( R.id.weightInput );
        editTextHeight = findViewById ( R.id.heightInput );
        editTextAge = findViewById ( R.id.ageInput );


        findViewById ( R.id.savebtn ).setOnClickListener ( this );

    }
    @Override
    protected void onStart() {
        super.onStart ();
        if(!SharedPreferenceManager.getInstance ( this ).isLoggedIn ()){
            Intent intent = new Intent ( this, MainActivity.class );
            intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity ( intent );
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void userStats(){
        String name = editTextName.getText ().toString ().trim ();
        String weightString = editTextWeight.getText ().toString ().trim ();
        String heightString = editTextHeight.getText ().toString ().trim ();
        String ageString = editTextAge.getText ().toString ().trim ();
        int gen =genSpinner.getSelectedItemPosition ();
        int goa = goalSpinner.getSelectedItemPosition ();
        int act = activitySpinner.getSelectedItemPosition ();
        String gender, goals, activity;
        gender = Integer.toString ( gen );
        goals = Integer.toString ( goa );
        activity = Integer.toString ( act );
        int weight =0;
        int height =0;
        int age=0;
        if (name.isEmpty()) {
            editTextName.setError("Username is required!");
            editTextName.requestFocus();
            return;
        }
        if (weightString.isEmpty()) {
            editTextWeight.setError("Email is required!");
            editTextWeight.requestFocus();
            return;
        }
        if (heightString.isEmpty()) {
            editTextHeight.setError("Username is required!");
            editTextHeight.requestFocus();
            return;
        }
        if(ageString.isEmpty ()){
            editTextAge.setError ( "Age is required" );
            editTextAge.requestFocus ();
            return;
        }
        if( gen == 0){
            Toast.makeText ( this, "Please select Gender", Toast.LENGTH_LONG ).show ();
        }

        if(goa == 0){
            Toast.makeText ( this, "Please select a goal", Toast.LENGTH_LONG ).show ();
        }

        if( act == 0){
            Toast.makeText ( this, "Please select activity type", Toast.LENGTH_LONG ).show ();
        }
        if(!"".equals ( weightString ))
        {
            weight = Integer.parseInt ( weightString );
        }
        if(!"".equals ( heightString ))
        {
            height = Integer.parseInt ( heightString );
        }
        if(!"".equals ( ageString ))
        {
            age = Integer.parseInt ( ageString );
        }

        Call<UserStatsResponse> call = RetrofitClient.getInstance ().getApi ().userInfo (gender, weight,height,goals,activity,age  );
        call.enqueue ( new Callback<UserStatsResponse> () {
            @Override
            public void onResponse(Call<UserStatsResponse> call, Response<UserStatsResponse> response) {
                UserStatsResponse userStatsResponse = response.body ();
                if (userStatsResponse != null) {
                    if (userStatsResponse.isSuccess ()) {
                        Toast.makeText ( UserStatistics.this, "Information Saved successfully", Toast.LENGTH_LONG ).show ();
                        Intent intent = new Intent ( UserStatistics.this, DashboardActivity.class );
                        intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity ( intent );
                    } else {
                        Toast.makeText ( UserStatistics.this, "All the fields are required", Toast.LENGTH_LONG ).show ();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserStatsResponse> call, Throwable t) {

            }
        } );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.savebtn:
                userStats ();
                break;

        }
    }
}
