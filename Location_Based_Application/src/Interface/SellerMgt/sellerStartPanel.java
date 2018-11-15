/*
Author: Ruifeng Wang
Date: 02/24/2018
After logging in successfully, the user will be taken to this panel, the work area of sellers, it has panels as follows:

    productPanel: is used to manage wines, view wine details, remove wine from product list, and log out. Upon startng, this panel should populate the wine table with all the wines
                  that this seller has. 

    TransactionPanel: this panel's purpose is to manage transaction list of the seller. User could comfirm a return for a specific order, or update the order status

    ManageBasicPanel: this panel manages or updates the seller's basic info

    BankAccountPanel: this panel manages or update the seller's bank info


 */
package Interface.SellerMgt;

import Business.*;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raywa
 */
public class sellerStartPanel extends javax.swing.JPanel {

    /**
     * Creates new form sellerStartPanel
     */
    private JPanel processPanel;
    private Seller seller;
    private Business business;

    public sellerStartPanel(JPanel processPanel, Seller seller, Business bus) {
        initComponents();
        this.processPanel = processPanel;
        this.seller = seller;
        this.business = bus;
        setOrder();
        populate();

        //need to implement
        refresh();
        populateOrder();
    }

    public void setOrder() {
        seller.getTransactionList().clear();
        for (Order o : business.getOrderList()) {
            if (o.getSeller().equals(seller)) {
                Order o2 = seller.addTransaction();
                o2.setBuyer(o.getBuyer());
                o2.setOrderNum(o.getOrderNum());
                o2.setQty(o.getQty());
                o2.setOrderStatus(o.getOrderStatus());
                o2.setTotal(o.getTotal());
                o2.setSellerNameName(o.getSeller());
                o2.setOrderDate(o.getOrderDate());
                o2.setWineName(o.getWine());
                o2.setOrderStatus(o.getOrderStatus());

            }
        }
    }

    public void setWineSeller(Seller s, String Name) {
        Wine ww = null;
        for (Wine w : seller.getWineList()) {
            if (w.getWineName().equals(Name)) {
                ww = w;
            }

        }
        ww.setSeller(s);
    }

    public void populate() {
        DefaultTableModel dtm = (DefaultTableModel) tblProduct.getModel();
        dtm.setRowCount(0);
        System.out.println("这个buyer显示库存");
        for (Wine w : seller.getWineList()) {
            Object row[] = new Object[7];
            System.out.println(w.getNumberofInventory());
            row[0] = w;
            row[1] = w.getPrice();
            row[2] = w.getAlcoholPercentage();
            row[3] = w.getOriginCountry();
            row[4] = w.getNumberofInventory();
            row[5] = w.getGrape();
            row[6] = w.getLocation();
            dtm.addRow(row);
        }
    }

    public void populateOrder() {
        DefaultTableModel dtm = (DefaultTableModel) tblTransaction.getModel();
        dtm.setRowCount(0);
        for (Order o : seller.getTransactionList()) {
            Object row[] = new Object[7];
            row[0] = o;
            row[1] = o.getOrderDate();
            row[2] = o.getWine();
            row[3] = o.getNewBuyer();
            row[4] = o.getQty();
            row[5] = o.getTotal();
            row[6] = o.getOrderStatus();
            System.out.print(o.getOrderStatus());
            dtm.addRow(row);
        }

    }

    public void setWineNumber(int a, String WineName) {
        Wine ww = null;
        for (Wine w : seller.getWineList()) {
            if (w.equals(WineName)) {
                ww = w;
            }
        }
        int b = ww.getNumberofInventory();
        ww.setNumberofInventory(b - a);
        populate();

    }

    //need to implement
    public void refresh() {
        txtFirstName.setText(seller.getFirstName());
        txtDOB.setText(seller.getDOB());
        txtLastName.setText(seller.getLastName());
        txtEmail.setText(seller.getEmail());
        txtGender.setText(seller.getGender());
        txtSSN.setText(String.valueOf(seller.getSSN()));
        txtName.setText(seller.getBank().getNameOnCard());
        txtStreet1.setText(seller.getBillingAddress().getStreetOne());
        txtStreet2.setText(seller.getBillingAddress().getStreetTwo());
        txtExpDate.setText(seller.getBank().getExpDate());
        txtCity.setText(seller.getBillingAddress().getCity());
        txtSecureCode.setText(String.valueOf(seller.getBank().getSecureCode()));
        txtZip.setText(String.valueOf(seller.getBillingAddress().getZip()));
        txtState.setText(seller.getBillingAddress().getState());
        txtCardNumber.setText(String.valueOf(seller.getBank().getCardNumber()));

        txtFirstName.setEnabled(false);
        txtDOB.setEnabled(false);
        txtLastName.setEnabled(false);
        txtEmail.setEnabled(false);
        txtGender.setEnabled(false);
        txtSSN.setEnabled(false);
        btnUpdate.setEnabled(true);
        txtName.setEnabled(false);
        txtStreet1.setEnabled(false);
        txtCardNumber.setEnabled(false);
        txtStreet2.setEnabled(false);
        txtExpDate.setEnabled(false);
        txtCity.setEnabled(false);
        txtSecureCode.setEnabled(false);
        txtZip.setEnabled(false);
        txtState.setEnabled(false);
        btnUpdateBasic.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sellerTabPane = new javax.swing.JTabbedPane();
        productPanel = new javax.swing.JPanel();
        txtGrape = new javax.swing.JTextField();
        txtWineName = new javax.swing.JTextField();
        saveProduct = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        txtAlcohol = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnDetail = new javax.swing.JButton();
        txtWinePrice = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();
        txtCountry = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        chkPrime = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        btnLogout = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtInventory = new javax.swing.JTextField();
        basicInfoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDOB = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtSSN = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        btnUpdateBasic = new javax.swing.JButton();
        btnSaveBasic = new javax.swing.JButton();
        bankAccountPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCardNumber = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtExpDate = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtSecureCode = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtStreet1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtStreet2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtState = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtZip = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        transactionPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTransaction = new javax.swing.JTable();
        btnReturn = new javax.swing.JButton();
        btnUpdateShipping = new javax.swing.JButton();

        productPanel.setBackground(new java.awt.Color(255, 255, 255));

        saveProduct.setBackground(new java.awt.Color(255, 255, 255));
        saveProduct.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        saveProduct.setForeground(new java.awt.Color(255, 102, 102));
        saveProduct.setText("Add to product list");
        saveProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveProductActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 102, 102));
        jLabel10.setText("Alcohol%");

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Wine Name", "Price", "Alcohol%", "Origin Country", "Number of Wine", "Grape Type", "Locatoin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProduct);
        if (tblProduct.getColumnModel().getColumnCount() > 0) {
            tblProduct.getColumnModel().getColumn(1).setResizable(false);
            tblProduct.getColumnModel().getColumn(2).setResizable(false);
            tblProduct.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel8.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 102));
        jLabel8.setText("Number of Inventory:");

        btnDetail.setBackground(new java.awt.Color(255, 255, 255));
        btnDetail.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnDetail.setForeground(new java.awt.Color(255, 102, 102));
        btnDetail.setText("View Wine Detail");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 102, 102));
        jLabel11.setText("Origin Country:");

        btnRemove.setBackground(new java.awt.Color(255, 255, 255));
        btnRemove.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(255, 102, 102));
        btnRemove.setText("Remove Wine from Inventory");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 102, 102));
        jLabel9.setText("Prime?");

        jLabel12.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 102, 102));
        jLabel12.setText("Type of Grape:");

        jLabel7.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 102, 102));
        jLabel7.setText("Wine Name:");

        jLabel24.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 102, 102));
        jLabel24.setText("Location of Product (State):");

        btnLogout.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 102, 102));
        btnLogout.setText("Log out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 102, 102));
        jLabel18.setText("Price:");

        javax.swing.GroupLayout productPanelLayout = new javax.swing.GroupLayout(productPanel);
        productPanel.setLayout(productPanelLayout);
        productPanelLayout.setHorizontalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel24)
                            .addComponent(jLabel7)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, productPanelLayout.createSequentialGroup()
                                        .addComponent(txtWineName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                        .addComponent(jLabel10))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, productPanelLayout.createSequentialGroup()
                                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtWinePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(18, 18, 18)
                                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAlcohol, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGrape, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                                .addComponent(txtInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkPrime))))
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(productPanelLayout.createSequentialGroup()
                                .addComponent(btnDetail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRemove)
                                .addGap(238, 238, 238))
                            .addComponent(btnLogout)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 80, Short.MAX_VALUE))
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(saveProduct)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        productPanelLayout.setVerticalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(txtWineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlcohol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtWinePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel24)
                            .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtGrape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkPrime)
                    .addComponent(jLabel9)
                    .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(saveProduct)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetail)
                    .addComponent(btnRemove))
                .addGap(29, 29, 29)
                .addComponent(btnLogout)
                .addContainerGap())
        );

        sellerTabPane.addTab("Wine Catalog", productPanel);

        basicInfoPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("First Name:");

        txtFirstName.setEnabled(false);
        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 102));
        jLabel4.setText("Date of Birth:");

        txtDOB.setEnabled(false);
        txtDOB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDOBActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 102));
        jLabel5.setText("Email Address:");

        txtEmail.setEnabled(false);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtSSN.setEnabled(false);
        txtSSN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSSNActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 102, 102));
        jLabel6.setText("SSN:");

        txtGender.setEnabled(false);
        txtGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGenderActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setText("Gender:");

        jLabel2.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("Last Name:");

        txtLastName.setEnabled(false);
        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });

        btnUpdateBasic.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdateBasic.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnUpdateBasic.setForeground(new java.awt.Color(255, 102, 102));
        btnUpdateBasic.setText("Update");
        btnUpdateBasic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateBasicActionPerformed(evt);
            }
        });

        btnSaveBasic.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveBasic.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnSaveBasic.setForeground(new java.awt.Color(255, 102, 102));
        btnSaveBasic.setText("Save Changes");
        btnSaveBasic.setEnabled(false);
        btnSaveBasic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBasicActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout basicInfoPanelLayout = new javax.swing.GroupLayout(basicInfoPanel);
        basicInfoPanel.setLayout(basicInfoPanelLayout);
        basicInfoPanelLayout.setHorizontalGroup(
            basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(basicInfoPanelLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(138, 138, 138)
                        .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                                .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSSN, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(basicInfoPanelLayout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(btnUpdateBasic)
                        .addGap(52, 52, 52)
                        .addComponent(btnSaveBasic)))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        basicInfoPanelLayout.setVerticalGroup(
            basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInfoPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(basicInfoPanelLayout.createSequentialGroup()
                        .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSSN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(basicInfoPanelLayout.createSequentialGroup()
                        .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(151, 151, 151)
                .addGroup(basicInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateBasic)
                    .addComponent(btnSaveBasic))
                .addContainerGap(177, Short.MAX_VALUE))
        );

        sellerTabPane.addTab("Manage Basic Info", basicInfoPanel);

        bankAccountPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 102, 102));
        jLabel13.setText("Name on Bank Account:");

        txtName.setEnabled(false);
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 102, 102));
        jLabel14.setText("Card Number:");

        txtCardNumber.setEnabled(false);
        txtCardNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCardNumberActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 102, 102));
        jLabel15.setText("ExpDate");

        txtExpDate.setEnabled(false);
        txtExpDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExpDateActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 102, 102));
        jLabel16.setText("Secure Code:");

        txtSecureCode.setEnabled(false);
        txtSecureCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSecureCodeActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 102, 102));
        jLabel17.setText("Billing Address:");

        jLabel19.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 102, 102));
        jLabel19.setText("Street Address #1:");

        txtStreet1.setEnabled(false);
        txtStreet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStreet1ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 102, 102));
        jLabel20.setText("Street Address #2:");

        txtStreet2.setEnabled(false);
        txtStreet2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStreet2ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 102, 102));
        jLabel21.setText("City:");

        txtCity.setEnabled(false);
        txtCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCityActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 102, 102));
        jLabel22.setText("State:");

        txtState.setEnabled(false);
        txtState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStateActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 102, 102));
        jLabel23.setText("Zip Code:");

        txtZip.setEnabled(false);
        txtZip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtZipActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 102, 102));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 102, 102));
        btnSave.setText("Save Changes");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bankAccountPanelLayout = new javax.swing.GroupLayout(bankAccountPanel);
        bankAccountPanel.setLayout(bankAccountPanelLayout);
        bankAccountPanelLayout.setHorizontalGroup(
            bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bankAccountPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bankAccountPanelLayout.createSequentialGroup()
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExpDate, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSecureCode, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bankAccountPanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel17))
                            .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel23)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bankAccountPanelLayout.createSequentialGroup()
                                    .addGap(75, 75, 75)
                                    .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(bankAccountPanelLayout.createSequentialGroup()
                        .addComponent(txtCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStreet1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStreet2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZip, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(172, 172, 172))
            .addGroup(bankAccountPanelLayout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(btnUpdate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bankAccountPanelLayout.setVerticalGroup(
            bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bankAccountPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bankAccountPanelLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtStreet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtStreet2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(bankAccountPanelLayout.createSequentialGroup()
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtExpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtSecureCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(111, 111, 111)
                .addGroup(bankAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnSave))
                .addContainerGap(124, Short.MAX_VALUE))
        );

        sellerTabPane.addTab("Manage Bank Account", bankAccountPanel);

        transactionPanel.setBackground(new java.awt.Color(255, 255, 255));

        tblTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order Number", "Order Date", "Wine Name", "Customer Name", "Quantity", "Subtotal", "Order Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblTransaction);
        if (tblTransaction.getColumnModel().getColumnCount() > 0) {
            tblTransaction.getColumnModel().getColumn(0).setResizable(false);
            tblTransaction.getColumnModel().getColumn(1).setResizable(false);
            tblTransaction.getColumnModel().getColumn(2).setResizable(false);
            tblTransaction.getColumnModel().getColumn(3).setResizable(false);
            tblTransaction.getColumnModel().getColumn(4).setResizable(false);
            tblTransaction.getColumnModel().getColumn(5).setResizable(false);
            tblTransaction.getColumnModel().getColumn(6).setResizable(false);
        }

        btnReturn.setBackground(new java.awt.Color(255, 255, 255));
        btnReturn.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(255, 102, 102));
        btnReturn.setText("Comfirm a order or Issue a refund");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        btnUpdateShipping.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdateShipping.setFont(new java.awt.Font("Viner Hand ITC", 1, 18)); // NOI18N
        btnUpdateShipping.setForeground(new java.awt.Color(255, 102, 102));
        btnUpdateShipping.setText("Update Shipping info");
        btnUpdateShipping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateShippingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout transactionPanelLayout = new javax.swing.GroupLayout(transactionPanel);
        transactionPanel.setLayout(transactionPanelLayout);
        transactionPanelLayout.setHorizontalGroup(
            transactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactionPanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnReturn)
                .addGap(46, 46, 46)
                .addComponent(btnUpdateShipping)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(transactionPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );
        transactionPanelLayout.setVerticalGroup(
            transactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactionPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(transactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReturn)
                    .addComponent(btnUpdateShipping))
                .addContainerGap(322, Short.MAX_VALUE))
        );

        sellerTabPane.addTab("Transaction History", transactionPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sellerTabPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sellerTabPane)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void txtDOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDOBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOBActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void txtGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGenderActionPerformed

    private void txtSSNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSSNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSSNActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtCardNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCardNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCardNumberActionPerformed

    private void txtExpDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExpDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExpDateActionPerformed

    private void txtSecureCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSecureCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSecureCodeActionPerformed

    private void txtStreet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStreet1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStreet1ActionPerformed

    private void txtStreet2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStreet2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStreet2ActionPerformed

    private void txtCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCityActionPerformed

    private void txtStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStateActionPerformed

    private void txtZipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtZipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtZipActionPerformed
    // log out
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        processPanel.remove(this);
        Component[] componentArray = processPanel.getComponents();
        Component comp = componentArray[componentArray.length - 1];
        JPanel firstPage = (JPanel) comp;
        CardLayout card = (CardLayout) processPanel.getLayout();
        card.previous(processPanel);
    }//GEN-LAST:event_btnLogoutActionPerformed
    //View detail of the wine
    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        // TODO add your handling code here:
        int rowSelect = tblProduct.getSelectedRow();
        if (rowSelect >= 0) {
            Wine w = (Wine) tblProduct.getValueAt(rowSelect, 0);
            viewWineDetailPanel vwdp = new viewWineDetailPanel(processPanel, w);
            processPanel.add("viewWineDetail", vwdp);
            CardLayout card = (CardLayout) processPanel.getLayout();
            card.next(processPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }
    }//GEN-LAST:event_btnDetailActionPerformed
    // This button will bring the screen to the updateShippingInfoPanel, 
    private void btnUpdateShippingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateShippingActionPerformed
        // TODO add your handling code here:
        int rowSelect = tblTransaction.getSelectedRow();

        if (rowSelect >= 0) {
            Order o1 = (Order) tblTransaction.getValueAt(rowSelect, 0);
            String b = o1.getOrderNum();
            Order oo = new Order();
            for (Order o : seller.getTransactionList()) {
                if (o.getOrderNum().equals(b)) {
                    oo = o;
                }
            }
            System.out.print(oo.getOrderStatus());
            updateShippingInfoPanel usip = new updateShippingInfoPanel(processPanel, oo);
            processPanel.add("updateShippingInfoPanel", usip);
            CardLayout card = (CardLayout) processPanel.getLayout();
            card.next(processPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }
    }//GEN-LAST:event_btnUpdateShippingActionPerformed
    //Add the wine to the wine list, and refresh the wine table after adding the wine
    private void saveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveProductActionPerformed

        // TODO add your handling code here:String serialN = txtSerialNum.getText();
        String wineName = txtWineName.getText();
        double alcohol = Double.parseDouble(txtAlcohol.getText());
        String country = txtCountry.getText();
        double price = Double.parseDouble(txtWinePrice.getText());
        String location = txtLocation.getText();
        String grape = txtGrape.getText();
        Integer in = Integer.parseInt(txtInventory.getText());
        /*if (chkPrime.isSelected()) {
            String types = "Prime";
        }*/

        Pattern pattern = Pattern.compile("[0-9]*");
        Pattern pattern3 = Pattern.compile("^[a-zA-Z]*\\s?[a-zA-Z]+$");
        Pattern pattern1 = Pattern.compile("^(([-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");

        Matcher num1 = pattern3.matcher(wineName);
        Matcher num2 = pattern3.matcher(country);
        Matcher num3 = pattern3.matcher(location);
        Matcher num4 = pattern3.matcher(grape);
        Matcher num5 = pattern1.matcher(txtAlcohol.getText());
        Matcher num6 = pattern1.matcher(txtWinePrice.getText());
        Matcher num7 = pattern1.matcher(txtInventory.getText());

//        if( !num1.matches() || !num2.matches() || !num3.matches() || !num4.matches()|| !num5.matches()|| !num6.matches()|| !num7.matches() ){
//            JOptionPane.showMessageDialog(null, "Please Enter an Valid Value", "ERROR", JOptionPane.ERROR_MESSAGE);
//           txtWineName.setText("");
//            txtAlcohol.setText("");
//            txtCountry.setText("");
//            txtWinePrice.setText("");
//            txtLocation.setText("");
//            txtGrape.setText("");
//            txtInventory.setText("");
//            // throw new IllegalArgumentException("Error");
//        }
//        else{
//        //Stemp.setAirplaneHistory(aph.getAirplaneHistory());
        Wine a = seller.addWine();
        //add a null wine
        a.setWineName(wineName);
        a.setAlcoholPercentage(alcohol);
        a.setGrape(grape);
        a.setLocation(location);
        a.setNumberofInventory(in);
        a.setOriginCountry(country);
        a.setPrice(price);
        a.setSeller(seller);
        JOptionPane.showMessageDialog(null, "Product Added Successfully");

        txtWineName.setText("");
        txtAlcohol.setText("");
        txtCountry.setText("");
        txtWinePrice.setText("");
        txtLocation.setText("");
        txtGrape.setText("");
        txtInventory.setText("");
        //setWineSeller(seller, wineName);
        populate();
    }//GEN-LAST:event_saveProductActionPerformed
    // remove the wine from the wine list
    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int index = tblProduct.getSelectedRow();
        if (index >= 0) {
            Wine a = (Wine) tblProduct.getValueAt(index, 0);
            seller.getWineList().remove(a);
            JOptionPane.showMessageDialog(null, "You have successfully deleted the product!");
            populate();
        } else {
            JOptionPane.showMessageDialog(null, "You need to select an Item!");
        }                    // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoveActionPerformed
    //By clicking thsi button, the application will set the status of the order to: refunded
    //the purpose of this button is to demostrate the implemenation of the return product feature
    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
//  int rowSelect = tblProduct.getSelectedRow();
//        if (rowSelect >= 0) {
//            Wine w = (Wine) tblProduct.getValueAt(rowSelect, 0);
//            viewWineDetailPanel vwdp = new viewWineDetailPanel(processPanel, w);
//            processPanel.add("viewWineDetail", vwdp);
//            CardLayout card = (CardLayout) processPanel.getLayout();
//            card.next(processPanel);
//        } else {
//            JOptionPane.showMessageDialog(null, "Please select any row");
//        }
//    }     

        int rowSelect = tblTransaction.getSelectedRow();
        Order oo = (Order) tblTransaction.getValueAt(rowSelect, 0);

        //
        if (rowSelect >= 0) {
            // b=odn
//            String b = (String) tblTransaction.getValueAt(rowSelect, 0);
//            String c = (String) tblTransaction.getValueAt(rowSelect, 6);
//            Order o1 = new Order();
//            for (Order o : seller.getTransactionList()) {
//                if (o.getOrderNum().equals(b)) {
//                    o1= o;
//                }
//            }
            //      String c1 = (String) tblTransaction.getValueAt(rowSelect, 6);
            String c = oo.getOrderStatus();
            if (c.equals("Return Pending")) {
                oo.setOrderStatus("Returned");
                populateOrder();
                for (Wine www : seller.getWineList()) {
                    if (www.getWineName().equals(oo.getWine().getWineName())) {
                        int a = www.getNumberofInventory() + oo.getQty();
                        www.setNumberofInventory(a);
                    }

                }
                JOptionPane.showMessageDialog(null, "Well done");
            } else if (c.equals("Payed")) {
                oo.setOrderStatus("Shiping");
                populateOrder();
                //setWineNumber(oo.getQty(),oo.getWine().getWineName());
                JOptionPane.showMessageDialog(null, "Well done");

            } else {
                JOptionPane.showMessageDialog(null, "No need to do anything");

            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }
        populateOrder();
        populate();
        for(Order o:business.getOrderList()){
        if(o.getOrderNum().equals(oo.getOrderNum())){
         o.setOrderStatus(oo.getOrderStatus());
        }}
// TODO add your handling code here:
    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnUpdateBasicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateBasicActionPerformed
        txtFirstName.setEnabled(true);
        txtDOB.setEnabled(true);
        txtLastName.setEnabled(true);
        txtEmail.setEnabled(true);
        txtGender.setEnabled(true);
        txtSSN.setEnabled(true);
        btnUpdateBasic.setEnabled(false);
        btnSaveBasic.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateBasicActionPerformed

    private void btnSaveBasicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBasicActionPerformed
        if (!txtFirstName.getText().equals("") & txtFirstName.getText().matches("^[a-zA-Z]*\\s?[a-zA-Z]+$")) {
            seller.setFirstName(txtFirstName.getText());
        }
        if (!txtDOB.getText().equals("") & txtDOB.getText().matches("mm/dd/yyyy")) {
            seller.setDOB(txtDOB.getText());
        }
        if (!txtLastName.getText().equals("") & txtLastName.getText().matches("^[a-zA-Z]*\\s?[a-zA-Z]+$")) {
            seller.setLastName(txtLastName.getText());
        }

        if (!txtEmail.getText().equals("")) {
            seller.setEmail(txtEmail.getText());

        }
        if (!txtGender.getText().equals("")) {
            seller.setGender(txtGender.getText());

        }
        if (!txtSSN.getText().equals("") & txtSSN.getText().matches("[0-9]*")) {
            seller.setSSN(Integer.parseInt(txtSSN.getText()));

        }
        JOptionPane.showMessageDialog(null, "Save Changes Successfully");
        txtFirstName.setEnabled(false);
        txtDOB.setEnabled(false);
        txtLastName.setEnabled(false);
        txtEmail.setEnabled(false);
        txtGender.setEnabled(false);
        txtSSN.setEnabled(false);
        btnUpdateBasic.setEnabled(true);
        btnSaveBasic.setEnabled(false);// TODO add your handling code here:
    }//GEN-LAST:event_btnSaveBasicActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        txtName.setEnabled(true);
        txtStreet1.setEnabled(true);
        txtCardNumber.setEnabled(true);
        txtStreet2.setEnabled(true);
        txtExpDate.setEnabled(true);
        txtCity.setEnabled(true);
        txtSecureCode.setEnabled(true);
        txtZip.setEnabled(true);
        txtState.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnSave.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (!txtSecureCode.getText().equals("") || !txtName.getText().equals("") || !txtCardNumber.getText().equals("") || !txtExpDate.getText().equals("")) {
            Bank b = new Bank();
            b.setNameOnCard(seller.getBank().getNameOnCard());
            b.setCardNumber(seller.getBank().getCardNumber());
            b.setSecureCode(seller.getBank().getSecureCode());
            b.setExpDate(seller.getBank().getExpDate());
            if (!txtSecureCode.getText().equals("")) {
                b.setSecureCode(Integer.parseInt(txtSecureCode.getText()));
            }
            if (!txtName.getText().equals("")) {
                b.setNameOnCard(txtName.getText());
            }
            if (!txtCardNumber.getText().equals("")) {
                b.setCardNumber(Integer.parseInt(txtCardNumber.getText()));
            }
            if (!txtExpDate.getText().equals("")) {
                b.setExpDate(txtExpDate.getText());
            }
            seller.setBank(b);
        }
        txtName.setEnabled(false);
        txtStreet1.setEnabled(false);
        txtCardNumber.setEnabled(false);
        txtStreet2.setEnabled(false);
        txtExpDate.setEnabled(false);
        txtCity.setEnabled(false);
        txtSecureCode.setEnabled(false);
        txtZip.setEnabled(false);
        txtState.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnSave.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Save Changes Successfully");// TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bankAccountPanel;
    private javax.swing.JPanel basicInfoPanel;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveBasic;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateBasic;
    private javax.swing.JButton btnUpdateShipping;
    private javax.swing.JCheckBox chkPrime;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel productPanel;
    private javax.swing.JButton saveProduct;
    private javax.swing.JTabbedPane sellerTabPane;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTable tblTransaction;
    private javax.swing.JPanel transactionPanel;
    private javax.swing.JTextField txtAlcohol;
    private javax.swing.JTextField txtCardNumber;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtExpDate;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtGrape;
    private javax.swing.JTextField txtInventory;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSSN;
    private javax.swing.JTextField txtSecureCode;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtStreet1;
    private javax.swing.JTextField txtStreet2;
    private javax.swing.JTextField txtWineName;
    private javax.swing.JTextField txtWinePrice;
    private javax.swing.JTextField txtZip;
    // End of variables declaration//GEN-END:variables
}
