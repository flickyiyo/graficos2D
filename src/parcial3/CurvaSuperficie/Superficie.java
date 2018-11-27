package parcial3.CurvaSuperficie;


import javafx.geometry.Point3D;
import parcial3.Sprite3D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Superficie extends Sprite3D {
    private ArrayList<Curva> curvas;

    public Superficie(int x, int y, int width, int height, ArrayList<Curva> curvas) {
        super(x, y, width, height);
        this.curvas = curvas;
    }

    public Superficie(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.curvas = new ArrayList<>();
    }

    public void limpiarBuffer() {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
    }

    public void dibujarSuperficie(int x, int zInicial, Point3D plano) {
        Curva curvaPrevia = curvas.get(0);
        curvaPrevia.dibujarCurva(x, 100, zInicial, plano);
        for (int i = 1; i < curvas.size(); i++) {
            Curva curvaActual = curvas.get(i);
            curvaActual.dibujarCurva(x, 100, zInicial+i*10, plano);
            this.getBuffer().getGraphics().drawImage(curvaActual.getBuffer(), 0,0, null);
            dibujarMalla(curvaPrevia.getListaPuntos(), curvaActual.getListaPuntos(), plano);
            curvaPrevia = curvaActual;
        }

        this.parent.paint(parent.getGraphics());
    }

    public void rotarSuperficie(int incAngX, int incAngY, int incAngZ, Point3D plano) {
        limpiarBuffer();
        Curva curvaPrevia = curvas.get(0);
        ArrayList<Point3D> puntosViejos = curvaPrevia.rotar(incAngX, incAngY, incAngZ, plano);
        for (int i = 1; i < curvas.size(); i++) {
            Curva curvaActual = curvas.get(i);
            ArrayList<Point3D> puntosNuevos =curvaActual.rotar(incAngX, incAngY, incAngZ, plano);
            this.getBuffer().getGraphics().drawImage(curvaActual.getBuffer(), 0,0, null);
            dibujarMalla(puntosViejos, puntosNuevos, plano);
            curvaPrevia = curvaActual;
            puntosViejos = puntosNuevos;
        }


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

    public ArrayList<Curva> getCurvas() {
        return curvas;
    }

    public void setCurvas(ArrayList<Curva> curvas) {
        this.curvas = curvas;
    }


}
