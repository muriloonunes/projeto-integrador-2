package hmd.teatroABC.controller;

/*
 * @created 21/11/2024 - 19:03
 * @project projeto-integrador-2
 * @autor Aluno
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
