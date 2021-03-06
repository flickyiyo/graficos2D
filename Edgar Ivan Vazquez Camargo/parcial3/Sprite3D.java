package parcial3;

import com.company.proyecto.Sprite;
import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite3D extends Sprite {
    protected JFrame parent;
    public int z;
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

    public void dibujarCubo(int x1, int y1, int z1, int x2, int y2, int z2, Point3D plano) {
        System.out.println("asdadsadasdasd");
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.pixel = new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        this.gPixel = pixel.getGraphics();
        this.gBuffer = buffer.getGraphics();
        int centerx = this.width /2;
        int centery = this.height / 2;
        /*Point3D vertices[] = new Point3D[]{
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

    public void setParent(JFrame parent) {
        this.parent = parent;
    }

    public void dibujarLinea3D (int x1, int y1, int z1, int x2, int y2, int z2, Point3D plano, Color color) {

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

        float x1Final = xp - ((float) x1 - xp) * ((float) zp / (z1 - zp));
        float y1Final = yp - ((float) y1 - yp) * ((float) zp / (z1 - zp));
        float x2Final = xp - ((float) x2 - xp) * ((float) zp / (z2 - zp));
        float y2Final = yp - ((float) y2 - yp) * ((float) zp / (z2 - zp));
        //System.out.println(xp+","+yp+","+zp); //*/
        if (color == Color.pink) {
            System.out.println(x1Final   + "," + y1Final + "," +z1);
            System.out.println(x2Final + "," + y2Final + "," +z2);
            System.out.println();
        }
        dibujarLinea((int)x1Final, (int)y1Final, (int)x2Final, (int)y2Final, color);
    }

    public void dibujarLinea3D (int x1, int y1, int z1, int x2, int y2, int z2, Point3D plano) {
        dibujarLinea3D(x1,y1,z1, x2, y2, z2, plano, Color.BLACK);
    }

    public void dibujarLinea3D(Point3D p1, Point3D p2, Point3D plano) {
        int x1 = (int)p1.getX();
        int y1 = (int)p1.getY();
        int z1 = (int)p1.getZ();
        int x2 = (int)p2.getX();
        int y2 = (int)p2.getY();
        int z2 = (int)p2.getZ();
        dibujarLinea3D(x1, y1, z1, x2, y2, z2, plano);
    }

    public void dibujarLinea3D(Point3D p1, Point3D p2, Point3D plano, Color color) {
        int x1 = (int)p1.getX();
        int y1 = (int)p1.getY();
        int z1 = (int)p1.getZ();
        int x2 = (int)p2.getX();
        int y2 = (int)p2.getY();
        int z2 = (int)p2.getZ();
        dibujarLinea3D(x1, y1, z1, x2, y2, z2, plano, color);
    }
}
