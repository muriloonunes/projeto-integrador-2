package hmd.teatroABC.controller;

import hmd.teatroABC.model.Peca;

/*
 * @created 21/11/2024 - 19:53
 * @project projeto-integrador-2
 * @autor Aluno
 */
public class Ingresso {
    private Area area;
    private Peca peca;

    public Ingresso(Area area, Peca peca) {
        this.area = area;
        this.peca = peca;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
}
