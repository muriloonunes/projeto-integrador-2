package hmd.teatroABC.controller;

/*
 * @created 21/11/2024 - 19:27
 * @project projeto-integrador-2
 * @autor Aluno
 */
public enum Area {
    PLATEIA_A("Plateia A", 40.00, 25),
    PLATEIA_B("Plateia B", 60.00, 100),
    CAMAROTE("Camarote", 80.00, 50),
    FRISA("Frisa", 120.00, 30),
    BALCAO_NOBRE("Balcao Nobre", 250.00, 50);

    private final String nomeLocal;
    private final Double preco;
    private final Integer nLugares;

    Area(String nomeLocal, Double preco, Integer nLugares) {
        this.nomeLocal = nomeLocal;
        this.preco = preco;
        this.nLugares = nLugares;
    }
}
