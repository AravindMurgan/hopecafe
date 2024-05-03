package com.cafe.hopecafe.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

/**
 * This class is to create load fxml panes.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class FxmlLoaderUtil {

    /**
     * This method is to create load fxml panes.
     * @param fxmlFilePath file path
     * @param rootPane root pane
     */

    public static void loadFxmlIntoBorderPane(String fxmlFilePath, BorderPane rootPane) {
        try {
            BorderPane fxmlLoader = FXMLLoader.load(FxmlLoaderUtil.class.getResource(fxmlFilePath));
            rootPane.getChildren().setAll(fxmlLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

