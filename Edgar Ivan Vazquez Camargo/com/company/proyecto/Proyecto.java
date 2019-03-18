package com.company.proyecto;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    Clip clip;
    public ArrayList<Proyectil> proyectils;
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
        proyectils = new ArrayList<Proyectil>();
        asteroidList = new ArrayList<>();
        asteroidList.add(new Asteroid());
        asteroidList.add(new Asteroid());
        asteroidList.add(new Asteroid());
        for (Asteroid ast : asteroidList) {
            ast.setParent(this);
            ast.dibujarAsteroide();
        }
        setOnClose();
        playMusic();
        run();
    }

    public void playMusic () {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("/Users/yiyo/Desktop/thrust.wav"));
            clip= AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            Thread.sleep(1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
        int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
        int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        if (isAlive) {
            nave.setX(x);
            nave.setY(y);
        } else isAlive = true;

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
            case KeyEvent.VK_SPACE:

        }
        if (nave != null) {
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


            while (true) {
                //System.out.println("in the loop");
                if (!isInitialized) {
                    setVisible(true);
                    for (Asteroid ast : asteroidList)
                        ast.start();
                    asteroid.start();
                    isInitialized = true;
                } else {
                    if (nave != null) {
                        if (nave.colisiona(asteroid)) {
                            nave.setX(getWidth()/2 - nave.getWidth()/2);
                            nave.setY(getHeight()/2 - nave.getHeight()/2);
                            isAlive = false;
                            //System.exit(0);
                        }
                        for (Asteroid ast : asteroidList) {
                            if (nave.colisiona(ast)) {
                                nave.setX(getWidth()/2 - nave.getWidth()/2);
                                nave.setY(getHeight()/2 - nave.getHeight()/2);

                                isAlive = false;
                                //System.exit(0);
                            }
                        }//*/
                    }
                }
                paint(getGraphics());
            }
        } catch (Exception ex ){

        }
    }
}
