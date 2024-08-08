package com.niantic.models;

import java.util.ArrayList;

public class User
{
    private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    private ArrayList<Transaction> transactions;

    public User() {}

    public User(int userId, String userName, String firstName, String lastName, String phone, String email)
    {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return String.format("%-5d %-15s %-15s", userId, firstName, lastName);
    }
}
