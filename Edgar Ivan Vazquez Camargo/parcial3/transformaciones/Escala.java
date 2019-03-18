package parcial3.transformaciones;

import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Escala extends JFrame implements KeyListener {
    public static void main(String[] args) {
        Escala proyeccion = new Escala();
        proyeccion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    int x1, x2, y1, y2, z1, z2;
    BufferedImage fondo;
    CuboTransform cubo;
    Point3D plano;

    public Escala() {
        addKeyListener(this);
        this.setBounds(500, 100, 700, 700);
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.plano = new Point3D(300, 200, 90);
        this.cubo = new CuboTransform(0, 0, 500, 400);
        this.cubo.setParent(this);
        this.setVisible(true);
        this.cubo.setBounds(10, 10, 10, 100, 100, 100);
        cubo.setPlano(plano);
        setBounds(10, 10, 10, 100, 100, 100);

        this.cubo.dibujarCubo(10, 10, 10, 100, 100, 100, plano);
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paiting");
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        fondo.getGraphics().fillRect(0, 0, fondo.getWidth(), fondo.getHeight());
        fondo.getGraphics().drawImage(cubo.getBuffer(), cubo.getX(), cubo.getY(), this);
        g.drawImage(fondo, 0, 0, this);
    }

    public void setBounds(Point3D p1, Point3D p2) {
        setBounds(
                (int)p1.getX(),
                (int)p1.getY(),
                (int)p1.getZ(),
                (int)p2.getX(),
                (int)p2.getY(),
                (int)p2.getZ()
        );
    }

    public void setBounds(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z2;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public boolean isPressingShift = false;

    @Override
    public void keyPressed(KeyEvent e) {
        repaint();
        this.cubo.dibujarCubo(x1, y1, z1, x2, y2, z2, plano);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                this.isPressingShift = !this.isPressingShift;
                return;
            case KeyEvent.VK_A:
                System.out.println("pressing a");
                this.x2+= isPressingShift ? 2 : -2;
            case KeyEvent.VK_S:
                System.out.println("pressing a");
                this.y2+= isPressingShift ? 2 : -2;

            case KeyEvent.VK_D:
                System.out.println("pressing a");
                this.z2+= isPressingShift ? 2 : -2;
                return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
