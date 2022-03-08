package com.example.workout_manager;

// BMR class
public class UserDetails_BMR {
    // properties
    private String weight;
    private String height;
    private String age;
    private String BMR_result;

    // constructors
    public UserDetails_BMR(String weight, String height, String age, String BMR_result) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.BMR_result = BMR_result;
    }

    public UserDetails_BMR() {
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

    public String getBMR_result() {
        return BMR_result;
    }

    public void setBMR_result(String BMR_result) {
        this.BMR_result = BMR_result;
    }
}
