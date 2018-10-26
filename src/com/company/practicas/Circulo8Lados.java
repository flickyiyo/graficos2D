package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Circulo8Lados extends JFrame {
    public static void main (String[] args) {
        Circulo8Lados  m = new Circulo8Lados ();
        m.setBounds(100, 100, 500, 500);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.pintarCirculo(250, 250, 120);
    }
    private BufferedImage buffer;
    private Graphics graphPixel;
    public Circulo8Lados  () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y,this);
    }

    public void pintarCirculo (int xc, int yc, float r) {
        float yr = r;
        int y = (int) r;
        int x = 0;
        while (x < yr){
            x = x + 1;
            yr = (float) Math.sqrt(r*r-x*x);
            y = (int)Math.round(yr);
            putPixel(xc+x,yc+y,Color.BLACK);
            putPixel(xc-x,yc+y,Color.BLACK);
            putPixel(xc+x,yc-y,Color.BLACK);
            putPixel(xc-x,yc-y,Color.BLACK);
            putPixel(xc+y,yc+x,Color.BLACK);
            putPixel(xc-y,yc+x,Color.BLACK);
            putPixel(xc+y,yc-x,Color.BLACK);
            putPixel(xc-y,yc-x,Color.BLACK);
        }
    }


}
