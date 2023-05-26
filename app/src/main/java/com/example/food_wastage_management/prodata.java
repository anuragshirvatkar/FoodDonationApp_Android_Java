package com.example.food_wastage_management;
public class prodata {
    private String photokey;
    private String dname;
    private String pname;
    private String pdes;
    private String pprice;
    private String pmail;
    private String imageurll;
    private  String key;
    public prodata(){

    }
    public String getPhotokey(){return photokey;}
    public String getDname() {
        return dname;
    }
    public String getPname() {
        return pname;
    }
    public String getPdes() {
        return pdes;
    }
    public String getPprice() {
        return pprice;
    }
    public String getPmail() {
        return pmail;
    }

    public String getImageurll() {
        return imageurll;
    }
public String getKey(){return key;}

    public prodata(String photokey,String dname,String pname, String pdes, String pprice, String pmail, String imageurll,String key){
        this.photokey = photokey;
        this.dname = dname;
        this.pname = pname;
        this.pdes = pdes;
        this.pprice = pprice;
        this.pmail = pmail;
        this.imageurll = imageurll;
        this.key = key;
    }
}
