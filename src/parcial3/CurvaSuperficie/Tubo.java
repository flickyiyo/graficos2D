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

    ArrayList<Circulo3D> circulos;

    public ArrayList<Circulo3D> getCirculos() {
        return circulos;
    }

    public void setCirculos(ArrayList<Circulo3D> circulos) {
        this.circulos = circulos;
    }

    public void dibujarVainaRara(int factor, Point3D plano) {
        int index = 0;
        for (int z = 0; z < circulos.size()*5; z+=5) {
            Circulo3D circulo3D = circulos.get(index);
            circulos.get(index).dibujarCirculo(z, factor, plano);
            this.buffer.getGraphics().drawImage(circulos.get(index).getBuffer(), circulo3D.getX(), circulo3D.getY(), null);
            index ++;
        }
    }
}
