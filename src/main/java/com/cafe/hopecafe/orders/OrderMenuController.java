package com.cafe.hopecafe.orders;

import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.Routing;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class OrderMenuController {



    public void startersOnAction(){
        String category ="starter";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);

    }

    public void mainsOnAction(){
        String category ="Main";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);
    }

    public void sidesOnAction(){
        String category ="Sides";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);
    }

    public void drinksOnAction(){
        String category ="Drink";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);
    }

    public void dessertOnAction(){
        String category ="Dessert";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);
    }

    public void meatMainOnAction(){
        String category ="Meat_Main";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);
    }

    public void grillMeatOnAction(){
        String category ="Grill_Meat";
        new Routing().routeToAddItemToCartController
                (FxmlPaths.ADD_ITEMS_TO_CART_FXML,category);
    }

    public void goBackOnAction(){
        new Routing().navigateToPage(FxmlPaths.CUSTOMER_HOME_PAGE);
    }




}
