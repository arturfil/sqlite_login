package com.arturofilio.sqlite_login_app.models;

import java.io.Serializable;

public class Account implements Serializable {

    private int id;
    private String username;
    private String password;
    private String fullName;
    private double budget;

    public Account() {

    }

    public Account(int id, String username, String password, String fullName, double budget) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
