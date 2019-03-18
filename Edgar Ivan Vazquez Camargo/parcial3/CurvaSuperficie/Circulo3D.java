package parcial3.CurvaSuperficie;

import javafx.geometry.Point3D;
import parcial3.Sprite3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * x = (2 + cos(t)) cos(o)
 * y = (2 + cos(t)) sin(o)
 * z = t
 */
public class Circulo3D extends Sprite3D {
    public Circulo3D(int x, int y, int z, int width, int height) {
        super(x, y, width, height);
        this.z = z;
        anguloX = anguloY = anguloZ = 0;
    }

    private double radio;
    private ArrayList<Point3D> listaPuntos;
    private ArrayList<Point3D> puntosRotados;
    private int anguloX, anguloY, anguloZ;

    public ArrayList<Point3D> getPuntosRotados() {
        return puntosRotados;
    }

    public void setPuntosRotados(ArrayList<Point3D> puntosRotados) {
        this.puntosRotados = puntosRotados;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    public void limpiarBuffer() {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
    }

    public ArrayList<Point3D> rotar(int incAngX, int incAngY, int incAngZ, Point3D plano) {
        ArrayList<Point3D> newPoints = rotarx(incAngX, plano);
        newPoints = rotarY(newPoints, incAngY, plano);
        newPoints = rotarZ(newPoints, incAngZ, plano);
        this.puntosRotados = newPoints;
        return newPoints;
    }

    /*
    public void dibujarCirculo(int radio, Point3D plano) {
        this.radio = radio;
        dibujarCirculo(plano);
    }
    //*/

    public ArrayList<Point3D> getListaPuntos() {
        return listaPuntos;
    }

    public void dibujarMalla(ArrayList<Point3D> puntos1, ArrayList<Point3D> puntos2, Point3D plano) {
        Point3D puntoPrevio1 = puntos1.get(0);
        Point3D puntoPrevio2 = puntos2.get(0);
        for (int i = 0; i < puntos1.size(); i++) {
            Point3D puntoActual1 = puntos1.get(i);
            Point3D puntoActual2 = puntos2.get(i);
            dibujarLinea3D(puntoPrevio1, puntoPrevio2, plano);
            dibujarLinea3D(puntoPrevio1, puntoActual2, plano);
            puntoPrevio1 = puntoActual1;
            puntoPrevio2 = puntoActual2;
        }
    }

    ArrayList<Point3D> listaPuntosRotados;
    public ArrayList<Point3D> dibujarCirculo (int z, int factor, ArrayList<Point3D> malla, Point3D plano) {
        ArrayList<Point3D> a = dibujarCirculo(z, factor, plano);
        dibujarMalla(a, malla, plano);
        dibujarLinea3D(malla.get(0), a.get(a.size()-1), plano);
        dibujarLinea3D(malla.get(malla.size()-1), a.get(a.size()-1), plano);
        return a;
    }
    public ArrayList<Point3D> dibujarCirculo (int z, int factor, Point3D plano) {
        int cx = getWidth() / 2;
        int cy = getHeight() /2;
        int prevY = (int)((2+Math.cos(Math.toRadians(3*z+factor)))*Math.sin(Math.toRadians(0))*25)+cx;
        int prevX = (int)((2+Math.cos(Math.toRadians(3*z+factor)))*Math.cos(Math.toRadians(0))*25)+cy;
        Point3D prevDot = new Point3D(prevX, prevY, z);
        Point3D origen = new Point3D(prevX, prevY, z);
        ArrayList<Point3D> listaPuntos = new ArrayList<>();
        listaPuntos.add(origen);
        int x = 0;
        for (x = 0; x < 360; x+=5) {
            double nextX = (2 + Math.cos(Math.toRadians(3*z+factor))) * Math.cos(Math.toRadians(x))*25+cx;
            double nextY = (2 + Math.cos(Math.toRadians(3*z+factor))) * Math.sin(Math.toRadians(x))*25+cy;
            Point3D nextDot = new Point3D(nextX, nextY, z);
            dibujarLinea3D(prevDot, nextDot, plano);
            prevDot = nextDot;
            listaPuntos.add(nextDot);
        }
        this.listaPuntos = listaPuntos;
        dibujarLinea3D(origen, prevDot, plano);
        return listaPuntos;
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



    public void rotarCirculo(int incAngX, int incAngY, int incAngZ, Point3D plano) {

    }

    /*public void dibujarCirculo(Point3D plano) {
        /**
         *
         * matriz[100, 360]
         *
        limpiarBuffer();
        int cx = getWidth() / 2;
        int cy = getHeight() /2;
        int factor = 1;
        int prevX = (int)((2+Math.cos(Math.toRadians(3*0+factor)))*Math.sin(Math.toRadians(0))*20)+cx;
        int prevY = (int)((2+Math.cos(Math.toRadians(3*0+factor)))*Math.cos(Math.toRadians(0))*20)+cy;
        // double prevX = (2 + Math.cos(Math.toRadians(0))) * Math.cos(Math.toRadians(0))*20;
        // double prevY = (2 + Math.cos(Math.toRadians(0))) * Math.sin(Math.toRadians(0))*20;
        Point3D prevDot = new Point3D(prevX, prevY, 0);
        for (int z = 0; z <100; z+=10) {
            for (int x = 0; x < 360; x+=5) {
                double nextX = (2 + Math.cos(Math.toRadians(3*z+factor))) * Math.cos(Math.toRadians(x))*20+cx;
                double nextY = (2 + Math.cos(Math.toRadians(3*z+factor))) * Math.sin(Math.toRadians(x))*20+cy;
                Point3D nextDot = new Point3D(nextX, nextY, z);
                //Curva.printPoint(nextDot);
                dibujarLinea3D(prevDot, nextDot, plano);
                prevDot = nextDot;
            }
        }//
    }//*/

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

    public ArrayList<Point3D> rotarY(int incAngX, Point3D plano) {
        return rotarY(this.listaPuntos, incAngX, plano);
    }

    public ArrayList<Point3D> rotarx(int incAngX, Point3D plano) {
        return rotarx(this.listaPuntos, incAngX, plano);
    }
    public ArrayList<Point3D> rotarZ(int incAngX, Point3D plano) {
        return rotarZ(this.listaPuntos, incAngX, plano);
    }

}
