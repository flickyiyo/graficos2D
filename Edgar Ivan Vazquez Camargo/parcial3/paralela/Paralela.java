package parcial3.paralela;

import javafx.geometry.Point3D;
import parcial3.perspectiva.CuboProyeccion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Paralela extends JFrame implements KeyListener {
    public static void main(String[] args) {
        Paralela paralela = new Paralela();
        paralela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paralela.setVisible(true);
        paralela.run();
    }

    private Point3D plano;
    private CuboProyeccion cubo;
    private BufferedImage fondo;

    public Paralela() {
        super();
        setBounds(500, 500, 700, 700);
        this.plano = new Point3D(150, 200, 0);
        this.cubo = new CuboProyeccion(0,0,500,400);
        cubo.setParent(this);

        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {
        fondo = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        fondo.getGraphics().fillRect(0,0, fondo.getWidth(), fondo.getHeight());

        fondo.getGraphics().drawImage(cubo.getBuffer(), 100, 100, null);
        g.drawImage(fondo, 0, 0, this);
    }


    public void run() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.plano = new Point3D(plano.getX()-5, plano.getY(), plano.getZ());
                break;
            case KeyEvent.VK_RIGHT:
                this.plano = new Point3D(plano.getX()+5, plano.getY(), plano.getZ());
                break;
            case KeyEvent.VK_UP:
                this.plano = new Point3D(plano.getX(), plano.getY()-5, plano.getZ());
                break;
            case KeyEvent.VK_DOWN:
                this.plano = new Point3D(plano.getX(), plano.getY()+5, plano.getZ());
        }
        this.cubo.dibujarCubo(10,10,10,40,40,40, this.plano);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
