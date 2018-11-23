package parcial3.transformaciones;

import javafx.geometry.Point3D;
import parcial3.Sprite3D;

import java.awt.image.BufferedImage;

public class CuboTransform extends Sprite3D {
    int anguloX, anguloY, anguloZ;
    int x1, x2, y1, y2, z1, z2;

    public void setBounds(Point3D p1, Point3D p2) {
        setBounds(
                (int)p1.getX(),
                (int)p1.getY(),
                (int)p1.getZ(),
                (int)p2.getX(),
                (int)p2.getY(),
                (int)p2.getZ()
        );
    }

    public void setBounds(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z2;
    }
    public CuboTransform(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.angulo=0;
        anguloX = anguloY = anguloZ = 0;
    }

    public void dibujarLinea3D(Point3D p1, Point3D p2, Point3D plano) {
        int x1 = (int)p1.getX();
        int y1 = (int)p1.getY();
        int z1 = (int)p1.getZ();
        int x2 = (int)p2.getX();
        int y2 = (int)p2.getY();
        int z2 = (int)p2.getZ();
        super.dibujarLinea3D(x1, y1, z1, x2, y2, z2, plano);
    }

    public void limpiarBuffer() {
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.pixel = new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        this.gPixel = pixel.getGraphics();
        this.gBuffer = buffer.getGraphics();
    }

    public void rotarX(Point3D p1, Point3D p2, int angullo , Point3D plano) {
        limpiarBuffer();
        int centerx = this.width /2;
        int centery = this.height / 2;
        p1 = new Point3D(p1.getX() + centerx, centery-p1.getY(), p1.getZ());
        p2 = new Point3D(p2.getX() + centerx, centery-p2.getY(), p2.getZ());
        this.setAngulo(angullo);
        anguloX = angullo;
        Point3D tmp1 = getCoordenadasRotadasX(p1);
        Point3D tmp2 = getCoordenadasRotadasX(new Point3D(p2.getX(), p1.getY(), p1.getZ()));
        dibujarLinea3D(tmp1, tmp2, plano);
        tmp2 = getCoordenadasRotadasX(new Point3D(p1.getX(), p1.getY(), p2.getZ()));
        dibujarLinea3D(tmp1, tmp2, plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasX(new Point3D(p1.getX(), p2.getY(), p1.getZ())), plano);
        tmp1 = getCoordenadasRotadasX(new Point3D(p1.getX(), p2.getY(), p2.getZ()));
        dibujarLinea3D(tmp1, getCoordenadasRotadasX(new Point3D(p1.getX(), p1.getY(), p2.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasX(new Point3D(p1.getX(), p2.getY(), p1.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasX(p2), plano);
        tmp1 = getCoordenadasRotadasX(new Point3D(p2.getX(), p2.getY(), p1.getZ()));
        dibujarLinea3D(tmp1, getCoordenadasRotadasX(p2), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasX(new Point3D(p1.getX(), p2.getY(), p1.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasX(new Point3D(p2.getX(), p1.getY(), p1.getZ())), plano);
        tmp1 = getCoordenadasRotadasX(new Point3D(p2.getX(), p1.getY(), p2.getZ()));
        dibujarLinea3D(tmp1, getCoordenadasRotadasX(new Point3D(p1.getX(), p1.getY(), p2.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasX(new Point3D(p2.getX(), p1.getY(), p1.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasX(new Point3D(p2.getX(), p2.getY(), p2.getZ())), plano);
        parent.paint(parent.getGraphics());
    }

    public void rotarY(Point3D p1, Point3D p2, int angullo , Point3D plano) {
        anguloY = angullo;
        limpiarBuffer();
        int centerx = this.width /2;
        int centery = this.height / 2;
        p1 = new Point3D(p1.getX() + centerx, centery-p1.getY(), p1.getZ());
        p2 = new Point3D(p2.getX() + centerx, centery-p2.getY(), p2.getZ());
        this.setAngulo(angullo);
        anguloX = angullo;
        Point3D tmp1 = getCoordenadasRotadasY(p1);
        Point3D tmp2 = getCoordenadasRotadasY(new Point3D(p2.getX(), p1.getY(), p1.getZ()));
        dibujarLinea3D(tmp1, tmp2, plano);
        tmp2 = getCoordenadasRotadasY(new Point3D(p1.getX(), p1.getY(), p2.getZ()));
        dibujarLinea3D(tmp1, tmp2, plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasY(new Point3D(p1.getX(), p2.getY(), p1.getZ())), plano);
        tmp1 = getCoordenadasRotadasY(new Point3D(p1.getX(), p2.getY(), p2.getZ()));
        dibujarLinea3D(tmp1, getCoordenadasRotadasY(new Point3D(p1.getX(), p1.getY(), p2.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasY(new Point3D(p1.getX(), p2.getY(), p1.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasY(p2), plano);
        tmp1 = getCoordenadasRotadasY(new Point3D(p2.getX(), p2.getY(), p1.getZ()));
        dibujarLinea3D(tmp1, getCoordenadasRotadasY(p2), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasY(new Point3D(p1.getX(), p2.getY(), p1.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasY(new Point3D(p2.getX(), p1.getY(), p1.getZ())), plano);
        tmp1 = getCoordenadasRotadasY(new Point3D(p2.getX(), p1.getY(), p2.getZ()));
        dibujarLinea3D(tmp1, getCoordenadasRotadasY(new Point3D(p1.getX(), p1.getY(), p2.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasY(new Point3D(p2.getX(), p1.getY(), p1.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasY(new Point3D(p2.getX(), p2.getY(), p2.getZ())), plano);
        parent.paint(parent.getGraphics());

    }

    public void rotarX(int angulo, Point3D plano) {
        rotarX(new Point3D(x1, y1, z1), new Point3D(x2, y2, z2), angulo, plano);
        Point3D rotado1 = getCoordenadasRotadasX(new Point3D(x1, y1, z1));
        Point3D rotado2 = getCoordenadasRotadasX(new Point3D(x2, y2, z2));
        this.setBounds(rotado1, rotado2);
    }

    public void rotarZ(Point3D p1, Point3D p2, int angullo , Point3D plano) {
        this.setAngulo(angullo);
        anguloZ = angullo;
        limpiarBuffer();
        int centerx = this.width /2;
        int centery = this.height / 2;
        p1 = new Point3D(p1.getX() + centerx, centery-p1.getY(), p1.getZ());
        p2 = new Point3D(p2.getX() + centerx, centery-p2.getY(), p2.getZ());
        this.setAngulo(angullo);
        Point3D tmp1 = getCoordenadasRotadasZ(p1);
        Point3D tmp2 = getCoordenadasRotadasZ(new Point3D(p2.getX(), p1.getY(), p1.getZ()));
        dibujarLinea3D(tmp1, tmp2, plano);
        tmp2 = getCoordenadasRotadasZ(new Point3D(p1.getX(), p1.getY(), p2.getZ()));
        dibujarLinea3D(tmp1, tmp2, plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasZ(new Point3D(p1.getX(), p2.getY(), p1.getZ())), plano);
        tmp1 = getCoordenadasRotadasZ(new Point3D(p1.getX(), p2.getY(), p2.getZ()));
        dibujarLinea3D(tmp1, getCoordenadasRotadasZ(new Point3D(p1.getX(), p1.getY(), p2.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasZ(new Point3D(p1.getX(), p2.getY(), p1.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasZ(p2), plano);
        tmp1 = getCoordenadasRotadasZ(new Point3D(p2.getX(), p2.getY(), p1.getZ()));
        dibujarLinea3D(tmp1, getCoordenadasRotadasZ(p2), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasZ(new Point3D(p1.getX(), p2.getY(), p1.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasZ(new Point3D(p2.getX(), p1.getY(), p1.getZ())), plano);
        tmp1 = getCoordenadasRotadasZ(new Point3D(p2.getX(), p1.getY(), p2.getZ()));
        dibujarLinea3D(tmp1, getCoordenadasRotadasZ(new Point3D(p1.getX(), p1.getY(), p2.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasZ(new Point3D(p2.getX(), p1.getY(), p1.getZ())), plano);
        dibujarLinea3D(tmp1, getCoordenadasRotadasZ(new Point3D(p2.getX(), p2.getY(), p2.getZ())), plano);
        parent.paint(parent.getGraphics());
    }

    public void dibujarCubo(Point3D plano) {
        super.dibujarCubo(x1, y1, z1, x2, y2, z2, plano);
    }

    public Point3D getCoordenadasRotadasX(Point3D punto) {
        Double cos = Math.cos(Math.toRadians(anguloX));
        Double sin = Math.sin(Math.toRadians(anguloX));
        Double x = punto.getX() - width/2;
        Double y = punto.getY() - height/2;
        double nuevaX = punto.getX();
        double nuevaY = cos * punto.getY() - sin * punto.getZ();
        double nuevaZ = sin * punto.getY() + cos * punto.getZ();
        Point3D tmp = new Point3D(
                punto.getX(),
                Math.cos(Math.toRadians(anguloX))*punto.getY() - Math.sin(Math.toRadians(anguloX))*punto.getZ(),
                Math.sin(Math.toRadians(anguloX))*punto.getY() + Math.cos(Math.toRadians(anguloX))*punto.getZ()
                //*/
        );


        return tmp;
    }
    public Point3D getCoordenadasRotadasY(Point3D punto) {
        return new Point3D(
                Math.cos(Math.toRadians(anguloY))*punto.getX() - Math.sin(Math.toRadians(anguloY))*punto.getZ(),
                punto.getY(),
                Math.sin(Math.toRadians(anguloY))*punto.getX() + Math.cos(Math.toRadians(anguloY))*punto.getZ()
        );
    }
    public Point3D getCoordenadasRotadasZ(Point3D punto) {
        return new Point3D(
                Math.cos(Math.toRadians(anguloZ))*punto.getX() - Math.sin(Math.toRadians(anguloZ))*punto.getY(),
                Math.sin(Math.toRadians(anguloZ))*punto.getX() + Math.cos(Math.toRadians(anguloZ))*punto.getY(),
                punto.getZ()
        );
    }

    @Override
    public void dibujarCubo(int x1, int y1, int z1, int x2, int y2, int z2, Point3D plano) {
        this.setBounds(x1, x2, y1, y2, z1, z2);
        super.dibujarCubo(x1, y1, z1, x2, y2, z2, plano);
    }

    public void dibujarCubo(Point3D p1, Point3D p2 , Point3D plano) {
        super.dibujarCubo((int)p1.getX(), (int)p1.getY(), (int)p1.getZ(), (int)p2.getX(), (int)p2.getY(), (int) p2.getZ(), plano);
    }

}
