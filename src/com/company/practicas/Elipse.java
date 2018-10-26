package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Elipse extends JFrame {
    public static void main (String[] args) {
        Elipse  m = new Elipse();
        m.setBounds(100, 100, 1000, 1000);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.dibujarElipse(250,250,50, 20);
        m.dibujarElipse(250,250,70, 40);
        m.dibujarElipse(250,250,90, 60);
        m.pintarCirculo(100, 150, 50);
        m.pintarCirculo(100, 150, 70);
        m.pintarCirculo(100, 150, 90);
        m.dibujarRectangulo(500, 500, 600, 600);
        m.dibujarRectangulo(510, 510, 590, 590);
        m.dibujarLinea(300, 10, 400, 100);
        m.dibujarLinea(300, 800, 400, 800);
        m.dibujarLinea(300, 800, 400, 700);
        m.dibujarLinea(400, 850, 300, 850);
    }
    private BufferedImage buffer;
    private Graphics graphPixel;
    public Elipse() {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y,this);
    }
    public void dibujarRectangulo (int x0, int y0, int y1, int x1) {
        dibujarLinea(x0, y0, x1, y0);
        dibujarLinea(x0, y0, x0, y1);
        dibujarLinea(x0, y1, x1, y1);
        dibujarLinea(x1, y0, x1, y1);
    }
    public void dibujarElipse (int xc, int yc, int rx, int ry) {
        int x, y, p, px, py;
        int rx2, ry2, tworx2, twory2;
        ry2 = ry * ry;
        rx2 = rx * rx;
        twory2 = 2 * ry2;
        tworx2 = 2 * rx2;
        x = 0;
        y = ry;
        putPixel(x + xc, y + yc, Color.black);
        p = (int) Math.round(ry2 - rx2 * ry + 0.25 * rx2);
        px = 0;
        py = tworx2 * y;
        while (px < py) {
            x = x + 1;
            px = px + twory2;
            if (p < 0)
                p = p + ry2 + px;
            else {
                y = y - 1;
                py = py - tworx2;
                p = p + ry2 + px - py;
            }
            putPixel(x + xc, y + yc, Color.black);
            putPixel(-x + xc, y + yc, Color.black);
            putPixel(x + xc, -y + yc, Color.black);
            putPixel(-x + xc, -y + yc, Color.black);
        }
        p = (int) Math.round(ry2 * (x + 0.5) * (x + 0.5) + rx2 * (y - 1) * (y - 1) - rx2 * ry2);
        px = 0;
        py = tworx2 * y;
        while (y > 0) {
            y = y - 1;
            py = py - tworx2;
            if (p > 0)
                p = p + rx2 - py;
            else {
                x = x + 1;
                px = px + twory2;
                p = p + rx2 + py + px;
            }
            putPixel(x + xc, y + yc, Color.black);
            putPixel(-x + xc, y + yc, Color.black);
            putPixel(x + xc, -y + yc, Color.black);
            putPixel(-x + xc, -y + yc, Color.black);
        }
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
    public void pintarCirculo (int xc, int yc, float r) {
        float p0 = 5.f/4.f - r;
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
