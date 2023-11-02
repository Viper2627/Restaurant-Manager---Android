package com.example.restro;

public class Emp {


    String name,email,password,post;


    public Emp(){

        // Default constructor required
    }
    public Emp(String name,String email,String password,String post){
        this.name = name;
        this.email = email;
        this.password = password;
        this.post= post;

        // Default constructor required
    }

    public void setname(String name){this.name = name;}
    public void setemail(String email){this.email = email;}
    public void setpassword(String password){this.password = password;}
    public void setpost(String post){this.post = post;}


    public String getname(){ return name; }

    public String getemail() {
        return email;
    }

    public String getpassword() {
        return password;}
    public String getpost() {
        return post;
    }


}