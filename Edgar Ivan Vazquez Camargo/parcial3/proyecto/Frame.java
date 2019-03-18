package parcial3.proyecto;

import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Frame extends JFrame implements Runnable, KeyListener {
    public static void main(String []args) {
        Frame frame = new Frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    boolean shouldContinue = true;
    Point3D plano;
    Cube cube;
    BufferedImage fondo;

    public void initBuffer() {
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    public Frame() {
        this.setBounds(100, 100, 700, 700);
        this.addKeyListener(this);
        this.plano = new Point3D(this.getWidth() / 2, this.getHeight() / 2, -10);
        this.cube = new Cube(0, 0, 700, 700);
        this.cube.setParent(this);
        initBuffer();
        this.setVisible(true);
        run();

    }

    @Override
    public void paint(Graphics g) {
        fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        fondo.getGraphics().fillRect(0,0,fondo.getWidth(), fondo.getHeight());
        fondo.getGraphics().drawImage(cube.getBuffer(), cube.getX(), cube.getY(), this);
        g.drawImage(fondo, 0, 0, this);
    }

    @Override
    public void run() {
        while(shouldContinue) {
            try {
                Thread.sleep(100);
                cube.setAnguloX(cube.getAnguloX() + 5);
                cube.setAnguloY(cube.getAnguloY() + 2);
                cube.setAnguloZ(cube.getAnguloZ() + 8);
                cube.dibujarCubo(100, 100, 50, 200, 200, 150, plano);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.shouldContinue = false;
            return;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
