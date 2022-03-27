package com.example.texto;

import javafx.css.Stylesheet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Locale;

public class HelloController {
    @FXML
    private TextArea area_texto;
    @FXML
    private Label count;
    @FXML
    private TextField word;
    @FXML
    private CheckBox check;
    @FXML
    private Spinner<Integer> FontSize;

    @FXML
    private void CountWords(){
        ArrayList<String> d = new ArrayList<String>();
        int count_word = 0;
        for (int i = 0; i < area_texto.getText().length(); i++){
            if (check.isSelected()){
                if(area_texto.getText().toUpperCase(Locale.ROOT).charAt(i) == word.getText().toUpperCase(Locale.ROOT).charAt(0) && i + word.getText().length() <= area_texto.getText().length()){
                    if (area_texto.getText().toUpperCase(Locale.ROOT).substring( i, i + word.getText().length()).contains(word.getText().toUpperCase(Locale.ROOT))){
                        count_word = count_word + 1;
                    }
                }
            }   else{
                if(area_texto.getText().charAt(i) == word.getText().charAt(0) && i + word.getText().length() <= area_texto.getText().length()){
                    if (area_texto.getText().substring( i, i + word.getText().length()).contains(word.getText())){
                        count_word = count_word + 1;
                    }
                }
            }
        }
        count.setText(String.valueOf(count_word));
    }

    @FXML
    private  void ChangeColorRed(){
        area_texto.setStyle("-fx-text-inner-color: red");
    }

    @FXML
    private  void ChangeColorGray(){
        area_texto.setStyle("-fx-text-inner-color: gray");
    }

    @FXML
    public void initialize(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
        valueFactory.setValue(16);
        FontSize.setValueFactory(valueFactory);
        area_texto.setFont(Font.font("arial", FontSize.getValue()));
    }

    @FXML
    private void ChangeFontSize(){
        area_texto.setFont(Font.font("Arial", FontSize.getValue()));
        area_texto.setStyle("-fx-text-alignment: left");
    }
}