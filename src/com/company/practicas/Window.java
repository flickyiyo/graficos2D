package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Window extends JFrame {
    protected BufferedImage buffer;
    protected Graphics graphPixel;
    protected int []mascara;
    public Window  () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D)buffer.getGraphics();
        setBounds(100, 100, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*@Override
    public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, this);
    }*/

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
}
