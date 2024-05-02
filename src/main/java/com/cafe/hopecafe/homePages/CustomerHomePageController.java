package com.cafe.hopecafe.homePages;

import com.cafe.hopecafe.DatabaseConnection;
import com.cafe.hopecafe.booking.Booking;
import com.cafe.hopecafe.orders.OrderItem;
import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.RootBorderPaneHolder;
import com.cafe.hopecafe.utils.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerHomePageController {
    @FXML
    private TableView bookingsTable;
    @FXML
    private Button placeEatInOrderButton;
    @FXML
    private Button placeTakeawayOrderButton;
    @FXML
    private Button requestDeliveryButton;
    @FXML
    private Button createBookingButton;
    @FXML
    private TableView upcomingOrdersListTableView;

        // Other code...

        public void initialize() throws SQLException {
            Integer userId = UserData.getInstance().getUserid();
            List<Booking> bookings = getBookingsForUser(userId);
            List<OrderItem> orders = getOrderedOrderItemsList(userId);

            // Create an ObservableList from the list of Booking objects
            ObservableList<Booking> bookingData = FXCollections.observableArrayList(bookings);

            // Set the ObservableList as the items for the TableView
            bookingsTable.setItems(bookingData);

            // Bind the TableView columns to the properties of the Booking class
            bindTableColumns();

            ObservableList<OrderItem> orderedOrderItemsList = FXCollections.observableArrayList(orders);

            TableColumn orderId = new TableColumn("Order ID");
            TableColumn name = new TableColumn("Name");
            TableColumn orderedOrderItems = new TableColumn("Ordered Items List");
            TableColumn dateAndTime = new TableColumn("Date and Time");
            TableColumn orderStatus = new TableColumn("Order Status");
            TableColumn totalPrice = new TableColumn("Order Type");


            upcomingOrdersListTableView.getColumns().addAll(orderId, name, orderedOrderItems, dateAndTime,orderStatus, totalPrice);


            orderId.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("orderId")
            );
            name.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("customerName")
            );
            orderedOrderItems.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("orderItemList")
            );
            dateAndTime.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("itemPrice")
            );
            orderStatus.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("orderStatus")
            );
            totalPrice.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("orderType")
            );

            upcomingOrdersListTableView.setItems(orderedOrderItemsList);


        }

    private List<OrderItem> getOrderedOrderItemsList(Integer userId) throws SQLException {
        List<OrderItem> orderedOrderItems = new ArrayList<>();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        PreparedStatement statement = connectDB.prepareStatement("SELECT * FROM orders WHERE customer_id = ?");
        statement.setInt(1, userId);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String orderId = String.valueOf(resultSet.getInt("order_id"));
                String customerName = resultSet.getString("customer_name");
                String orderItemList = resultSet.getString("order_item_list");
                String itemPrice = resultSet.getString("total_price");
                String orderStatus = resultSet.getString("order_status");
                String orderType = resultSet.getString("order_type");
                OrderItem item = new OrderItem(orderId,customerName, orderItemList, itemPrice, orderStatus, orderType);
                orderedOrderItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderedOrderItems;
    }

        private List<Booking> getBookingsForUser(Integer userId) throws SQLException {
            List<Booking> bookings = new ArrayList<>();
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            PreparedStatement statement = connectDB.prepareStatement("SELECT * FROM bookings WHERE account_id = ?");
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int bookingId = resultSet.getInt("booking_id");
                    String tableNumber = resultSet.getString("table_number");
                    String date = resultSet.getString("date");
                    String time = resultSet.getString("time");
                    int noOfGuests = resultSet.getInt("no_of_guests");
                    int accountId = resultSet.getInt("account_id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    Booking booking = new Booking(bookingId, tableNumber, date, time, noOfGuests, accountId, name, email, phone,"");
                    bookings.add(booking);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return bookings;
        }

        private void bindTableColumns() {
            TableColumn<Booking, String> nameColumn = (TableColumn<Booking, String>)
                    bookingsTable.getColumns().get(0);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<Booking, String> dateColumn = (TableColumn<Booking, String>)
                    bookingsTable.getColumns().get(1);
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            TableColumn<Booking, String> timeColumn = (TableColumn<Booking, String>)
                    bookingsTable.getColumns().get(2);
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

            TableColumn<Booking, Integer> noOfGuestsColumn = (TableColumn<Booking, Integer>)
                    bookingsTable.getColumns().get(3);
            noOfGuestsColumn.setCellValueFactory(new PropertyValueFactory<>("noOfGuests"));

            TableColumn<Booking, Integer> tableNo = (TableColumn<Booking, Integer>)
                    bookingsTable.getColumns().get(4);
            tableNo.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        }


        public void createBookingButtonOnAction() {
            BorderPane rootPane = RootBorderPaneHolder.getInstance().getRootPane();
            try {
                BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource(FxmlPaths.CREATE_BOOKING_FXML));
                rootPane.getChildren().setAll(fxmlLoader);
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }

        public void placeEatInOrderOnAction() {
            BorderPane rootPane = RootBorderPaneHolder.getInstance().getRootPane();
            try {
                AnchorPane fxmlLoader = FXMLLoader.load(getClass().getResource(FxmlPaths.ORDER_MENU_FXML));
                rootPane.getChildren().setAll(fxmlLoader);
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }

}

