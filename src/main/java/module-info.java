/**
 * This contains module needed for this app.
 */

module com.cafe.hopecafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires MaterialFX;

    opens com.cafe.hopecafe to javafx.fxml;
    exports com.cafe.hopecafe;
    exports com.cafe.hopecafe.homePages;
    opens com.cafe.hopecafe.homePages to javafx.fxml;
    exports com.cafe.hopecafe.booking;
    opens com.cafe.hopecafe.booking to javafx.fxml;
    exports com.cafe.hopecafe.utils;
    opens com.cafe.hopecafe.utils to javafx.fxml;

    exports com.cafe.hopecafe.orders;
    opens com.cafe.hopecafe.orders to javafx.fxml;

}