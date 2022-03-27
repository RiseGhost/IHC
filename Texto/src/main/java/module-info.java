module com.example.texto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.texto to javafx.fxml;
    exports com.example.texto;
}