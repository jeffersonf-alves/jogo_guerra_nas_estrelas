package meujogo.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Player player;

    private Timer timer;
    private List<Enemy01> enemy01;
    private boolean emJogo;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon reference = new ImageIcon("res\\fundo\\bg_02_h.png");
        fundo = reference.getImage();

        player = new Player();
        player.load();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(7, this);
        timer.start();

        inicializaInimigos();
        emJogo = true;
    }

    public void inicializaInimigos() {
        int coordenadas[] = new int[40];
        enemy01 = new ArrayList<Enemy01>();

        for(int i = 0; i < coordenadas.length; i++) {
            int x = (int)(Math.random() * 8000+1024);
            int y = (int)(Math.random() * 728+30);
            enemy01.add(new Enemy01(x, y));
        }
    }


    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if(emJogo) {
            graficos.drawImage(fundo, 0, 0, null);
            graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

            java.util.List<Tiro> tiros = player.getTiros();
            for(int i = 0; i < tiros.size(); i++) {
                Tiro m = tiros.get(i);
                m.load();
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }
            for(int t = 0; t < enemy01.size(); t++) {
                Enemy01 in = enemy01.get(t);
                in.load();
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
        } else {
                ImageIcon fimJogo = new ImageIcon("res\\game_over.png");
                graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        java.util.List<Tiro> tiros = player.getTiros();
        for(int i = 0; i < tiros.size(); i++) {
            Tiro m = tiros.get(i);
                if(m.isVisibel()) {
                    m.update();
                } else {
                    tiros.remove(i);
                }
        }

        for(int t = 0; t < enemy01.size(); t++) {
            Enemy01 in = enemy01.get(t);

            if(in.isVisibel()) {
                in.update();
            } else {
                enemy01.remove(t);
            }
        }
        checarColisoes();
        repaint();
    }

    public void checarColisoes() {
        Rectangle formaNave = player.getBounds();
        Rectangle formaEnemy1;
        Rectangle formaTiro;

        for(int i = 0; i < enemy01.size(); i++) {
            Enemy01 tempEnemy1 = enemy01.get(i);
            formaEnemy1 = tempEnemy1.getBounds();
            if(formaNave.intersects(formaEnemy1)) {
                player.setVisivel(false);
                tempEnemy1.setVisibel(false);
                emJogo = false;
            }
        }

        List<Tiro> tiros = player.getTiros();
        for(int i = 0; i < tiros.size(); i++) {
            Tiro tempTiro = tiros.get(i);
            formaTiro = tempTiro.getBounds();

            for(int j = 0; j < enemy01.size(); j++) {
                Enemy01 tempEnemy1 = enemy01.get(j);
                formaEnemy1 = tempEnemy1.getBounds();
                if(formaTiro.intersects(formaEnemy1)) {
                    tempEnemy1.setVisibel(false);
                    tempTiro.setVisibel(false);

                }
            }
        }
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
