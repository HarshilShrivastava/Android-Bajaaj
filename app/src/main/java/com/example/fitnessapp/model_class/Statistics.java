package com.example.fitnessapp.model_class;

public class Statistics {
    private String Gender;
    private int Weight;
    private int Height;
    private String Goals;
    private String Activity;
    private int Age;
    private String Username;
    private double BMI;
    private int DailyCalories;
    private double BMR;
    private  String Condition;

    public Statistics(String gender, int weight, int height, String goals, String activity, int age, String username, double BMI, int dailyCalories, double BMR, String condition) {
        Gender = gender;
        Weight = weight;
        Height = height;
        Goals = goals;
        Activity = activity;
        Age = age;
        Username = username;
        this.BMI = BMI;
        DailyCalories = dailyCalories;
        this.BMR = BMR;
        Condition = condition;
    }

    public String getGender() {
        return Gender;
    }

    public int getWeight() {
        return Weight;
    }

    public int getHeight() {
        return Height;
    }

    public String getGoals() {
        return Goals;
    }

    public String getActivity() {
        return Activity;
    }

    public int getAge() {
        return Age;
    }

    public String getUsername() {
        return Username;
    }

    public double getBMI() {
        return BMI;
    }

    public int getDailyCalories() {
        return DailyCalories;
    }

    public double getBMR() {
        return BMR;
    }

    public String getCondition() {
        return Condition;
    }
}
