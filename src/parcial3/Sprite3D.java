package parcial3;

import com.company.proyecto.Sprite;
import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite3D extends Sprite {
    protected JFrame parent;
    public Sprite3D(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.pixel = new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);

        this.gPixel = pixel.getGraphics();
        this.gBuffer = buffer.getGraphics();
    }

    public void setParent(JFrame parent) {
        this.parent = parent;
    }

    public void dibujarLinea3D (int x1, int y1, int z1, int x2, int y2, int z2, Point3D plano, Color color) {
        if (color == Color.pink) {
            System.out.println(x1 + "," + y1 + "," +z1);
            System.out.println(x2 + "," + y2 + "," +z2);
            System.out.println();
        }
        int xp = (int)plano.getX();
        int yp = (int)plano.getY();
        int zp = (int)plano.getX();
        /*float x1Final = x1 - xp * (z1 / zp);
        float y1Final = y1 - yp * (z1 / zp);
        float x2Final = x2 - xp * (z2 / zp);
        float y2Final = y2 - yp * (z2 / zp);
        System.out.println(x1Final);
        System.out.println(x2Final);
        System.out.println(y1Final);
        System.out.println(y2Final);
        System.out.println("_______");
        //*/

        float x1Final = xp + ((float) x1 - xp) * ((float) zp / (z1 - zp));
        float y1Final = yp + ((float) y1 - yp) * ((float) zp / (z1 - zp));
        float x2Final = xp + ((float) x2 - xp) * ((float) zp / (z2 - zp));
        float y2Final = yp + ((float) y2 - yp) * ((float) zp / (z2 - zp));
        //System.out.println(xp+","+yp+","+zp); //*/

        dibujarLinea((int)x1Final, (int)y1Final, (int)x2Final, (int)y2Final, color);
    }

    public void dibujarLinea3D (int x1, int y1, int z1, int x2, int y2, int z2, Point3D plano) {
        dibujarLinea3D(x1,y1,z1, x2, y2, z2, plano, Color.BLACK);
    }
}
