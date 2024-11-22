package hmd.teatroABC.model.entities;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief Class Ingresso
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
