# Location_Based_E-commerce_Application
This is a Java Swing application imitated the real world location based B2C Trading System


## Introduction
The purpose of this assignment is to create an online wine shopping store, where users could browse the wine resellers in their state, place orders, track an order, or even return an order. For Sellers on the virtual mall, they could manage their wine inventory and manage their transactions. 
  
  
## Log in and account creation
At the start page, all users are promoted to sign in or create an account. When creating accounts, a user could choose to be a buyer or a seller, that is, a personal account, or a business account, then the user will be taken to the corresponding screens. At the log in screen, a buyer or a seller enters the login credential and the system will validate the user name/password combination, and then, check if the identification of the user (buyer or seller). 

A business class is used in this project to demonstrate the Singleton programming style and meanwhile, to have a better management over the classes and access what we need a lot easier. When the application runs, the first thing it does is to create an instance and only one single instance of the business class, whose contracture creates the instances of buyer array list class, seller array list class, and a global order class, which we will be using dealing with transactions. Buy using the business class, we could ensure that we only have one instance of buyer, seller and order array lists. 

Furthermore, once the business class is instantiated, it will invoke two methods to read a prebuilt CSV files with login credentials for buyers and sellers as well as a list of wines. 
  
## Seller’s functions

Once successfully logged in, a seller could see four tabs on the panel: manage wine catalog, manage transactions, manage personal info, and manage banking info. The manage wine catalog tab, allows the seller to add a wine, view wine detail, remove a wine, and see all the wines he/she owns. On the manage transaction tab, the seller is able to see the detailed information about each transaction, update shipping status, and issue a refund for a return.
  
## Buyer’s functions

Once successfully logged in as a buyer, a table with wines in his/her state will be populated, but again, only the wines whose available location is in the state where the buyer’s mailing address is. With that be saying, the we can promise a 2-hour shipping for each order place on our application. The user could also search for a specific wine or seller, add the selected wine to cart after selecting the quantity to purchase, use the 1-click checkout button as an express way, and of course, view the detail of a selected wine. 

In the Cart page, the user is able to view the wines currently in his/her cart, or move the wines to the wish list by clicking the Save for later button. Our checkout rule is, by default, the user has to check out all the wines in his/her shopping cart, otherwise, the wines have to be moved to the wish list and then added back to the cart again for the checkout process. 

By clicking the Checkout button in the Cart tab, the application will ask the user to confirm the shipping and the payment method and provides a chance for the user to ship to a different address or pay with a different card. Once the order is completed, the order table in the buyer’s My Orders tab, and the seller’s My Transaction tab will be updated, showing the status of the order is paid. After that, the seller could update the shipping status in his transaction table, buyers could start a return process, and the sellers could issue a refund for that return. All the activities about the order will be reflected on the both seller and buyer’s screens.

The logic behind this is that, whenever a buyer completes an order, the application will add one order to the buyer’s order array list, and add the same order to the global order array list in the business class. On the seller’s side, the application will retrieve that global order array list and match the orders issued under the current active seller and then show the order information. Any changes the seller makes on those orders will also be reflected on the buyer’s order list. 

## Summary
Several key points to be noticed in this assignment: the use of Singleton, which nearly changed the way we program from the start of this class, an architecture that makes the whole development process a lot more manageable. The second thing is the login functionality, so users could play different roles in the same application. The third thing is the real time order management system, where we could make the order information synchronized on both buyer and seller sides.  

## Existed Account:
Seller: Account:jianxi  Password:jianxi 

Buyer: Account:q  Password:q

More accounts you could find in the .csv file

