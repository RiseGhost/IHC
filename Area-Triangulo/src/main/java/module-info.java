module com.example.areatriangulo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.areatriangulo to javafx.fxml;
    exports com.example.areatriangulo;
}