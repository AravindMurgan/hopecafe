package com.cafe.hopecafe.utils;

import javafx.scene.layout.BorderPane;

public class RootBorderPaneHolder {
    private static RootBorderPaneHolder instance;
    private BorderPane rootPane;

    private RootBorderPaneHolder() {
        // Private constructor to prevent instantiation
    }

    public static RootBorderPaneHolder getInstance() {
        if (instance == null) {
            instance = new RootBorderPaneHolder();
        }
        return instance;
    }

    public BorderPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(BorderPane rootPane) {
        this.rootPane = rootPane;
    }
}
