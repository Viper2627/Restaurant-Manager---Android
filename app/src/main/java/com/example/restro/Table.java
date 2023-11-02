package com.example.restro;

public class Table {
    int id,price;
    String date,time,status;


    public Table(){

        // Default constructor required
    }
    public Table(int id,String time,String date,int price,String status){
        this.id=id;
        this.time=time;
        this.date=date;
        this.price=price;
        this.status= status;
        // Default constructor required
    }

    public void setid(int id){this.id = id;}
    public void settime(String time){this.time = time;}
    public void setdate(String date){this.date = date;}
    public void setprice(int price){this.price = price;}
    public void setStatus(String status){this.status = status;}

    public int getid(){ return id; }

    public String gettime() {
        return time;
    }

    public String getdate() {
        return date;}
        public String getstatus() {
            return status;
        }

        public int getprice() {
            return price;
        }
    }