package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class ModeloMatematico extends JFrame {
    private BufferedImage buffer;
    private Graphics graphPixel;
    public ModeloMatematico () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, (ImageObserver) this);
    }

    public float pendiente (int x0, int y0, int x1, int y1) {
        return ((float)y1-y0)/((float)x1-x0);
    }

    public int b(int y0, int x0, int m) {
        return y0 - m*x0;
    }

    public void dibujarLinea (int x0, int y0, int x1, int y1) {
        float dx = x1 -x0;
        float dy = y1 - y0;
        float steps = Math.abs(dy);
        if (Math.abs(dx) > Math.abs(dy))
            steps = Math.abs(dx);

        float xincs = dx / steps;
        float yincs = dy / steps;
        float x = x0;
        float y = y0;
        System.out.println("" + steps + " " + " " + xincs + " " + yincs);
        putPixel((int)x, (int)y, Color.black);
        for (int k = 1; k < steps; k++) {
            x = x + xincs;
            y = y + yincs;
            putPixel((int)x, (int)y, Color.black);
        }
    }

    public static void main (String[] args) {
        ModeloMatematico m = new ModeloMatematico();
        m.setBounds(100, 100, 250, 250);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.dibujarLinea(30, 30, 20,100);
    }
}
