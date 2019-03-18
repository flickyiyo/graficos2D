package parcial3.CurvaSuperficie;

import javafx.geometry.Point3D;
import parcial3.Sprite3D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Curva extends Sprite3D {
    public Curva(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    ArrayList<Point3D> listaPuntos = new ArrayList<>();
    ArrayList<Point3D> puntosRotados = new ArrayList<>();
    int anguloX;
    int anguloY;
    int anguloZ;

    public ArrayList<Point3D> getListaPuntos() {
        return listaPuntos;
    }

    public ArrayList<Point3D> getPuntosRotados() {
        return puntosRotados;
    }

    public int getAnguloX() {
        return anguloX;
    }

    public int getAnguloY() {
        return anguloY;
    }

    public int getAnguloZ() {
        return anguloZ;
    }

    public void setAnguloX(int anguloX) {
        this.anguloX = anguloX;
    }

    public void setAnguloY(int anguloY) {
        this.anguloY = anguloY;
    }

    public void setAnguloZ(int anguloZ) {
        this.anguloZ = anguloZ;
    }

    public void limpiarBuffer() {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
    }

    public static void printPoint(Point3D p) {
        System.out.println("X: " + p.getX() + ", Y: " + p.getY() + ", Z: " + p.getZ());
    }

    public ArrayList<Point3D> rotar(int incAngX, int incAngY, int incAngZ, Point3D plano) {
        ArrayList<Point3D> newPoints = rotarx(incAngX, plano);
        newPoints = rotarY(newPoints, incAngY, plano);
        newPoints = rotarZ(newPoints, incAngZ, plano);
        this.puntosRotados = newPoints;
        return newPoints;
    }

    public Point3D getCoordenadasRotadasX(Point3D punto) {
        Double cos = Math.cos(Math.toRadians(anguloX));
        Double sin = Math.sin(Math.toRadians(anguloX));
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        Double x = punto.getX() - cx;
        Double y = punto.getY() - cy;
        double nuevaX = x; //punto.getX();
        double nuevaY = cos * y - sin * punto.getZ();
        double nuevaZ = sin * y + cos * punto.getZ();
        return new Point3D(nuevaX + cx, nuevaY + cy, nuevaZ);
                /*
                punto.getX(),
                Math.cos(Math.toRadians(anguloX))*punto.getY() - Math.sin(Math.toRadians(anguloX))*punto.getZ(),
                Math.sin(Math.toRadians(anguloX))*punto.getY() + Math.cos(Math.toRadians(anguloX))*punto.getZ()
                //*/

        //);
    }
    public Point3D getCoordenadasRotadasY(Point3D punto) {
        Double cos = Math.cos(Math.toRadians(anguloY));
        Double sin = Math.sin(Math.toRadians(anguloY));
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        Double x = punto.getX() - cx;
        Double y = punto.getY() - cy;
        double nuevaX = cos * x - sin * punto.getZ();
        double nuevaY = y;
        double nuevaZ = sin * x + cos * punto.getZ();
        return new Point3D(nuevaX + cx, nuevaY + cy, nuevaZ);
        /* return new Point3D(//nuevaX + width/2, nuevaY + height/2, nuevaZ);

                Math.cos(Math.toRadians(anguloY))*punto.getX() - Math.sin(Math.toRadians(anguloY))*punto.getZ(),
                punto.getY(),
                Math.sin(Math.toRadians(anguloY))*punto.getX() + Math.cos(Math.toRadians(anguloY))*punto.getZ()
        );//*/
    }
    public Point3D getCoordenadasRotadasZ(Point3D punto) {
        Double cos = Math.cos(Math.toRadians(anguloY));
        Double sin = Math.sin(Math.toRadians(anguloY));
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        Double x = punto.getX() - cx;
        Double y = punto.getY() - cy;
        return new Point3D(
                Math.cos(Math.toRadians(anguloZ))*(x+cx) - Math.sin(Math.toRadians(anguloZ))*(y+cy),
                Math.sin(Math.toRadians(anguloZ))*(x+cx) + Math.cos(Math.toRadians(anguloZ))*(y+cy),
                punto.getZ()
        );
    }

    public void dibujarCurva(int x, int y, int z, Point3D plano) {
        limpiarBuffer();
        for (double i = 10; i < 500; i+=10) {
            ;
            Point3D p1 = new Point3D(i-10 + width/2, height/2+100+Math.cos(Math.toRadians(i-10))*50, z);
            Point3D p2 = new Point3D(i + width/2, height/2+100+Math.cos(Math.toRadians(i))*50, z);
            this.listaPuntos.add(p1);
            printPoint(p1);
            dibujarLinea3D((int)p1.getX(), (int)p1.getY(), (int)p1.getZ(),
                    (int)p2.getX(), (int)p2.getY(), (int)p2.getZ(), plano, Color.black);

        }
    }

    public ArrayList<Point3D> rotarx(ArrayList<Point3D> puntos, int incAngX, Point3D plano) {
        this.anguloX+=incAngX;
        limpiarBuffer();
        Point3D p1 = getCoordenadasRotadasX(puntos.get(0));

        ArrayList<Point3D> nuevasPosiciones = new ArrayList<>();
        for (int j = 0; j < puntos.size(); j ++) {
            Point3D i = puntos.get(j);
            Point3D p2 = getCoordenadasRotadasX(i);
            nuevasPosiciones.add(p2);
            dibujarLinea3D((int)p1.getX(), (int)p1.getY(), (int)p1.getZ(),
                    (int)p2.getX(), (int)p2.getY(), (int)p2.getZ(), plano, Color.black);
            p1 = p2;
            //puntos.set(j, p2);

        }
        return nuevasPosiciones;
    }


    public ArrayList<Point3D> rotarY(ArrayList<Point3D> puntos, int incAngX, Point3D plano) {
        this.anguloY+=incAngX;
        limpiarBuffer();
        Point3D p1 = getCoordenadasRotadasY(puntos.get(0));

        ArrayList<Point3D> nuevasPosiciones = new ArrayList<>();
        for (int j = 0; j < puntos.size(); j ++) {
            Point3D i = puntos.get(j);
            Point3D p2 = getCoordenadasRotadasY(i);
            nuevasPosiciones.add(p2);
            dibujarLinea3D((int)p1.getX(), (int)p1.getY(), (int)p1.getZ(),
                    (int)p2.getX(), (int)p2.getY(), (int)p2.getZ(), plano, Color.black);
            p1 = p2;
            //puntos.set(j, p2);
            //printPoint(p2);
        }
        return nuevasPosiciones;
    }

    public ArrayList<Point3D> rotarY(int incAngX, Point3D plano) {
        return rotarY(this.listaPuntos, incAngX, plano);
    }

    public ArrayList<Point3D> rotarx(int incAngX, Point3D plano) {
        return rotarx(this.listaPuntos, incAngX, plano);
    }
    public ArrayList<Point3D> rotarZ(int incAngX, Point3D plano) {
        return rotarZ(this.listaPuntos, incAngX, plano);
    }

    public ArrayList<Point3D> rotarZ(ArrayList<Point3D> puntos, int incAngX, Point3D plano) {
        this.anguloZ+=incAngX;
        limpiarBuffer();
        Point3D p1 = getCoordenadasRotadasZ(puntos.get(0));
        ArrayList<Point3D> nuevasPosiciones = new ArrayList<>();
        for (int j = 0; j < puntos.size(); j ++) {
            Point3D i = puntos.get(j);
            Point3D p2 = getCoordenadasRotadasZ(i);
            nuevasPosiciones.add(p2);
            dibujarLinea3D((int)p1.getX(), (int)p1.getY(), (int)p1.getZ(),
                    (int)p2.getX(), (int)p2.getY(), (int)p2.getZ(), plano, Color.black);
            p1 = p2;
            //puntos.set(j, p2);
        }
        return nuevasPosiciones;
    }
}
