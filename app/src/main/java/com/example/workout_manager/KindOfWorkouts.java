package com.example.workout_manager;

import android.telephony.SignalStrength;

public class KindOfWorkouts {
    // properties
    private String id;
    private String title;
    private String typeofWorkout;
    private String workout_details;
    private String sets;
//    String example;

    // constructors

    public KindOfWorkouts(String id, String title, String typeofWorkout, String workout_details, String sets) {
        this.id = id;
        this.title = title;
        this.typeofWorkout = typeofWorkout;
        this.workout_details = workout_details;
        this.sets = sets;
//        this.example = example;
    }

    public KindOfWorkouts() {}

    // getters & setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeofWorkout() {
        return typeofWorkout;
    }

    public void setTypeofWorkout(String typeofWorkout) {
        this.typeofWorkout = typeofWorkout;
    }

    public String getWorkout_details() {
        return workout_details;
    }

    public void setWorkout_details(String workout_details) {
        this.workout_details = workout_details;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

//    public String getExample() {
//        return example;
//    }
//
//    public void setExample(String example) {
//        this.example = example;
//    }
}
