/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;

/**
 *
 * @author raywa
 */
public class Business {

    public static Business business;
    private AccountDirectory ad;
    private BuyerDirectory bd;
    private SellerDirectory sd;

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order o) {
        orderList.add(o);

    }

    public void removeOrder(Order o) {
        orderList.remove(o);
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }
    private ArrayList<Order> orderList = new ArrayList();

    private Business() {
        ad = new AccountDirectory();
        bd = new BuyerDirectory();
        sd = new SellerDirectory();
    }

    public static Business getInstance() {
        if (business == null) {
            business = new Business();
        }
        return business;
    }

    public static Business getBusiness() {
        return business;
    }

    public static void setBusiness(Business business) {
        Business.business = business;
    }

    public AccountDirectory getAd() {
        return ad;
    }

    public void setAd(AccountDirectory ad) {
        this.ad = ad;
    }

    public BuyerDirectory getBd() {
        return bd;
    }

    public void setBd(BuyerDirectory bd) {
        this.bd = bd;
    }

    public SellerDirectory getSd() {
        return sd;
    }

    public void setSd(SellerDirectory sd) {
        this.sd = sd;
    }
}
