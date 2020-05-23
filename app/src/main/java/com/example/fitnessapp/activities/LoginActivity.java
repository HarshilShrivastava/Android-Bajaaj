package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.model_class.LoginResponse;
import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.storage.SharedPreferenceManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        requestWindowFeature ( Window.FEATURE_NO_TITLE ); //will hide the title
        getSupportActionBar ().hide (); // hide the title bar
        setContentView ( R.layout.activity_login );

        editTextUsername = findViewById ( R.id.luname );
        editTextPassword = findViewById ( R.id.lpassword );

        findViewById ( R.id.loginbtn ).setOnClickListener ( this );
        findViewById ( R.id.signupclick ).setOnClickListener ( this );
    }

    @Override
    protected void onStart() {
        super.onStart ();
        if (SharedPreferenceManager.getInstance ( this ).isLoggedIn ()) {
            Intent intent = new Intent ( this, UserStatistics.class );
            intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity ( intent );
        }
    }

    private void userLogin() {

        String username = editTextUsername.getText ().toString ().trim ();
        String password = editTextPassword.getText ().toString ().trim ();

        if (username.isEmpty ()) {
            editTextUsername.setError ( "Username is required!" );
            editTextUsername.requestFocus ();
            return;
        }
        if (password.isEmpty ()) {
            editTextPassword.setError ( "Password is required!" );
            editTextPassword.requestFocus ();
            return;
        }
        if (password.length () < 8) {
            editTextPassword.setError ( "Password should be atleast 8 characters long" );
            editTextPassword.requestFocus ();
            return;
        }

        Call<LoginResponse> call = RetrofitClient.getInstance ().getApi ().userLogin ( username, password );


        call.enqueue ( new Callback<LoginResponse> () {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                if(loginResponse != null) {
                    if (loginResponse.isSuccess ()) {
                        Toast.makeText ( LoginActivity.this, loginResponse.getError_message (), Toast.LENGTH_LONG ).show ();
                        SharedPreferenceManager.getInstance ( LoginActivity.this ).saveUser ( loginResponse.getUser () );
                        Intent intent = new Intent ( LoginActivity.this, UserStatistics.class );
                        intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                        startActivity ( intent );

                    } else {
                        Toast.makeText ( LoginActivity.this, "Login failed", Toast.LENGTH_LONG ).show ();
                    }
                }


//int try1;
//        LoginResponse loginResponse = response.body ();
//        try1 = loginResponse.getResponse ();
//       if(try1 == 200){
//           Toast.makeText ( LoginActivity.this, "Logged in Successfully", Toast.LENGTH_LONG ).show ();
//           SharedPreferenceManager.getInstance ( LoginActivity.this ).saveUser ( loginResponse.getUser () );
//
//           Intent intent = new Intent ( LoginActivity.this, UserStatistics.class );
//          intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
//          startActivity ( intent );
//       }
//       else{
//           Toast.makeText ( LoginActivity.this, "Log in failed", Toast.LENGTH_LONG ).show ();
//       }

            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        } );
    }


    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.loginbtn:
                userLogin ();
                break;
            case R.id.signupclick:
                startActivity ( new Intent ( this, MainActivity.class ) );
                break;
        }
    }
}