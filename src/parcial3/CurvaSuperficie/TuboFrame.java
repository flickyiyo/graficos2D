package parcial3.CurvaSuperficie;

import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TuboFrame extends JFrame implements KeyListener, Runnable {
    public static void main(String []args) {
        TuboFrame tuboFrame = new TuboFrame();
        tuboFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    Tubo tubo;
    Circulo3D circulo3D;
    BufferedImage fondo;
    Point3D plano;
    ArrayList<Circulo3D> circulos;
    boolean isShiftPressed = false;

    public TuboFrame() {
        addKeyListener(this);
        this.setBounds(100, 100, 700, 700);
        this.fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        circulo3D = new Circulo3D(10, 10, 100, 500, 500);
        this.plano = new Point3D(getWidth()/2, getHeight()/2, -100);
        circulos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            circulos.add(new Circulo3D(0, -50, 100, 700, 700));
        }

        this.tubo = new Tubo(10, 10, 700, 700);
        tubo.setParent(this);
        this.tubo.setCirculos(circulos);
        this.setVisible(true);
        this.tubo.dibujarVainaRara(1, plano);
        run();
    }

    @Override
    public void paint(Graphics g) {
        fondo = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        fondo.getGraphics().fillRect(0,0,fondo.getWidth(), fondo.getHeight());
        //circulo3D.limpiarBuffer();
        //circulo3D.dibujarCirculo(50, 1, plano);
        //tubo.limpiarBuffer();
        //this.tubo.dibujarVainaRara(1, plano);
        //fondo.getGraphics().drawImage(tubo.getBuffer(), 0, 0, this);
        fondo.getGraphics().drawImage(tubo.getBuffer(), tubo.getX(), tubo.getY(), this);
        g.drawImage(fondo, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int factor = isShiftPressed ? 1: -1;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                isShiftPressed = !isShiftPressed;
                break;
            case KeyEvent.VK_A:
                this.plano = new Point3D(factor*20+plano.getX(), plano.getY(), plano.getZ());
                break;
            case KeyEvent.VK_S:
                this.plano = new Point3D(plano.getX(), plano.getY()+factor*20, plano.getZ());
                break;
            case KeyEvent.VK_D:
                this.plano = new Point3D(plano.getX(), plano.getY(), plano.getZ()+factor*20);
                break;

        }//*/
        System.out.println("Presed");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    int time = 1;

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
                tubo.limpiarBuffer();
                tubo.dibujarVainaRara(time, plano);
                time +=20;
                if (time > 1000) {
                    time = 1;
                }


                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
