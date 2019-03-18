package com.company.practicas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BufferedWindow extends JFrame {
    protected BufferedImage buffer;
    protected Graphics graphPixel;
    protected BufferedImage fondo;
    protected Graphics gFondo;
    protected int []mascara;
    public BufferedWindow  () {
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphPixel = (Graphics2D)buffer.getGraphics();
        setBounds(100, 100, 500, 500);
        fondo = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        gFondo = (Graphics2D)fondo.getGraphics();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, this);
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0,0, c.getRGB());
        gFondo.drawImage(buffer, x, y, this);
    }
}
