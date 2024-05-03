package com.cafe.hopecafe.homePages;

import com.cafe.hopecafe.DatabaseConnection;
import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.Routing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is to remove staff control pane and methods to remove staffs.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class RemoveStaffController {

    @FXML
    private TableView<User> tableView;

    private ObservableList<User> data;

    /**
     * initializer for staff controller.
     */

    public void initialize() {
        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> userTypeColumn = new TableColumn<>("User Type");
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));

        TableColumn<User, String> selectStaff = new TableColumn("Select Staffs");
        selectStaff.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        tableView.getColumns().addAll(usernameColumn, userTypeColumn,selectStaff);

        data = FXCollections.observableArrayList();
        tableView.setItems(data);

        fetchStaffData();
    }

    /**
     * This creates connection to database and fetch staff data.
     */

    private void fetchStaffData() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String fetchStaff = "SELECT username, user_type FROM user_account WHERE user_type IN ('CHEF', 'WAITER')";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(fetchStaff);

            while (queryResult.next()) {
                String username = queryResult.getString("username");
                String userType = queryResult.getString("user_type");

                User user = new User(username, userType,"");
                data.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /**
     * This is a method create button to remove staff on click.
     */

    public void removeStaffOnAction() {
        List<User> usersToRemove = new ArrayList<>();
        for (User user : data) {
            if (user.getCheckBox().isSelected()) {
                usersToRemove.add(user);
                removeUserFromDatabase(user);
            }
        }
        data.removeAll(usersToRemove);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Selected staff members have been removed!");

        alert.showAndWait();

        tableView.refresh();
    }

    /**
     * This is a method create button to return to manager page on click.
     */
    public void goBackOnAction(){
        Routing routing = new Routing();
        routing.navigateToPage(FxmlPaths.MANAGER_VIEW_FXML);
    }

    /**
     * This is a method to remove user from database.
     * @param user
     */
    private void removeUserFromDatabase(User user) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String removeUser = "DELETE FROM user_account WHERE username = '" + user.getUsername() + "'";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(removeUser);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}