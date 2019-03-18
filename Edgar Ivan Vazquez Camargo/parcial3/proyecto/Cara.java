package parcial3.proyecto;

import javafx.geometry.Point3D;
import parcial3.Sprite3D;

import java.awt.image.BufferedImage;

public class Cara extends Sprite3D {

    public Cara(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void limpiarBuffer() {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.gBuffer = buffer.getGraphics();
    }

    public void dibujarCara(Point3D plano) {
        int length = this.width;
    }



}
