package application;

import Product.Category;
import Users.StoreSystem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.*;
import Users.Customer;
import Users.Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.EventListener;


public class Controller{
    // -------------- ADMIN APPLICATION --------------

    // CREATE A USER
    public Button btnCreateUser;
    public TextField txtEmailAddress;
    public TextField txtPassword;
    public TextField txtUsername;

    // CATEGORY MANAGEMENT
    public TextField addCatIdTextField;
    public TextField addCatNameTextField;
    public TextField addCatDescTextField;
    public Button addCatButton;
    public TextField removeCatIdTextField;
    public Button removeCatButton;

    // PRODUCT MANAGEMENT
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

    // FINALIZED ORDER REPORT
    public TextField Orderlist;
    public Button checkOut;
    private Order order = new Order();
    private  Customer c = new Customer();

    Client client;

    // LOGIN WINDOW
    public Button loginButton;
    public TextField usernameTextField;
    public TextField passwordTextField;
    public Label loginLabel;
    public Label loginConfirmLabel;

    private StoreSystem store = new StoreSystem();

    public Controller(){
        this.boxProdType = new ComboBox<Category>();
        client = new Client();
        client.connect();
    }

    public void initialize(){
        this.boxProdType.setItems(FXCollections.observableArrayList(store.getListOfCategories()));
    }

    public void login(javafx.event.ActionEvent actionEvent) throws Exception {
        for(int i = 0; i < store.getListOfUsers().size(); i ++) {
            if ((usernameTextField.getText().equals(store.getListOfUsers().get(i).getDisplayName()) |
                    usernameTextField.getText().equals(store.getListOfUsers().get(i).getEmail())) &&
                    passwordTextField.getText().equals(store.getListOfUsers().get(i).getPassword())) {
                loginConfirmLabel.setText("Login Successful.");
                loginConfirmLabel.setTextFill(Color.GREEN);

                if(store.getListOfUsers().get(i).getClass().getSimpleName().equals("Admin")) {
                    Parent root = FXMLLoader.load(getClass().getResource("Application.fxml"));
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Department Store Admin");
                    primaryStage.setScene(new Scene(root, 1000, 600));
                    primaryStage.show();
                    return;
                }else if(store.getListOfUsers().get(i).getClass().getSimpleName().equals("Customer")) {
                    Parent root = FXMLLoader.load(getClass().getResource("CatalogApp.fxml"));
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Department Store Catalog");
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

    public void addElectronic(ActionEvent actionEvent) {
        try {
            store.addElectronic(boxProdType.getTypeSelector(), txtProductID.getText(), txtProductName.getText(),
                    txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                    txtSerialNum.getText(), txtWarranty.getText());
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
        store.addCellphone(boxProdType.getTypeSelector(), txtProductID.getText(), txtProductName.getText(),
                txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                txtSerialNum.getText(), txtWarranty.getText(), txtIMEI.getText(), txtOS.getText());
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
        store.addComputer(boxProdType.getTypeSelector(), txtProductID.getText(), txtProductName.getText(),
                txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                txtSerialNum.getText(), txtWarranty.getText(), txtRAM.getText(), txtHardDrive.getText());
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
        store.addBook(boxProdType.getTypeSelector(), txtProductID.getText(), txtProductName.getText(),
                txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                txtAuthorName.getText(), calPubDate.getValue(), Integer.parseInt(txtNumPages.getText()),
                Integer.parseInt(txtBookEdition.getText()));
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
        store.addHome(boxProdType.getTypeSelector(), txtProductID.getText(), txtProductName.getText(),
                txtBrandName.getText(), txtProductDesc.getText(), calDateIncorp.getValue(),
                txtIntendedLoc.getText());
            Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Product has been added successfully.");
            success.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding product. Please try again.");
            error.show();
        }
    }

    public void custOrderList(ActionEvent actionEvent) {
        store.countDuplicateItems(this.txtUsername.getText());
    }

    public void finalizeOrder(ActionEvent actionEvent) {
        store.finalizeOrder(this.txtUsername.getText(), order.getOrderNum());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Order Finalized!");
        alert.show();
    }


}
