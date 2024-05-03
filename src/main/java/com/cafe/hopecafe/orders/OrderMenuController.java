package com.cafe.hopecafe.orders;

import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.Routing;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods for order page button control.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class OrderMenuController {

    /**
     * This method directs user to starter page.
     */

    public void startersOnAction(){
        String category ="starter";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);

    }

    /**
     * This method directs user to main course page.
     */

    public void mainsOnAction(){
        String category ="Main";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);
    }

    /**
     * This method directs user to sides page.
     */

    public void sidesOnAction(){
        String category ="Sides";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);
    }

    /**
     * This method directs user to drinks page.
     */

    public void drinksOnAction(){
        String category ="Drink";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);
    }

    /**
     * This method directs user to dessert page.
     */

    public void dessertOnAction(){
        String category ="Dessert";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);
    }




}
