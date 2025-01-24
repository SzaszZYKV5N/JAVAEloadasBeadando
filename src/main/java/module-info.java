module com.example.menu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires java.sql;

    requires jakarta.xml.ws;
requires  gson;
    requires httpcore;
    requires httpclient;
requires org.joda.time;
    requires java.persistence;


    opens com.example.menu to javafx.fxml;
    exports com.example.menu;


    opens com.example.mnbapi to com.sun.xml.bind;


}