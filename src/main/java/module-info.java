module com.example.menu {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.naming;
    requires java.sql;
    requires jakarta.persistence;
    opens com.example.menu to javafx.fxml;
    exports com.example.menu;


}