package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TiposDeLinea extends JFrame {
    public static void main (String[] args) {
        TiposDeLinea  m = new TiposDeLinea();
        m.setBounds(100, 100, 500, 500);
        m.setVisible(true);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // m.pintarCirculo(250, 250, 120);
        m.setMascara("1111111111111111111000011000011");
        m.tipoLinea(new Position(100, 300), new Position(20, 40));
    }
    private BufferedImage buffer;
    private Graphics graphPixel;
    private int []mascara;
    public TiposDeLinea  () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y,this);
    }

    public void setMascara(String mascara) {
        this.mascara = new int[mascara.length()];
        for (int i = 0; i < mascara.length(); i++)
            this.mascara[i] = Integer.parseInt(String.valueOf(mascara.charAt(i)));
    }

    public void tipoLinea(Position inicio, Position fin) {
        float dx = fin.getX() - inicio.getX();
        float dy = fin.getY() - inicio.getY();
        float steps = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy);
        float incX = dx / steps;
        float inxY = dy / steps;
        float x = inicio.getX();
        float y = inicio.getY();
        int contador = 0;
        putPixel((int)x, (int)y, Color.BLACK);
        for (int i = 1; i <= steps; i++ ) {
            x += incX;
            y += inxY;
            if (contador == this.mascara.length)
                contador = 0;
            if (this.mascara[contador] == 1)
                putPixel((int)x, (int)y, Color.BLACK);
            contador ++;
        }

    }
}
