package com.example.madhurgoyal.lamavendors;

public class Vendors {
    String name;
    String loc;
    public Vendors(){

    }
    public Vendors(String name, String loc){
        this.name = name;
        this.loc = loc;
    }

    public String getName(){
        return name;
    }

    public String getLoc(){
        return loc;
    }
}
