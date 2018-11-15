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
public class BuyerDirectory {
    private ArrayList<Buyer> buyerList;

    public BuyerDirectory() {
        buyerList = new ArrayList<Buyer>();
    }
    public Buyer addBuyer(){
        Buyer b = new Buyer();
        buyerList.add(b);
        return b;
    }

    public ArrayList<Buyer> getBuyerList() {
        return buyerList;
    }

    public void setBuyerList(ArrayList<Buyer> buyerList) {
        this.buyerList = buyerList;
    }
    
    
}
