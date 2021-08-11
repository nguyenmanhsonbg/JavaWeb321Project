/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public class Paid {

    private Date date;
    private double money;
    private String formatDate;

    public Paid() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFormatDate() {
       SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        return (format1.format(date));
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
