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
public class Import {
   private int id;
   private Supplier supplier;
   private String date;
   private String picture;
   private String note;
   private double value;
    private ArrayList<ImportDetail> listImportDetail = new ArrayList<>();

    public ArrayList<ImportDetail> getListImportDetail() {
        return listImportDetail;
    }

    public void setListImportDetail(ArrayList<ImportDetail> listImportDetail) {
        this.listImportDetail = listImportDetail;
    }
   

    public Import() {
    }

    public Import(int id, Supplier supplier, String date, String picture, String note, double value) {
        this.id = id;
        this.supplier = supplier;
        this.date = date;
        this.picture = picture;
        this.note = note;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    
}
