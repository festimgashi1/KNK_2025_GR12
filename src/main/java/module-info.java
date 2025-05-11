module org.example.knk_2025_gr12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    opens controller to javafx.fxml;
    exports controller;


    opens model to javafx.base;
    opens org.example.knk_2025_gr12 to javafx.fxml;
    exports org.example.knk_2025_gr12;
    exports App;
    exports services;
}