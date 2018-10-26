package com.company.proyecto;

import java.awt.image.BufferedImage;

public class PokemonLetras extends Sprite {
    public PokemonLetras (int x, int y) {
        this.buffer = new BufferedImage(21, 36, BufferedImage.TYPE_INT_ARGB);
        this.pixel = new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        this.gPixel = pixel.getGraphics();
        this.gBuffer = buffer.getGraphics();
        this.x = x;
        this.y = y;
    }
}
