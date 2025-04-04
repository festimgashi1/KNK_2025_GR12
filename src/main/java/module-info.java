module org.example.knk_2025_gr12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.knk_2025_gr12 to javafx.fxml;
    exports org.example.knk_2025_gr12;
}