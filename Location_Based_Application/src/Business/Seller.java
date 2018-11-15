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
public class Seller extends Person {

    private int SSN;
    private ArrayList<Wine> wineList;
    private ArrayList<Order> transactionList;

    public Seller() {
        wineList = new ArrayList<Wine>();
        transactionList = new ArrayList<Order>();
    }

    public Order addTransaction() {
        Order o = new Order();
        transactionList.add(o);
        return o;
    }

    @Override
    public String toString() {
        return getFirstName()+getLastName();
    }
   
    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public ArrayList<Wine> getWineList() {
        return wineList;
    }

    public void setWineList(ArrayList<Wine> wineList) {
        this.wineList = wineList;
    }

    public Wine addWine() {
        Wine w = new Wine();
        wineList.add(w);
        return w;
    }

    public void removeWine(Wine w) {
        wineList.remove(w);
    }

    public ArrayList<Order> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(ArrayList<Order> transactionList) {
        this.transactionList = transactionList;
    }

}
