package parcial3.perspectiva;

import com.company.proyecto.Sprite;
import javafx.geometry.Point3D;
import parcial3.Sprite3D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CuboProyeccion extends Sprite3D {

    public CuboProyeccion(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void setParent(JFrame parent) {
        this.parent = parent;
    }
    @Override
    public void dibujarCubo(int x1, int y1, int z1, int x2, int y2, int z2, Point3D plano) {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.pixel = new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        this.gPixel = pixel.getGraphics();
        this.gBuffer = buffer.getGraphics();
        int centerx = this.width /2;
        int centery = this.height / 2;
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
        /*for (int i = 0; i < vertices.length; i++) {
            for (int j = 1; j < vertices.length; j++) {
                //
                if (
                        (i!=j)
                        && (
                            vertices[i].getX() == vertices[j].getX()
                            && vertices[i].getY() == vertices[j].getY()
                        )
                        || (
                            vertices[i].getZ() == vertices[j].getZ()
                            && vertices[i].getY() == vertices[j].getY()
                        )
                            || (
                            vertices[i].getX() == vertices[j].getX()
                            && vertices[i].getZ() == vertices[j].getZ()
                        )
                ) {
                    System.out.println("holi");
                    dibujarLinea3D(
                            (int)vertices[i].getX() + centerx,
                            centery - (int)vertices[i].getY(), (int)vertices[i].getZ(),
                            centerx + (int)vertices[j].getX(),
                            centery-(int)vertices[j].getY(), (int)vertices[j].getZ(), plano);
                }

            }
        }
        //*/
        dibujarLinea3D(x1 + centerx, centery - y1, z1, x1 + centerx, centery - y1, z2, plano);
        dibujarLinea3D(x1 + centerx, centery - y1, z1, x2 + centerx, centery - y1, z1, plano);
        dibujarLinea3D(x1 + centerx, centery - y1, z1, x1 + centerx, centery - y2, z1, plano);

        dibujarLinea3D(x2 + centerx, -y2 + centery, z1, x2 + centerx, centery - y1, z1, plano);
        dibujarLinea3D(x2 + centerx, -y2 + centery, z1, x1 + centerx, centery - y2, z1, plano);
        dibujarLinea3D(x2 + centerx, -y2 + centery, z1, x2 + centerx, centery - y2, z2, plano);

        //dibujarLinea3D(x2 + centerx, centery - y1, z2, x2 + centerx, centery - y1, z1, plano);
        dibujarLinea3D(x2 + centerx, centery-y1, z1,x2+centerx,centery-y1, z2, plano);
        dibujarLinea3D(x2 + centerx, centery - y1, z2, x1 + centerx, centery - y1, z2, plano);
        dibujarLinea3D(x2 + centerx, centery - y1, z2, x2 + centerx, centery - y2, z2, plano);

        dibujarLinea3D(x1 + centerx, centery - y2, z2,  x1 + centerx, centery - y1, z2, plano);
        dibujarLinea3D(x1 + centerx, centery - y2, z2,  x2 + centerx, centery - y2, z2, plano);
        dibujarLinea3D(x1 + centerx, centery - y2, z1, x1 + centerx, centery - y2, z2, plano);
        //*/
        parent.paint(parent.getGraphics());

    }

    @Override
    public void dibujarLinea3D (int x1, int y1, int z1, int x2, int y2, int z2, Point3D plano, Color color) {
        if (color == Color.pink) {
            System.out.println(x1 + "," + y1 + "," +z1);
            System.out.println(x2 + "," + y2 + "," +z2);
            System.out.println();
        }
        int xp = (int)plano.getX();
        int yp = (int)plano.getY();
        int zp = (int)plano.getX();
/*        float x1Final = x1 - xp * (z1 / zp);
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
}
