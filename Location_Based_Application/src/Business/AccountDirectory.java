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
public class AccountDirectory {
    private ArrayList<Account> accountList;

    public AccountDirectory() {
        accountList = new ArrayList<Account>();
    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }
    public Account addAccount(){
        Account a = new Account();
        accountList.add(a);
        return a;
    }
    public void removeAccount(Account a){
        accountList.remove(a);
    }
}
