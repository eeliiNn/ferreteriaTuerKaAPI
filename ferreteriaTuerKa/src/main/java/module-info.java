module org.example.ferreteriatuerka {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    requires java.net.http;
    requires com.google.gson;
    requires java.sql;


    opens org.example.ferreteriatuerka.controller to javafx.fxml;
    opens org.example.ferreteriatuerka.model to javafx.base, com.google.gson;

    exports org.example.ferreteriatuerka;
}
