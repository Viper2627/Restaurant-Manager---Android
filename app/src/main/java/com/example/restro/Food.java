package com.example.restro;

import java.util.ArrayList;

public class Food {





    String name;

    ArrayList<String> list;
    int price;


    public Food(){

        // Default constructor required
    }

    public Food(String name,ArrayList<String> list,int price){
        this.name=name;

        this.list=list;
        this.price=price;

        // Default constructor required
    }

    public void setName(String name){this.name = name;}


    public void setPrice(int price){this.price= price;}



    public void setList(ArrayList<String> list){this.list = list;}

    public String getName(){ return name; }

    public int getPrice(){ return price; }



    public ArrayList<String> getList(){
        return list;
    }
}
