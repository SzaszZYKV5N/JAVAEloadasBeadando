module com.example.menu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;


    opens com.example.menu to javafx.fxml;
    exports com.example.menu;
}