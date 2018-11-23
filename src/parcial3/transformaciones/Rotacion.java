package parcial3.transformaciones;

import javafx.geometry.Point3D;
import parcial3.perspectiva.CuboParalela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Rotacion extends JFrame implements KeyListener {

    public static void main(String []args) {
        Rotacion proyeccion = new Rotacion();
        proyeccion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    BufferedImage fondo;
    CuboTransform cubo;
    Point3D plano;

    public Rotacion() {
        addKeyListener(this);
        this.setBounds(500, 100, 700, 700);
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.plano = new Point3D(300, 200, 90);
        this.cubo = new CuboTransform(0,0,500,400);
        this.cubo.setParent(this);
        this.setVisible(true);
        this.cubo.dibujarCubo(10, 10, 10, 100, 100, 100, plano);
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paiting");
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        fondo.getGraphics().fillRect(0,0,fondo.getWidth(), fondo.getHeight());
        fondo.getGraphics().drawImage(cubo.getBuffer(), cubo.getX(), cubo.getY(), this);
        g.drawImage(fondo, 0, 0, this   );
    }

    @Override
    public void keyTyped(KeyEvent e) {



    }
    public boolean isPressingShift = false;
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                this.isPressingShift = !this.isPressingShift;
                return;
            case KeyEvent.VK_A:
                System.out.println("pressing a");
                //this.cubo.rotarX(isPressingShift ? cubo.getAngulo()+5: cubo.getAngulo()-5, plano);
                this.cubo.rotarX(new Point3D(10, 10, 10), new Point3D(100, 100, 100), isPressingShift ? cubo.getAngulo()+5: cubo.getAngulo()-5, plano);
                return;
            case KeyEvent.VK_S:
                System.out.println("pressing a");
                this.cubo.rotarY(new Point3D(10, 10, 10), new Point3D(100, 100, 100), isPressingShift ? cubo.getAngulo()+5: cubo.getAngulo()-5, plano);
                return;
            case KeyEvent.VK_D:
                System.out.println("pressing a");
                this.cubo.rotarZ(new Point3D(10, 10, 10), new Point3D(100, 100, 100), isPressingShift ? cubo.getAngulo()+5: cubo.getAngulo()-5, plano);
                return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
