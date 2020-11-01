package application;

import Users.StoreSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.*;

import javax.xml.soap.Text;
import java.awt.*;

public class Controller{
    // -------------- ADMIN APPLICATION --------------

    // CREATE A USER
    public Button btnCreateUser;
    public TextField txtEmailAddress;
    public TextField txtPassword;
    public TextField txtUsername;

    Client client;

    // -------------- LOGIN APPLICATION --------------
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
        if(usernameTextField.getText().equals("username") && passwordTextField.getText().equals("password")) {
            loginConfirmLabel.setText("Login Successful.");
            loginConfirmLabel.setTextFill(Color.GREEN);

            Parent root = FXMLLoader.load(getClass().getResource("Application.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Department Store Application");
            primaryStage.setScene(new Scene(root, 1400, 800));
            primaryStage.show();
        }else{
            loginConfirmLabel.setText("Username or password incorrect.");
            loginConfirmLabel.setTextFill(Color.RED);
        }
    }

    public void createUser(ActionEvent actionEvent) {
        store.createUser(this.txtEmailAddress.getText(), this.txtPassword.getText(), this.txtUsername.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Account created successfully.");
        alert.show();
    }
}
