package com.cafe.hopecafe.homePages;

import com.cafe.hopecafe.DatabaseConnection;
import com.cafe.hopecafe.booking.Booking;
import com.cafe.hopecafe.orders.OrderItem;
import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.RootBorderPaneHolder;
import com.cafe.hopecafe.utils.Routing;
import com.cafe.hopecafe.utils.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates customer home page pane and methods to control.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class CustomerHomePageController {
    @FXML
    private TableView bookingsTable;
    @FXML
    private TableView upcomingOrdersListTableView;
    @FXML
    private Label welcomeLabel;

    /**
     * This method initialise text in booking page.
     * @throws SQLException This throws SQLException.
     */

    public void initialize() throws SQLException {
            String firstName = UserData.getInstance().getFirstName();
            String lastName = UserData.getInstance().getLastName();

            welcomeLabel.setText("Welcome " + firstName + " " + lastName + "!");

            Integer userId = UserData.getInstance().getUserid();
            List<Booking> bookings = getBookingsForUser(userId);
            List<OrderItem> orders = getOrderedOrderItemsList(userId);

            ObservableList<Booking> bookingData = FXCollections.observableArrayList(bookings);

            bookingsTable.setItems(bookingData);

            bindTableColumns();

            ObservableList<OrderItem> orderedOrderItemsList = FXCollections.observableArrayList(orders);

            TableColumn orderId = new TableColumn("Order ID");
            TableColumn orderType = new TableColumn("Order Type");
            TableColumn name = new TableColumn("Name");
            TableColumn orderedOrderItems = new TableColumn("Ordered Items List");
            TableColumn dateAndTime = new TableColumn("Date and Time");
            TableColumn orderStatus = new TableColumn("Order Status");
            TableColumn totalPrice = new TableColumn("Total Price");



            upcomingOrdersListTableView.getColumns().addAll(orderId,orderType, name, orderedOrderItems, dateAndTime,orderStatus, totalPrice);


            orderId.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("orderId")
            );
            orderType.setCellValueFactory(
                new PropertyValueFactory<OrderItem,String>("orderType")
            );
            name.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("customerName")
            );
            orderedOrderItems.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("orderItemList")
            );
            dateAndTime.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("dateAndTime")
            );
            orderStatus.setCellValueFactory(
                    new PropertyValueFactory<OrderItem,String>("orderStatus")
            );
        totalPrice.setCellValueFactory(
                new PropertyValueFactory<OrderItem,String>("totalPrice")
        );


            upcomingOrdersListTableView.setItems(orderedOrderItemsList);


        }


    /**
     * This method get items that ordered by customer and put in order list.
     * @param userId pass in user ID from database.
     * @return List of things that ordered by customer.
     * @throws SQLException This throws SQLException.
     */



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

                Timestamp timestamp = resultSet.getTimestamp("order_date");
                LocalDateTime dateAndTime = timestamp != null ? (timestamp.toLocalDateTime())  : null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String formattedDateAndTime = dateAndTime != null ? dateAndTime.format(formatter) : null;

                String itemPrice = resultSet.getString("total_price");
                String orderStatus = resultSet.getString("order_status");
                String orderType = resultSet.getString("order_type");
                OrderItem item = new OrderItem(orderId,customerName, orderItemList,formattedDateAndTime, itemPrice, orderStatus, orderType);
                orderedOrderItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderedOrderItems;
    }

    /**
     * This method gets list of order booked by customer.
     * @param userId pass in user ID
     * @return value of customer's booking list
     * @throws SQLException This thorws SQLException.
     */

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
                    String bookingStatus = resultSet.getString("booking_status");
                    Booking booking = new Booking(bookingId,
                            tableNumber, date, time, noOfGuests, accountId, name, email, phone,bookingStatus);
                    bookings.add(booking);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return bookings;
        }

    /**
     * This method creates booking table that shows in booking page.
     */

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

        TableColumn<Booking, String> bookingStatus = (TableColumn<Booking, String>)
                bookingsTable.getColumns().get(5);
        bookingStatus.setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));


    }

    /**
     * This method directs user to create booking page on click.
     */

        public void createBookingButtonOnAction() {
            Routing routing = new Routing();
            routing.navigateToPage(FxmlPaths.CREATE_BOOKING_FXML);
        }

    /**
     * This method is to navigate to order menu.
     */

    public void navigateToOrderMenu(){
            Routing routing = new Routing();
            routing.navigateToPage(FxmlPaths.ORDER_MENU_FXML);
        }

    /**
     * This method is for user to select Eat In.
     */

    public void placeEatInOrderOnAction() {
            OrderType.getInstance().setOrderType("Eat_In");
            navigateToOrderMenu();
        }

    /**
     * This method is for user to select Takeaway.
     */

    public void placeTakeawayOrderOnAction() {
        OrderType.getInstance().setOrderType("Takeaway");
        navigateToOrderMenu();
    }

    /**
     * This method is for user to select Delivery.
     */

    public void requestDeliveryOnAction() {
        OrderType.getInstance().setOrderType("Delivery");
        navigateToOrderMenu();
    }

    public void logoutOnAction() {
        Routing routing = new Routing();
        routing.navigateToLoginPage();
    }

}


