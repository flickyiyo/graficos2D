package parcial3.CurvaSuperficie;

import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class CurvaFrame extends JFrame implements KeyListener, Runnable {
    public static void main(String []args) {
        CurvaFrame curvaFrame = new CurvaFrame();
        curvaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        curvaFrame.run();
    }

    Curva curva;
    Point3D plano;
    BufferedImage fondo;

    public CurvaFrame() {
        addKeyListener(this);
        this.setBounds(100, 100, 700, 700);

        this.plano = new Point3D(getWidth()/2, getHeight()/2, -100);
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.curva = new Curva(10, 10, 500, 500);
        this.curva.dibujarCurva(200, 100, 20, plano);
        this.setVisible(true);
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
                curva.rotar(5,0, 0, plano);
                break;
            case KeyEvent.VK_S:
                curva.rotar(0,5, 0, plano);
                break;
            case KeyEvent.VK_D:
                curva.rotar(0,0, 5, plano);
                break;

        }//*/
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
    }
}
