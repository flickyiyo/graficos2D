package parcial3.CurvaSuperficie;

import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class SuperficieFrame extends JFrame implements KeyListener, Runnable {
    public static void main(String []args) {
        SuperficieFrame curvaFrame = new SuperficieFrame();
        curvaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Curva curva;
    Point3D plano;
    BufferedImage fondo;

    public SuperficieFrame() {
        addKeyListener(this);
        this.setBounds(100, 100, 700, 700);

        this.plano = new Point3D(103, 103, 1);
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.curva = new Curva(10, 10, 500, 500);
        //this.curva.dibujarCurva(200, 100, 50, plano);
        this.setVisible(true);
        run();
    }

    @Override
    public void paint(Graphics g) {
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        fondo.getGraphics().fillRect(0,0,fondo.getWidth(), fondo.getHeight());
        fondo.getGraphics().drawImage(this.curva.getBuffer(), this.curva.getX(), this.curva.getY(), this);

        g.drawImage(fondo, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    boolean isShiftPressed = false;
    @Override
    public void keyPressed(KeyEvent e) {
        int factor = isShiftPressed ? 1 : -1;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                this.curva.rotarx(factor*5, plano);
                break;
            case KeyEvent.VK_S:
                this.curva.rotarY(factor * 5, plano);
                break;
            case KeyEvent.VK_D:
                this.curva.rotarZ(factor * 5, plano);
                break;
            case KeyEvent.VK_SHIFT:
                this.isShiftPressed = !this.isShiftPressed;
                break;

        }
        System.out.println("Pressed");
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        int factor = isShiftPressed ? 1 : -1;
        while(true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.curva.rotarx(factor*5, plano);
            repaint();
        }
    }
}
