/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dal.OrderDAO;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Customer {

    private int id;
    private String companyName;
    private String contactName;
    private String address;
    private String phone;
    private double unpaid;
    private String formatUnpaid;
    private double paid;
    private String formatPaid;
    private double noConLai;
    private String formatNoConLai;
    private OrderDAO oD = new OrderDAO();

    private ArrayList<Order> listOrder = new ArrayList<>();

    public Customer() {
    }

    public Customer(int id, String companyName, String contactName, String address, String phone, double unpaid, double paid) {
        this.id = id;
        this.companyName = companyName;
        this.contactName = contactName;
        this.address = address;
        this.phone = phone;
        this.paid = oD.totalPaidByCustomerId(id);
      
        this.unpaid = oD.totalOrderValue(id);

    }

    public String getFormatUnpaid() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(unpaid);

    }

    public void setFormatUnpaid(String formatUnpaid) {
        this.formatUnpaid = formatUnpaid;
    }

    public String getFormatPaid() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(paid);
    }

    public void setFormatPaid(String formatPaid) {
        this.formatPaid = formatPaid;
    }

    public String getFormatNoConLai() {
        noConLai = unpaid - paid;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(noConLai);
    }

    public void setFormatNoConLai(String formatNoConLai) {
        this.formatNoConLai = formatNoConLai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(double unpaid) {
        this.unpaid = unpaid;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public ArrayList<Order> getListOrder() {
        return listOrder;
    }

    public void setListOrder(ArrayList<Order> listOrder) {
        this.listOrder = listOrder;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", companyName=" + companyName + ", contactName=" + contactName + ", address=" + address + ", phone=" + phone + ", unpaid=" + unpaid + ", paid=" + paid + ", listOrder=" + listOrder + '}';
    }

}
