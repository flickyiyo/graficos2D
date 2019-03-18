package com.company.practicas;

import java.awt.*;

public class ModeloMejorado extends Window {
    public static void main(String []arg) {
        ModeloMejorado modeloMatematico = new ModeloMejorado();
        modeloMatematico.dibujarLinea(150, 50, 40, 200);
    }

    public void setMascara(String mascara) {
        this.mascara = new int[mascara.length()];
        for (int i = 0; i < mascara.length(); i++)
            this.mascara[i] = Integer.parseInt(String.valueOf(mascara.charAt(i)));
    }

    public float pendiente(int x0, int y0, int x1, int y1) {
        return (y1-y0)/((float)x1-x0);
    }
    public float b(int y0, int x0, float m) {
        return y0 - m*x0;
    }

    public void dibujarLinea(int x0, int y0, int x1, int y1) {
        float m = pendiente(x0, y0, x1, y1);
        float b = b(y0, x0, m);
        if (Math.abs(m)>1) {
            for(int y = y0; y < y1 && y >= y0; y = (y0<y1) ? y+1: y - 1) {
                float x = (y - b)/m;
                putPixel((int)y, (int)x, Color.BLACK);
            }
        } else {
            for(int x = x0; x < x1 && x >= x0; x = (x0<x1) ? x+1: x - 1) {
                float y = m*x+b;
                putPixel((int)y, (int)x, Color.BLACK);
            }
        }
    }
}
