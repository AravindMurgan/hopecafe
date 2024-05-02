package com.cafe.hopecafe.homePages;

import com.cafe.hopecafe.DatabaseConnection;
import com.cafe.hopecafe.orders.OrderItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WaiterViewController{

    @FXML
    private TableView<OrderItem> orderStatusTableView;
    ObservableList<OrderItem> data;

    public void initialize() throws SQLException {
        List<OrderItem> ordersData = getOrdersData();

        // TODO
        TableColumn orderIdCol = new TableColumn("Order Id");
        TableColumn customerNameCol = new TableColumn("Customer Name");
        TableColumn orderItemListCol = new TableColumn("Orders List");
        TableColumn timeCol = new TableColumn("Date and Time");
        TableColumn totalPriceCol = new TableColumn("Total Price");
        TableColumn orderStatusCol = new TableColumn("Order Status");
        TableColumn orderTypeCol = new TableColumn("Order Type");
        TableColumn selectOrdersCol = new TableColumn("Select Orders");

        orderStatusTableView.getColumns().addAll(orderIdCol,selectOrdersCol,orderStatusCol, customerNameCol,orderItemListCol, timeCol,
                totalPriceCol, orderTypeCol);

        data = FXCollections.observableArrayList(ordersData);

        orderIdCol.setCellValueFactory(
                new PropertyValueFactory<OrderItem, String>("orderId")
        );
        selectOrdersCol.setCellValueFactory(
                new PropertyValueFactory<OrderItem, String>("checkBox")
        );
        orderStatusCol.setCellValueFactory(
                new PropertyValueFactory<OrderItem, String>("orderStatus")
        );
        customerNameCol.setCellValueFactory(
                new PropertyValueFactory<OrderItem, String>("customerName")
        );
        orderItemListCol.setCellValueFactory(
                new PropertyValueFactory<OrderItem, String>("orderItemList")
        );
        timeCol.setCellValueFactory(
                new PropertyValueFactory<OrderItem, String>("formattedOrderDate")
        );
        totalPriceCol.setCellValueFactory(
                new PropertyValueFactory<OrderItem, String>("totalPrice")
        );
        orderTypeCol.setCellValueFactory(
                new PropertyValueFactory<OrderItem, String>("orderType")
        );


        orderStatusTableView.setItems(data);
    }


    public List<OrderItem> getOrdersData() {

        List<OrderItem> orders = new ArrayList<>();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        PreparedStatement statement = null;
        try {
            statement = connectDB.prepareStatement("SELECT * FROM orders");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String orderId=String.valueOf(resultSet.getInt("order_id"));
                String customerName = resultSet.getString("customer_name");
                String orderItemList = resultSet.getString("order_item_list");
                Timestamp timestamp = resultSet.getTimestamp("order_date");
                LocalDateTime orderDate = timestamp.toLocalDateTime();
                String totalPrice = resultSet.getString("total_price");
                String orderStatus = resultSet.getString("order_status");
                String orderType = resultSet.getString("order_type");
                OrderItem order = new OrderItem(orderId, customerName,orderItemList, orderDate, totalPrice, orderStatus, orderType,"");
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }



    public void approveOrderItemOnAction() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        for (OrderItem item : orderStatusTableView.getItems()) {
            if (item.getCheckBox().isSelected()) {
                System.out.println("order Id: " + item.getOrderId());

                // Update order_status to 'pending' for the selected order
                String updateStatusSql = "UPDATE orders SET order_status = ? WHERE order_id = ?";
                try {
                    PreparedStatement preparedStatement = connectDB.prepareStatement(updateStatusSql);
                    preparedStatement.setString(1, "APPROVED");
                    preparedStatement.setInt(2, Integer.parseInt( item.getOrderId()));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Show popup
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order Status");
        alert.setHeaderText(null);
        alert.setContentText("Order Approved Successfully");

        // Refresh the TableView
        if (alert.showAndWait().get() == ButtonType.OK) {
            orderStatusTableView.getItems().clear();
            orderStatusTableView.getItems().addAll(getOrdersData());
        }
    }

    public void rejectOrderItemOnAction() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        for (OrderItem item : orderStatusTableView.getItems()) {
            if (item.getCheckBox().isSelected()) {
                System.out.println("order Id: " + item.getOrderId());

                // Update order_status to 'rejected' for the selected order
                String updateStatusSql = "UPDATE orders SET order_status = ? WHERE order_id = ?";
                try {
                    PreparedStatement preparedStatement = connectDB.prepareStatement(updateStatusSql);
                    preparedStatement.setString(1, "REJECTED");
                    preparedStatement.setInt(2,Integer.parseInt( item.getOrderId()));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Show popup
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order Status");
        alert.setHeaderText(null);
        alert.setContentText("Order Rejected Successfully");

        // Refresh the TableViewd
        if (alert.showAndWait().get() == ButtonType.OK) {
            orderStatusTableView.getItems().clear();
            orderStatusTableView.getItems().addAll(getOrdersData());
        }
    }

}


