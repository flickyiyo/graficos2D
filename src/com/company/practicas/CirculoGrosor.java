package com.company.practicas;

import java.awt.*;

public class CirculoGrosor extends Window {
    public CirculoGrosor() {
        super();
    }
    public static void main(String []args) {
        CirculoGrosor circuloGrosor = new CirculoGrosor();
        circuloGrosor.pintarCirculo(250, 250, 120, 4);
    }

    public void pintarPixelGrosor(int x, int y, int grosor) {
        Position p = new Position(x, y);
        int yInicial = p.getY() - (grosor/2);
        int xInicial = p.getX() - (grosor/2);
        for (int i = xInicial; i < xInicial+grosor; i++) {
            for (int j = yInicial; j < yInicial+grosor; j++ ){
                putPixel(i, j, Color.BLACK);
            }
        }
    }

    public void pintarCirculo (int xc, int yc, float r, int grosor) {
        float p0 = 5.f/4.f - r;
        float yr = r;
        int y = (int) r;
        int x = 0;
        while (x < yr){
            x = x + 1;
            yr = (float) Math.sqrt(r*r-x*x);
            y = (int)Math.round(yr);
            pintarPixelGrosor(xc+x,yc+y, grosor);
            pintarPixelGrosor(xc-x,yc+y, grosor);
            pintarPixelGrosor(xc+x,yc-y, grosor);
            pintarPixelGrosor(xc-x,yc-y, grosor);
            pintarPixelGrosor(xc+y,yc+x, grosor);
            pintarPixelGrosor(xc-y,yc+x, grosor);
            pintarPixelGrosor(xc+y,yc-x, grosor);
            pintarPixelGrosor(xc-y,yc-x, grosor);
        }
    }
}
