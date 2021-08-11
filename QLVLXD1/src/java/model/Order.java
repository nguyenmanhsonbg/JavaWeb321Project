/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Order {
    private int id;
    private Customer customer;
    private String date;
    private String picture;
    private String note;
    private double value;
    private ArrayList<OrderDetail> listOrderDetail = new ArrayList<>();
    private String formatDate;
    private String formatValue;
    private int size;

    public int getSize() {
       return listOrderDetail.size();
    }

    public void setSize(int size) {
        this.size = size;
    }
   
    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
   
    public String getFormatValue(){
       

      DecimalFormat formatter = new DecimalFormat("###,###,###");
      return formatter.format(value);
    }
    
    public String getFormatDate() {
        Date d=Date.valueOf(date);
     SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        return (format1.format(d));
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
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

    public ArrayList<OrderDetail> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(ArrayList<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customer=" + customer + ", date=" + date + ", picture=" + picture + ", note=" + note + ", value=" + value + ", listOrderDetail=" + listOrderDetail + '}';
    }

    
 

    
}
