module org.example.knk_2025_gr12 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.knk_2025_gr12 to javafx.fxml;
    exports org.example.knk_2025_gr12;
}