package hmd.teatroABC.model.entities;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 19/11/2024
 * @brief Class Peca
 */

public class Peca {
    private Sessao sessao;
    private String nome;
    private ArrayList<String> assentos = new ArrayList<>();
    private int ingressosVendidos;

    public Peca(Sessao sessao, String nome) {
        this.sessao = sessao;
        this.nome = nome;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarAssento(String assento) {
        assentos.add(assento);
    }

    public void adicionarAssentos(List<String> assentos) {
        this.assentos.addAll(assentos);
    }

    public List<String> getAssentos() {
        return Collections.unmodifiableList(assentos);
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

    public void setIngressosVendidos(int ingressosVendidos) {
        this.ingressosVendidos = ingressosVendidos;
    }
}
