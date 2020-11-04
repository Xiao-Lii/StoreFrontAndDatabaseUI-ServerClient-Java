## JavaPAOne - CSCI 3920 Programming Assignment for PA#1

### Program Summary
Upon the start of the program, it will prompt the user if they'd like to:
1. Load Database from a File
2. Start Server

<b>Note for Option 1: At the moment we currently don't have a pre-saved database file with a full databse that can be used to load into the database! If you attempt to load the database before starting the server, an error occurs on terminal. Only after the server has been started, does the program seem to have no issues with saving the database and loading the database.</b>

If prompted to 'Start the Server,' a login window will appear without prompting the user for an IP address or port # and will connect the user automatically; depending on the account type, it will prompt different windows to appear. This window will remain open and open new threads/connections when signing in other admin or customer accounts.

<p><b>IMPORTANT NOTE:</b> Only an admin account can manipulate the data for the store system and if the program detects a 'Admin' login, it will only prompt the Admin Application. If the system detects a Customer has signed in, it will prompt the Customer Catalog Application. An Admin account will not be able access or view the catalog application. The same goes vice versa for a Customer Account, they may only view the Store Catalog Application. For convenience, there will be only one pre-built Admin Account which will have all the options to manipulate products, categories, users, orders, etc. There will also be one pre-built customer account that may only access the Catalog Application. </p>

<b>Admin Username: admin ------------------- Admin Password: password

Customer Username: user ------------------- Customer Password: pw </b>

### Notes for Admin Application

1. No visible issues with creating / removing a user from the database.
2. Product Management Tab is semi-functional - Refer to more notes on Product Management Below
3. Product Category Management is functional - Can create/add categories and remove existing categories by Category ID.
4. Finalized Order Report - Method has been written in place to update and display all Finalized Orders but due to not having any pre-set data to load in, we can't fully verify this.
5. Terminate Server - Saving the database appears to be functional when server is running but currently do not have an exact way to terminate the server without closing out the entire program. 

### Notes for Product Management
Products that are added through the designated Default Category buttons at the bottom (Appear to have no issue with adding products to catalog and have thorough error handling). However, trying to add general products through the 'Add General Product' button for User-added Categories is bringing up issues and does not have a fully-functional error handling system. Otherwise, removing products by ID seems entirely successful and will update the list beside it when successful. 

### Notes for Customer Catalog GUI

Must of the Customer GUI is not complete nor functional due to the nature of not being able to pre-load in data. 

### ----------- Team Members -----------
    Xiao-Lii    -   Lee Phonthongsy
    rinv12      -   Rin(Loureen) Viloria 
    rrk01       -   Rizzul(Ryan Karki)
