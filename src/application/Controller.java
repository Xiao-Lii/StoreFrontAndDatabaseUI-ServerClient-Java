package application;

import Users.StoreSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.*;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Controller{
    Client client;

    public Button loginButton;
    public TextField usernameTextField;
    public TextField passwordTextField;
    public Label loginLabel;
    public Label loginConfirmLabel;

    StoreSystem store = new StoreSystem();

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
}
