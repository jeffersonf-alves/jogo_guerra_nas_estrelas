package meujogo.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    private int x, y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;

    public Player() {
        this.x = 100;
        this.y = 100;
    }

    public void load() {
        ImageIcon referencia = new ImageIcon("res\\player\\player_main.png");
        imagem = referencia.getImage().getScaledInstance(60, 60, 40);
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP) {
            dy = -3;
        }
        if(codigo == KeyEvent.VK_DOWN) {
            dy = 3;
        }
        if(codigo == KeyEvent.VK_LEFT) {
            dx = -3;
        }
        if(codigo == KeyEvent.VK_RIGHT) {
            dx = 3;
        }
    }

    public void keyRelease(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP) {
            dy = 0;
        }
        if(codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if(codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if(codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
}
