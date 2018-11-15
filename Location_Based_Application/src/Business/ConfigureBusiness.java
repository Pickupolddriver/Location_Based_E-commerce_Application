/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author raywa
 */
public class ConfigureBusiness {

    private Business business;

    public ConfigureBusiness(Business business) {
        this.business = business;
        configure();
    }

    public void configure() {
        loadBuyer();
        loadSeller();
    }

    public void loadBuyer() {
        final String fileName = "buyerCSV.csv";
        try (BufferedReader inline = new BufferedReader(new FileReader(fileName));) {
            String inputline = null;
            while ((inputline = inline.readLine()) != null) {
                String[] fields = inputline.split(",");
                String fname = fields[0];
                String lname = fields[1];
                String gender = fields[2];
                String dob = fields[3];
                String email = fields[4];
                String mailStreet1 = fields[5];
                String mailStreet2 = fields[6];
                String mailCity = fields[7];
                String mailState = fields[8];
                int mailZip = Integer.valueOf(fields[9]);
                long cardNumber = Long.valueOf(fields[10]);
                String expDate = fields[11];
                int secureCode = Integer.valueOf(fields[12]);
                String nameOnCard = fields[13];
                String billStreet1 = fields[14];
                String billStreet2 = fields[15];
                String billCity = fields[16];
                String billState = fields[17];
                int billZip = Integer.valueOf(fields[18]);
                String username = fields[19];
                String password = fields[20];

                Buyer b = business.getBd().addBuyer();

                b.setFirstName(fname);
                b.setLastName(lname);
                b.setGender(gender);
                b.setDOB(dob);
                b.setEmail(email);
                b.getMailingAddress().setStreetOne(mailStreet1);
                b.getMailingAddress().setStreetTwo(mailStreet2);
                b.getMailingAddress().setCity(mailCity);
                b.getMailingAddress().setState(mailState);
                b.getMailingAddress().setZip(mailZip);
                b.getBank().setCardNumber(cardNumber);
                b.getBank().setExpDate(expDate);
                b.getBank().setSecureCode(secureCode);
                b.getBank().setNameOnCard(nameOnCard);
                b.getBillingAddress().setStreetOne(billStreet1);
                b.getBillingAddress().setStreetTwo(billStreet2);
                b.getBillingAddress().setCity(billCity);
                b.getBillingAddress().setState(billState);
                b.getBillingAddress().setZip(billZip);

                Account a = business.getAd().addAccount();
                a.setUserName(username);
                a.setPassword(password);
                a.setRole(Account.BUYER);
                a.setPerson(b);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSeller() {
        final String fileName = "sellerCSV.csv";
        try (BufferedReader inline = new BufferedReader(new FileReader(fileName));) {
            String inputline = null;
            while ((inputline = inline.readLine()) != null) {
                String[] fields = inputline.split(",");
                String fname = fields[0];
                String lname = fields[1];
                String gender = fields[2];
                String dob = fields[3];
                String email = fields[4];
                int ssn = Integer.valueOf(fields[5]);
                long cardNumber = Long.valueOf(fields[6]);
                String expDate = fields[7];
                int secureCode = Integer.valueOf(fields[8]);
                String nameOnCard = fields[9];
                String billStreet1 = fields[10];
                String billStreet2 = fields[11];
                String billCity = fields[12];
                String billState = fields[13];
                int billZip = Integer.valueOf(fields[14]);
                String username = fields[15];
                String password = fields[16];

                Seller s = business.getSd().addSeller();

                s.setFirstName(fname);
                s.setLastName(lname);
                s.setGender(gender);
                s.setDOB(dob);
                s.setEmail(email);
                s.setSSN(ssn);
                s.getBank().setCardNumber(cardNumber);
                s.getBank().setExpDate(expDate);
                s.getBank().setSecureCode(secureCode);
                s.getBank().setNameOnCard(nameOnCard);
                s.getBillingAddress().setStreetOne(billStreet1);
                s.getBillingAddress().setStreetTwo(billStreet2);
                s.getBillingAddress().setCity(billCity);
                s.getBillingAddress().setState(billState);
                s.getBillingAddress().setZip(billZip);

                Account a = business.getAd().addAccount();
                a.setUserName(username);
                a.setPassword(password);
                a.setRole(Account.SELLER);
                a.setPerson(s);

                loadWine(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadWine(Seller s) {
        final String fileName = "wineCSV.csv";
        try (BufferedReader inline = new BufferedReader(new FileReader(fileName));) {
            String inputline = null;
            while ((inputline = inline.readLine()) != null) {
                String[] fields = inputline.split(",");
                String wineName = fields[0];
                double alchol = Double.valueOf(fields[1]);
                double price = Double.valueOf(fields[2]);
                String country = fields[3];
                String location = fields[4];
                String grape = fields[5];
                int inventory = Integer.valueOf(fields[6]);
                Wine w = s.addWine();
                w.setWineName(wineName);
                w.setAlcoholPercentage(alchol);
                w.setOriginCountry(country);
                w.setPrice(price);
                w.setLocation(location);
                w.setGrape(grape);
                w.setNumberofInventory(inventory);
                w.setSeller(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
