package com.example.fitnessapp.api;

import com.example.fitnessapp.model_class.DashboardResponse;
import com.example.fitnessapp.model_class.Food;
import com.example.fitnessapp.model_class.FoodQuery;
import com.example.fitnessapp.model_class.LoginResponse;
import com.example.fitnessapp.model_class.UserStatsResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("account/registration/")
    Call<ResponseBody> createUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("confirm_password") String confirm_password
    );

    @FormUrlEncoded
    @POST("/account/login/")
    Call<LoginResponse> userLogin(
            @Field("username") String username,
            @Field ( "password" ) String password
    );
    @FormUrlEncoded
    @POST("info/profile/")
    Call<UserStatsResponse> userInfo(
            @Field ( "Gender" ) String gender,
            @Field("Weight") int weight,
            @Field("Height") int height,
            @Field ( "Goals" ) String goals,
            @Field ( "Activity" ) String activity,
            @Field ( "Age" ) int age

    );

    @GET("info/profile/")
    Call<DashboardResponse> getInfo();

    @GET("food/get-recoomended-food/")
    Call<List<Food>> getFood();

    @GET("food/list-of-food/")
    Call<FoodQuery>getFoodInfo(@Query ( "search" ) String foodItemNAme);


}
