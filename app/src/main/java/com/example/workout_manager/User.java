package com.example.workout_manager;

// user class
public class User {
    // properties
    String full_name;
    String id;
    String phone_number;
    String email;

    // constructors
    public User(String full_name, String id, String phone_number, String email) {
        this.full_name = full_name;
        this.id = id;
        this.phone_number = phone_number;
        this.email = email;
    }

    public User() {}

    // getters
    public String getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
