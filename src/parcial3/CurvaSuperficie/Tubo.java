package parcial3.CurvaSuperficie;

import javafx.geometry.Point3D;
import parcial3.Sprite3D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tubo extends Sprite3D {

    public Tubo(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void limpiarBuffer() {
        for(Circulo3D circulo3D : circulos) {
            circulo3D.limpiarBuffer();
        }
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
    }

    public void rotar(int incX, int incY, int incZ, int factor, Point3D plano) {

    }

    ArrayList<Circulo3D> circulos;

    public ArrayList<Circulo3D> getCirculos() {
        return circulos;
    }

    public void setCirculos(ArrayList<Circulo3D> circulos) {
        this.circulos = circulos;
    }

    public void dibujarVainaRara(int factor, Point3D plano) {
        int index = 0;
        ArrayList<Point3D> dots = circulos.get(0).dibujarCirculo(z, factor, plano);
        for (int z = 5; z < circulos.size()*5; z+=5) {
            Circulo3D circulo3D = circulos.get(index);
            dots = circulos.get(index).dibujarCirculo(z, factor, dots, plano);
            this.buffer.getGraphics().drawImage(circulos.get(index).getBuffer(), circulo3D.getX(), circulo3D.getY(), parent);
            index ++;
        }


        parent.paint(parent.getGraphics());
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
}
