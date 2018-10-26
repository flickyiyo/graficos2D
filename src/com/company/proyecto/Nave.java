package com.company.proyecto;

import com.company.practicas.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public class Nave extends Sprite {
    Point p1l1 = new Point(50, 40);
    Point p2l1 = new Point(40, 75);
    Point p1l2 = new Point(50, 40);
    Point p2l2 = new Point(60, 75);
    Point p1l3 = new Point(40, 75);
    Point p2l3 = new Point(60, 75);
    int cx, cy;
    public Nave (int x, int y) {
        this.width = 21;
        this.height = 36;
        this.buffer = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
        this.pixel = new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        this.gPixel = pixel.getGraphics();
        this.gBuffer = buffer.getGraphics();
        this.x = x;
        this.y = y;
        this.angulo = 0;
        this.width = 30;
        this.height = 30;
        cx = buffer.getWidth()/2;
        cy = buffer.getHeight()/2;
        p1l1 = new Point(cx, cy - 10);
        p2l1 = new Point(cx -10, cy + 15);
        p1l2 = new Point(cx, cy -10);
        p2l2 = new Point(cx + 10, cy + 15);
        p1l3 = new Point(cx-10, cy+15);
        p2l3 = new Point(cx+10, cy+15);
    }

    @Override
    public void setAngulo(int angulo) {
        this.angulo = angulo;
        /*rotarRespectoAngulo(p1l1);
        rotarRespectoAngulo(p2l1);
        rotarRespectoAngulo(p1l2);
        rotarRespectoAngulo(p2l2);
        rotarRespectoAngulo(p1l3);
        rotarRespectoAngulo(p2l3);*/
    }


    public boolean colisiona(Sprite sprite) {
        Rectangle spriteArea = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        Rectangle naveArea = new Rectangle(x, y, width, height);
        return spriteArea.intersects(naveArea);
    }

    public int getXRotada(int x, int y) {
        float cos = (float) Math.cos(Math.toRadians(angulo));
        float sin = (float) Math.sin(Math.toRadians(angulo));
        x -= getWidth()/2;
        float newX = x * cos - y * sin;
        x +=newX - cx;
        return (int) x;
        //return (int)(x*Math.cos(Math.toRadians(angulo))-y*Math.sin(Math.toRadians(angulo))) + getWidth()/2;
    }

    @Override
    public void putPixel(int x, int y, Color c) {
        if (!c.equals(Color.BLACK)) {
            super.putPixel(x, y, c);
            return;
        }
        Double cos =  Math.cos(Math.toRadians(angulo));
        Double sin =  Math.sin(Math.toRadians(angulo));
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        double rX = x - cx;
        double rY = y - cy;
        double nuevaX = rX * cos - rY * sin;
        double nuevaY = rX * sin + rY * cos;
        super.putPixel((int)nuevaX + cx, (int)nuevaY+cy, c);
    }

    public void rotarRespectoAngulo(Point p) {
        Double cos =  Math.cos(Math.toRadians(angulo));
        Double sin =  Math.sin(Math.toRadians(angulo));
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        double rX = p.getX() - cx;
        double rY = p.getY() - cy;
        double nuevaX = rX * cos - rY * sin;
        double nuevaY = rX * sin + rY * cos;
        p.setLocation(nuevaX + cx, nuevaY + cy);
    }

    public void dibujarNave () {
        this.buffer = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
        super.putPixel(1, 1, Color.BLACK);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dibujarLinea((int)p1l1.getX(), (int)p1l1.getY(), (int)p2l1.getX(), (int)p2l1.getY());
        dibujarLinea((int)p1l2.getX(), (int)p1l2.getY(), (int)p2l2.getX(), (int)p2l2.getY());
        dibujarLinea((int)p1l3.getX(), (int)p1l3.getY(), (int)p2l3.getX(), (int)p2l3.getY());
        Color rosa = new Color(221,150,156);
        rellenar(new Position(cy, cx), rosa);
        //*/
        /*dibujarLinea(10, 0, 0, 35);
        dibujarLinea(10, 0, 20, 35);
        dibujarLinea(0, 35, 20, 35);
        */
    }



}
