package com.example.surinklietuva.DataStructures;

import java.util.List;

public class Magnet {

    String area;
    String name;
    List<String> listOfShops;


    public Magnet(String area, String name, List<String> listOfShops) {
        this.area = area;
        this.name = name;
        this.listOfShops = listOfShops;
    }


    public String getArea() {
        return area;
    }


    public String getName() {
        return name;
    }


    public List<String> getListOfShops() {
        return listOfShops;
    }

}
