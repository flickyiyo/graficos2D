package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Bresenham extends JFrame {
    private BufferedImage buffer;
    private Graphics graphPixel;
    public Bresenham () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, (ImageObserver) this);
    }

    public int pendiente (int x0, int y0, int x1, int y1) {
        return (y1-y0)/(x1-x0);
    }

    public int b(int y0, int x0, int m) {
        return y0 - m*x0;
    }

    public void dibujarLinea(int x1, int y1, int x2, int y2) {
        int a, b, pk, aux, yk = y1, xk = x1;
        if (Math.abs(x2 - x1) > Math.abs(y2 - y1)) {
            a = 2 * Math.abs(y2 - y1);
            b = (2 * Math.abs(y2 - y1)) - (2 * Math.abs(x2 - x1));
            pk = (2 * Math.abs(y2 - y1)) - (Math.abs(x2 - x1));
            if (x2 < x1) {
                aux = x2;
                x2 = x1;
                x1 = aux;
            }
            for (xk = x1; xk < x2; xk++) {
                if (pk < 0) {
                    putPixel(xk, yk, Color.BLACK);
                    pk += a;
                } else {
                    if (y2 > y1)
                        putPixel(xk, ++yk, Color.BLACK);
                    else
                        putPixel(xk, --yk, Color.BLACK);

                    pk += b;
                }
            }
        } else if (Math.abs(x2 - x1) < Math.abs(y2 - y1)) {
            a = 2 * Math.abs(x2 - x1);
            b = (2 * Math.abs(x2 - x1)) - (2 * Math.abs(y2 - y1));
            pk = (2 * Math.abs(x2 - x1)) - (Math.abs(y2 - y1));
            if (y2 < y1) {
                aux = y2;
                y2 = y1;
                y1 = aux;
            }
            for (yk = y1; yk < y2; yk++) {
                if (pk < 0) {
                    putPixel(xk, yk, Color.BLACK);
                    pk += a;
                } else {
                    if (x2 > x1)
                        putPixel(++xk, yk, Color.BLACK);
                    else
                    putPixel(--xk, yk, Color.BLACK);
                    pk += b;
                }
            }
        }
    }

    public static void main (String[] args) {
        Bresenham m = new Bresenham();
        m.setBounds(100, 100, 500, 500);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.dibujarLinea(300, 100, 100,200);
    }
}
