/*
Author: Ruifeng Wang
Date: 02/24/2018
This is the screen a user will see after a successful login, the user will be able to search products based on different filters, view wine detail
add to cart, 1-click checkout, and log out. Detailed description of each panel is below.

BrowseProductPanel:
NNOTE::: Upon opening the panel, the product table will show all the products 
****whose variable Loaction is same as the buyer's variable mailling address state.******
With that be saying, the table will first show all the wines without using any filters, by searching for specific wine names, 
seller name, or prime only wines,
the table will be able to refresh its content and show the search result.

MyOrderPanel:
Upon starting, the order table will be populated with all the orders placed by this buyer, it has two buttons: view wine detail and return wine.
See button event hadnler for detailed description

MyCartPanel:
Upon starting, the cart table and wish list table should be populated, using the buyer's cartList and wishList.
Button in this panel are: view wine detail, check out, remove from my cart, and save for later. Detailed button event handler will be explained in detail. 
PLEASE NOTE: the subtotal text field should be filled with the subtotal of all rows in the cart table
PLEASE NOTE: The check out logic is, by default, we are checking out ALL wines in the cart list, if the user wants to check out one specific wine, he/she has to
             put the other wines in the wish list by clicking save for later.
ALSO NOTE: the cart table has columns that are computed by using the user entered quantity and the price of the selected wine. 

myAccountPanel:
To update and save baisc personal information

myPayementPanel:
To update and save payment information 

 */
package Interface.BuyerMgt;

import Business.*;
import Interface.SellerMgt.viewWineDetailPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raywa
 */
public class buyerStartPanel extends javax.swing.JPanel {

    /**
     * Creates new form userStartPanel
     */
    private JPanel processPanel;
    private Buyer buyer;
    private Business business;

    public buyerStartPanel(JPanel processPanel, Buyer buyer, Business business) {
        initComponents();
        this.processPanel = processPanel;
        this.buyer = buyer;
        this.business = business;
        this.showMailInfo();
        this.showCardInfo();
        //At first we need to populate the table in all seller and all product;
        this.refreshTable();
        this.refreshOrder();
        refreshCarts();
    }

    public void refreshCarts() {
        int rowCount = tblCarts.getRowCount();
        DefaultTableModel model = (DefaultTableModel) tblCarts.getModel();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        //System.out.print("OK");
        for (Wine ws : buyer.getCart()) {
            Object row[] = new Object[8];
            row[0] = ws;
            row[1] = ws.getSeller();
            row[2] = ws.getNumberofInventory();
            row[3] = ws.getPrice() * ws.getNumberofInventory();

            model.addRow(row);

        }
    }

    public void refreshOrder() {
        int rowCount = tblMyOrders.getRowCount();
        DefaultTableModel model = (DefaultTableModel) tblMyOrders.getModel();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        //System.out.print("OK");
        for (Order ovo : buyer.getOrderList()) {

            Object row[] = new Object[8];
            row[0] = ovo;
            row[1] = ovo.getOrderDate().toString();
            row[2] = ovo.getWine();
            row[3] = ovo.getSeller().getFirstName() + " " + ovo.getSeller().getLastName();
            row[4] = ovo.getQty();
            row[5] = ovo.getTotal();
            row[6] = ovo.getOrderStatus();

            model.addRow(row);

        }
    }

    public void refreshTable() {
        //载入busienss里面的商家以及货物
        int rowCount = tblBrowseProduct.getRowCount();
        DefaultTableModel model = (DefaultTableModel) tblBrowseProduct.getModel();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        //System.out.print("OK");
        for (Seller sl : business.getSd().getSellerList()) {
            //if (sl.getMailingAddress().getState().equals(buyer.getMailingAddress().getState())) {
            //if (sl.getWineList().size() != 0) {
            for (Wine w1 : sl.getWineList()) {
                //System.out.println(w1.getWineName());
                if (w1.getLocation().equals(buyer.getMailingAddress().getState())) {
                    Object row[] = new Object[8];
                    row[0] = w1;
                    //row[1] = w1.getSeller().getFirstName() + " " + w1.getSeller().getLastName();
                    row[1] = sl.getFirstName() + " " + sl.getLastName();
                    row[2] = w1.getPrice();
                    row[3] = w1.getAlcoholPercentage();
                    row[4] = w1.getOriginCountry();
                    row[5] = w1.getNumberofInventory();
                    row[6] = w1.getGrape();
                    row[7] = w1.getLocation();
                    //row[2]=s.getNumplane();
                    model.addRow(row);
                }
            }
            // }
            //}

        }

    }

    public void showMailInfo() {
        txtFistName.setText(buyer.getFirstName());
        txtLastName.setText(buyer.getLastName());
        txtGender.setText(buyer.getGender());
        txtDOB.setText(buyer.getDOB());
        txtEmail.setText(buyer.getEmail());
        for (Account ac1 : business.getAd().getAccountList()) {
            if (ac1.getPerson().equals(buyer)) {
                txtUserName.setText(ac1.getUserName());
                txtPassword.setText(ac1.getPassword());
            }
        }
        txtStreetOne.setText(buyer.getMailingAddress().getStreetOne());
        txtStreetTwo.setText(buyer.getMailingAddress().getStreetTwo());
        txtMailCity.setText(buyer.getMailingAddress().getCity());
        txtMailState.setText(buyer.getMailingAddress().getState());
        txtMailZip.setText(String.valueOf(buyer.getMailingAddress().getZip()));
    }

    public void showCardInfo() {
        txtCardNo.setText(String.valueOf(buyer.getBank().getCardNumber()));
        txtExpDate.setText(buyer.getBank().getExpDate());
        txtSecureCode.setText(String.valueOf(buyer.getBank().getSecureCode()));
        txtName.setText(buyer.getBank().getNameOnCard());
        txtStreet1.setText(buyer.getBillingAddress().getStreetOne());
        txtStreet2.setText(buyer.getBillingAddress().getStreetTwo());
        txtCity.setText(buyer.getBillingAddress().getCity());
        txtZip.setText(String.valueOf(buyer.getBillingAddress().getZip()));
        txtState.setText(buyer.getBillingAddress().getState());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userTabPane = new javax.swing.JTabbedPane();
        browseProductPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKeyword = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSeller = new javax.swing.JTextField();
        chkPrime = new javax.swing.JCheckBox();
        btnDetail = new javax.swing.JButton();
        btnAddToCart = new javax.swing.JButton();
        btnCheckout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBrowseProduct = new javax.swing.JTable();
        btnLogout = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        quantitySpinner = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        myCartPanel = new javax.swing.JPanel();
        btnDetail1 = new javax.swing.JButton();
        btnCheckOut = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnLater = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblWishList = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCarts = new javax.swing.JTable();
        btnRemoveWishList = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        btnMoveToCart = new javax.swing.JButton();
        myAccountPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtFistName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDOB = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtMailZip = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtMailState = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMailCity = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtStreetTwo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtStreetOne = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnSaveBasic = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        myPaymentPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtCardNo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtExpDate = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtSecureCode = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtZip = new javax.swing.JTextField();
        txtState = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        txtStreet2 = new javax.swing.JTextField();
        txtStreet1 = new javax.swing.JTextField();
        btnSaveBank = new javax.swing.JButton();
        btnUpdateBank = new javax.swing.JButton();
        myOrderPanel = new javax.swing.JPanel();
        btnWineDetail = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMyOrders = new javax.swing.JTable();

        userTabPane.setForeground(new java.awt.Color(255, 102, 102));

        browseProductPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Search Product:");

        txtKeyword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeywordActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("Search Seller: ");

        txtSeller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSellerActionPerformed(evt);
            }
        });

        chkPrime.setBackground(new java.awt.Color(255, 255, 255));
        chkPrime.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        chkPrime.setForeground(new java.awt.Color(255, 102, 102));
        chkPrime.setText("Show only Prime products");

        btnDetail.setBackground(new java.awt.Color(255, 255, 255));
        btnDetail.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnDetail.setForeground(new java.awt.Color(255, 102, 102));
        btnDetail.setText("View Product Detail");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        btnAddToCart.setBackground(new java.awt.Color(255, 255, 255));
        btnAddToCart.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnAddToCart.setForeground(new java.awt.Color(255, 102, 102));
        btnAddToCart.setText("Add to Cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        btnCheckout.setBackground(new java.awt.Color(255, 255, 255));
        btnCheckout.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnCheckout.setForeground(new java.awt.Color(255, 102, 102));
        btnCheckout.setText("1-Click Check out");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setText("Quantity: ");

        tblBrowseProduct.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        tblBrowseProduct.setForeground(new java.awt.Color(255, 102, 102));
        tblBrowseProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Wine Name", "Seller Name", "Price", "Alcohol%", "Origin Country", "Number of Wine", "Grape Type", "Locatoin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblBrowseProduct);

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 102, 102));
        btnLogout.setText("Log out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 102, 102));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        quantitySpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jButton1.setText("显示所有供应商的名字");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout browseProductPanelLayout = new javax.swing.GroupLayout(browseProductPanel);
        browseProductPanel.setLayout(browseProductPanelLayout);
        browseProductPanelLayout.setHorizontalGroup(
            browseProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(browseProductPanelLayout.createSequentialGroup()
                .addGroup(browseProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(browseProductPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(browseProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(browseProductPanelLayout.createSequentialGroup()
                                .addGroup(browseProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(browseProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtKeyword, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(txtSeller))
                                .addGap(102, 102, 102)
                                .addComponent(btnSearch))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkPrime)
                            .addGroup(browseProductPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnDetail)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddToCart)
                                .addGap(18, 18, 18)
                                .addComponent(btnCheckout))))
                    .addGroup(browseProductPanelLayout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout)))
                .addContainerGap(186, Short.MAX_VALUE))
        );
        browseProductPanelLayout.setVerticalGroup(
            browseProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(browseProductPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(browseProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(browseProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSeller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPrime)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(browseProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnDetail)
                    .addComponent(btnAddToCart)
                    .addComponent(btnCheckout)
                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(browseProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(jButton1))
                .addContainerGap(253, Short.MAX_VALUE))
        );

        userTabPane.addTab("Browse Products", browseProductPanel);

        myCartPanel.setBackground(new java.awt.Color(255, 255, 255));

        btnDetail1.setBackground(new java.awt.Color(255, 255, 255));
        btnDetail1.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnDetail1.setForeground(new java.awt.Color(255, 102, 102));
        btnDetail1.setText("View Product Detail");
        btnDetail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetail1ActionPerformed(evt);
            }
        });

        btnCheckOut.setBackground(new java.awt.Color(255, 255, 255));
        btnCheckOut.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnCheckOut.setForeground(new java.awt.Color(255, 102, 102));
        btnCheckOut.setText("Check out");
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(255, 255, 255));
        btnRemove.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(255, 102, 102));
        btnRemove.setText("Remove from my cart");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnLater.setBackground(new java.awt.Color(255, 255, 255));
        btnLater.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnLater.setForeground(new java.awt.Color(255, 102, 102));
        btnLater.setText("Save for later");
        btnLater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaterActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 102));
        jLabel4.setText("Order Sub Total:");

        tblWishList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Wine Name", "Seller", "Quantity", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblWishList);

        tblCarts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Wine Name", "Seller", "Quantity", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblCarts);

        btnRemoveWishList.setBackground(new java.awt.Color(255, 255, 255));
        btnRemoveWishList.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnRemoveWishList.setForeground(new java.awt.Color(255, 102, 102));
        btnRemoveWishList.setText("Remove from my wish list");
        btnRemoveWishList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveWishListActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 102, 102));
        jLabel28.setText("Wish List:");

        btnMoveToCart.setBackground(new java.awt.Color(255, 255, 255));
        btnMoveToCart.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnMoveToCart.setForeground(new java.awt.Color(255, 102, 102));
        btnMoveToCart.setText("Move Back to Cart");
        btnMoveToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveToCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout myCartPanelLayout = new javax.swing.GroupLayout(myCartPanel);
        myCartPanel.setLayout(myCartPanelLayout);
        myCartPanelLayout.setHorizontalGroup(
            myCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myCartPanelLayout.createSequentialGroup()
                .addGroup(myCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(myCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(myCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(myCartPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel28))
                            .addGroup(myCartPanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(myCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(myCartPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(myCartPanelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(btnDetail1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCheckOut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRemove)
                                .addGap(18, 18, 18)
                                .addComponent(btnLater)))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(myCartPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnMoveToCart)
                        .addGap(36, 36, 36)
                        .addComponent(btnRemoveWishList)))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        myCartPanelLayout.setVerticalGroup(
            myCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myCartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(myCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(myCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetail1)
                    .addComponent(btnCheckOut)
                    .addComponent(btnRemove)
                    .addComponent(btnLater))
                .addGap(51, 51, 51)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(myCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveWishList)
                    .addComponent(btnMoveToCart))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        userTabPane.addTab("My Cart", myCartPanel);

        myAccountPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 102));
        jLabel5.setText("First Name:");

        txtFistName.setEnabled(false);
        txtFistName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFistNameActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 102, 102));
        jLabel6.setText("Last Name:");

        txtLastName.setEnabled(false);
        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 102, 102));
        jLabel7.setText("Gender:");

        txtGender.setEnabled(false);
        txtGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGenderActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 102));
        jLabel8.setText("Date of Birth:");

        txtDOB.setEnabled(false);
        txtDOB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDOBActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 102, 102));
        jLabel9.setText("Email Address:");

        txtEmail.setEnabled(false);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 102, 102));
        jLabel16.setText("User Name: ");

        txtUserName.setEnabled(false);

        jLabel24.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 102, 102));
        jLabel24.setText("Password:");

        txtPassword.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 102, 102));
        jLabel12.setText("Zip Code:");

        txtMailZip.setEnabled(false);
        txtMailZip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailZipActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 102, 102));
        jLabel11.setText("State:");

        txtMailState.setEnabled(false);
        txtMailState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailStateActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 102, 102));
        jLabel10.setText("City:");

        txtMailCity.setEnabled(false);
        txtMailCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailCityActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 102, 102));
        jLabel13.setText("Street Address #2:");

        txtStreetTwo.setEnabled(false);
        txtStreetTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStreetTwoActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 102, 102));
        jLabel14.setText("Street Address #1:");

        txtStreetOne.setEnabled(false);
        txtStreetOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStreetOneActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 102, 102));
        jLabel15.setText("Maill Address:");

        btnSaveBasic.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnSaveBasic.setForeground(new java.awt.Color(255, 102, 102));
        btnSaveBasic.setText("Save");
        btnSaveBasic.setEnabled(false);
        btnSaveBasic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBasicActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 102, 102));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout myAccountPanelLayout = new javax.swing.GroupLayout(myAccountPanel);
        myAccountPanel.setLayout(myAccountPanelLayout);
        myAccountPanelLayout.setHorizontalGroup(
            myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myAccountPanelLayout.createSequentialGroup()
                .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(myAccountPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(myAccountPanelLayout.createSequentialGroup()
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                                .addComponent(jLabel14))
                            .addGroup(myAccountPanelLayout.createSequentialGroup()
                                .addComponent(txtFistName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15))
                            .addGroup(myAccountPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel13))
                            .addGroup(myAccountPanelLayout.createSequentialGroup()
                                .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10))
                            .addGroup(myAccountPanelLayout.createSequentialGroup()
                                .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myAccountPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel9)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(txtUserName)
                            .addComponent(txtPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStreetOne, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStreetTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMailCity, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMailState, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMailZip, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 165, 165))
            .addGroup(myAccountPanelLayout.createSequentialGroup()
                .addGap(348, 348, 348)
                .addComponent(btnUpdate)
                .addGap(29, 29, 29)
                .addComponent(btnSaveBasic)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        myAccountPanelLayout.setVerticalGroup(
            myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myAccountPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(myAccountPanelLayout.createSequentialGroup()
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtFistName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(myAccountPanelLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtStreetOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtStreetTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtMailCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtMailState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMailZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                .addGroup(myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnSaveBasic))
                .addGap(51, 51, 51))
        );

        userTabPane.addTab("Manage My Account", myAccountPanel);

        myPaymentPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 102, 102));
        jLabel17.setText("Card Number:");

        txtCardNo.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 102, 102));
        jLabel18.setText("Exp Date:");

        txtExpDate.setEnabled(false);
        txtExpDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExpDateActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 102, 102));
        jLabel19.setText("Secure Code:");

        txtSecureCode.setEnabled(false);
        txtSecureCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSecureCodeActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 102, 102));
        jLabel20.setText("Name On Card:");

        txtName.setEnabled(false);
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 102, 102));
        jLabel21.setText("Billing Address:");

        jLabel22.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 102, 102));
        jLabel22.setText("Street Address #1:");

        jLabel23.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 102, 102));
        jLabel23.setText("Street Address #2:");

        jLabel25.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 102, 102));
        jLabel25.setText("City:");

        jLabel26.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 102, 102));
        jLabel26.setText("State:");

        jLabel27.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 102, 102));
        jLabel27.setText("Zip Code:");

        txtZip.setEnabled(false);
        txtZip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtZipActionPerformed(evt);
            }
        });

        txtState.setEnabled(false);
        txtState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStateActionPerformed(evt);
            }
        });

        txtCity.setEnabled(false);
        txtCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCityActionPerformed(evt);
            }
        });

        txtStreet2.setEnabled(false);

        txtStreet1.setEnabled(false);
        txtStreet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStreet1ActionPerformed(evt);
            }
        });

        btnSaveBank.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnSaveBank.setForeground(new java.awt.Color(255, 102, 102));
        btnSaveBank.setText("Save");
        btnSaveBank.setEnabled(false);
        btnSaveBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBankActionPerformed(evt);
            }
        });

        btnUpdateBank.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnUpdateBank.setForeground(new java.awt.Color(255, 102, 102));
        btnUpdateBank.setText("Update");
        btnUpdateBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateBankActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout myPaymentPanelLayout = new javax.swing.GroupLayout(myPaymentPanel);
        myPaymentPanel.setLayout(myPaymentPanelLayout);
        myPaymentPanelLayout.setHorizontalGroup(
            myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPaymentPanelLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myPaymentPanelLayout.createSequentialGroup()
                        .addComponent(txtCardNo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21))
                    .addGroup(myPaymentPanelLayout.createSequentialGroup()
                        .addComponent(txtExpDate, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151)
                        .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel27)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myPaymentPanelLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSecureCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStreet1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStreet2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZip, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(183, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myPaymentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateBank)
                .addGap(36, 36, 36)
                .addComponent(btnSaveBank)
                .addGap(388, 388, 388))
        );
        myPaymentPanelLayout.setVerticalGroup(
            myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myPaymentPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel21)
                    .addComponent(txtCardNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(myPaymentPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtExpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtSecureCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(myPaymentPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtStreet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtStreet2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addGroup(myPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateBank)
                    .addComponent(btnSaveBank))
                .addGap(68, 68, 68))
        );

        userTabPane.addTab("Manage my payment", myPaymentPanel);

        myOrderPanel.setBackground(new java.awt.Color(255, 255, 255));

        btnWineDetail.setBackground(new java.awt.Color(255, 255, 255));
        btnWineDetail.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnWineDetail.setForeground(new java.awt.Color(255, 102, 102));
        btnWineDetail.setText("Wiew Wine Detail");
        btnWineDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWineDetailActionPerformed(evt);
            }
        });

        btnReturn.setBackground(new java.awt.Color(255, 255, 255));
        btnReturn.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(255, 102, 102));
        btnReturn.setText("Return this Product");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        tblMyOrders.setFont(new java.awt.Font("Viner Hand ITC", 0, 14)); // NOI18N
        tblMyOrders.setForeground(new java.awt.Color(255, 102, 102));
        tblMyOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order Number", "Order Date", "Wine Name", "Seller Name", "Quantity", "Subtotal", "Order Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblMyOrders);

        javax.swing.GroupLayout myOrderPanelLayout = new javax.swing.GroupLayout(myOrderPanel);
        myOrderPanel.setLayout(myOrderPanelLayout);
        myOrderPanelLayout.setHorizontalGroup(
            myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myOrderPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(myOrderPanelLayout.createSequentialGroup()
                        .addComponent(btnWineDetail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReturn)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        myOrderPanelLayout.setVerticalGroup(
            myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myOrderPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(myOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnWineDetail)
                    .addComponent(btnReturn))
                .addContainerGap(422, Short.MAX_VALUE))
        );

        userTabPane.addTab("My orders", myOrderPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userTabPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userTabPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtKeywordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeywordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeywordActionPerformed

    private void txtSellerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSellerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSellerActionPerformed

    private void txtFistNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFistNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFistNameActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void txtGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGenderActionPerformed

    private void txtDOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDOBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOBActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtMailZipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailZipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailZipActionPerformed

    private void txtMailStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailStateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailStateActionPerformed

    private void txtMailCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMailCityActionPerformed

    private void txtStreetTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStreetTwoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStreetTwoActionPerformed

    private void txtStreetOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStreetOneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStreetOneActionPerformed

    private void txtExpDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExpDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExpDateActionPerformed

    private void txtSecureCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSecureCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSecureCodeActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtZipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtZipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtZipActionPerformed

    private void txtStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStateActionPerformed

    private void txtCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCityActionPerformed

    private void txtStreet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStreet1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStreet1ActionPerformed

    // log out the user
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        for (Seller ss : business.getSd().getSellerList()) {
            for (Wine x : ss.getWineList()) {
                System.out.println(x.getNumberofInventory());
            }
        }

        processPanel.remove(this);
        Component[] componentArray = processPanel.getComponents();
        Component comp = componentArray[componentArray.length - 1];
        JPanel firstPage = (JPanel) comp;
        CardLayout card = (CardLayout) processPanel.getLayout();
        card.previous(processPanel);
    }//GEN-LAST:event_btnLogoutActionPerformed

    //View Product Detail button: will take the user to a view detail panel
    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        // TODO add your handling code here:
        int rowSelect = tblBrowseProduct.getSelectedRow();
        if (rowSelect >= 0) {
            Wine w = (Wine) tblBrowseProduct.getValueAt(rowSelect, 0);
            buyerViewWineDetailPanel bvwdp = new buyerViewWineDetailPanel(processPanel, w);
            processPanel.add("buyerViewWineDetailPanel", bvwdp);
            //we added a new panel on this cardLayout
            CardLayout card = (CardLayout) processPanel.getLayout();
            card.next(processPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }
    }//GEN-LAST:event_btnDetailActionPerformed

    //bring the user to the buyerViewWineDetailPanel
    private void btnWineDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWineDetailActionPerformed
        // TODO add your handling code here:
        /*int rowSelect = tblMyOrders.getSelectedRow();
        if (rowSelect >= 0) {
            Wine w = (Wine) tblMyOrders.getValueAt(rowSelect, 0);
            buyerViewWineDetailPanel bvwdp = new buyerViewWineDetailPanel(processPanel, w);
            processPanel.add("buyerViewWineDetailPanel2", bvwdp);
            CardLayout card = (CardLayout) processPanel.getLayout();
            card.next(processPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }*/

        int rowSelect = tblMyOrders.getSelectedRow();
        if (rowSelect >= 0) {
            Order ovo = (Order) tblMyOrders.getValueAt(rowSelect, 0);
            Wine w = ovo.getWine();
            buyerViewWineDetailPanel bvwdp = new buyerViewWineDetailPanel(processPanel, w);
            processPanel.add("buyerViewWineDetailPanel", bvwdp);
            //we added a new panel on this cardLayout
            CardLayout card = (CardLayout) processPanel.getLayout();
            card.next(processPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }


    }//GEN-LAST:event_btnWineDetailActionPerformed

    //this button is a simple demo of the return function in a real world. This button will take the user to the returnPanel panel, where a return label image will be shown,
    //asking the user to print it and palce it in the return package. But no specific implementation of return process will be coded.
    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        int rowSelect = tblMyOrders.getSelectedRow();

        if (rowSelect >= 0) {
            Order ovo = (Order) tblMyOrders.getValueAt(rowSelect, 0);
            //We need to change the status of this order;
            for (Order od1 : business.getOrderList()) {
                if (od1.getOrderNum().equals(ovo.getOrderNum())) {
                    od1.setOrderStatus("Return Pending");
                    refreshOrder();

                }
            }
            ovo.setOrderStatus("Return Pending");
            JOptionPane.showMessageDialog(null, "Order is at returned pending status");
            Wine w = ovo.getWine();
            returnPanel rp = new returnPanel(processPanel, w);
            processPanel.add("returnPanel", rp);
            CardLayout card = (CardLayout) processPanel.getLayout();
            card.next(processPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }

        /*Wine w = (Wine) tblBrowseProduct.getValueAt(rowSelect, 0);
        buyerViewWineDetailPanel bvwdp = new buyerViewWineDetailPanel(processPanel, w);
        processPanel.add("buyerViewWineDetailPanel", bvwdp);
        //we added a new panel on this cardLayout
        CardLayout card = (CardLayout) processPanel.getLayout();
        card.next(processPanel);*/

    }//GEN-LAST:event_btnReturnActionPerformed

    // jump the buyerViewWineDetailPanel with selected wine
    private void btnDetail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetail1ActionPerformed
        // TODO add your handling code here:
        /*int rowSelect = tblCarts.getSelectedRow();
        if (rowSelect >= 0) {
            Wine w = (Wine) tblCarts.getValueAt(rowSelect, 0);
            buyerViewWineDetailPanel bvwdp = new buyerViewWineDetailPanel(processPanel, w);
            processPanel.add("buyerViewWineDetailPanel3", bvwdp);
            CardLayout card = (CardLayout) processPanel.getLayout();
            card.next(processPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }*/
        int rowSelect = tblCarts.getSelectedRow();
        if (rowSelect >= 0) {
            Wine w = (Wine) tblCarts.getValueAt(rowSelect, 0);
            buyerViewWineDetailPanel bvwdp = new buyerViewWineDetailPanel(processPanel, w);
            processPanel.add("buyerViewWineDetailPanel", bvwdp);
            //we added a new panel on this cardLayout
            CardLayout card = (CardLayout) processPanel.getLayout();
            card.next(processPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }

    }//GEN-LAST:event_btnDetail1ActionPerformed

    // By clicking the check out button, the application will take the whole cartList to the checkoutPanel, becuasw we are checking out all the wines in the cart list
    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        // TODO add your handling code here:
        ////passing in buyer to the check out panel, 
        //because, we want to check every wines in the buyer's cart, so we need the buyer's cart list

        checkoutPanel cp = new checkoutPanel(processPanel, buyer, business);
        processPanel.add("checkoutPanel", cp);
        CardLayout card = (CardLayout) processPanel.getLayout();
        card.next(processPanel);

    }//GEN-LAST:event_btnCheckOutActionPerformed

//    Add to Cart button: will add the selected wine to the cart (an arrayList under Buyer class), PLEASE NOTE that this button requires an input from the quantity text field. 
//    before adding the wine to the cart, the button will check if the entered quantity is less than the wine inventury number
//    This button will opens a message box saying wine added to the cart, but no need to upate the inventory amount of that product
    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblBrowseProduct.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Pls select a row!!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Wine selectedProduct = (Wine) tblBrowseProduct.getValueAt(selectedRow, 0);
        System.out.println(selectedProduct.getNumberofInventory());
        //the Wine you have choosed ;

        int fetchQty = (Integer) quantitySpinner.getValue();
        if (fetchQty <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity cannot be less than equalto 0", "Warning", JOptionPane.WARNING_MESSAGE);
            //JOptionPane.showMessageDialog(null, "Quantity cannot be less than equalto 0");
            return;
        }
        //at this point fetchQty>0;
        try {
            //first we need to validate if the fetchQty <= all Qty of this wine
            //if we already have added this wine in to card, we need to calculate the number that we can still add
            // and validate again???   or just leave it and validate it unitil checked out???
            // emm....... maybe validate until check out is better? but it do not make sense
            //so I need to validate it twice!  <-_-!>
            if (fetchQty <= (int) tblBrowseProduct.getValueAt(selectedRow, 5)) {
                //Valid number, continue, add num to the card
                boolean alreadyPresent = false;
                //this for loop is to check if is there any same product has been add in to the cart already;
                for (Wine wx : buyer.getCart()) {
                    if (wx.getWineName().equals(selectedProduct.getWineName()) && wx.getSeller().equals(selectedProduct.getSeller())) {
                        alreadyPresent = true;
                        int oldnum = wx.getNumberofInventory();
                        // get the number already in the cart
                        if (oldnum + fetchQty <= (int) tblBrowseProduct.getValueAt(selectedRow, 5)) {
                            wx.setNumberofInventory(oldnum + fetchQty);
                            refreshcartTable();
                            JOptionPane.showMessageDialog(null, "Successfully Added to Cart");

                            txtTotal.setText(TOOL_TIP_TEXT_KEY);
                        } else {
                            JOptionPane.showMessageDialog(null, "Qty in cart > Avail", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }

                //to check if this wine has already been added;
                //to travers the cart list of the buyer;
                //Create a new Wine object in cart List, and copy the information form the selected Wine object;
                if (!alreadyPresent && fetchQty <= selectedProduct.getNumberofInventory()) {
                    Wine cw2 = buyer.addCart();
                    cw2.setWineName(selectedProduct.getWineName());
                    cw2.setAlcoholPercentage(selectedProduct.getAlcoholPercentage());
                    cw2.setPrice(selectedProduct.getPrice());
                    cw2.setOriginCountry(selectedProduct.getOriginCountry());
                    cw2.setLocation(selectedProduct.getLocation());
                    cw2.setGrape(selectedProduct.getGrape());
                    cw2.setNumberofInventory(fetchQty);
                    cw2.setSeller(selectedProduct.getSeller());
                    refreshcartTable();
                    JOptionPane.showMessageDialog(null, "Successfully Added to Cart");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Qty > Avail", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Quantity");
            return;
        }
        double sumMoney = 0;
        for (Wine w1 : buyer.getCart()) {
            sumMoney = sumMoney + w1.getPrice() * w1.getNumberofInventory();

        }
        txtTotal.setText(String.valueOf(sumMoney));

    }//GEN-LAST:event_btnAddToCartActionPerformed

    public void refreshcartTable() {
        int rowCount = tblCarts.getRowCount();
        DefaultTableModel model = (DefaultTableModel) tblCarts.getModel();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        //System.out.print("OK");

        for (Wine w1 : buyer.getCart()) {
            Object row[] = new Object[4];
            row[0] = w1;
            //row[1] = w1.getSeller().getFirstName() + " " + w1.getSeller().getLastName();
            row[2] = w1.getNumberofInventory();
            row[3] = w1.getPrice() * w1.getNumberofInventory();

            //row[2]=s.getNumplane();
            model.addRow(row);
        }

    }

//1-Click checkout button: will check out the selected wine without asking the buyer any question, the button will add a new order in Buyer's orderList and seller's transactionList
//The wine has an attribute of Seller, wich can be used to access a seller's transaction list
//meanwhile, the button will also show a message box saying the order has been placed successfully, PLEASE note that this button requires the input in the quantity text field.
//Before placing the order, the button will check if the entered quantity is less that the wine's inventory number, after placing the order, the button will subtract the
//number of inventory of that product by the amount of entered quantity
//By defualt, the "status" of the newly added order is "order placed", which can be further updated by the seller.
    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblBrowseProduct.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Pls select a row!!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Wine wseller = (Wine) tblBrowseProduct.getValueAt(selectedRow, 0);
        //the Wine you have choosed ;

        int fetchQty = (Integer) quantitySpinner.getValue();
        if (fetchQty <= 0) {
            JOptionPane.showMessageDialog(null, "Quantity cannot be less than equalto 0", "Warning", JOptionPane.WARNING_MESSAGE);
            //JOptionPane.showMessageDialog(null, "Quantity cannot be less than equalto 0");
            return;
        }
        try {
            if (fetchQty <= wseller.getNumberofInventory()) {
                Order o1 = buyer.addOrder();
                //首先在buyer自己的orderList里面加入这个新的order
                //此次加入购物车的数量
                for (Seller sls : business.getSd().getSellerList()) {
                    if (sls.equals(wseller.getSeller())) {
                        //meet the seller
                        for (Wine wx : sls.getWineList()) {
                            //meet the wine;
                            if (wx.getWineName().equals(wseller.getWineName())) {
                                //compare the number 
                                if (fetchQty <= wx.getNumberofInventory()) {
                                    //so this is a validated order;
                                    o1.setBuyer(buyer);
                                    // Date d1 = new Date();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    String str = sdf.format(new Date());
                                    o1.setOrderDate(str);
                                    //Generate a unique orderNumber
                                    o1.setOrderNum(GenerateNum.getInstance().GenerateOrder());
                                    o1.setOrderStatus("Payed");
                                    o1.setQty(fetchQty);
                                    //订单数量

                                    o1.setSellerNameName(wseller.getSeller());
                                    o1.setTotal(fetchQty * wseller.getPrice());
                                    //Actually this is not set wine name, this put the wine object into the order;
                                    o1.setWineName(wseller);
                                    business.addOrder(o1);
                                    //And I need to substract the number of this wine in seller;
                                    System.out.println("减去前货存量" + wx.getNumberofInventory());
                                    wx.setNumberofInventory(wx.getNumberofInventory() - fetchQty);
                                    System.out.println("减去后货存量" + wx.getNumberofInventory());
                                    JOptionPane.showMessageDialog(null, "Successfully Checked Out");
                                    //break;
                                    //Seems like we need to debug here
                                } else {
                                    JOptionPane.showMessageDialog(null, "Qty in cart > Avail", "Warning", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                            }
                        }
                    }

                }
                refreshcartTable();
                //JOptionPane.showMessageDialog(null, "Successfully Added to Cart");

            } else {
                JOptionPane.showMessageDialog(null, "Qty > Avail", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid SalsePrice");
            return;
        }

        showCardInfo();
        refreshTable();
        refreshOrder();
        refreshCarts();

    }//GEN-LAST:event_btnCheckoutActionPerformed
    // delete the wine from the cartList
    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblCarts.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "pls select a row");
            return;
        }
        Wine selectedProduct = (Wine) tblCarts.getValueAt(selectedRow, 0);
        buyer.removeCart(selectedProduct);
        JOptionPane.showMessageDialog(null, "Successfully removed");
        this.refreshcartTable();
        double sumMoney = 0;
        for (Wine w1 : buyer.getCart()) {
            sumMoney = sumMoney + w1.getPrice() * w1.getNumberofInventory();

        }
        txtTotal.setText(String.valueOf(sumMoney));
    }//GEN-LAST:event_btnRemoveActionPerformed
    // Add the selected wine to the buyer's wishlist, and refresh the wishlist
    private void btnLaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaterActionPerformed
        // TODO add your handling code here:

        //Still a bug, need to validate if it has been in the WishList for the same type.
        int selectedRow = tblCarts.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "pls select a row");
            return;
        }
        Wine selectedProduct = (Wine) tblCarts.getValueAt(selectedRow, 0);
        buyer.addWishList(selectedProduct);
        buyer.removeCart(selectedProduct);
        JOptionPane.showMessageDialog(null, "Successfully Added to wish List");
        this.refreshcartTable();
        this.refreshtblWishList();
        double sumMoney = 0;
        for (Wine w1 : buyer.getCart()) {
            sumMoney = sumMoney + w1.getPrice() * w1.getNumberofInventory();

        }
        txtTotal.setText(String.valueOf(sumMoney));
    }//GEN-LAST:event_btnLaterActionPerformed
    // remove wine from the buyer's wish list
    public void refreshtblWishList() {
        int rowCount = tblWishList.getRowCount();
        DefaultTableModel model = (DefaultTableModel) tblWishList.getModel();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        //System.out.print("OK");

        for (Wine w1 : buyer.getWishList()) {
            Object row[] = new Object[4];
            row[0] = w1;
            row[1] = w1.getSeller().getFirstName() + " " + w1.getSeller().getLastName();
            row[2] = w1.getNumberofInventory();
            row[3] = w1.getPrice() * w1.getNumberofInventory();

            //row[2]=s.getNumplane();
            model.addRow(row);
        }
    }

    private void btnRemoveWishListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveWishListActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblWishList.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "pls select a row");
            return;
        }
        Wine selectedProduct = (Wine) tblWishList.getValueAt(selectedRow, 0);
        //Here is a Bug; Solve it latter;
        buyer.removeWishlist(selectedProduct);
        refreshtblWishList();
        JOptionPane.showMessageDialog(null, "Successfully removed from WishList");

    }//GEN-LAST:event_btnRemoveWishListActionPerformed

    private void btnMoveToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveToCartActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblWishList.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "pls select a row");
            return;
        }
        Wine selectedProduct = (Wine) tblWishList.getValueAt(selectedRow, 0);
        Wine cw2 = buyer.addCart();
        cw2.setWineName(selectedProduct.getWineName());
        cw2.setAlcoholPercentage(selectedProduct.getAlcoholPercentage());
        cw2.setPrice(selectedProduct.getPrice());
        cw2.setOriginCountry(selectedProduct.getOriginCountry());
        cw2.setLocation(selectedProduct.getLocation());
        cw2.setGrape(selectedProduct.getGrape());
        cw2.setNumberofInventory(selectedProduct.getNumberofInventory());
        cw2.setSeller(selectedProduct.getSeller());

        buyer.removeWishlist(selectedProduct);

        JOptionPane.showMessageDialog(null, "Successfully Moved to Cart");
        this.refreshcartTable();
        this.refreshtblWishList();

        double sumMoney = 0;
        for (Wine w1 : buyer.getCart()) {
            sumMoney = sumMoney + w1.getPrice() * w1.getNumberofInventory();

        }
        txtTotal.setText(String.valueOf(sumMoney));

    }//GEN-LAST:event_btnMoveToCartActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String seller = txtSeller.getText().trim();
        String product = txtKeyword.getText().trim();

        //System.out.print("OK");
        if (seller.equals("") && product.equals("")) {
            refreshTable();
        } else if (product.equals("")) {
            //only filter the seller;
            int rowCount = tblBrowseProduct.getRowCount();
            DefaultTableModel model = (DefaultTableModel) tblBrowseProduct.getModel();
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            for (Seller sl : business.getSd().getSellerList()) {
                if (sl.getFirstName().equals(seller) || sl.getLastName().equals(seller)) {
                    for (Wine w1 : sl.getWineList()) {
                        Object row[] = new Object[8];
                        row[0] = w1;
                        row[1] = w1.getSeller().getFirstName() + " " + w1.getSeller().getLastName();
                        row[2] = w1.getPrice();
                        row[3] = w1.getAlcoholPercentage();
                        row[4] = w1.getOriginCountry();
                        row[5] = w1.getNumberofInventory();
                        row[6] = w1.getGrape();
                        row[7] = w1.getLocation();
                        //row[2]=s.getNumplane();
                        model.addRow(row);
                    }
                }

            }

        } else if (seller.equals("")) {
            //only filter the product
            int rowCount = tblBrowseProduct.getRowCount();
            DefaultTableModel model = (DefaultTableModel) tblBrowseProduct.getModel();
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            for (Seller sl : business.getSd().getSellerList()) {

                for (Wine w1 : sl.getWineList()) {
                    if (product.equals(w1.getWineName())) {
                        Object row[] = new Object[8];
                        row[0] = w1;
                        row[1] = w1.getSeller().getFirstName() + " " + w1.getSeller().getLastName();
                        row[2] = w1.getPrice();
                        row[3] = w1.getAlcoholPercentage();
                        row[4] = w1.getOriginCountry();
                        row[5] = w1.getNumberofInventory();
                        row[6] = w1.getGrape();
                        row[7] = w1.getLocation();
                        //row[2]=s.getNumplane();
                        model.addRow(row);
                    }
                }

            }
        } else {
            int rowCount = tblBrowseProduct.getRowCount();
            DefaultTableModel model = (DefaultTableModel) tblBrowseProduct.getModel();
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            for (Seller sl : business.getSd().getSellerList()) {
                if (sl.getFirstName().equals(seller) || sl.getLastName().equals(seller)) {
                    for (Wine w1 : sl.getWineList()) {
                        if (product.equals(w1.getWineName())) {
                            Object row[] = new Object[8];
                            row[0] = w1;
                            row[1] = w1.getSeller().getFirstName() + " " + w1.getSeller().getLastName();
                            row[2] = w1.getPrice();
                            row[3] = w1.getAlcoholPercentage();
                            row[4] = w1.getOriginCountry();
                            row[5] = w1.getNumberofInventory();
                            row[6] = w1.getGrape();
                            row[7] = w1.getLocation();
                            //row[2]=s.getNumplane();
                            model.addRow(row);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateBankActionPerformed
        // TODO add your handling code here:

        txtCardNo.setEnabled(true);
        txtExpDate.setEnabled(true);
        txtSecureCode.setEnabled(true);
        txtName.setEnabled(true);
        txtStreet1.setEnabled(true);
        txtStreet2.setEnabled(true);
        txtCity.setEnabled(true);
        txtZip.setEnabled(true);
        txtState.setEnabled(true);
        btnSaveBank.setEnabled(true);
        btnUpdateBank.setEnabled(false);

    }//GEN-LAST:event_btnUpdateBankActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        txtFistName.setEnabled(true);
        txtLastName.setEnabled(true);
        txtGender.setEnabled(true);
        txtDOB.setEnabled(true);
        txtEmail.setEnabled(true);
        //txtUserName.setText(ac1.getUserName());
        txtPassword.setEnabled(true);
        txtStreetOne.setEnabled(true);
        txtStreetTwo.setEnabled(true);
        txtMailCity.setEnabled(true);
        txtMailState.setEnabled(true);
        txtMailZip.setEnabled(true);
        btnSaveBasic.setEnabled(true);
        btnUpdate.setEnabled(false);

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveBasicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBasicActionPerformed
        // TODO add your handling code here:
        if (txtFistName.getText().equals("") || txtLastName.getText().equals("")
                || txtGender.getText().equals("") || txtDOB.getText().equals("")
                || txtEmail.getText().equals("") || txtStreetOne.getText().equals("")
                || txtStreetTwo.getText().equals("") || txtMailCity.getText().equals("")
                || txtMailState.getText().equals("") || txtMailZip.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter values for all fields.");
            //make sure that all of the textbox have been filled; 
        } else {
            int zip = Integer.valueOf(txtMailZip.getText());
            String fname = txtFistName.getText();
            String lname = txtLastName.getText();
            String gender = txtGender.getText();
            String dob = txtDOB.getText();
            String email = txtEmail.getText();
            String street1 = txtStreetOne.getText();
            String street2 = txtStreetTwo.getText();
            String city = txtMailCity.getText();
            String state = txtMailState.getText();
            //String username = txtUserName.getText();
            String password = txtPassword.getText();

            buyer.setFirstName(fname);
            buyer.setLastName(lname);
            buyer.setGender(gender);
            buyer.setDOB(dob);
            buyer.setEmail(email);
            buyer.getMailingAddress().setStreetOne(street1);
            buyer.getMailingAddress().setStreetTwo(street2);
            buyer.getMailingAddress().setCity(city);
            buyer.getMailingAddress().setState(state);
            buyer.getMailingAddress().setZip(zip);
            for (Account ac : business.getAd().getAccountList()) {
                if (ac.getPerson().equals(buyer)) {
                    ac.setPassword(password);
                }
            }
            JOptionPane.showMessageDialog(null, "You have succeffuly updated the  Maling information!");
            refreshTable();
            txtFistName.setEnabled(false);
            txtLastName.setEnabled(false);
            txtGender.setEnabled(false);
            txtDOB.setEnabled(false);
            txtEmail.setEnabled(false);
            //txtUserName.setText(ac1.getUserName());
            txtPassword.setEnabled(false);
            txtStreetOne.setEnabled(false);
            txtStreetTwo.setEnabled(false);
            txtMailCity.setEnabled(false);
            txtMailState.setEnabled(false);
            txtMailZip.setEnabled(false);
            btnSaveBasic.setEnabled(false);
            btnUpdate.setEnabled(true);
            this.showMailInfo();
        }
    }//GEN-LAST:event_btnSaveBasicActionPerformed

    private void btnSaveBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBankActionPerformed
        // TODO add your handling code here:
        if (txtCardNo.getText().equals("") || txtExpDate.getText().equals("") || txtSecureCode.getText().equals("")
                || txtName.getText().equals("") || txtStreet1.getText().equals("") || txtStreet2.getText().equals("")
                || txtCity.getText().equals("") || txtState.getText().equals("") || txtZip.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter values for all fields.");
        } else {
            long cardNo = Long.valueOf(txtCardNo.getText());
            String expDate = txtExpDate.getText();
            int secureCode = Integer.valueOf(txtSecureCode.getText());
            String nameOnCard = txtName.getText();
            String street1 = txtStreet1.getText();
            String street2 = txtStreet2.getText();
            String city = txtCity.getText();
            String state = txtState.getText();
            int zip = Integer.valueOf(txtZip.getText());
            buyer.getBank().setCardNumber(cardNo);
            buyer.getBank().setExpDate(expDate);
            buyer.getBank().setSecureCode(secureCode);
            buyer.getBank().setNameOnCard(nameOnCard);
            buyer.getBillingAddress().setStreetOne(street1);
            buyer.getBillingAddress().setStreetTwo(street2);
            buyer.getBillingAddress().setCity(city);
            buyer.getBillingAddress().setState(state);
            buyer.getBillingAddress().setZip(zip);
            JOptionPane.showMessageDialog(null, "Buyer payment information and account Updated!");
            txtCardNo.setEnabled(false);
            txtExpDate.setEnabled(false);
            txtSecureCode.setEnabled(false);
            txtName.setEnabled(false);
            txtStreet1.setEnabled(false);
            txtStreet2.setEnabled(false);
            txtCity.setEnabled(false);
            txtZip.setEnabled(false);
            txtState.setEnabled(false);
            this.showCardInfo();
            btnSaveBank.setEnabled(false);
            btnUpdateBank.setEnabled(true);
        }

    }//GEN-LAST:event_btnSaveBankActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        for (Seller sl : business.getSd().getSellerList()) {
            System.out.println(sl.getFirstName());
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel browseProductPanel;
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnDetail1;
    private javax.swing.JButton btnLater;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMoveToCart;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnRemoveWishList;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSaveBank;
    private javax.swing.JButton btnSaveBasic;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateBank;
    private javax.swing.JButton btnWineDetail;
    private javax.swing.JCheckBox chkPrime;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel myAccountPanel;
    private javax.swing.JPanel myCartPanel;
    private javax.swing.JPanel myOrderPanel;
    private javax.swing.JPanel myPaymentPanel;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JTable tblBrowseProduct;
    private javax.swing.JTable tblCarts;
    private javax.swing.JTable tblMyOrders;
    private javax.swing.JTable tblWishList;
    private javax.swing.JTextField txtCardNo;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtExpDate;
    private javax.swing.JTextField txtFistName;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtKeyword;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMailCity;
    private javax.swing.JTextField txtMailState;
    private javax.swing.JTextField txtMailZip;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSecureCode;
    private javax.swing.JTextField txtSeller;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtStreet1;
    private javax.swing.JTextField txtStreet2;
    private javax.swing.JTextField txtStreetOne;
    private javax.swing.JTextField txtStreetTwo;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtZip;
    private javax.swing.JTabbedPane userTabPane;
    // End of variables declaration//GEN-END:variables
}
