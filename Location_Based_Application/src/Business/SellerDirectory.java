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
public class SellerDirectory {
    private ArrayList<Seller> sellerList;

    public SellerDirectory() {
        sellerList = new ArrayList<Seller>();
    }

    public ArrayList<Seller> getSellerList() {
        return sellerList;
    }

    public void setSellerList(ArrayList<Seller> sellerList) {
        this.sellerList = sellerList;
    }
    public Seller addSeller(){
        Seller s = new Seller();
        sellerList.add(s);
        return s;
    }
    
}
