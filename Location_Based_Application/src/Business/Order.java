/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.Date;

/**
 *
 * @author raywa
 */
public class Order {
    private String orderNum;
    private Seller seller;
    private Buyer buyer;
    private String orderDate;
    private Wine wine;
    private int qty;
    private double total;
    // several values for orderStatus: order Placed, shipped, delievered, return started, refunded
    private String orderStatus;

    public String getNewBuyer(){
 return (buyer.getFirstName() + " " + buyer.getLastName());
    }
    
    public Order() {
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSellerNameName(Seller seller) {
        this.seller = seller;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWineName(Wine wine) {
        this.wine = wine;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return String.valueOf(orderNum);
    }
    
}
