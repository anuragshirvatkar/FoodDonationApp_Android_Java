package com.example.food_wastage_management;

public class users {
    public String sname;
    public String sphone;
    public String shouse;
    public String sstreet;
    public String scity;
    public String sstate;
    public String slink;
    public users(){

    }

    public String getSname() {
        return sname;
    }
    public String getSphone() {
        return sphone;
    }
    public String getShouse(){return shouse;}
    public String getSstreet() {
        return sstreet;
    }
    public String getScity(){return scity;}
    public String getSstate() {
        return sstate;
    }
    public String getSlink(){return slink;}

    public users(String sname,  String sphone,String shouse ,String sstreet,String scity,String sstate,String slink) {
        this.sname = sname;
        this.sphone = sphone;
        this.shouse = shouse;
        this.sstreet = sstreet;
        this.scity = scity;
        this.sstate = sstate;
        this.slink = slink;
    }
}
