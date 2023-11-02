package com.example.restro;



public class User{

    String name;

    String phone;


    public User(){

        // Default constructor required
    }

    public User(String name,String phone){
        this.name=name;

        this.phone=phone;
        // Default constructor required
    }

    public void setName(String name){this.name = name;}

    public void setPhone(String phone){this.phone = phone;}

    public String getName(){ return name; }



    public String getPhone() {
        return phone;
    }
}
