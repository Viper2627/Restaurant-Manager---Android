package com.example.restro;

import java.util.ArrayList;

public class Stat {

    String id;


    String status;


    public Stat(){

        // Default constructor required
    }

    public Stat(String id,String status){
        this.id=id;

        this.status=status;


        // Default constructor required
    }

    public void setstatus(String status){this.status = status;}


    public void setid(String id){this.id= id;}





    public String getstatus(){ return status; }

    public String getid(){ return id; }




}

