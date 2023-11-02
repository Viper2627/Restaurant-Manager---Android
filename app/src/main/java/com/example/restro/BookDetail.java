package com.example.restro;

public class BookDetail {
    String name;

    String phone,id,price;


    public BookDetail(){

        // Default constructor required
    }

    public BookDetail(String id,String name,String phone,String price){
        this.name=name;
this.id =id;
this.price=price;
this.phone=phone;
        // Default constructor required
    }

    public void setName(String name){this.name = name;}

    public void setPhone(String phone){this.phone = phone;}
    public void setid(String id){this.id = id;}

    public void setprice(String price){this.price = price;}

    public String getName(){ return name; }



    public String getPhone() {
        return phone;
    }
    public String getid(){ return id; }



    public String getprice() {
        return price;
    }
}



