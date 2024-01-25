package meujogo.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Player player;

    private Timer timer;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon reference = new ImageIcon("res\\fundo\\bg_02_h.png");
        fundo = reference.getImage();

        player = new Player();
        player.load();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        repaint();
    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyRelease(e);
        }
    }
}
