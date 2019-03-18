package com.company.practicas;

import java.awt.*;

public class GrosorLinea extends Window {
    public GrosorLinea() {
        super();
    }

    public static void main(String []args) {
        GrosorLinea grosorLinea = new GrosorLinea();
        grosorLinea.dibujarLinea(300, 100, 100,200, 5);
        //grosorLinea.paint(grosorLinea.getGraphics());
    }

    public float pendiente(int x1, int y1, int x2, int y2) {
        return ((float)x2-x1)/((float)y1-y1);
    }

    public void putPixelConGrosor(int x, int y, int grosor, float pendiente) {
        Position p = new Position(x, y);
        if (pendiente > 1) {
            int yInicial = p.getY() - (grosor/2);
            for (int i = yInicial; i < yInicial+grosor; i++) {
                putPixel(p.getX(), i, Color.BLACK);
            }
        } else {
            int xInicial = p.getX() - (grosor/2);
            for (int i = xInicial; i < xInicial+grosor; i++) {
                putPixel(i, p.getY(), Color.BLACK);
            }
        }
    }



    public void dibujarLinea(int x1, int y1, int x2, int y2, int grosor) {
        int a, b, pk, aux, yk = y1, xk = x1;
        float m = Math.abs(pendiente(x1, y1, x2, y2));
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
                    putPixelConGrosor(xk, yk, grosor, m);
                    pk += a;
                } else {
                    if (y2 > y1)

                        putPixelConGrosor(xk, ++yk, grosor, m);
                    else
                        putPixelConGrosor(xk, --yk, grosor, m);

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
                    putPixelConGrosor(xk, yk, grosor, m);
                    pk += a;
                } else {
                    if (x2 > x1)
                        putPixelConGrosor(++xk, yk, grosor, m);
                    else
                        putPixelConGrosor(--xk, yk, grosor, m);
                    pk += b;
                }
            }
        }
    }
}
