package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.api.RetrofitClient;
import com.example.fitnessapp.model_class.Food;
import com.example.fitnessapp.model_class.FoodQuery;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFood extends AppCompatActivity implements View.OnClickListener{
    private TextView labelDisplay;

    private EditText searchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        requestWindowFeature ( Window.FEATURE_NO_TITLE ); //will hide the title
        getSupportActionBar ().hide (); // hide the title bar
        setContentView ( R.layout.activity_search_food );
        searchText = findViewById ( R.id.searchFood );

        labelDisplay=findViewById ( R.id.labelDisplay );
        findViewById ( R.id.searchFoodBtn ).setOnClickListener ( this );



    }





    public void requestFood(){
        final String foodItem = searchText.getText ().toString ().trim ();
        if(foodItem.isEmpty ()){
            searchText.setError ( "Please enter an item to search" );
            searchText.requestFocus ();
            return;
        }

        Call<FoodQuery> call = RetrofitClient.getInstance ().getApi ().getFoodInfo (foodItem  );
        call.enqueue ( new Callback<FoodQuery> () {
            @Override
            public void onResponse(Call<FoodQuery> call, Response<FoodQuery> response) {
                if(!response.isSuccessful ()){
                    labelDisplay.setText ( response.code () +"Item Not Found" );
                    return;
                }
                FoodQuery foodQuery = response.body ();
                String name="";
                name+= "Food-Item-name : " + foodQuery.getName ()+"\n";
                name+= "Calories : " + foodQuery.getCalories ()+"\n";
                name+= "Fat : "+foodQuery.getFat ()+"\n";
                name+= "Protein : " +foodQuery.getProtein ()+"\n";
                name+="Carbohydrates : " + foodQuery.getCarbohydrate ()+"\n\n";

                    labelDisplay.append ( name );
            }

            @Override
            public void onFailure(Call<FoodQuery> call, Throwable t) {
labelDisplay.setText ( t.getMessage () );
            }
        } );



    }


    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.searchFoodBtn:
                requestFood ();
                break;
        }
    }
}
