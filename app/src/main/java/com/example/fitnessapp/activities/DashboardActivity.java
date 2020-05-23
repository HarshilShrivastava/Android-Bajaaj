package com.example.fitnessapp.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.DashboardResponse;
import com.example.fitnessapp.model_class.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtViewResult;
    private TextView  weightVal;
    private TextView conditionVal;
    private TextView goalVal;
    private TextView bmiVal;
    private TextView bmrVal;
    private TextView calVal;
    private TextView testTry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        requestWindowFeature ( Window.FEATURE_NO_TITLE ); //will hide the title
        getSupportActionBar ().hide (); // hide the title bar
        setContentView ( R.layout.activity_dashboard );

        final ListView listView = (ListView) findViewById ( R.id.listView );

        txtViewResult = findViewById ( R.id.test );
        weightVal = findViewById ( R.id.weightValue );
        conditionVal = findViewById ( R.id.conditionType );
        goalVal = findViewById ( R.id.goalValue );
        bmiVal = findViewById ( R.id.bmiValue );
        bmrVal = findViewById ( R.id.bmrValue );
        calVal = findViewById ( R.id.calValue );
        testTry = findViewById ( R.id.testTry );
        findViewById ( R.id.searchFood ).setOnClickListener ( this );
        Call<DashboardResponse> call = RetrofitClient.getInstance ().getApi ().getInfo ();
        call.enqueue ( new Callback<DashboardResponse> () {
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
                if(!response.isSuccessful ()){
                 txtViewResult.setText ( "Code" + response.code () );
                 return;
                }
                DashboardResponse dashboardResponse = response.body ();
                String weight="";
                String condition="";
                String goal="";
                String bmi="";
                String bmr="";
                String cal="";
                weight += dashboardResponse.getStatistics ().getWeight ()+"Kg";
                bmr += dashboardResponse.getStatistics ().getBMR ()+"\n";
                bmi+= dashboardResponse.getStatistics ().getBMI ()+"\n";
                goal += dashboardResponse.getStatistics ().getGoals ();
                cal += +dashboardResponse.getStatistics ().getDailyCalories ()+"\n";
               condition += dashboardResponse.getStatistics ().getCondition ();
                  weightVal.append ( weight );
                  conditionVal.append ( condition );
                  goalVal.append ( goal );
                  bmiVal.append ( bmi );
                  bmrVal.append ( bmr );
                  calVal.append ( cal );

            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {
            txtViewResult.setText ( t.getMessage () );
            }
        } );

        Call<List<Food>> callFood = RetrofitClient.getInstance ().getApi ().getFood ();
        callFood.enqueue ( new Callback<List<Food>> () {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
               List<Food> foods=response.body ();

               String[] foodNames = new String[foods.size ()];

                for(int i =0;i<foods.size ();i++){
                    foodNames[i] = foods.get(i).getName ();
                }
                listView.setAdapter (
                        new ArrayAdapter<String> (
                                getApplicationContext (), android.R.layout.simple_list_item_1,foodNames
                        )

                );


            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                testTry.setText ( t.getMessage () );
            }
        } );


    }

    public void searchFood(){
        Intent intent = new Intent ( DashboardActivity.this, SearchFood.class );
        startActivity ( intent );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.searchFood:
                searchFood ();
                break;

        }

    }
}

