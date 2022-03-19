package com.example.areatriangulo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private Label total;

    @FXML
    private void formula(){
        int a_num = Integer.parseInt(a.getText());
        int b_num = Integer.parseInt(b.getText());
        int c_num = Integer.parseInt(c.getText());
        if (a_num <= 0 | b_num <= 0 | c_num <= 0 | a_num > b_num + c_num | b_num > a_num + c_num | c_num > a_num + b_num){
            total.setTextFill(Color.web("#FF0000"));
            total.setText("Erro de calculo");
        }   else{
            double soma = (a_num + b_num + c_num)/2;
            double resultado = Math.sqrt(soma*(soma-a_num)*(soma-b_num)*(soma-c_num));
            total.setTextFill(Color.web("#e2ff0a"));
            total.setText(String.format("%.3f", resultado));
        }
    }
}