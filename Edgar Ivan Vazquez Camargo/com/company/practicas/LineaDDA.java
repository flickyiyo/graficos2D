package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class LineaDDA extends JFrame {
    private BufferedImage buffer;
    private Graphics graphPixel;
    public LineaDDA () {
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
        int dx = x1 - x0;
        int dy = y1 - y0;
        int steps;
        if (Math.abs(dx) > Math.abs(dy))
            steps = Math.abs(dx);
        else
            steps = Math.abs(dy);
        float xi = (float)dx/(float)steps;
        float yi = (float)dy/(float)steps;
        float x = x0, y = y0;
        putPixel((int)x, (int)y, Color.BLACK);
        for (int i = 1; i < steps; i++) {
            x+=xi;
            y+=yi;
            putPixel((int)x, (int)y, Color.BLACK);
        }
    }

    public static void main (String[] args) {
        LineaDDA m = new LineaDDA();
        m.setBounds(100, 100, 250, 250);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.dibujarLinea(100, 100, 70,10);
    }
}
