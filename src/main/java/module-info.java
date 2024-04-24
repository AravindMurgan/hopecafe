module com.cafe.hopecafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens com.cafe.hopecafe to javafx.fxml;
    exports com.cafe.hopecafe;
}