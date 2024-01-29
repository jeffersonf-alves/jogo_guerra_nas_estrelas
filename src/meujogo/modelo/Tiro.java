package meujogo.modelo;

import javax.swing.*;
import java.awt.*;

public class Tiro {

    private Image imagem;
    private int x, y;
    private int altura, largura;
    private boolean isVisibel;

    private static final int LARGURA = 938;
    private static int VELOCIDADE = 5;

    public Tiro(int x, int y) {
        this.x = x;
        this.y = y;
        isVisibel = true;
    }

    public void load() {
        ImageIcon reference = new ImageIcon("res\\arm\\01.png");
        imagem = reference.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update() {
        this.x += VELOCIDADE;
        if(this.x > LARGURA) {
            isVisibel = false;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisibel() {
        return isVisibel;
    }

    public void setVisibel(boolean visibel) {
        isVisibel = visibel;
    }
}
