module com.example.rgb {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rgb to javafx.fxml;
    exports com.example.rgb;
}