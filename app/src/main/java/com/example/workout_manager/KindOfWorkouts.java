package com.example.workout_manager;

public class KindOfWorkouts {
    // properties
    String title;
    String workout_details;

    // constructors
    public KindOfWorkouts(String title, String workout_details) {
        this.title = title;
        this.workout_details = workout_details;
    }

    public KindOfWorkouts() {}

    // getters & setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkout_details() {
        return workout_details;
    }

    public void setWorkout_details(String workout_details) {
        this.workout_details = workout_details;
    }
}
