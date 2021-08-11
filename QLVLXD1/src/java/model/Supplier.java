/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dal.ImportDAO;
import dal.OrderDAO;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Supplier {

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
   
    ImportDAO iD = new ImportDAO();
    private ArrayList<Import> listImport = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(int id, String companyName, String contactName, String address, String phone, double unpaid, double paid) {
         this.id = id;
        this.companyName = companyName;
        this.contactName = contactName;
        this.address = address;
        this.phone = phone;
        //this.paid = iD.totalPaidByCustomerId(id);
      
        this.unpaid = iD.totalImportValue(id);
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

    public double getNoConLai() {
        return noConLai;
    }

    public void setNoConLai(double noConLai) {
        this.noConLai = noConLai;
    }

    public ArrayList<Import> getListImport() {
        return listImport;
    }

    public void setListImport(ArrayList<Import> listImport) {
        this.listImport = listImport;
    }





}
