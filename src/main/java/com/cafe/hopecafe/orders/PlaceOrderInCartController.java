package com.cafe.hopecafe.orders;


import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;;


public class PlaceOrderInCartController {


    @FXML
    private TableView tableView;
    ObservableList<Item> data;

    @FXML
    private Label totalPriceLabel;
    private Integer totalPrice=0;



    public void initialize(List<Item> itemsList)  {

        TableColumn itemNameCol = new TableColumn("Item Name");
        TableColumn itemPriceCol = new TableColumn("Price");

        tableView.getColumns().addAll(itemNameCol, itemPriceCol );
        data = FXCollections.observableArrayList(itemsList);
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<Item,String>("itemName")
        );
        itemPriceCol.setCellValueFactory(
                new PropertyValueFactory<Item,String>("itemPrice")
        );

        for (Item item : itemsList) {
         String priceString = item.getItemPrice();
         int price = Integer.parseInt(priceString);
            totalPrice += price;
        }

        tableView.setItems(data);
        totalPriceLabel.setText(String.valueOf(totalPrice));
    }

    public void placeOrderAction(){

    }

    public void clearCartOnAction(){
        data.clear();
        tableView.refresh();
    }

}
