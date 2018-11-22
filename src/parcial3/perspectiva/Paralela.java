package parcial3.perspectiva;

import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Paralela extends JFrame implements KeyListener {

    public static void main ( String []args) {
        Paralela proyeccion = new Paralela();
        proyeccion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    BufferedImage fondo;
    CuboParalela cubo;
    Point3D plano;
    public Paralela() {
        addKeyListener(this);
        this.setBounds(500, 100, 700, 700);
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.plano = new Point3D(50, 50, -10);
        this.cubo = new CuboParalela(200,200, this.getWidth(), this.getHeight());
        this.cubo.setParent(this);
        this.cubo.dibujarCubo(10, 10, 10, 100, 100, 100, plano);
        this.setVisible(true);

    }

    boolean initialized = false;

    @Override
    public void paint(Graphics g) {
        System.out.println("paiting");
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        fondo.getGraphics().fillRect(0,0,fondo.getWidth(), fondo.getHeight());
        fondo.getGraphics().drawImage(cubo.getBuffer(), cubo.getX(), cubo.getY(), this);
        g.drawImage(fondo, 0, 0, this   );
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("asd");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.cubo.setX(this.cubo.getX()-5);
                break;
            case KeyEvent.VK_RIGHT:
                this.cubo.setX(this.cubo.getX()+5);
                break;
            case KeyEvent.VK_UP:
                this.cubo.setY(this.cubo.getY()+5);
                break;
            case KeyEvent.VK_DOWN:
                this.cubo.setY(this.cubo.getY()-5);
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                this.plano = new Point3D(plano.getX()-5, plano.getY(), plano.getZ());
                break;
            case KeyEvent.VK_D:
                this.plano = new Point3D(plano.getX()+5, plano.getY(), plano.getZ());
                break;
            case KeyEvent.VK_W:
                this.plano = new Point3D(plano.getX(), plano.getY()-5, plano.getZ());
                break;
            case KeyEvent.VK_S:
                this.plano = new Point3D(plano.getX(), plano.getY()+5, plano.getZ());
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void run() {
        while   (true) {
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
