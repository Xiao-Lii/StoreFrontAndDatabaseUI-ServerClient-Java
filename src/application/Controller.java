package application;

import Product.*;
import Users.*;
import javafx.scene.paint.Color;
import server.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.concurrent.ExecutionException;


public class Controller{
    // -------------- ADMIN APPLICATION --------------

    // START & CLOSE SERVER APP
    public Button btnLoadData;
    public Button btnStartServer;
    public Button btnCloseAdminApp;
    public Button btnSaveDatabase;
    public Button btnLoadDatabase;
    private MultiThreadServer server;

    // LOGIN WINDOW
    public Button loginButton;
    public TextField usernameTextField;
    public TextField passwordTextField;
    public Label loginLabel;
    public Label loginConfirmLabel;

    // CREATE A USER - ADMIN APP
    public Button btnCreateUser;
    public TextField txtEmailAddress;
    public TextField txtPassword;
    public TextField txtUsername;

    // CATEGORY MANAGEMENT - ADMIN APP
    public TextField addCatIdTextField;
    public TextField addCatNameTextField;
    public TextField addCatDescTextField;
    public Button addCatButton;
    public TextField removeCatIdTextField;
    public Button removeCatButton;

    // PRODUCT MANAGEMENT - ADMIN APP
    // ADD PRODUCT - NESTED TAB IN ADMIN APP
    public Tab tabProductManagement;
    public TextField txtProductID;
    public TextField txtProductName;
    public TextField txtBrandName;
    public TextField txtProductDesc;
    public TextField txtWarranty;
    public TextField txtIMEI;
    public TextField txtRAM;
    public TextField txtOS;
    public TextField txtHardDrive;
    public TextField txtAuthorName;
    public TextField txtNumPages;
    public TextField txtBookEdition;
    public DatePicker calDateIncorp;
    public DatePicker calPubDate;
    public TextField txtSerialNum;
    public TextField txtIntendedLoc;
    public ComboBox<Category> boxProdType;
    public Button btnAddElectronic;
    public Button btnAddCellphone;
    public Button btnAddComputer;
    public Button btnAddBook;
    public Button btnAddHome;
    public Button btnAddProduct;
    public Label LabelSerialNum;
    public Label LabelWarranty;
    public Label LabelIMEI;
    public Label LabelRAM;
    public Label LabelHardDrive;
    public Label LabelOS;
    public Label LabelAuthorName;
    public Label LabelPubDate;
    public Label labelNumPages;
    public Label LabelBookEd;
    public Label LabelIntendedLoc;
    public EventListener eventListener;

    // PRODUCT MANAGEMENT - ADMIN APP
    // REMOVE PRODUCT - NESTED TAB IN ADMIN APP
    public Tab tabRemoveProduct;
    public TextField txtRemoveProductByID;
    public Button btnRemoveProductByID;
    public ListView listOfProductsToRemove;
    public Button btnRemoveProductBySelection;

    // LIST ALL PRODUCTS TAB - ADMIN APP
    public ListView listOfProducts;
    public Tab tabProductList;

    //LIST ALL CATEGORIES TAB - ADMIN APP
    public ListView listOfCategories;
    public Tab tabCategoryList;

    // FINALIZED ORDER REPORT - ADMIN APP
    public Tab tabFinalizedOrderReport;
    public Button btnFinalizeOrder;
    public ListView listAllCustomerOrdersAdmin;
    public ListView listProductsInOrderAdmin;
    private Order order = new Order();
    private  Customer c = new Customer();

    // BROWSE CATEGORIES - CUSTOMER CATALOG APP
    public Tab tabBrowseCategories;
    public ListView listBrowseListOfCategories;
    public ListView listItemsInCategory;
    public Button btnAddItemToCart;

    // SEARCH FOR PRODUCT - CUSTOMER CATALOG APP
    public Tab tabSearchProduct;
    public ListView listOfSearchedProducts;

    private StoreSystem store = new StoreSystem();
    Client client;
    Category electronic = new Category("Electronic","Electronic",
            "Technological entertainment revolving around electronic devices and media.");
    Category computer = new Category("Computer","Computer", "Technological entertainment revolving around computers");
    //this.prodCategory.add(electronic)
    Category home = new Category("Home", "Home",
            "For decorating the household.");
    Category cellphone = new Category("Cellphone","Cellphone",
            "Technological entertainment revolving around personal cellphones");
    Category book = new Category("Book", "Book", "Paperback media entertainment.");


    // TESTING LOAD FROM FILE FUNCTION - ADMIN SIDE - LEE
    public void loadFromFile(ActionEvent actionEvent) {
        try {

            StoreSystem.loadFromFile();
            Alert success = new Alert(Alert.AlertType.CONFIRMATION,
                    "Success. The database has now been preloaded with data from the file.");
            success.show();
        }
        catch (Exception e){
            Alert error = new Alert(Alert.AlertType.ERROR, "Data could not be uploaded to the server.\n" +
                    "The server must be running to upload new data into the database.");
            error.show();
        }
    }

    // TESTING SAVE DATABASE TO FILE FUNCTION - ADMIN SIDE - LEE
    public void saveDatabaseToFile(ActionEvent actionEvent) {
        try {
            this.store.saveToFile();
            Alert success = new Alert(Alert.AlertType.CONFIRMATION,
                    "The database has been successfully saved.");
            success.show();
        }
        catch (Exception e){
            Alert error = new Alert(Alert.AlertType.ERROR, "The database could not be saved.");
            error.show();
        }
    }

    public void startServer(javafx.event.ActionEvent actionEvent) throws IOException {
        server = new MultiThreadServer(10000);
        new Thread(server).start();

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Store System Login");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();

        if (server.isServerClosed()) {
            System.out.println("STOPPING THE SERVER");
            server.stop();
        }
    }

    public void stopAdminApp(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btnCloseAdminApp.getScene().getWindow();
        stage.close();
    }

    public Controller(){
        this.boxProdType = new ComboBox<>();                    // INITIALIZING COMBO BOX OF DEFAULT CATEGORIES - ADMIN APP
        this.listOfCategories = new ListView<>();               // INITIALIZING LIST OF CATEGORIES - ADMIN APP
        this.listOfProducts = new ListView<>();                 // INITIALIZING LIST OF PRODUCTS - ADMIN APP
        this.listBrowseListOfCategories = new ListView<>();     // INITIALIZING LIST OF CATEGORIES - CUSTOMER APP
        this.listItemsInCategory = new ListView<>();            // INITIALIZING LIST OF ITEMS IN A CATEGORY - CUSTOMER APP
        this.listOfSearchedProducts = new ListView<>();         // INITIALIZING LIST OF SEARCH PRODUCT LIST - CUSTOMER APP
        client = new Client();
        client.connect();
    }

    public void updateDefaultCategoryBox(Event event) {
        if (this.tabProductManagement.isSelected()){
            boxProdType.setItems(FXCollections.observableArrayList(store.getListOfCategories()));
        }
    }

    public void initialize() {
        boxProdType.setItems(FXCollections.observableArrayList(store.getListOfCategories()));
        listOfProducts.setItems(FXCollections.observableArrayList(store.getCatalog()));
        listOfCategories.setItems(FXCollections.observableArrayList(store.getListOfCategories()));
        listBrowseListOfCategories.setItems(FXCollections.observableArrayList(store.getListOfCategories()));
        listOfSearchedProducts.setItems(FXCollections.observableArrayList(store.getCatalog()));

        // FOR ADMIN APP - ADD PRODUCT TAB - PRODUCT DEFAULT CATEGORY SELECTION OPTIONS
        boxProdType.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->
                // BELOW IS THE CODE WE EXECUTE WHEN OUR DEFAULT PRODUCT CATEGORY IS SELECTED
                //System.out.println(newValue)
                viewOptionsOnly()
        );
        listBrowseListOfCategories.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) ->
                // BELOW IS THE CODE WE EXECUTE WHEN OUR DEFAULT PRODUCT CATEGORY IS SELECTED
                // NEW VALUE IS OUR NEW SELECTED CATEGORY
                updateListOfItems(newValue)
                //System.out.println(newValue)
                //listItemsInCategory.setItems(FXCollections.observableArrayList(store.getProductsInCategory(newValue)))
        );
    }

    public void updateListOfItems(Object category){
        listItemsInCategory.setItems(FXCollections.observableArrayList(store.getProductsInCategory(category)));
    }

    /*
       public void listProductsToRemove(Event event){
            if (this.tabRemoveProduct.isSelected()){
                this.listOfProductsToRemove.setItems(FXCollections.observableArrayList(store.getCatalog()));
            }
        }*/

    public void login(javafx.event.ActionEvent actionEvent) throws Exception {
        for(int i = 0; i < store.getListOfUsers().size(); i ++) {
            if ((usernameTextField.getText().equalsIgnoreCase(store.getListOfUsers().get(i).getDisplayName()) |
                    usernameTextField.getText().equalsIgnoreCase(store.getListOfUsers().get(i).getEmail())) &&
                    passwordTextField.getText().equals(store.getListOfUsers().get(i).getPassword())) {
                loginConfirmLabel.setText("Login Successful.");
                loginConfirmLabel.setTextFill(Color.GREEN);

                if(store.getListOfUsers().get(i).getClass().getSimpleName().equals("Admin")) {
                    Parent root = FXMLLoader.load(getClass().getResource("Application.fxml"));
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Store System Admin");
                    primaryStage.setScene(new Scene(root, 1000, 600));
                    primaryStage.show();
                    return;
                }
                else if(store.getListOfUsers().get(i).getClass().getSimpleName().equals("Customer")) {
                    Parent root = FXMLLoader.load(getClass().getResource("CatalogApp.fxml"));
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Store System Catalog");
                    primaryStage.setScene(new Scene(root, 1000, 600));
                    primaryStage.show();
                    return;
                }
            }
        }
        loginConfirmLabel.setText("Username or password incorrect.");
        loginConfirmLabel.setTextFill(Color.RED);
    }

    public void createUser(javafx.event.ActionEvent actionEvent) {
        try {
            store.createUser(txtEmailAddress.getText(), txtPassword.getText(), txtUsername.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Account created successfully.");
            alert.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding user: Email or display name already exists.");
            error.show();
        }
    }

    public void addCategory() {
        try {
            store.addCategory(addCatIdTextField.getText(), addCatNameTextField.getText(), addCatDescTextField.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Category created successfully.");
            alert.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding category: Category already exists.");
            error.show();
        }
    }

    public void removeCategory() {
        try {
            store.removeCategory(removeCatIdTextField.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Category removed successfully.");
            alert.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error removing category: Category does not exist.");
            error.show();
        }
    }

    public void addProduct(ActionEvent actionEvent){
        try {
            // CHECK FOR GENERAL PRODUCTS
            if (txtProductID.getText() != "" && txtProductName.getText() != "" && txtBrandName.getText() != "" &&
                    txtProductDesc.getText() != "" && calDateIncorp.getValue() != null){
                // CHECK FOR ADD CELLPHONE
                if (txtSerialNum.getText() != "" && txtWarranty.getText() != "" && txtIMEI.getText() != "" &&
                        txtOS.getText() != "") {
                    store.addCellphone(txtProductID.getText(), txtProductName.getText(),
                            txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                            txtSerialNum.getText(), txtWarranty.getText(), txtIMEI.getText(), txtOS.getText());
                    clearInputs();
                    Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Cellphone has been added successfully.");
                    success.show();
                }

                // CHECK FOR COMPUTER
                else if (txtSerialNum.getText() != "" && txtWarranty.getText() != "" && txtRAM.getText() != "" &&
                            txtHardDrive.getText() != "") {
                        store.addComputer(txtProductID.getText(), txtProductName.getText(),
                                txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                                txtSerialNum.getText(), txtWarranty.getText(), txtRAM.getText(), txtHardDrive.getText());
                        clearInputs();
                    Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Computer has been added successfully.");
                    success.show();
                }

                // CHECK FOR ELECTRONIC
                else if (txtSerialNum.getText() != "" && txtWarranty.getText() != "") {
                    store.addElectronic(txtProductID.getText(), txtProductName.getText(),
                            txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                            txtSerialNum.getText(), txtWarranty.getText());
                    clearInputs();
                    Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Electronic has been added successfully.");
                    success.show();
                }

                // CHECK FOR BOOK
                else if (txtAuthorName.getText() != "" && calPubDate.getValue() != null && txtNumPages.getText() != "" &&
                        txtBookEdition.getText() != "") {
                    store.addBook(txtProductID.getText(), txtProductName.getText(),
                            txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                            txtAuthorName.getText(), calPubDate.getValue(), Integer.parseInt(txtNumPages.getText()),
                            Integer.parseInt(txtBookEdition.getText()));
                    clearInputs();
                    Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Book has been added successfully.");
                    success.show();
                }

                // CHECK FOR HOME PRODUCT
                 else if (txtIntendedLoc.getText() != "") {
                        store.addHome(txtProductID.getText(), txtProductName.getText(),
                                txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                                txtIntendedLoc.getText());
                     clearInputs();
                    Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Home Product has been added successfully.");
                    success.show();
                    }

                else{
                    store.addProduct(boxProdType.getValue(), txtProductID.getText(), txtProductName.getText(),
                            txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(), txtSerialNum.getText(),
                            txtWarranty.getText(), txtIMEI.getText(), txtOS.getText(), txtRAM.getText(), txtHardDrive.getText(),
                            txtAuthorName.getText(), calPubDate.getValue(), Integer.parseInt(txtNumPages.getText()),
                            Integer.parseInt(txtBookEdition.getText()), txtIntendedLoc.getText());
                    Alert success = new Alert(Alert.AlertType.CONFIRMATION, "General has been added successfully.");
                    success.show();
                    clearInputs();
                }
            }
            else
                throw new IllegalArgumentException();
        /*
        txtProductID.clear();txtProductName.clear();txtBrandName.clear();txtProductDesc.clear();calDateIncorp.setValue(null);
        txtSerialNum.clear();txtWarranty.clear();txtIMEI.clear();txtOS.clear();txtRAM.clear();txtHardDrive.clear();
        txtAuthorName.clear();calPubDate.setValue(null);txtNumPages.clear();txtBookEdition.clear();txtIntendedLoc.clear();
         */
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding product. Please try again.");
            error.show();
        }
    }

    public void addGeneralProduct (ActionEvent actionEvent){
        try {
            store.addProduct(boxProdType.getValue(), txtProductID.getText(), txtProductName.getText(),
                    txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(), txtSerialNum.getText(),
                    txtWarranty.getText(), txtIMEI.getText(), txtOS.getText(), txtRAM.getText(), txtHardDrive.getText(),
                    txtAuthorName.getText(), calPubDate.getValue(), Integer.parseInt(txtNumPages.getText()),
                    Integer.parseInt(txtBookEdition.getText()), txtIntendedLoc.getText());
            Alert success = new Alert(Alert.AlertType.CONFIRMATION, "General has been added successfully.");
            success.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding product. Please try again.");
            error.show();
        }
    }

    public void clearInputs(){
        txtProductID.clear();txtProductName.clear();txtBrandName.clear();txtProductDesc.clear();calDateIncorp.setValue(null);
        txtSerialNum.clear();txtWarranty.clear();txtIMEI.clear();txtOS.clear();txtRAM.clear();txtHardDrive.clear();
        txtAuthorName.clear();calPubDate.setValue(null);txtNumPages.clear();txtBookEdition.clear();txtIntendedLoc.clear();
    }

    public void viewOptionsOnly(){
        if (this.boxProdType.equals(electronic)){
            LabelIMEI.disableProperty();
            txtIMEI.disableProperty();
            LabelOS.disableProperty();
            txtOS.disableProperty();
            LabelRAM.disableProperty();
            txtRAM.disableProperty();
            LabelHardDrive.disableProperty();
            txtHardDrive.disableProperty();
            LabelAuthorName.disableProperty();
            txtAuthorName.disableProperty();
            LabelPubDate.disableProperty();
            calPubDate.disableProperty();
            labelNumPages.disableProperty();
            txtNumPages.disableProperty();
            LabelBookEd.disableProperty();
            txtBookEdition.disableProperty();
            LabelIntendedLoc.disableProperty();
            txtIntendedLoc.disableProperty();
        }
    }

    public void addElectronic(ActionEvent actionEvent) {
        try {
            store.addElectronic(txtProductID.getText(), txtProductName.getText(),
                    txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                    txtSerialNum.getText(), txtWarranty.getText());
            clearInputs();
            Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Product has been added successfully.");
            success.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding product. Please try again.");
            error.show();
        }
    }

    public void addCellphone(ActionEvent actionEvent) {
        try {
        store.addCellphone(txtProductID.getText(), txtProductName.getText(),
                txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                txtSerialNum.getText(), txtWarranty.getText(), txtIMEI.getText(), txtOS.getText());
        clearInputs();
            Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Product has been added successfully.");
            success.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding product. Please try again.");
            error.show();
        }
    }

    public void addComputer(ActionEvent actionEvent) {
        try {
        store.addComputer(txtProductID.getText(), txtProductName.getText(),
                txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                txtSerialNum.getText(), txtWarranty.getText(), txtRAM.getText(), txtHardDrive.getText());
        clearInputs();
            Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Product has been added successfully.");
            success.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding product. Please try again.");
            error.show();
        }
    }

    public void addBook(ActionEvent actionEvent) {
        try {
        store.addBook(txtProductID.getText(), txtProductName.getText(),
                txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                txtAuthorName.getText(), calPubDate.getValue(), Integer.parseInt(txtNumPages.getText()),
                Integer.parseInt(txtBookEdition.getText()));
            clearInputs();
            Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Product has been added successfully.");
            success.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding product. Please try again.");
            error.show();
        }
    }

    public void addHome(ActionEvent actionEvent) {
        try {
        store.addHome(txtProductID.getText(), txtProductName.getText(),
                txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                txtIntendedLoc.getText());
            clearInputs();
            Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Product has been added successfully.");
            success.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding product. Please try again.");
            error.show();
        }
    }

    public void removeProductByID(ActionEvent actionEvent) {
        try {
            store.removeProductByID(txtRemoveProductByID.getText());
            this.listOfProductsToRemove.setItems(FXCollections.observableArrayList(store.getCatalog()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Product removed successfully.");
            alert.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Product could not be removed. \n" +
                    "Please verify that the Product ID matches with the product you'd like to remove.");
            error.show();
        }
    }

    public void custOrderList(ActionEvent actionEvent) {
        store.countDuplicateItems(this.txtUsername.getText());
    }

    public void finalizeOrder(ActionEvent actionEvent) {
        // WE NEED TO PASS THE CUSTOMER'S VALUE & THEIR ORDER THAT THEY'RE TRYING TO FINALIZE
        //store.finalizeOrder(this.txtUsername.getText(), order.getOrderNum());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Order Finalized!");
        alert.show();
    }

    // LIST ALL PRODUCTS - ADMIN SIDE
    public void listProducts(Event event){
        if (this.tabProductList.isSelected()){
            this.listOfProducts.setItems(FXCollections.observableArrayList(store.getCatalog()));
        }
    }

    // LIST ALL PRODUCTS - SEARCH FOR A PRODUCT CUSTOMER CATALOG SIDE
    public void updateSearchList(Event event){
        // WHEN SEARCH FOR A PRODUCT TAB IS SELECTED - UPDATE LIST OF PRODUCTS
        if (this.tabSearchProduct.isSelected()){
            this.listOfSearchedProducts.setItems(FXCollections.observableArrayList(store.getCatalog()));
        }

        // NOW WHEN SOMETHING IS SEARCHED BY NAME, WE WANT TO UPDATE THE LIST TO SHOW RELEVANT PRODUCTS
        // MAYBE WE CAN ACHIEVE THIS BY GETTING A STRING OF PRODUCT NAME AND USING CONTAINS
    }

    public void listProductsToRemove(Event event){
        if (this.tabRemoveProduct.isSelected()){
            this.listOfProductsToRemove.setItems(FXCollections.observableArrayList(store.getCatalog()));
        }
    }

    // LIST CATEGORIES - ADMIN SIDE
    public void listCategories(Event event){
        if (this.tabCategoryList.isSelected()){
            this.listOfCategories.setItems(FXCollections.observableArrayList(store.getListOfCategories()));
        }
    }

    // LIST ALL CUSTOMER ORDERS - ADMIN SIDE
    public void listAllCustomerOrders(Event event){
        if (this.tabFinalizedOrderReport.isSelected()) {
            if (store.getFinalizedListOfCustOrders().equals(null))
                throw new IllegalArgumentException();
            else
                this.listAllCustomerOrdersAdmin.setItems(FXCollections.observableArrayList(store.getFinalizedListOfCustOrders()));
        }
    }

    // LIST ALL CATEGORIES FOR CUSTOMER - CUSTOMER CATALOG APP SIDE
    public void listAllCategories(Event event) {
        if (this.tabBrowseCategories.isSelected()){
            this.listBrowseListOfCategories.setItems(FXCollections.observableArrayList(store.getListOfCategories()));
        }
    }


    // BROWSE CATEGORY - CUSTOMER SIDE APP - ADD ITEM TO CART
    public void addItemToCart(ActionEvent actionEvent) {
        // CHECK IF CUSTOMER HAS ANY OPEN ORDERS
            // IF YES = ADD ITEM TO CURRENT OPEN ORDER
            // IF NO = CALL CREATE ORDER FOR USER
    }

    // LIST ALL PRODUCTS - ADMIN SIDE
    public void listAllProducts(Event event){
        if (this.tabProductList.isSelected()){
            this.listOfProducts.setItems(FXCollections.observableArrayList(store.getCatalog()));
        }
    }


}
