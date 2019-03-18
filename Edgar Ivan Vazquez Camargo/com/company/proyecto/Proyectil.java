package com.company.proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

public class Proyectil extends Sprite implements Runnable {
    int radioX;
    int radioY;
    boolean shouldStop = false;
    Proyecto parent;
    public Proyectil(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
        this.pixel = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        this.gPixel = pixel.getGraphics();
        this.radioX = width/2;
        this.radioY = height/2;
    }

    public void dibujarProyectil () {
        this.pintarCirculo(getWidth()/2, getHeight()/2, getWidth()/2);
    }

    public boolean colisiona(Sprite sprite) {
        Rectangle proyectil = new Rectangle(x, y, width, height);
        Rectangle spriteRect = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        return proyectil.intersects(spriteRect);
    }

    public void trayectoria (int x0, int y0, int x1, int y1) {
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

    public int getRadioX() {
        return radioX;
    }

    public void setRadioX(int radioX) {
        this.radioX = radioX;
    }

    public int getRadioY() {
        return radioY;
    }

    public void setRadioY(int radioY) {
        this.radioY = radioY;
    }

    public boolean isShouldStop() {
        return shouldStop;
    }

    public void setShouldStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    public JFrame getParent() {
        return parent;
    }

    public void setParent(Proyecto parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        while (!shouldStop) {
            this.y--;
            if (this.y < -height) {
                this.shouldStop = true;
                ArrayList<Proyectil> proyectils = this.parent.proyectils;
                proyectils.remove(this);
                this.shouldStop = true;
            }
        }
    }
}
