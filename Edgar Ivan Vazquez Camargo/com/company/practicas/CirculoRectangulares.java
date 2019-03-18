package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CirculoRectangulares extends JFrame {
    public static void main (String[] args) {
        CirculoRectangulares m = new CirculoRectangulares();
        m.setBounds(100, 100, 500, 500);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.pintarCirculo(250, 250, 120);
    }
    private BufferedImage buffer;
    private Graphics graphPixel;
    public CirculoRectangulares () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y,this);
    }

    public void pintarCirculo (int xc, int yc, int r) {
        for (int x = xc-r ; x < xc+r; x++) {
            float y = (float) (yc + Math.sqrt((r*r)-Math.pow((x-xc), 2)));
            putPixel(x, (int)y, Color.BLACK);
            y = (float) (yc - Math.sqrt((r*r)-Math.pow((x-xc), 2)));
            putPixel(x, (int)y, Color.BLACK);
        }
    }
}
