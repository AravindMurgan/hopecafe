package com.cafe.hopecafe.orders;


import com.cafe.hopecafe.DatabaseConnection;
import com.cafe.hopecafe.homePages.OrderType;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;;

/**
 * This class contains methods for user to place or clear order.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */


public class PlaceOrderInCartController {


    @FXML
    private TableView tableView;
    ObservableList<Item> data;

    @FXML
    private Label totalPriceLabel;
    private Integer totalPrice = 0;

    private List<Item> cartItemList = new ArrayList<>();

    /**
     * This method initialise cart page with order table.
     * @param itemsList create item list
     */


    public void initialize(List<Item> itemsList) {


        TableColumn itemNameCol = new TableColumn("Item Name");
        TableColumn itemPriceCol = new TableColumn("Price");

        tableView.getColumns().addAll(itemNameCol, itemPriceCol);
        data = FXCollections.observableArrayList(itemsList);
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemName")
        );
        itemPriceCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemPrice")
        );

        for (Item item : itemsList) {
            String priceString = item.getItemPrice();
            int price = Integer.parseInt(priceString);
            totalPrice += price;
        }
        cartItemList.addAll(itemsList); // Merge new items with existing items
        tableView.setItems(data);
        totalPriceLabel.setText(String.valueOf(totalPrice)+" "+"(£)");
    }

    /**
     * This method creates connection to database and allows customer to place order.
     */

    public void placeOrderOnAction() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Integer user_id = UserData.getInstance().getUserid();
        String fullName = UserData.getInstance().getFirstName() + " " + UserData.getInstance().getLastName();
        StringBuilder insertQuery = new StringBuilder("INSERT INTO orders ( customer_name, order_date, total_price, order_status, customer_id, order_type, order_item_list) VALUES ");
        String orderType = OrderType.getInstance().getOrderType();
        // Create a comma-separated string of item names
        String orderItemList = cartItemList.stream()
                .map(Item::getItemName)
                .collect(Collectors.joining(", "));

        insertQuery.append("(")
                .append("'").append(fullName).append("', ")
                .append("'").append(formattedDateTime).append("', ")
                .append("'").append(totalPrice).append("', ") // assuming totalPrice is the total price for all items
                .append("'").append("Pending").append("', ")
                .append("'").append(user_id).append("', ")
                .append("'").append(orderType).append("', ")
                .append("'").append(orderItemList).append("'")
                .append(")");

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertQuery.toString());
            // Clear the cartItemList
            clearCartOnAction();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Order Placed Successfully");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        Stage stage = (Stage) tableView.getScene().getWindow();
                        stage.close();
                    });
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * This method is for user to clear cart.
     */
    public void clearCartOnAction(){
        totalPriceLabel.setText("(£)");
        data.clear();
        tableView.refresh();
    }

//    public void goBackOnAction(){
//        Routing routing = new Routing();
//        routing.routeTo(FxmlPaths.ORDER_MENU_FXML);
//    }

}
