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
public class Buyer extends Person {

    private ArrayList<Wine> cart;
    private ArrayList<Wine> wishList;
    private ArrayList<Order> orderList;

    public Buyer() {
        cart = new ArrayList<Wine>();
        wishList = new ArrayList<Wine>();
        orderList = new ArrayList<Order>();
    }

    public Order addOrder() {
        Order o = new Order();
        orderList.add(o);
        return o;
    }

    public Wine addCart() {
        Wine w = new Wine();
        cart.add(w);
        return w;
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
    public String toSring() {
        return (this.getFirstName() + " " + this.getLastName());
    }

    public void removeCart(Wine w) {
        cart.remove(w);
    }

    public ArrayList<Wine> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Wine> cart) {
        this.cart = cart;
    }

    public ArrayList<Wine> getWishList() {
        return wishList;
    }

    public void setWishList(ArrayList<Wine> wishList) {
        this.wishList = wishList;
    }

    public Wine addWishList(Wine w) {
        wishList.add(w);
        return w;
    }

    public void removeWishlist(Wine w) {
        wishList.remove(w);
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

}
