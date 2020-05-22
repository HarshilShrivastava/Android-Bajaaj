package com.example.fitnessapp.model_class;

public class Data {
    private String Gender;
    private int Weight;
    private int Height;
    private String Goals;
    private String Activity;
    private int Age;
    private int BMI;
    private int Condition;

    public Data(String gender, int weight, int height, String goals, String activity, int age, int BMI, int condition) {
        Gender = gender;
        Weight = weight;
        Height = height;
        Goals = goals;
        Activity = activity;
        Age = age;
        this.BMI = BMI;
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

    public int getBMI() {
        return BMI;
    }

    public int getCondition() {
        return Condition;
    }
}
