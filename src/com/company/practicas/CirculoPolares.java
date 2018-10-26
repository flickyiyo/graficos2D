package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CirculoPolares extends JFrame {
    public static void main (String[] args) {
        CirculoPolares m = new CirculoPolares();
        m.setBounds(100, 100, 500, 500);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.pintarCirculo(250, 250, 120);
    }
    private BufferedImage buffer;
    private Graphics graphPixel;
    public CirculoPolares () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y,this);
    }

    public void pintarCirculo (int xc, int yc, int r) {
        for (float i = 0 ; i < 360; i+=.3) {
            float x = (float) (xc + r*Math.cos(i));
            float y = (float) (yc + r*Math.sin(i));
            putPixel((int)x, (int)y, Color.BLACK);
        }
    }


}
