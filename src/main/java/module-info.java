module com.cafe.hopecafe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cafe.hopecafe to javafx.fxml;
    exports com.cafe.hopecafe;
}