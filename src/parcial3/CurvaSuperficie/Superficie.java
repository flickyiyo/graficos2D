package parcial3.CurvaSuperficie;


import javafx.geometry.Point3D;

import java.util.ArrayList;

public class Superficie {
    private ArrayList<Curva> curvas;

    Superficie(ArrayList<Curva> curvas) {
        this.curvas = curvas;
    }

    public void dibujarSuperficie(int x, int zInicial, Point3D plano) {

    }

    public ArrayList<Curva> getCurvas() {
        return curvas;
    }

    public void setCurvas(ArrayList<Curva> curvas) {
        this.curvas = curvas;
    }


}
