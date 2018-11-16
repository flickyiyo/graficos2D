package com.company.proyecto;

import com.company.practicas.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Asteroid extends Sprite implements Runnable {
    JFrame parent;
    boolean shouldStop = false;
    int tipoFigura;
    int []positionsX = {30, 420, 30, 420};
    int []positionsY = {30, 420, 30, 420};
    int times = 1;
    Thread thread;
    public Asteroid() {
        Random rnd = new Random();
        int rndX = rnd.nextInt(4);
        int rndY = rnd.nextInt(4);
        height  =41;
        tipoFigura = getRandomFigura();
        width = 41;
        if (tipoFigura%2==0)
            width = 61;
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.pixel = new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        this.gPixel = pixel.getGraphics();
        this.gBuffer = buffer.getGraphics();
        this.x = positionsX[rndX];
        this.width = buffer.getWidth();
        this.y = positionsY[rndY];
        this.height = buffer.getHeight();
        this.angulo = -90;
        this.positionsX = new int[]{-width, 500+width,-width,500+width};
        this.positionsY = new int[]{-height, 500+height,-height,500+height};
        thread = new Thread(this);
    }

    public int getRandomFigura () {
        Random rnd = new Random();
        return rnd.nextInt(4);
    }

    public void setDimensiones(int width, int height) {
        this.width = width;
        this.height = height;
        this.buffer = new BufferedImage(width+3, height+3, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
    }

    public void setShouldStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    public void dibujarAsteroide () {
        this.grosor=2;
        this.dibujarElipse(width/2, height/2, width/2, height/2);
        this.rellenar(new Position(width/2, height/2), Color.orange);
        // 10, 10,5,3
    }

    public void setParent(JFrame parent) {
        this.parent = parent;
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

    @Override
    public void putPixel(int x, int y, Color c) {
        //if (x+this.x < 0 || x+this.x > parent.getWidth() || y+this.y < 0 || y+this.y > parent.getHeight()) {return;}
        super.putPixel(x, y, c);
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

    public void start() {
        this.thread.start();
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

    @Override
    public void run() {
        int despX = 1; //rnd.nextInt((8 + 4) + 1) + 4;
        int despY = 1; //rnd.nextInt((8 + 4) + 1) + 4;
        while(!shouldStop) {
            try {
                Thread.sleep(10);
                this.x += despX;
                this.y += despY;
                if (
                        (this.x > (parent.getWidth() + 2*this.width)
                        || this.y > (parent.getHeight() + 2*this.height))
                        || this.x < - width || this.y < -2*height
                ) {
                    Random rnd = new Random();
                    int rndX = rnd.nextInt(4);
                    int rndY = rnd.nextInt(4);
                    if (rndX%2==0) despX = rnd.nextInt(5) + 2;
                    else despX = -rnd.nextInt(5)-2;
                    if (rndY%2==0) despY = rnd.nextInt(5) + 2;
                    else despY = -rnd.nextInt(5) -2 ;
                    this.x = positionsX[rndX];
                    this.y = positionsY[rndY];
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
