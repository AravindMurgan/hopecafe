package com.cafe.hopecafe.utils;

import com.cafe.hopecafe.HelloApplication;
import com.cafe.hopecafe.orders.AddItemToCartController;
import com.cafe.hopecafe.orders.Item;
import com.cafe.hopecafe.orders.PlaceOrderInCartController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * This class is for navigation functions.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class Routing {

    /**
     * This method is for navigating to home page.
     * @param path page to goto
     */

    public void navigateToHomePage(String path) {
        BorderPane rootPane = RootBorderPaneHolder.getInstance().getRootPane();
        try {
            BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource(path));
            rootPane.getChildren().setAll(fxmlLoader);
        } catch (Exception e) {
            e.printStackTrace();
            // It's advisable to handle the exception appropriately,
            // depending on the requirements of your application.
        }
    }

    /**
     * This method is for navigating to a certain page.
     * @param path page to goto
     */

    public void routeTo(String path){
        BorderPane rootPane = RootBorderPaneHolder.getInstance().getRootPane();
        try {
//            BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource(path));
            FXMLLoader fxmlLoader = new FXMLLoader(Routing.class.getResource(path));
            Parent root = fxmlLoader.load();
            rootPane.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
            // It's advisable to handle the exception appropriately,
            // depending on the requirements of your application.
        }

    }

    /**
     * This method is for navigating to a new page.
     * @param path page to goto
     */

    public void routeToNewWindow(String path) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader
                    (HelloApplication.class.getResource(path));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root, 800, 427);
            stage.setTitle("Hope Cafe");
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * This method is for navigating to add item to cart page.
     * @param path page to goto
     * @param itemType Which type of item in menu you want to go.
     */


    public void routeToAddItemToCartController(String path,String itemType){
        BorderPane rootPane = RootBorderPaneHolder.getInstance().getRootPane();
        try {
//            BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource(path));
            FXMLLoader fxmlLoader = new FXMLLoader(Routing.class.getResource(path));
            Parent root = fxmlLoader.load();
            AddItemToCartController controller = fxmlLoader.getController();
            controller.initialize(itemType);
            rootPane.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
            // It's advisable to handle the exception appropriately,
            // depending on the requirements of your application.
        }

    }

    /**
     * This method is for navigating to place order page.
     * @param path page to goto
     * @param list Show order list.
     */

    public void routeToPlaceOrderInCartController(String path, List<Item> list){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Routing.class.getResource(path));
            Parent root = fxmlLoader.load();
            PlaceOrderInCartController controller = fxmlLoader.getController();
            controller.initialize(list);
            Stage stage = new Stage();
            Scene scene = new Scene(root, 900, 600);
            stage.setTitle("Cart");
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * This method is for navigating to login page.
     */


    public void navigateToLoginPage() {
        BorderPane rootPane = RootBorderPaneHolder.getInstance().getRootPane();
        try {
            BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource("/com/cafe/hopecafe/login.fxml"));
            if(rootPane != null) {
                rootPane.getChildren().setAll(fxmlLoader);
            } else {
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
