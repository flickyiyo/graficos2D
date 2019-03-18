package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CirculoPuntoMedio extends JFrame {
    public static void main (String[] args) {
        CirculoPuntoMedio  m = new CirculoPuntoMedio ();
        m.setBounds(100, 100, 500, 500);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.pintarCirculo(250, 250, 120);
    }
    private BufferedImage buffer;
    private Graphics graphPixel;
    public CirculoPuntoMedio  () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y,this);
    }

    public void pintarCirculo (int xc, int yc, float r) {
        float p0 = 5.f/4.f - r;
        float y = r;
        for (int x = 0 ; x < y; x++) {
            if (p0 < 0) {
                p0 = p0 + 2*x + 1;
            } else {
                y = y - 1;
                p0 = p0 + 2*(x - y) + 1;
            }
            putPixel((int)Math.round(xc+x),(int)Math.round(yc+y),Color.BLACK);
            putPixel((int)Math.round(xc-x),(int)Math.round(yc+y),Color.BLACK);
            putPixel((int)Math.round(xc+x),(int)Math.round(yc-y),Color.BLACK);
            putPixel((int)Math.round(xc-x),(int)Math.round(yc-y),Color.BLACK);
            putPixel((int)Math.round(xc-y),(int)Math.round(yc+x),Color.BLACK);
            putPixel((int)Math.round(xc+y),(int)Math.round(yc+x),Color.BLACK);
            putPixel((int)Math.round(xc+y),(int)Math.round(yc-x),Color.BLACK);
            putPixel((int)Math.round(xc-y),(int)Math.round(yc-x),Color.BLACK);
        }
    }


}
