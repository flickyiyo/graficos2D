package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CirculoBresenham extends JFrame {
    public static void main (String[] args) {
        CirculoBresenham  m = new CirculoBresenham ();
        m.setBounds(100, 100, 500, 500);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.pintarCirculo(250, 250, 120);
    }
    private BufferedImage buffer;
    private Graphics graphPixel;
    public CirculoBresenham  () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y,this);
    }

    public void pintarCirculo (int xc, int yc, int radio) {
        int x = -radio, y = 0, err = 2-2*radio;
        do {
            putPixel(xc - x, yc + y, Color.BLACK);
            putPixel(xc - y, yc - x, Color.BLACK);
            putPixel(xc + x, yc - y, Color.BLACK);
            putPixel(xc + y, yc + x, Color.BLACK);
            radio = err;
            if (radio > x) err += ++x * 2 + 1;
            if
            (radio <= y) err += ++y * 2 + 1;
        } while (x < 0);
    }


}
