package com.company.proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Proyecto extends JFrame implements KeyListener, Runnable{
    public Nave nave;
    public BufferedImage fondo;
    public Asteroid asteroid;
    int incX = 0;
    int incY = 0;
    int anguloNave = 0;
    boolean isAlive = true;
    boolean isInitialized = false;
    public ArrayList<Asteroid> asteroidList;
    public Proyecto() {
        setBounds(100, 100, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fondo = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        addKeyListener(this);
        nave = new Nave(getWidth()/2, getHeight()/2);
        nave.dibujarNave();
        asteroid = new Asteroid();
        asteroid.dibujarAsteroide();
        asteroid.setParent(this);
        asteroidList = new ArrayList<>();
        asteroidList.add(new Asteroid());
        asteroidList.add(new Asteroid());
        asteroidList.add(new Asteroid());
        for (Asteroid ast : asteroidList) {
            ast.setParent(this);
            ast.dibujarAsteroide();
        }
        setOnClose();
        run();
    }

    public void setOnClose() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                asteroid.setShouldStop(true);
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                super.windowLostFocus(e);
                asteroid.setShouldStop(true);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        fondo.getGraphics().fillRect(0,0, fondo.getWidth(), fondo.getHeight());
        nave.dibujarNave();
        fondo.getGraphics().drawImage(nave.buffer, nave.getX(), nave.getY(),this);
        fondo.getGraphics().drawImage(asteroid.getBuffer(), asteroid.getX(), asteroid.getY(), this);
        for (Asteroid ast : asteroidList)
            fondo.getGraphics().drawImage(ast.getBuffer(), ast.getX(), ast.getY(), this);
        g.drawImage(fondo, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public static void main(String[] args) {
        Proyecto proyecto = new Proyecto();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("typed");
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_Q:
                this.anguloNave -= 15;
                nave.setAngulo(anguloNave);
                break;
            case KeyEvent.VK_E:
                this.anguloNave +=15;
                nave.setAngulo(anguloNave);
                break;
            case KeyEvent.VK_A:
                incX = -5;
                break;
            case KeyEvent.VK_D:
                incX = +5;
                break;
            case KeyEvent.VK_W:
                incY = -5;
                break;
            case KeyEvent.VK_S:
                incY = 5;
                break;
        }
        if (nave != null) {
            System.out.println(anguloNave+"°");

            nave.setY(nave.getY() + incY);
            nave.setX(nave.getX() + incX);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                incX = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                incY = 0;
                break;
        }
    }

    @Override
    public void run() {
        try {


            while (isAlive) {
                //System.out.println("in the loop");
                if (!isInitialized) {
                    setVisible(true);
                    for (Asteroid ast : asteroidList)
                        ast.start();
                    asteroid.start();
                    isInitialized = true;
                } else {
                    if (nave != null) {
                        Thread.sleep(1);
                        if (nave.colisiona(asteroid)) {
                            nave = null;
                            isAlive = false;
                            asteroid.thread.setName("thread1");
                            System.exit(0);
                        }
                        for (Asteroid ast : asteroidList) {
                            if (nave.colisiona(ast)) {
                                nave = null;
                                isAlive = false;
                                asteroid.thread.setName("thread1");
                                System.exit(0);
                            }
                        }
                    }
                }
                Thread.sleep(1);
                paint(getGraphics());
            }
        } catch (Exception ex ){

        }
    }
}
