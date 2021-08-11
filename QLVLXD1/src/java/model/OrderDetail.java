/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DecimalFormat;

/**
 *
 * @author Administrator
 */
public class OrderDetail {
   private int orderId;
   private Product product;
   private double unitPrice;
   private String formatUnitPrice;
   private double quantity;
   private String place;
   private double thanhtien;
   private String formatThanhTien;
   

    public OrderDetail() {
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getFormatThanhTien() {
        thanhtien = quantity * unitPrice;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
      return formatter.format(thanhtien);
    }

    public void setFormatThanhTien(String formatThanhTien) {
        this.formatThanhTien = formatThanhTien;
    }

    public String getFormatUnitPrice() {
         DecimalFormat formatter = new DecimalFormat("###,###,###");
      return formatter.format(unitPrice);
    }

    public void setFormatUnitPrice(String formatUnitPrice) {
        this.formatUnitPrice = formatUnitPrice;
    }

    public OrderDetail(int orderId, Product product, double unitPrice, double quantity, String place) {
        this.orderId = orderId;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.place = place;
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

   

   

   
}
