package com.cafe.hopecafe.homePages;

import com.cafe.hopecafe.DatabaseConnection;
import com.cafe.hopecafe.booking.Booking;
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

public class customerHomePageController {
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

    public void initialize() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
//        Statement statement = connectDB.createStatement();
        Integer userId = UserData.getInstance().getUserid();
        PreparedStatement statement = connectDB.prepareStatement("SELECT * FROM bookings WHERE account_id = ?");
        statement.setInt(1, userId);

        try(ResultSet resultSet = statement.executeQuery()){
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
                Booking booking = new Booking(bookingId, tableNumber, date, time, noOfGuests, accountId, name, email, phone);
                bookings.add(booking);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }





// 2. Create an ObservableList from the list of Booking objects
        ObservableList<Booking> bookingData = FXCollections.observableArrayList(bookings);

// 3. Set the ObservableList as the items for the TableView
        bookingsTable.setItems(bookingData);

// 4. Bind the TableView columns to the properties of the Booking class


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
        noOfGuestsColumn.setCellValueFactory(new PropertyValueFactory<>("no_of_guests"));

        TableColumn<Booking, Integer> tableNo = (TableColumn<Booking, Integer>)
                bookingsTable.getColumns().get(4);
        noOfGuestsColumn.setCellValueFactory(new PropertyValueFactory<>("table_number"));

//        TableColumn<Booking, String> nameColumn = (TableColumn<Booking, String>) bookingsTable.getColumns().get(6);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    public void createBookingButtonOnAction(){
        BorderPane rootPane =RootBorderPaneHolder.getInstance().getRootPane();
        try {
            BorderPane fxmlLoader = FXMLLoader.load(getClass().getResource(FxmlPaths.CREATE_BOOKING_FXML));
            rootPane.getChildren().setAll(fxmlLoader);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void placeEatInOrderOnAction(){
        BorderPane rootPane =RootBorderPaneHolder.getInstance().getRootPane();
        try {
            AnchorPane fxmlLoader = FXMLLoader.load(getClass().getResource(FxmlPaths.ORDER_MENU_FXML));
            rootPane.getChildren().setAll(fxmlLoader);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


}

//public class Booking {
//    private LocalDate date;
//    private LocalTime time;
//    private int guests;
//
//    public Booking(LocalDate date, LocalTime time, int guests) {
//        this.date = date;
//        this.time = time;
//        this.guests = guests;
//    }
//
//    // Getters and setters
//}
