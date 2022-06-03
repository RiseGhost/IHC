package com.example.paint;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class HelloController {
    @FXML
    private Canvas canvas;
    @FXML
    private Slider sizeBar;
    @FXML
    private TextField sizeBarText;
    @FXML
    private Slider border;
    @FXML
    private Slider SaturationSlider;
    @FXML
    private Slider BrightnessSlider;
    @FXML
    private Slider ContrastSlider;
    @FXML
    private Slider RotateSlider;
    @FXML
    private Slider FractalRed;
    @FXML
    private Slider FractalGreen;
    @FXML
    private Slider FractalBlue;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private RadioButton rubber;
    @FXML
    private RadioButton circle;
    @FXML
    private RadioButton triangle;
    @FXML
    private RadioButton quadrangle;
    @FXML
    private RadioButton ampulheta;
    @FXML
    private RadioButton move;
    @FXML
    private RadioButton Balde;
    @FXML
    private RadioButton CircleBrush;
    @FXML
    private RadioButton SquareBrush;
    @FXML
    private RadioButton Fractal;
    @FXML
    private AnchorPane Pane;
    @FXML
    private AnchorPane CanvasPane;
    @FXML
    private ToggleButton line;
    @FXML
    private RadioButton Text;

    private TextInputDialog dialog = new TextInputDialog(); //MSG BOX
    private ImageView image = new ImageView();
    int click = 0; //Conta o número de clicks
    String texto; //String onde fica guardado o texto que será escrito no canvas
    private Color Cor;

    //Retorna o máximo entre dois números:
    private double max(double a, double b) {
        if (a > b) {
            return a;
        }   else {
            return b;
        }
    }

    //Retorna o mínimo entre dois números:
    private double min(double a, double b) {
        if (a < b) {
            return a;
        }   else {
            return b;
        }
    }

    private double modulo(double a, double b) {
        if (a - b < 0) {
            return - (a - b);
        }   else {
            return a - b;
        }
    }

    //Retorna o ponto media entre dois pontos:
    private Cordenadas PontoMedia(Cordenadas a, Cordenadas b) {
        Cordenadas media = new Cordenadas(0,0);
        media.setX((a.getX() + b.getX()) / 2);
        media.setY((a.getY() + b.getY()) / 2);
        return media;
    }

    private double DistanciaEntrePontos(Cordenadas a, Cordenadas b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    //Retorna a subtração entre dois números:
    private double sub(double a, double b) {
        return a - b;
    }

    //Verifica se o texto é um número, se não for, retorna false:
    private boolean NumberString(String s) {
        char c;
        for (int i = 0; i < s.length(); i++) {
            for( c = 'A'; c <= 'z'; c++) {
                if (s.charAt(i) == c) {
                    return false;
                }
            }
        }
        return true;
    }

    //Desanha uma linha entre dois pontos:
    private void drawLine(double x1, double y1, double x2, double y2, double size, Canvas layer) {
        GraphicsContext corrds = layer.getGraphicsContext2D();
        double slope = (y2 - y1) / (x2 - x1);
        double y3 = y1 - (slope * x1);
        if(modulo(min(x1, x2), max(x1, x2)) > 1) {
            for (double x3 = min(x1, x2); x3 <= max(x1, x2); x3 = x3 + 0.05) {
                double y4 = y3 + x3 * slope;
                corrds.fillOval(x3, y4, size, size);
            }
        }   else{
            for (double y4 = min(y1, y2); y4 <= max(y1, y2); y4 = y4 + 0.05) {
                corrds.fillOval(x1, y4, size, size);
            }
        }
    }

    //Apaga uma linha entre dois pontos:
    private void clearLine(double x1, double y1, double x2, double y2, double size, Canvas layer) {
        GraphicsContext corrds = layer.getGraphicsContext2D();
        double slope = (y2 - y1) / (x2 - x1);
        double y3 = y1 - (slope * x1);
        if(modulo(min(x1, x2), max(x1, x2)) > 1) {
            for (double x3 = min(x1, x2); x3 <= max(x1, x2); x3 = x3 + 0.05) {
                double y4 = y3 + x3 * slope;
                corrds.clearRect(x3, y4, size, size);
            }
        }   else{
            for (double y4 = min(y1, y2); y4 <= max(y1, y2); y4 = y4 + 0.05) {
                corrds.clearRect(x1, y4, size, size);
            }
        }
    }

    //Abre uma janela para selecionar um arquivo e retorna o caminho:
    private String FilePath(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Background Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpg")
        );
        File file = fileChooser.showOpenDialog(Pane.getScene().getWindow());
        return file.getAbsolutePath();
    }

    //Criar uma nova camada:
    private Canvas CreateLayer(){
        Canvas layer = new Canvas(Window.getWindows().get(0).getWidth(), Window.getWindows().get(0).getHeight() - 125);
        layer.setLayoutY(canvas.getLayoutY() + 62);
        layer.setLayoutX(canvas.getLayoutX());
        layer.setTranslateX(canvas.getTranslateX());
        layer.setTranslateY(canvas.getTranslateY());
        layer.setRotate(canvas.getRotate());
        layer.getGraphicsContext2D().setFill(colorPicker.getValue());
        return  layer;
    }

    //Coloca o tamanho da linha no textField:
    @FXML
    private void BrushNumberTextBox(){
        sizeBarText.setText(String.valueOf(Math.round(sizeBar.getValue())));
    }

    @FXML
    private void DeselectCircleBrush(){CircleBrush.setSelected(false);}

    @FXML
    private void DeselectSquareBrush(){SquareBrush.setSelected(false);}

    //Coloca o tamanho da linha no slider:
    @FXML
    private void BrushNumberSet(){
        if (sizeBarText.getText().equals("") == false && NumberString(sizeBarText.getText()) == true) {
            if(Integer.parseInt(sizeBarText.getText()) >= 4 && Integer.parseInt(sizeBarText.getText()) <= 200) {
                sizeBar.setValue(Integer.parseInt(sizeBarText.getText()));
            }
        }
    }

    //Função responsável por preenchimento:
    private void paint(int x, int y, int k) {
        canvas.getGraphicsContext2D().setFill(colorPicker.getValue());
        Color CanvasColor = canvas.snapshot(null, null).getPixelReader().getColor(x, y);
        if(Cor.getBlue() == CanvasColor.getBlue() && Cor.getGreen() == CanvasColor.getGreen() && Cor.getRed() == CanvasColor.getRed()) {
            canvas.getGraphicsContext2D().fillRect(x, y, 4,4 );
            paint(x+k, y, k);
            paint(x, y+k, k);
            paint(x-k, y, k);
            paint(x, y-k, k);
        }
    }

    //Salva a imagem no formato PNG:
    @FXML
    private void SaveIamge() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpg")
        );
        File file = fileChooser.showSaveDialog(Pane.getScene().getWindow());
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(CanvasPane.snapshot(null, null), null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void WriteText(){
        dialog.setTitle("Text");
        dialog.setHeaderText("Write your text");
        dialog.setContentText("Text:");
        dialog.show();
    }

    @FXML
    private void ImportImage(){
        image = new ImageView(FilePath());
        image.setLayoutY(canvas.getLayoutY());
        image.setLayoutX(canvas.getLayoutX());
        CanvasPane.getChildren().add(image);
        initialize();
    }

    @FXML
    private void BackgroundImage(){
        image = new ImageView(FilePath());
        image.setLayoutY(canvas.getLayoutY());
        image.setLayoutX(canvas.getLayoutX());
        canvas.getGraphicsContext2D().drawImage(image.getImage(), 0, 0);
    }

    //Responsável por manipualr a Saturaçãi, Brilho e Contraste:
    @FXML
    private void GrayScale(){
        canvas.setEffect(new ColorAdjust(0, SaturationSlider.getValue(), BrightnessSlider.getValue(), ContrastSlider.getValue()));
    }

    @FXML
    private void ResetColor(){
        SaturationSlider.setValue(0);
        BrightnessSlider.setValue(0);
        ContrastSlider.setValue(0);
        canvas.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    //Desanho do Fractal:
    //*************************************************************************************************************************
    private double reMin = -2;
    private double reMax = 1;
    private double imMin = -1.2;
    private double imMax = 1.2;

    @FXML
    private void paintSet(double FractalRed,double FractalGreen, double FractalBlue) {
        double precision = Math.max((reMax - reMin) / canvas.getWidth(), (imMax - imMin) / canvas.getHeight());
        int convergenceSteps = 205;
        for (double c = reMin, xR = 0; xR < canvas.getWidth(); c = c + precision, xR++) {
            for (double ci = imMin, yR = 0; yR < canvas.getHeight(); ci = ci + precision, yR++) {
                double convergenceValue = checkConvergence(ci, c, convergenceSteps);
                double t1 = (double) convergenceValue / convergenceSteps;
                double c1 = Math.min(255 * 2 * t1, 255);
                double c2 = Math.max(255 * (2 * t1 - 1), 0);

                if (convergenceValue != convergenceSteps) {
                    canvas.getGraphicsContext2D().setFill(Color.color(c1 / FractalRed, c1 / FractalGreen, c1/FractalBlue));
                } else {
                    canvas.getGraphicsContext2D().setFill(Color.BLACK); // Convergence Color
                }
                canvas.getGraphicsContext2D().fillRect(xR, yR, 1, 1);
            }
        }
    }
    private int checkConvergence(double ci, double c, int convergenceSteps) {
        double z = 0;
        double zi = 0;
        for (int i = 0; i < convergenceSteps; i++) {
            double ziT = 2 * (z * zi);
            double zT = z * z - (zi * zi);
            z = zT + c;
            zi = ziT + ci;

            if (z * z + zi * zi >= 4.0) {
                return i;
            }
        }
        return convergenceSteps;
    }
    //*************************************************************************************************************************

    //Responsável por manipular a Rotação:
    @FXML
    private void RotateCanvas(){
        canvas.setRotate(RotateSlider.getValue());
    }

    //Responsável por repor a Rotação:
    @FXML
    private void ResetRotate(){
        RotateSlider.setValue(0);
        canvas.setRotate(0);
    }

    //Responsável por Repor o Canvas:
    @FXML
    private void ResetAll(){
        ResetRotate();
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.setTranslateX(0);
        canvas.setTranslateY(0);
        canvas.setEffect(new ColorAdjust(0, 0, 0, 0));
        SaturationSlider.setValue(0);
        BrightnessSlider.setValue(0);
        ContrastSlider.setValue(0);
        Pane.getChildren().remove(image);
    }

    //Função responsável por desenhar um circulo:
    private void drawCircle(Canvas canvas, double x, double y, double raio, double var, double bordersize) {
        GraphicsContext corrds = canvas.getGraphicsContext2D();
        double varnew;
        //Y1 -> Semi circunferência inferior
        //Y2 -> Semi circunferência superior
        //X1 -> Semi circunferência direita
        //X2 -> Semi circunferência esquerda
        corrds.setFill(colorPicker.getValue());
        if ( var <= 65){
            varnew = var;
        }   else{
            varnew = var/4;
        }
        for (double x1 = x - varnew; x1 <= x + varnew + 1; x1 = x1 + 2) {
            double y1 = Math.sqrt(raio - Math.pow((x - x1), 2)) + y;
            double y2 = -Math.sqrt(raio - Math.pow((x - x1), 2)) + y;
            corrds.fillOval(x1, y1, bordersize, bordersize);
            corrds.fillOval(x1, y2, bordersize, bordersize);
        }
        for (double y1 = y - varnew; y1 <= y + varnew + 1; y1 = y1 + 2) {
            double x1 = Math.sqrt(raio - Math.pow((y - y1), 2)) + x;
            double x2 = -Math.sqrt(raio - Math.pow((y - y1), 2)) + x;
            corrds.fillOval(x1, y1, bordersize, bordersize);
            corrds.fillOval(x2, y1, bordersize, bordersize);
        }
    }

    @FXML
    private void drawSquare(Canvas canvas, double x, double y, double size, double borderSize) {
        GraphicsContext corrds = canvas.getGraphicsContext2D();
        corrds.setFill(colorPicker.getValue());
        for (double y1 = y - size; y1 <= y + size; y1 = y1 + 1) {
            corrds.fillOval(x - size, y1, borderSize, borderSize);
            corrds.fillOval(x + size, y1, borderSize, borderSize);
        }
        for (double x1 = x - size; x1 <= x + size; x1 = x1 + 1) {
            corrds.fillOval(x1, y - size, borderSize, borderSize);
            corrds.fillOval(x1,y + size, borderSize, borderSize);
        }
    }

    //Responsável por alterar o intrevalo do farctal:
    @FXML
    private void ChangeIntrevaloIn(){
        if(Fractal.isSelected()){
            imMax = imMax * 0.9;
            imMin = imMin * 0.9;
            reMax = reMax * 0.9;
            reMin = reMin * 0.9;
            paintSet(FractalRed.getValue(),FractalGreen.getValue(),FractalBlue.getValue());
        }
    }

    @FXML
    private void ChangeIntrevaloOut(){
        if(Fractal.isSelected()){
            imMax = imMax * 1.1;
            imMin = imMin * 1.1;
            reMax = reMax * 1.1;
            reMin = reMin * 1.1;
            paintSet(FractalRed.getValue(),FractalGreen.getValue(),FractalBlue.getValue());
        }
    }

    @FXML
    private void ChangeFractalColor(){
        if(Fractal.isSelected()){
            paintSet(FractalRed.getValue(),FractalGreen.getValue(),FractalBlue.getValue());
        }
    }

    public void initialize() {
        GraphicsContext corrds = canvas.getGraphicsContext2D();
        sizeBar.setValue(10);
        sizeBar.setMin(4);
        sizeBar.setMax(200);
        border.setMin(4);
        border.setValue(4);
        SaturationSlider.setValue(0);
        SaturationSlider.setMin(-1);
        SaturationSlider.setMax(1);
        BrightnessSlider.setValue(0);
        BrightnessSlider.setMin(-1);
        BrightnessSlider.setMax(1);
        ContrastSlider.setValue(0);
        ContrastSlider.setMin(-1);
        ContrastSlider.setMax(1);
        RotateSlider.setValue(0);
        RotateSlider.setMin(-360);
        RotateSlider.setMax(360);
        border.setValue(1);
        border.setMax(20);
        CircleBrush.setSelected(true);
        FractalGreen.setMin(255);
        FractalGreen.setMax(1200);
        FractalGreen.setValue(255);
        FractalRed.setMin(255);
        FractalRed.setMax(1200);
        FractalRed.setValue(800);
        FractalBlue.setMin(255);
        FractalBlue.setMax(1200);
        FractalBlue.setValue(800);

        ArrayList <Cordenadas> c = new ArrayList<>(3); //Array das coordenadas dos vertices do triângulo
        ArrayList <Cordenadas> reta = new ArrayList<>(); //Array das coordenadas dos pontos da reta
        ArrayList <Cordenadas> CanvasMove = new ArrayList<>(); //Array das coordenadas dos pontos para mover o canvas
        ArrayList <Cordenadas> ImageMove = new ArrayList<>(); //Array das coordenadas dos pontos para mover a Imagem
        ArrayList <Cordenadas> Circle = new ArrayList<>();
        ArrayList <Cordenadas> Square = new ArrayList<>();
        Pane.setOnMouseMoved(event -> {
            if(canvas.getHeight() <= Window.getWindows().get(0).getHeight() - 125 && canvas.getWidth() <= Window.getWindows().get(0).getWidth()) {
                canvas.setHeight(Window.getWindows().get(0).getHeight() - 132);
                canvas.setWidth(Window.getWindows().get(0).getWidth());
                canvas.setLayoutY(0);
            }
        });

        Fractal.setOnAction(event -> {
            paintSet(FractalRed.getValue(),FractalGreen.getValue(), FractalBlue.getValue());
                });

        image.setOnMouseDragged(event -> {
            double size = sizeBar.getValue();
            double x = event.getX() - size / 2;
            double y = event.getY() - size / 2;
            ImageMove.add(new Cordenadas(x, y));
            image.setTranslateX(image.getTranslateX() - (sub(ImageMove.get(0).getX(), ImageMove.get(ImageMove.size() - 1).getX())/20));
            image.setTranslateY(image.getTranslateY() - (sub(ImageMove.get(0).getY(), ImageMove.get(ImageMove.size() - 1).getY())/20));
        });

        image.setOnScroll(event -> {
            double size = sizeBar.getValue();
            double x = event.getX() - size / 2;
            double y = event.getY() - size / 2;
            if ((image.getScaleX() + event.getDeltaY() / 1000) > 0 && (image.getScaleY() + event.getDeltaY() / 1000) > 0) {
                image.setScaleX(image.getScaleX() + event.getDeltaY() / 1000);
                image.setScaleY(image.getScaleY() + event.getDeltaY() / 1000);
            }
        });

        image.setOnMouseMoved(event -> {
            ImageMove.clear();
        });

        canvas.setOnMouseClicked(event -> {
            double size = sizeBar.getValue();
            double x = event.getX();
            double y = event.getY();
            double borderSize = border.getValue();

            //Desenha um quadrado com centro no ponto clicado:
            if (quadrangle.isSelected()) {
                Canvas layer = CreateLayer();
                Pane.getChildren().add(layer);
                layer.setOnMouseClicked(event1 -> {
                    click++;
                });
                layer.setOnMouseMoved(event1 -> {
                    Square.add(new Cordenadas(event1.getX(), event1.getY()));
                    if (Square.size() >= 2) {
                        Cordenadas ponto = PontoMedia(Square.get(0), Square.get(Square.size() - 1));
                        layer.getGraphicsContext2D().clearRect(0, 0, layer.getWidth(), layer.getHeight());
                        double tamanho = DistanciaEntrePontos(Square.get(0), Square.get(Square.size() - 1));
                        drawSquare(layer, ponto.getX(), ponto.getY(), tamanho/2,borderSize);
                        if (click >= 1) {
                            layer.setOnMouseMoved(null);
                            Pane.getChildren().remove(layer);
                            drawSquare(canvas, ponto.getX(), ponto.getY(), tamanho/2,borderSize);
                            click = 0;
                            circle.setSelected(false);
                            Square.clear();
                        }
                    }
                });
            }
            //Desenha do triângulo, o utilzador escolhe três pontos no canvas e ele desenha o triângulo:
            if (triangle.isSelected()) {
                click++;
                corrds.setFill(colorPicker.getValue());
                Cordenadas cord = new Cordenadas(x, y);
                if (click <= 3) {
                    cord.setX(x);
                    cord.setY(y);
                    c.add(cord);
                    corrds.fillOval(x, y, 2, 2);
                }
                if (click == 3) {
                    drawLine(c.get(0).getX(), c.get(0).getY(), c.get(1).getX(), c.get(1).getY(), borderSize, canvas);
                    drawLine(c.get(0).getX(), c.get(0).getY(), c.get(2).getX(), c.get(2).getY(), borderSize, canvas);
                    drawLine(c.get(1).getX(), c.get(1).getY(), c.get(2).getX(), c.get(2).getY(), borderSize, canvas);
                    click = 0;
                    c.clear();
                }
            }
            //Desenhar uma linha e motras o feedback em real time para o usuário:
            if(line.isSelected()){
                Canvas layer = CreateLayer();
                Pane.getChildren().add(layer);
                layer.setOnMouseClicked(event1 -> {
                    click++;
                });
                corrds.setFill(colorPicker.getValue());
                Cordenadas cord = new Cordenadas(x, y);
                reta.add(cord);
                layer.setOnMouseMoved(event1 -> {
                    reta.add(new Cordenadas(event1.getX(), event1.getY()));
                    if (reta.size() > 2) {
                        clearLine(reta.get(0).getX(), reta.get(0).getY(), reta.get(reta.size() - 2).getX(), reta.get(reta.size() - 2).getY(), borderSize, layer);
                    }
                    drawLine(reta.get(0).getX(), reta.get(0).getY(), reta.get(reta.size() - 1).getX(), reta.get(reta.size() - 1).getY(), borderSize, layer);
                    if (click >= 1) {
                        layer.setOnMouseMoved(null);
                        Pane.getChildren().remove(layer);
                        drawLine(reta.get(0).getX(), reta.get(0).getY(), reta.get(reta.size() - 1).getX(), reta.get(reta.size() - 1).getY(), borderSize, canvas);
                        click = 0;
                        line.setSelected(false);
                        reta.clear();
                    }
                });
            }
            //Pegar as corres de umas coordenadas do canvas escolhidas pelo usuario:
            if(ampulheta.isSelected()){
                try{
                    Robot robot = new Robot();
                    PointerInfo mouseInfo = MouseInfo.getPointerInfo();
                    java.awt.Color C = robot.getPixelColor(mouseInfo.getLocation().x, mouseInfo.getLocation().y);
                    String hex = "#"+Integer.toHexString(C.getRGB()).substring(2); //Converter RGB para Hexadecimal
                    colorPicker.setValue(Color.web(hex));
                }   catch(Exception e){
                    System.out.println(e);
                }
            }
            //Escrever o texto na tela:
            if(Text.isSelected()){
                corrds.strokeText(dialog.getResult(), x, y);
                System.out.println(dialog.getResult());
                Text.setSelected(false);
            }
            //Fazer o preenchimento da area:
            if(Balde.isSelected()){
                Cor = canvas.snapshot(null, null).getPixelReader().getColor((int) x,(int) y);
                System.out.println(Cor);
                paint((int) x,(int) y, 4);
                System.out.println("1º parte");
                System.out.println("2º parte");
            }
            //Desenha um circulo com centro no ponto clicado:
            if (circle.isSelected()) {
                Canvas layer = CreateLayer();
                Pane.getChildren().add(layer);
                layer.setOnMouseClicked(event1 -> {
                    click++;
                });
                layer.setOnMouseMoved(event1 -> {
                        Circle.add(new Cordenadas(event1.getX(), event1.getY()));
                        if (Circle.size() >= 2) {
                            Cordenadas ponto = PontoMedia(Circle.get(0), Circle.get(Circle.size() - 1));
                            double raio = DistanciaEntrePontos(Circle.get(0), Circle.get(Circle.size() - 1)) / 2 * DistanciaEntrePontos(Circle.get(0), Circle.get(Circle.size() - 1)) / 2;
                            double var = raio / 10;
                            layer.getGraphicsContext2D().clearRect(0, 0, layer.getWidth(), layer.getHeight());
                            drawCircle(layer, ponto.getX(), ponto.getY(), raio, var, borderSize);
                            if (click >= 1) {
                                System.out.println(click);
                                layer.setOnMouseMoved(null);
                                Pane.getChildren().remove(layer);
                                drawCircle(canvas, ponto.getX(), ponto.getY(), raio, var, borderSize);
                                click = 0;
                                circle.setSelected(false);
                                Circle.clear();
                            }
                        }
                });
            }
        });

        canvas.setOnMouseDragged(event -> {
            double size = sizeBar.getValue();
            double borderSize = border.getValue();
            double x = event.getX() - size / 2;
            double y = event.getY() - size / 2;

            //Pegar os valores das cores:
            corrds.setFill(colorPicker.getValue());

            //Desenhas as figuras geometricas:
            if (circle.isSelected() == false && quadrangle.isSelected() == false && triangle.isSelected() == false && move.isSelected() == false && line.isSelected() == false && ampulheta.isSelected() == false) {
                if (rubber.isSelected()) {
                    corrds.clearRect(x, y, size, size);
                }  else {
                    if(CircleBrush.isSelected()){
                        corrds.fillOval(x, y, size, size);
                    } else if (SquareBrush.isSelected()){
                        corrds.fillRect(x, y, size, size);
                    }
                }
            }
            //Poder mover o Canvas:
            if(move.isSelected()){
                CanvasMove.add(new Cordenadas(x, y));
                canvas.setTranslateX(canvas.getTranslateX() - (sub(CanvasMove.get(0).getX(), CanvasMove.get(CanvasMove.size() - 1).getX())/2));
                canvas.setTranslateY(canvas.getTranslateY() - (sub(CanvasMove.get(0).getY(), CanvasMove.get(CanvasMove.size() - 1).getY())/2));
            }
        });

        canvas.setOnMouseMoved(event -> {
            CanvasMove.clear();
        });

        //Ação de fazer zoom no canvas:
        canvas.setOnScroll(event -> {
            double size = sizeBar.getValue();
            double x = event.getX() - size / 2;
            double y = event.getY() - size / 2;
                if(Fractal.isSelected()){
                    reMax = reMax + ((event.getDeltaX() / 1000) * 2);
                    imMax = imMax + ((event.getDeltaY() / 1000) * 2);
                    reMin = reMin + ((event.getDeltaX() / 1000) * 2);
                    imMin = imMin + ((event.getDeltaY() / 1000) * 2);
                    paintSet(FractalRed.getValue(),FractalGreen.getValue(), FractalBlue.getValue());
                }   else{
                    canvas.setScaleX(canvas.getScaleX() + event.getDeltaY()/1000);
                    canvas.setScaleY(canvas.getScaleY() + event.getDeltaY()/1000);
                }
        });
    }
}
