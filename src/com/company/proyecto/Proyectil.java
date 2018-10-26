package com.company.proyecto;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Proyectil extends Sprite {
    int radioX;
    int radioY;
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

    public boolean colisiona(Sprite sprite) {
        Rectangle proyectil = new Rectangle(x, y, width, height);
        Rectangle spriteRect = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        return proyectil.intersects(spriteRect);
    }


}
