package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Department Store Login");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();

        /*
        if(store.getListOfUsers().get(i).getClass().getSimpleName().equals("Admin")) {
                    Parent root = FXMLLoader.load(getClass().getResource("Application.fxml"));
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Store System Admin");
                    primaryStage.setScene(new Scene(root, 1000, 600));
                    primaryStage.show();
                    return;
                }
         */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
