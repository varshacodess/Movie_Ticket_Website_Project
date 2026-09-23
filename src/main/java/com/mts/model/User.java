package com.mts.model;

public class User {

    //User Table fields
    private int userId; //primary key
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;

    //no-arg constructor - used to set the values using setters
    public User(){

    }

    //parameterized constructor
    public User(int userId, String name, String email, String phone, String password, String role){

        //instance variable = constructor parameter

        //takes the 'userId' parameter and stores it in the object's 'userId' field (same for all fields)
        this.userId=userId;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.password=password;
        this.role=role;
    }

    //getters methods - used to access user data
    public int getUserId(){
        return userId;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }

    //setters method - used to modify user data
    public void setUserId(int userId){
        this.userId=userId;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setRole(String role){
        this.role=role;
    }



}
