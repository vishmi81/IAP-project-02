module com.example.demofx2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.demofx2 to javafx.fxml;
    exports com.example.demofx2;
}