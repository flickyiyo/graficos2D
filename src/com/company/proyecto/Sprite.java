package com.company.proyecto;

import com.company.practicas.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    protected BufferedImage pixel;
    protected Graphics gPixel;
    protected BufferedImage buffer;
    protected Graphics gBuffer;
    int angulo;

    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }

    public int getGrosor() {
        return grosor;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    int grosor = 1;
    int width, height;
    protected int x, y;
    public BufferedImage rotateImage(BufferedImage img, int angle){
        int diag = (int) Math.sqrt(img.getWidth()*img.getWidth()+img.getHeight()*img.getHeight());
        this.buffer = new BufferedImage(diag, diag, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
        for (int i = -img.getHeight()/2; i < img.getHeight()/2; i++) {
            for (int j = -img.getWidth()/2; j < img.getWidth()/2; j++) {
                int clr= img.getRGB((j+img.getWidth()/2),(i+img.getHeight()/2));
                int x = (int)(j*Math.cos(Math.toRadians(angle))-i*Math.sin(Math.toRadians(angle)));
                int y = (int)(j*Math.sin(Math.toRadians(angle))+i*Math.cos(Math.toRadians(angle)));
                if(clr!=0) putPixel(diag/2+x,diag/2+y, Color.black);
            }
        }
        return this.buffer;
    }


    public void rotar (BufferedImage img, int angle) {
        int diag = (int) Math.sqrt(img.getWidth()*img.getWidth()+img.getHeight()*img.getHeight());
        for (int i = -img.getHeight()/2; i < img.getHeight()/2; i++) {
            for (int j = -img.getWidth()/2; j < img.getWidth()/2; j++) {
                int clr= img.getRGB((j+img.getWidth()/2),(i+img.getHeight()/2));
                int red   = (clr & 0x00ff0000) >> 16;
                int green = (clr & 0x0000ff00) >> 8;
                int blue  =  clr & 0x000000ff;
                int x = (int)(j*Math.cos(Math.toRadians(angle))-i*Math.sin(Math.toRadians(angle)));
                int y = (int)(j*Math.sin(Math.toRadians(angle))+i*Math.cos(Math.toRadians(angle)));

            }
        }
    }

    public void pintarCirculo (int xc, int yc, float r) {
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

    public void rellenar(Position punto, Color color) {
        int x0 = punto.getX()-1;
        int y0 = punto.getY()-1;
        int oldColor = buffer.getRGB(x0, y0);
        int nuevoColor = buffer.getRGB(x0, y0);
        for (int y = y0; oldColor == nuevoColor; y++) {
            for (int x = x0; oldColor == nuevoColor; x++) {
                if(x<=0 || x>getWidth()-1) break;
                oldColor = buffer.getRGB(x + 1, y);
                putPixel(x, y, color);
            }
            oldColor = buffer.getRGB(x0 - 1, y);
            for (int x = x0; oldColor == nuevoColor; x--) {
                if(x<=0 || x>getWidth()-1) break;
                oldColor = buffer.getRGB(x - 1, y);
                putPixel(x, y, color);
            }
            oldColor = buffer.getRGB(x0, y + 1);
        }
        oldColor = buffer.getRGB(x0, y0 - 1);
        for (int y = y0 - 1; oldColor == nuevoColor; y--) {
            if(y<=0 || y>getHeight()-1) break;
            for (int x = x0; oldColor == nuevoColor; x++) {
                if(x<=0 || x>getWidth()-1) break;
                oldColor = buffer.getRGB(x + 1, y);
                putPixel(x, y, color);
            }
            oldColor = buffer.getRGB(x0 - 1, y);
            for (int x = x0; oldColor == nuevoColor; x--) {
                if(x<=0 || x>getWidth()-1) break;
                oldColor = buffer.getRGB(x - 1, y);
                putPixel(x, y, color);
            }
            oldColor = buffer.getRGB(x0, y - 1);
        }

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getPixel() {
        return pixel;
    }

    public Graphics getgBuffer() {
        return gBuffer;
    }

    public Graphics getgPixel() {
        return gPixel;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public void setgBuffer(Graphics gBuffer) {
        this.gBuffer = gBuffer;
    }

    public void setgPixel(Graphics gPixel) {
        this.gPixel = gPixel;
    }

    public void setPixel(BufferedImage pixel) {
        this.pixel = pixel;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
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

    public void putPixel(int x, int y, Color c) {
        pixel.setRGB(0,0, c.getRGB());
        gBuffer.drawImage(pixel, x, y,  null);
    }
}
