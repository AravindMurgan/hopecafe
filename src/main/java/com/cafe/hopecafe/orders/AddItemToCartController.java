package com.cafe.hopecafe.orders;

import com.cafe.hopecafe.DatabaseConnection;
import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.RootBorderPaneHolder;
import com.cafe.hopecafe.utils.Routing;
import com.cafe.hopecafe.utils.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddItemToCartController {

    @FXML
    private TableView<Item> tableView;
    ObservableList<Item> data;

    List<Item> itemsList = new ArrayList<>();

    public void initialize(String type) throws SQLException {

        TableColumn itemNameCol = new TableColumn("Item Name");
        TableColumn itemPriceCol = new TableColumn("Price");
        TableColumn selectItemsCol = new TableColumn("Select Items");

        List<Item> itemsList = new ArrayList<>();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String itemType =type;
        PreparedStatement statement =
                connectDB.prepareStatement("SELECT * FROM menu WHERE type = ?");
        statement.setString(1, itemType);

        try(ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                String itemName = resultSet.getString("name");
                BigDecimal priceDecimal = resultSet.getBigDecimal("price");
                String itemPrice = priceDecimal.toString();
                Item item = new Item(itemName, itemPrice,"");
                itemsList.add(item);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        tableView.getColumns().addAll(itemNameCol, itemPriceCol, selectItemsCol );

        data = FXCollections.observableArrayList(itemsList);

        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<Item,String>("itemName")
        );
        itemPriceCol.setCellValueFactory(
                new PropertyValueFactory<Item,String>("itemPrice")
        );

        selectItemsCol.setCellValueFactory(
                new PropertyValueFactory<Item, CheckBox>("checkBox")
        );

        tableView.setItems(data);
    }

    public void addToCartOnAction(){
        for (Item item : tableView.getItems()) {
            if (item.getCheckBox().isSelected()) {
                System.out.println("Person: " + item.getItemName() + " " + item.getItemPrice());
                // Add item to cart (you can implement this logic)
                itemsList.add(item);
            }
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION
                , "Items added to cart successfully");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    new Routing().routeToPlaceOrderInCartController(FxmlPaths.PLACE_ORDER_IN_CART_FXML,itemsList);
                });
    }

    public void goBackOnAction(){
        new Routing().routeTo(FxmlPaths.ORDER_MENU_FXML);
    }
}
