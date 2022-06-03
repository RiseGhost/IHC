<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

# Traço sublime:

<div class="container">
    <h3>Project details:</h3>
    <div class="itens">
    <div> &emsp;<i class="material-icons">calendar_month</i> &emsp; 03/06/2022 </div>
    <div> &emsp;<i class="material-icons">school</i> &emsp; Interação   Humana com o Computador. </div>
    <div> &emsp;<i class="material-icons">person</i> &emsp; José Miguel Alves Melo dos Santos.</div>
    <div> &emsp;<i class="material-icons">code</i> &emsp; Java</div>
    <h3> Project Objetive: </h3>
    <div class="texto">
        O objetivo desse projeto foi a realização de um programa que permitisse ao utilizador desanhar livermente. Mas que também oferecesse algumas ferramente que ajudasse o utilizador a fazer desanhos mais complexos. Para facilitar na criação da interface utilizei o <strong> SceneBuilder </strong> juntamente com o biblioteca <strong> JavaFX </strong>.
    </div>
    <h3>  Project Layout: </h3>
    <div class="image">
        <img src="https://user-images.githubusercontent.com/91985039/171900686-79dcf948-2f76-47de-9957-8ec8bba49fe7.jpg">
        O software tem uma aparência de hamburguer 🍔, com menus em cima e em baixo da área de desanho. Na área de desanho foi colocado um canvas. Na parte superior e inferior foi utilizado dois GridPane. Para criar os menus utilizei MenuBars. Isto todo está coloca por cima de um AnchorPane.
    </div>
    <h3> Functionality: </h3>
    <h4> Draw Geometric Figure:</h4>
    <div class="image">
        <img src="https://user-images.githubusercontent.com/91985039/171907007-3a3abcb3-8f54-4470-8428-bd3127e4b4a7.jpg">
        O utilizador consegue desenhar 3 figuras geometricas. Em qualquer umas das figuras geometricas ele pode escolher a cor que deseja e a espesura da borda. No circulo e no quardrado o utilizador clica num ponto e depois o programa mostra um real time o tamanho da figura quando o utilizador volta a clicar a figura é desenhar naquela posição. No triângulo o utilizador apenas clica em 3 pontos na tela e ele desenhar um triângulo unindo os 3 pontos.
    </div>
</div>
    O código seguinte foi utilizado para desenhar os quadrados:

```java
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
```

Pego na cor seleciona pelo utilizador no: __corrds.setFill(colorPicker.getValue());__ e depois desenho 4 linhas com tamanho size a uma distância de size/2.
<p>
O Código seguinte foi utilizado para desenhar os circulos:

```java
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
```

<div class="container">
    <div class="image">
        Se o circulo for pequeno ( raio <= 65 ) eu desanho praticamente das vezes o circulo se ele for maior eu desanho uma parte do circulo no primeiro ciclo for utilizando o X como constante e a segunda parte do circulo desanho no segundo ciclo for utilizando o Y como constante. Eu utilizo isso porque a equação que tem o Y em evidência (1º ciclo for) desenha com facilidade o topo e a base do circulo mas necessita de muitas incrementações para desenhar bem as laterais do circulo. Já a equação que tem o X como evidência desenha muito bem as laterais mas necessita de muitas incrementações para desenhar o topo e a base do circulo.
    </div>
</div>

<img src="https://user-images.githubusercontent.com/91985039/171914434-89b6b235-dd62-4bbb-a61e-8fe0ac6f8203.jpg" style="width:100%">
<p>

O código seguinte foi utilizado para desenhar os triângulos:

```Java
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
            drawLine(c.get(0).getX(), c.get(0).getY(), c.g(1).getX(), c.get(1).getY(), borderSize, canva);
            drawLine(c.get(0).getX(), c.get(0).getY(), c.g(2).getX(), c.get(2).getY(), borderSize, canva);
            drawLine(c.get(1).getX(), c.get(1).getY(), c.g(2).getX(), c.get(2).getY(), borderSize, canva);
            click = 0;
            c.clear();
        }
    }
```

Utilizo um ArrayList de Coordenadas para guardar as coordenasdas dos 3 pontos do triângulo e depois desanha 3 linhas a unir os pontos.

<div class="container">
    <h4> Tools: </h4>
    <div class="image">
        <img src="https://user-images.githubusercontent.com/91985039/171916807-1d65f811-701b-468f-9dcd-6aa5bd081657.jpg">
        O utlizador tem várias ferramentas ao seu dispor. O conta gotas premite ao utlizador que quando clique em cima de pixel da image a cor desse pixel fica defenida no pincel. O mover premeti ao utilizador mover o canvas inteiro. A linha premite ao utilizador desenhar um linha mostrado feedback em real time. O texto premite adicionar texto escrito a image. O balde de tinta pitar um área o o fractal desenhar o fractal de mandelbrot.
    </div>
    <h4> File: </h4>
    <div class="image">
        <img src="https://user-images.githubusercontent.com/91985039/171918703-e53292ab-4722-4ff0-bdda-68a17f653cf7.jpg">
        O utilizador pode guardar o desanho que fez em PGN, assim como importar imagem e alterar o fundo do canvas para uma imagem. Se o utilizador importar uma imagem ele pode alterar o tamnaho dela a posição dela mas não pode desenhar por cima. Se alterar o fundo do canvas para uma imagem o utilizar poderar desenhar por cima mas também poderar apagar a imagem com a borracha.
    </div>
    <h4> Mandelbrot: </h4>
    <div class="image">
        <img src="https://user-images.githubusercontent.com/91985039/171925822-ad1d011a-9f34-4657-9287-9a1417906700.jpg">
        O utilizador quando desejar desenhar um fractal ele poderar escolher algumas coisas, como por exemplo a cor do fractal. Usando os dedos num touchpad poderar alterar a posição do fractal e através dos botões: <br>
        Fractal Zoom Out; <br>
        Fractal Zoom in; <br>
        Poderá fazer zoom no fractal.
    </div>
    <h4> Small Features: </h4>
    &emsp; -> O utilizador pode alterar a saturação, constraste e brilho do desanho; <br>
    &emsp; -> O utilizador pode rodar o canvas quando tiver a desenhar; <br>
    &emsp; -> O utilizador pode escolher o formato do pencil, se deseja um circulo ou quardrado; <br>
    &emsp; -> O utilizador pode alterar o tamanho do pincel.
    <h4 style="color:red"> ❌ Problemas do programa: </h4>
    &emsp; -> O balde de tinta é extramente lento a funcionar, funciona só em áreas pequenas e a bordas não ficam muito bem; <br>
    &emsp; -> O desanho dos fractais é um pouco lento. Se o utilizador estiver em full screen fica praticamente imporssível de desenhar devido ao tempo necessário.
    <h4> Some software image: </h4>
    <img src="https://user-images.githubusercontent.com/91985039/171925532-4d4a36d9-92f5-43ac-9a93-3a271233c3b4.jpg">
    <img src="https://user-images.githubusercontent.com/91985039/171925550-6895e347-bfd6-4252-9cfe-c80bfbef1ae4.jpg">
    <img src="https://user-images.githubusercontent.com/91985039/171925564-aa6d8664-876b-4609-9dd8-d51c1a21c7df.jpg">
    <img src="https://user-images.githubusercontent.com/91985039/171925587-2182b73f-3eab-4cea-aa85-da7040b2c7f9.jpg">
    <img src="https://user-images.githubusercontent.com/91985039/171929745-5ed7ff3a-8dec-411e-aa8c-0d623b5e32af.jpg">
    <img src="https://user-images.githubusercontent.com/91985039/171929761-2344e9cf-a13d-41d7-9738-5bdde2dd8362.jpg">
</div>

<style>
    .container h3{
        color: rgb(0,230,0);
        padding-bottom: 5px;
    }

    .container h4{
        color: rgb(0,230,0);
        padding-bottom: 5px;
    }

    .container .texto{
        text-align:justify;
        text-justify: auto;
    }

    .container .image{
        display: flex;
        align-items: center;
        gap: 25px;
        text-align:justify;
        text-justify: auto;
    }

    .container .image img{
        width: 415px;
        padding-right: 25px;
        border-right: 1px solid rgb(0,230,0);
    }

</style>

