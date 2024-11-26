package hmd.teatroABC.model.entities;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief Enum Area
 */
public enum Area {
    PLATEIA_A("Plateia A", 40.00, 25),
    PLATEIA_B("Plateia B", 60.00, 100),
    CAMAROTE1("Camarote1", 800.00, 10),
    CAMAROTE2("Camarote2", 800.00, 10),
    CAMAROTE3("Camarote3", 800.00, 10),
    CAMAROTE4("Camarote4", 800.00, 10),
    CAMAROTE5("Camarote5", 800.00, 10),
    FRISA1("Frisa1", 600.00, 5),
    FRISA2("Frisa2", 600.00, 5),
    FRISA3("Frisa3", 600.00, 5),
    FRISA4("Frisa4", 600.00, 5),
    FRISA5("Frisa5", 600.00, 5),
    FRISA6("Frisa6", 600.00, 5),
    BALCAO_NOBRE("Balcao Nobre", 250.00, 50);

    private final String nomeLocal;
    private final Double preco;
    private final Integer nLugares;

    Area(String nomeLocal, Double preco, Integer nLugares) {
        this.nomeLocal = nomeLocal;
        this.preco = preco;
        this.nLugares = nLugares;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getnLugares() {
        return nLugares;
    }
}
