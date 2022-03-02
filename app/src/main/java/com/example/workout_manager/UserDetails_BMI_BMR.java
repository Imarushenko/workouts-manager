package com.example.workout_manager;

public class UserDetails_BMI_BMR {
    // properties
    private String weight;
    private String height;
    private String age;

    // constructors
    public UserDetails_BMI_BMR(String weight, String height, String age) {
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public UserDetails_BMI_BMR() {
    }

    // getters & setters
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
