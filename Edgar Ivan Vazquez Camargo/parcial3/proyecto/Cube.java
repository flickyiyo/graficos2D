package parcial3.proyecto;

import javafx.geometry.Point3D;
import parcial3.CurvaSuperficie.Curva;
import parcial3.Sprite3D;
import sun.awt.geom.Curve;

import java.awt.image.BufferedImage;

public class Cube extends Sprite3D {
    public Cube(int x, int y, int width, int height) {
        super(x, y, width, height);
        anguloX = anguloY = anguloZ = 0;
    }
    private int anguloX, anguloY, anguloZ;
    public void limpiarBuffer() {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
    }

    public Point3D[] rotarPuntos(Point3D []vertices) {
        Point3D []vert = new Point3D[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            vert[i] = rotarX(vertices[i]);
            vert[i] = rotarY(vertices[i]);
            vert[i] = rotarZ(vertices[i]);
        }
        return vert;
    }

    @Override
    public void dibujarCubo(int x1, int y1, int z1, int x2, int y2, int z2, Point3D plano) {
        Point3D vertices[] = new Point3D[]{
                new Point3D(x1, y1, z1),
                new Point3D(x2, y1, z1),
                new Point3D(x1, y2, z1),
                new Point3D(x2, y2, z1),
                new Point3D(x1, y1, z2),
                new Point3D(x2, y1, z2),
                new Point3D(x1, y2, z2),
                new Point3D(x2, y2, z2)
        };
        limpiarBuffer();
        int cx = this.getWidth() / 2;
        int cy = this.getHeight() / 2;
        x1-=cx;
        x2-=cx;
        y1-=cy;
        y2-=cy;
        Point3D tmp1 = rotarX(new Point3D(x1, y1, z1));
        Point3D tmp2 = rotarX(new Point3D(x2, y2, z2));
        tmp1 = rotarY(tmp1);
        tmp2 = rotarY(tmp2);
        tmp1 = rotarZ(tmp1);
        tmp2 = rotarZ(tmp2);
        Curva.printPoint(tmp1);

        x1 = cx + (int) tmp1.getX();
        x2 = cx + (int) tmp2.getX();
        y1 = cy + (int) tmp1.getY();
        y2 = cy + (int) tmp2.getY();
        z1 = (int) tmp1.getZ();
        z2 = (int) tmp2.getZ();
        vertices = rotarPuntos(vertices);
        dibujarLinea3D(x1, y1, z1, x1 ,  y1, z2, plano);
        dibujarLinea3D(x1, y1, z1, x2 ,  y1, z1, plano);
        dibujarLinea3D(x1 , y1, z1, x1 ,  y2, z1, plano);

        dibujarLinea3D(x2 ,y2, z1, x2 ,  y1, z1, plano);
        dibujarLinea3D(x2 ,y2, z1, x1 ,  y2, z1, plano);
        dibujarLinea3D(x2 ,y2, z1, x2 ,  y2, z2, plano);

        //dibujarLinea3D(x2 ,  y1, z2, x2 ,  y1, z1, plano);
        dibujarLinea3D(x2 ,  y1, z1, x2, y1, z2, plano);
        dibujarLinea3D(x2 ,  y1, z2, x1 ,  y1, z2, plano);
        dibujarLinea3D(x2 ,  y1, z2, x2 ,  y2, z2, plano);

        dibujarLinea3D(x1 ,  y2, z2,  x1 ,  y1, z2, plano);
        dibujarLinea3D(x1 ,  y2, z2,  x2 ,  y2, z2, plano);
        dibujarLinea3D(x1 ,  y2, z1, x1 ,  y2, z2, plano);

        //*/
        parent.paint(parent.getGraphics());
    }

    public int getAnguloZ() {
        return anguloZ;
    }

    public void setAnguloZ(int anguloZ) {
        this.anguloZ = anguloZ;
    }

    public int getAnguloY() {
        return anguloY;
    }

    public void setAnguloY(int anguloY) {
        this.anguloY = anguloY;
    }

    public int getAnguloX() {
        return anguloX;
    }

    public void setAnguloX(int anguloX) {
        this.anguloX = anguloX;
    }

    public Point3D rotarZ(Point3D punto) {
        return new Point3D(
                Math.cos(Math.toRadians(anguloZ))*(punto.getX()) - Math.sin(Math.toRadians(anguloZ))*(punto.getY()),
                Math.sin(Math.toRadians(anguloZ))*(punto.getX()) + Math.cos(Math.toRadians(anguloZ))*(punto.getY()),
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
}
