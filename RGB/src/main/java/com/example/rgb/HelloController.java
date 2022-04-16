package com.example.rgb;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane backgroud;
    @FXML
    private Label RedLabel;
    @FXML
    private Label BlueLabel;
    @FXML
    private  Label GreenLabel;
    @FXML
    private Slider SliderREd;
    @FXML
    private Slider SliderGreen;
    @FXML
    private Slider SliderBlue;

    @FXML
    private void initialize(){
        SliderREd.setMax(255);
        SliderBlue.setMax(255);
        SliderGreen.setMax(255);
        backgroud.setStyle("-fx-background-color: #000");
    }

    @FXML
    private void ChangeBackgroudColor(){
        int red = (int) SliderREd.getValue();
        int green = (int) SliderGreen.getValue();
        int blue = (int) SliderBlue.getValue();
        RedLabel.setText(" -> " + String.valueOf(red) + " <- ");
        GreenLabel.setText(" -> " + String.valueOf(green) + " <- ");
        BlueLabel.setText(" -> " + String.valueOf(blue) + " <- ");

        String cor = String.format("rgb(%d,%d,%d);", red, green, blue);
        String LabelRedColor = String.format("rgb(%d,0,0);", red);
        String LabelGreenColor = String.format("rgb(0,%d,0);", green);
        String LabelBlueColor = String.format("rgb(0,0,%d);", blue);

        RedLabel.setStyle("-fx-background-color: " + LabelRedColor);
        GreenLabel.setStyle("-fx-background-color: " + LabelGreenColor);
        BlueLabel.setStyle("-fx-background-color: " + LabelBlueColor);

        backgroud.setStyle("-fx-background-color: " + cor);
    }
}