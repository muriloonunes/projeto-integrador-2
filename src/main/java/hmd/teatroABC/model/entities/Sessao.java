package hmd.teatroABC.model.entities;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief Enum Sessao
 */
public enum Sessao {
    MANHA("Manha"),
    TARDE("Tarde"),
    NOITE("Noite");

    private final String nome;

    Sessao(String nome) {
        this.nome = nome;
    }
}
