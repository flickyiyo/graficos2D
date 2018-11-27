package parcial3.CurvaSuperficie;

import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SuperficieFrame extends JFrame implements KeyListener, Runnable {
    public static void main(String []args) {
        SuperficieFrame curvaFrame = new SuperficieFrame();
        curvaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Curva curva;
    Point3D plano;
    BufferedImage fondo;
    Superficie superficie;

    public SuperficieFrame() {
        addKeyListener(this);
        this.setBounds(100, 100, 700, 700);

        this.plano = new Point3D(getWidth()/2, getHeight()/2-50, 10);
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        ArrayList<Curva> curvas = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            curvas.add(new Curva(10, 10, 500, 500));
        }
        this.superficie = new Superficie(10, 10, 500, 500, curvas);

        //this.curva.dibujarCurva(200, 100, 50, plano);
        this.setVisible(true);
        superficie.setParent(this);
        this.superficie.dibujarSuperficie(200, 50, plano);

    }

    @Override
    public void paint(Graphics g) {
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        fondo.getGraphics().fillRect(0,0,fondo.getWidth(), fondo.getHeight());
        fondo.getGraphics().drawImage(this.superficie.getBuffer(), this.superficie.getX(), this.superficie.getY(), this);


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
            case KeyEvent.VK_SHIFT:
                isShiftPressed = !isShiftPressed;
                break;
            case KeyEvent.VK_A:
                superficie.rotarSuperficie(factor*5,0, 0, plano);
                break;
            case KeyEvent.VK_S:
                superficie.rotarSuperficie(0,factor*5, 0, plano);
                break;
            case KeyEvent.VK_D:
                superficie.rotarSuperficie(0,0, factor*5, plano);
                break;

        }//*/
        repaint();
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
            repaint();
        }
    }
}
