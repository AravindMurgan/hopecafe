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
/**
 * This contains Application and main method.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */
public class HelloApplication extends Application {
    /**
     *
     * Set and initialise primaryStage.
     * @throws IOException This throws IOException.
     * @throws SQLException This throws SQLException.
     * @param primaryStage parameter for homePage.
     */
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(FxmlPaths.LOGIN_FXML));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Hope Cafe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Method launches the programme when jvm calls main method.
     * @param args entrance of programme
     * */
    public static void main(String[] args) {
        launch();
    }
}