package com.cafe.hopecafe.utils;

import javafx.scene.layout.BorderPane;

/**
 * This class creates root border pane.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class RootBorderPaneHolder {
    private static RootBorderPaneHolder instance;
    private BorderPane rootPane;

    /**
     * This is a private constructor to prevent instantiation.
     */
    private RootBorderPaneHolder() {
        //
    }

    /**
     * This method is to get instance in root border pane.
     * @return value of instance
     */

    public static RootBorderPaneHolder getInstance() {
        if (instance == null) {
            instance = new RootBorderPaneHolder();
        }
        return instance;
    }

    /**
     * getter for root pane
     * @return value of root pane
     */

    public BorderPane getRootPane() {
        return rootPane;
    }

    /**
     * setter for root pane
     * @param rootPane set root pane
     */

    public void setRootPane(BorderPane rootPane) {
        this.rootPane = rootPane;
    }
}
