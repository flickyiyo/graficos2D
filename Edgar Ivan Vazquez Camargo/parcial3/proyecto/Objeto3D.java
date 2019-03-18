package parcial3.proyecto;

import javafx.geometry.Point3D;
import jdk.nashorn.internal.objects.annotations.Getter;
import parcial3.Sprite3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Objeto3D extends Sprite3D {
    int anguloX, anguloY, anguloZ;
    public Objeto3D(int x, int y, int width, int height) {
        super(x, y, width, height);
        anguloX = 0;
        anguloY = 0;
        anguloZ = 0;
    }


    public Point3D rotarZ(Point3D punto) {
        return new Point3D(
                Math.cos(Math.toRadians(anguloZ))*(x) - Math.sin(Math.toRadians(anguloZ))*(y),
                Math.sin(Math.toRadians(anguloZ))*(x) + Math.cos(Math.toRadians(anguloZ))*(y),
                punto.getZ()
        );
    }

    public Point3D rotarY(Point3D punto) {
        return new Point3D(
                Math.cos(Math.toRadians(anguloY))*punto.getX() - Math.sin(Math.toRadians(anguloY))*punto.getZ(),
                punto.getY(),
                Math.sin(Math.toRadians(anguloY))*punto.getX() + Math.cos(Math.toRadians(anguloY))*punto.getZ()
        );
    }

    public Point3D rotarX(Point3D punto) {
        return new Point3D(
                punto.getX(),
                Math.cos(Math.toRadians(anguloX))*punto.getY() - Math.sin(Math.toRadians(anguloX))*punto.getZ(),
                Math.sin(Math.toRadians(anguloX))*punto.getY() + Math.cos(Math.toRadians(anguloX))*punto.getZ()

        );
    }

    public void limpiarBuffer() {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
    }


}
