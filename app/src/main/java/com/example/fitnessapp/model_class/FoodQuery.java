package com.example.fitnessapp.model_class;

public class FoodQuery {
    private String name;
    private  String Fat;
    private Float Calories;
    private String Protein;
    private String Carbohydrate;

    public FoodQuery(String name, String fat, Float calories, String protein, String carbohydrate) {
        this.name = name;
        Fat = fat;
        Calories = calories;
        Protein = protein;
        Carbohydrate = carbohydrate;
    }

    public String getName() {
        return name;
    }

    public String getFat() {
        return Fat;
    }

    public Float getCalories() {
        return Calories;
    }

    public String getProtein() {
        return Protein;
    }

    public String getCarbohydrate() {
        return Carbohydrate;
    }
}

