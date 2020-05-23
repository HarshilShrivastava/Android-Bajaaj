package com.example.fitnessapp.model_class;

public class Food {
    private String name;
    private float Calories;
    private float Fat;
    private float Protein;
    private float Carbohydrate;
    private String Image;
    private float Cholestrol;

    public Food(String name, float calories, float fat, float protein, float carbohydrate, String image, float cholestrol) {
        this.name = name;
        Calories = calories;
        Fat = fat;
        Protein = protein;
        Carbohydrate = carbohydrate;
        Image = image;
        Cholestrol = cholestrol;
    }

    public String getName() {
        return name;
    }

    public float getCalories() {
        return Calories;
    }

    public float getFat() {
        return Fat;
    }

    public float getProtein() {
        return Protein;
    }

    public float getCarbohydrate() {
        return Carbohydrate;
    }

    public String getImage() {
        return Image;
    }

    public float getCholestrol() {
        return Cholestrol;
    }
}


