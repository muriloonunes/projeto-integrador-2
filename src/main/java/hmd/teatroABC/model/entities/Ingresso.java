package hmd.teatroABC.model.entities;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief Class Ingresso
 */

public class Ingresso {
    private Area area;
    private Peca peca;
    private String assento;

    public Ingresso(Area area, Peca peca, String assento) {
        this.area = area;
        this.peca = peca;
        this.assento = assento;
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

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    @Override
    public String toString() {
        return "Ingresso{" +
                "area=" + area +
                ", peca=" + peca +
                ", assento='" + assento + '\'' +
                '}';
    }
}
