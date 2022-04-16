package com.example.calc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label subtotal;
    @FXML
    private Label total;
    int sinais = 0; //contador de sinais

    //Calcula o resultado:
    private void Calcular() {
        String[] parts = subtotal.getText().split("[+\\-x/=]");
        int result = 0;
        for (int i = 1; i < subtotal.getText().length() - 1 ; i++) {
            switch (subtotal.getText().charAt(i)) {
                case '+':
                    result = Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]);
                    subtotal.setText(subtotal.getText().replace(parts[0] + "+" + parts[1], String.valueOf(result)));
                    break;
                case '-':
                    result = Integer.parseInt(parts[0]) - Integer.parseInt(parts[1]);
                    subtotal.setText(subtotal.getText().replace(parts[0] + "-" + parts[1], String.valueOf(result)));
                    break;
                case 'x':
                    result = Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
                    System.out.println(result);
                    subtotal.setText(subtotal.getText().replace(parts[0] + "x" + parts[1], String.valueOf(result)));
                    break;
                case '/':
                    if (Integer.parseInt(parts[1]) == 0) {
                        subtotal.setText("Erro");
                    } else {
                        result = Integer.parseInt(parts[0]) / Integer.parseInt(parts[1]);
                        subtotal.setText(subtotal.getText().replace(parts[0] + "/" + parts[1], String.valueOf(result)));
                        break;
                    }
            }
        }
    }

    @FXML private void Button9() {subtotal.setText(subtotal.getText() + "9");}
    @FXML private void Button8() {subtotal.setText(subtotal.getText() + "8");}
    @FXML private void Button7() {subtotal.setText(subtotal.getText() + "7");}
    @FXML private void Button6() {subtotal.setText(subtotal.getText() + "6");}
    @FXML private void Button5() {subtotal.setText(subtotal.getText() + "5");}
    @FXML private void Button4() {subtotal.setText(subtotal.getText() + "4");}
    @FXML private void Button3() {subtotal.setText(subtotal.getText() + "3");}
    @FXML private void Button2() {subtotal.setText(subtotal.getText() + "2");}
    @FXML private void Button1() {subtotal.setText(subtotal.getText() + "1");}
    @FXML private void Button0() {subtotal.setText(subtotal.getText() + "0");}
    @FXML private void ButtonPlus() {
        if (subtotal.getText().charAt(subtotal.getText().length() - 1) != '+' && subtotal.getText().charAt(subtotal.getText().length() - 1) != 'x' && subtotal.getText().charAt(subtotal.getText().length() - 1) != '/' && subtotal.getText().charAt(subtotal.getText().length() - 1) != '-') {
            subtotal.setText(subtotal.getText() + "+");
            sinais++;
            if (sinais > 1) {Calcular();}
        }
    }
    @FXML private void ButtonMinus() {
        if (subtotal.getText().charAt(subtotal.getText().length() - 1) != '+' && subtotal.getText().charAt(subtotal.getText().length() - 1) != 'x' && subtotal.getText().charAt(subtotal.getText().length() - 1) != '/' && subtotal.getText().charAt(subtotal.getText().length() - 1) != '-') {
            subtotal.setText(subtotal.getText() + "-");
            sinais++;
            if (sinais > 1) {
                Calcular();
            }
        }
    }
    @FXML private void ButtonMultiply() {
        if (subtotal.getText().charAt(subtotal.getText().length() - 1) != '+' && subtotal.getText().charAt(subtotal.getText().length() - 1) != 'x' && subtotal.getText().charAt(subtotal.getText().length() - 1) != '/' && subtotal.getText().charAt(subtotal.getText().length() - 1) != '-') {
            subtotal.setText(subtotal.getText() + "x");
            sinais++;
            if (sinais > 1) {Calcular();}
        }
    }
    @FXML private void ButtonDivide() {
        if (subtotal.getText().charAt(subtotal.getText().length() - 1) != '+' && subtotal.getText().charAt(subtotal.getText().length() - 1) != 'x' && subtotal.getText().charAt(subtotal.getText().length() - 1) != '/' && subtotal.getText().charAt(subtotal.getText().length() - 1) != '-') {
            subtotal.setText(subtotal.getText() + "/");
            sinais++;
            if (sinais > 1) {Calcular();}
        }
    }
    @FXML private void ButtonResul() {
        Calcular();
        sinais = 0;
        total.setText(subtotal.getText());
    }
    @FXML private void ButtonClear() {subtotal.setText("");}
}