package com.cafe.hopecafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
//        Parent root = fxmlLoader.load();
//        LoginController controller = fxmlLoader.getController();
        Scene scene = new Scene(fxmlLoader.load(), 800, 427);
        primaryStage.setTitle("Hope Cafe");
        primaryStage.setScene(scene);
        primaryStage.show();

//        controller.setPrimaryStage(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}