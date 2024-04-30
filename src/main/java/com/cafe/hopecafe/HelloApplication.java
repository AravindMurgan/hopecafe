package com.cafe.hopecafe;

import com.cafe.hopecafe.orders.AddItemToCartController;
import com.cafe.hopecafe.utils.FxmlPaths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(FxmlPaths.LOGIN_FXML));
        Parent root = fxmlLoader.load();
//        FXMLLoader fxmlLoader = new
//        FXMLLoader(HelloApplication.class.getResource("orders/addItemsToCart.fxml"));
//
//
//        AddItemToCartController controller = fxmlLoader.getController();
//        controller.initialize("Grill_Meat");


        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Hope Cafe");
        primaryStage.setScene(scene);
        primaryStage.show();

//        controller.setPrimaryStage(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}