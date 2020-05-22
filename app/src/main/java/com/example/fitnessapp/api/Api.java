package com.example.fitnessapp.api;

import com.example.fitnessapp.model_class.LoginResponse;
import com.example.fitnessapp.model_class.UserStatsResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
