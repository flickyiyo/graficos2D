package com.company.practicas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Inundacion extends BufferedWindow implements MouseListener {

    public static void main(String []args) {
        Inundacion scanLine = new Inundacion();
        scanLine.dibujarRect(50, 50, 250, 250);
        //scanLine.paint(scanLine.getGraphics());
        scanLine.addMouseListener(scanLine);
    }

    @Override
    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0,c.getRGB());
        gFondo.drawImage(buffer, x, y, this);
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void dibujarRect(int x1, int y1, int x2, int y2) {
        dibujarLinea(x1, y1, x2, y1);
        dibujarLinea(x1, y1, x1, y2);
        dibujarLinea(x1, y2, x2, y2);
        dibujarLinea(x2, y1, x2, y2);
    }

    public void rellenar(Position punto, int color) {
        int colorFondo = fondo.getRGB(punto.getX(), punto.getY());
        System.out.println(color == colorFondo);
        if(colorFondo == color) {
            putPixel(punto.getX(), punto.getY(), Color.BLUE);
            rellenar(new Position(punto.getX()+1, punto.getY()), color);
            rellenar(new Position(punto.getX(), punto.getY()+1), color);
            rellenar(new Position(punto.getX()-1, punto.getY()), color);
            rellenar(new Position(punto.getX(), punto.getY()-1), color);
        }
        System.out.println("Executed");
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

    @Override
    public void mouseClicked(MouseEvent e) {
        Position punto = new Position(e.getX(), e.getY());
        System.out.println(punto.getX() + " " + punto.getY());
        rellenar(punto, fondo.getRGB(punto.getX(), punto.getY()));
        paint(getGraphics());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
