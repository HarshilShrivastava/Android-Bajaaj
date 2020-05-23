package com.example.fitnessapp.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.storage.SharedPreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;
    RelativeLayout rellay1,rellay2;
    Handler handler = new Handler (  );
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        requestWindowFeature( Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
//
        setContentView( R.layout.activity_main);

        rellay1=(RelativeLayout) findViewById(R.id.rellays1);
        rellay2=(RelativeLayout) findViewById(R.id.rellays2);
        handler.postDelayed(runnable, 2000);


        editTextUsername = findViewById(R.id.uname);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextConfirmPassword = findViewById(R.id.confpassword);

        findViewById ( R.id.signupbtn ).setOnClickListener ( this );
        findViewById ( R.id.loginclick ).setOnClickListener ( this );

    }

    @Override
    protected void onStart() {
        super.onStart ();
        if(SharedPreferenceManager.getInstance ( this ).isLoggedIn ()){
            Intent intent = new Intent ( this, UserStatistics.class );
            intent.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity ( intent );
        }
    }
    private void userSignUp() {
        final String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirm_password = editTextConfirmPassword.getText().toString().trim();

        if (username.isEmpty()) {
            editTextUsername.setError("Username is required!");
            editTextUsername.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 8) {
            editTextPassword.setError("Password should be atleast 8 characters long");
            editTextPassword.requestFocus();
            return;
        }
        if (!confirm_password.equals(password)) {
            editTextConfirmPassword.setError("Passwords do not match");
            editTextConfirmPassword.requestFocus();
            return;
        }
//
        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().createUser(username,email,password,confirm_password);
        call.enqueue ( new Callback<ResponseBody> () {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String s= response.body ().string ();
//                    Toast.makeText ( MainActivity.this, s, Toast.LENGTH_LONG ).show ();
//                } catch (IOException e) {
//                    e.printStackTrace ();
//                }



//                working code
                String s = null;
                try {
                    s = response.body().string();
//            Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(s !=null){
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        Toast.makeText(MainActivity.this,jsonObject.getString("error_message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
//                try code

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText ( MainActivity.this,t.getMessage (), Toast.LENGTH_LONG ).show ();

            }
        } );




//
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signupbtn:
                userSignUp();
                break;
            case R.id.loginclick:
                startActivity ( new Intent ( this, LoginActivity.class ) );
                break;
        }
    }
}
