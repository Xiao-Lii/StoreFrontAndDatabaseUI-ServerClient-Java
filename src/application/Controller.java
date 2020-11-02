package application;

import Users.StoreSystem;
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
    public ComboBox boxProdType;
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
        client = new Client();
        client.connect();
    }

    public void login(javafx.event.ActionEvent actionEvent) throws Exception {
        for(int i = 0; i < store.getListOfUsers().size(); i ++) {
            if (usernameTextField.getText().equals(store.getListOfUsers().get(i).getDisplayName()) &&
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
    }

    public void addCellphone(ActionEvent actionEvent) {
    }

    public void addComputer(ActionEvent actionEvent) {
    }

    public void addBook(ActionEvent actionEvent) {
    }

    public void addHome(ActionEvent actionEvent) {
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
