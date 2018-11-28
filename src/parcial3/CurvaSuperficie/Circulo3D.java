package parcial3.CurvaSuperficie;

import javafx.geometry.Point3D;
import parcial3.Sprite3D;

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
    }

    private double radio;
    private ArrayList<Point3D> listaPuntos;

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

    /*
    public void dibujarCirculo(int radio, Point3D plano) {
        this.radio = radio;
        dibujarCirculo(plano);
    }
    //*/

    ArrayList<Point3D> listaPuntosRotados;

    public ArrayList<Point3D> dibujarCirculo (int z, int factor, Point3D plano) {
        int cx = getWidth() / 2;
        int cy = getHeight() /2;
        int prevY = (int)((2+Math.cos(Math.toRadians(3*z+factor)))*Math.sin(Math.toRadians(0))*25)+cx;
        int prevX = (int)((2+Math.cos(Math.toRadians(3*z+factor)))*Math.cos(Math.toRadians(0))*25)+cy;
        Point3D prevDot = new Point3D(prevX, prevY, z);
        Point3D origen = new Point3D(prevX, prevY, z);
        ArrayList<Point3D> listaPuntos = new ArrayList<>();
        listaPuntos.add(origen);
        for (int x = 0; x < 360; x+=5) {
            double nextX = (2 + Math.cos(Math.toRadians(3*z+factor))) * Math.cos(Math.toRadians(x))*25+cx;
            double nextY = (2 + Math.cos(Math.toRadians(3*z+factor))) * Math.sin(Math.toRadians(x))*25+cy;
            Point3D nextDot = new Point3D(nextX, nextY, z);
            //Curva.printPoint(nextDot);
            dibujarLinea3D(prevDot, nextDot, plano);
            prevDot = nextDot;
            listaPuntos.add(nextDot);
        }

        dibujarLinea3D(origen, prevDot, plano);
        return listaPuntos;
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


}
