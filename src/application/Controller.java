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
import java.util.logging.SocketHandler;

public class Controller{
    Client client;

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

                Parent root = FXMLLoader.load(getClass().getResource("Application.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Department Store Application");
<<<<<<< Updated upstream
                primaryStage.setScene(new Scene(root, 1400, 800));
=======
                primaryStage.setScene(new Scene(root, 1000, 600));
>>>>>>> Stashed changes
                primaryStage.show();
                return;
            }
        }
        loginConfirmLabel.setText("Username or password incorrect.");
        loginConfirmLabel.setTextFill(Color.RED);
<<<<<<< Updated upstream
=======
    }

    public void createUser(ActionEvent actionEvent) {
        try {
            store.createUser(txtEmailAddress.getText(), txtPassword.getText(), txtUsername.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Account created successfully.");
            alert.show();
        }
        catch (IllegalArgumentException iae){
            Alert error = new Alert(Alert.AlertType.ERROR, "Error adding user: Email or display name already exists.");
            error.show();
        }
>>>>>>> Stashed changes
    }
}
