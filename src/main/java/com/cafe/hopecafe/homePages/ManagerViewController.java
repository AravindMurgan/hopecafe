package com.cafe.hopecafe.homePages;

import com.cafe.hopecafe.DatabaseConnection;
import com.cafe.hopecafe.booking.Booking;
import com.cafe.hopecafe.orders.Item;
import com.cafe.hopecafe.orders.OrderItem;
import com.cafe.hopecafe.utils.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ManagerViewController {

    @FXML
    private TableView<Booking> bookingStatusTableView;
    ObservableList<Booking> data;
    List<Booking> itemsIdList = new ArrayList<>();

    public void initialize() throws SQLException {
        List<Booking> bookingsData = getBookingsData();

        // TODO
        TableColumn name = new TableColumn("Name");
        TableColumn date = new TableColumn("Date");
        TableColumn time = new TableColumn("Time");
        TableColumn tableNumber = new TableColumn("Table Number");
        TableColumn noOfGuests = new TableColumn("No Of Guests");
        TableColumn status = new TableColumn("Status");
        TableColumn phoneNumber = new TableColumn("Phone Number");
        TableColumn checkBoxCol = new TableColumn("Select Bookings");


        bookingStatusTableView.getColumns().addAll(name, date, time, tableNumber, noOfGuests, status, phoneNumber,checkBoxCol);

        data = FXCollections.observableArrayList(bookingsData);

        name.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("name")
        );
        date.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("date")
        );
        time.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("time")
        );
        tableNumber.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("tableNumber")
        );
        noOfGuests.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("noOfGuests")
        );
        status.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("bookingStatus")
        );
        phoneNumber.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("phone")
        );
        checkBoxCol.setCellValueFactory(
                new PropertyValueFactory<Booking, String>("checkBox")
        );

        bookingStatusTableView.setItems(data);
    }


    public List<Booking> getBookingsData() {

        List<Booking> bookings = new ArrayList<>();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        PreparedStatement statement = null;
        try {
            statement = connectDB.prepareStatement("SELECT * FROM bookings");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
                String status = resultSet.getString("booking_status"); // new field
                Booking booking = new Booking(bookingId, tableNumber, date, time,
                        noOfGuests, accountId, name, email, phone, status, ""); // modified constructor
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }



public void approveBookingOnAction() {
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    for (Booking item : bookingStatusTableView.getItems()) {
        if (item.getCheckBox().isSelected()) {
            System.out.println("booking Id: " + item.getBookingId());

            // Update booking_status to 'pending' for the selected booking
            String updateStatusSql = "UPDATE bookings SET booking_status = ? WHERE booking_id = ?";
            try {
                PreparedStatement preparedStatement = connectDB.prepareStatement(updateStatusSql);
                preparedStatement.setString(1, "APPROVED");
                preparedStatement.setInt(2, item.getBookingId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Show popup
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Booking Status");
    alert.setHeaderText(null);
    alert.setContentText("Booking Approved Successfully");

    // Refresh the TableView
    if (alert.showAndWait().get() == ButtonType.OK) {
        bookingStatusTableView.getItems().clear();
        bookingStatusTableView.getItems().addAll(getBookingsData());
    }
}

public void rejectBookingOnAction() {
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    for (Booking item : bookingStatusTableView.getItems()) {
        if (item.getCheckBox().isSelected()) {
            System.out.println("booking Id: " + item.getBookingId());

            // Update booking_status to 'rejected' for the selected booking
            String updateStatusSql = "UPDATE bookings SET booking_status = ? WHERE booking_id = ?";
            try {
                PreparedStatement preparedStatement = connectDB.prepareStatement(updateStatusSql);
                preparedStatement.setString(1, "REJECTED");
                preparedStatement.setInt(2, item.getBookingId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Show popup
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Booking Status");
    alert.setHeaderText(null);
    alert.setContentText("Booking Rejected Successfully");

    // Refresh the TableViewd
    if (alert.showAndWait().get() == ButtonType.OK) {
        bookingStatusTableView.getItems().clear();
        bookingStatusTableView.getItems().addAll(getBookingsData());
    }
}

}


