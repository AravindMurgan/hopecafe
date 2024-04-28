package com.cafe.hopecafe.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class FxmlLoaderUtil {

    public static void loadFxmlIntoBorderPane(String fxmlFilePath, BorderPane rootPane) {
        try {
            BorderPane fxmlLoader = FXMLLoader.load(FxmlLoaderUtil.class.getResource(fxmlFilePath));
            rootPane.getChildren().setAll(fxmlLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

