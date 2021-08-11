/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Group {
    int id;
    String name;
    ArrayList<Feature> listFeature = new ArrayList<>();

    public Group() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Feature> getListFeature() {
        return listFeature;
    }

    public void setListFeature(ArrayList<Feature> listFeature) {
        this.listFeature = listFeature;
    }
    
}
