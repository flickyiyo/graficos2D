package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Rectangulo extends JFrame {
    private BufferedImage buffer;
    private Graphics graphPixel;
    int x0, y0, x1, y1;
    public Rectangulo () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public int pendiente (int x0, int y0, int x1, int y1) {
        System.out.println(x0);
        System.out.println(x1);
        System.out.println(y0);
        System.out.println(y1);
        return (y1-y0)/(x1-x0);
    }

    public int b(int y0, int x0, int m) {
        return y0 - m*x0;
    }

    public void dibujarLinea (int x0, int y0, int x1, int y1) {
        float x;
        float y;
    }

    public void setCoordenadas(int x0, int x1, int y0, int y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }
    @Override
    public void paint(Graphics g) {
        try{
            dibujarLinea(x0, y0, x1, y0);
            Thread.sleep(500);
            dibujarLinea(x0, y0, x0, y1);
            Thread.sleep(500);
            dibujarLinea(x0, y1, x1, y1);
            Thread.sleep(500);
            dibujarLinea(x1, y0, x1, y1);
            Thread.sleep(500);
        } catch (Exception e) {

        }


    }

    public void dibujarRect (int x0, int x1, int y0, int y1) {
        try {

        } catch (Exception _ ){

        }

    }

    public static void main (String[] args) {
        Rectangulo m = new Rectangulo();
        m.setBounds(100, 100, 500, 500);
        m.setCoordenadas(40, 100, 200,250);
        m.setVisible(true);

        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*m.dibujarLinea(40, 200, 100, 200);
        m.dibujarLinea(40, 200, 40, 250);
        m.dibujarLinea(40, 250, 100, 250);
        m.dibujarLinea(100, 200, 100, 250);*/
    }
}
