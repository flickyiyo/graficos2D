package com.company.practicas;

import java.awt.*;

public class LineaPuntoMedio extends Window {
    public static void main(String []args) {
        LineaPuntoMedio lineaPuntoMedio = new LineaPuntoMedio();
        lineaPuntoMedio.dibujarLinea(400, 30, 20, 100);
    }

    public void dibujarLinea (int x0, int y0, int x1, int y1) {
        int x, y, dx, dy, pk, A, B, pxlx, pxly;
        dx = (x1 - x0);
        dy = (y1 - y0);
        if (dy < 0) {
            dy = -dy;
            pxly = -1;
        }
        else {
            pxly = 1;
        }
        if (dx < 0) {
            dx = -dx;
            pxlx = -1;
        } else {
            pxlx = 1;
        }

        x = x0;
        y = y0;
        putPixel(x0,y0,Color.BLACK);

        if(dx>dy){
            pk = 2*dy - dx;
            A = 2*dy;
            B = 2*(dy-dx);
            while (x != x1){
                x = x + pxlx;
                if (pk < 0){
                    pk = pk + A;
                } else {
                    y = y + pxly;
                    pk = pk + B;
                }
                putPixel(x,y,Color.BLACK);
            }
        } else {
            pk = 2*dx - dy;
            A = 2*dx;
            B = 2*(dx-dy);
            while (y != y1){
                y = y + pxly;
                if (pk < 0){
                    pk = pk + A;
                } else {
                    x = x + pxlx;
                    pk = pk + B;
                }
                putPixel(x,y,Color.BLACK);
            }
        }
    }
}
