package hmd.teatroABC.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 21/11/2024
 * @brief class Pessoa
 */

public class Pessoa {
    private long cpf;
    private boolean ehFidelidade;
    private ArrayList<Ingresso> ingressos = new ArrayList<>();
    private String nome;
    private String telefone;
    private String endereco;
    private LocalDate dataNascimento;

    public Pessoa(long cpf, boolean ehFidelidade) {
        this.cpf = cpf;
        this.ehFidelidade = ehFidelidade;
    }

    public Pessoa(long cpf, boolean ehFidelidade, String nome, String telefone, String endereco, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.ehFidelidade = ehFidelidade;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public boolean isEhFidelidade() {
        return ehFidelidade;
    }

    public void setEhFidelidade(boolean ehFidelidade) {
        this.ehFidelidade = ehFidelidade;
    }

    public ArrayList<Ingresso> getIngressos() {
        return new ArrayList<>(ingressos);
    }

    public void setIngressos(ArrayList<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public static boolean validarCPF(long cpf) {
        String cpfString = String.format("%011d", cpf);
        if (cpf == 0 || cpf % 11111111111L == 0) {
            return false;
            // retorna false se o cpf digitado foi 000 ou se todos seus dígitos forem iguais (22222222222, etc)
        }

        int[] cpfArray = new int[11];
        for (int i = 0; i < 11; i++) {
            cpfArray[i] = Integer.parseInt(String.valueOf(cpfString.charAt(i)));
            // passa os dígitos da string cpf para cada posiçao do array
        }

        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            soma += cpfArray[i] * peso;
            peso--;
        }
        int primeiroDigito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);
        // se o resto da divisão de soma por 11 for menor que 2, primeiroDigito recebe 0. caso contrario, primeiroDigito recebe 11 - soma % 11

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += cpfArray[i] * peso;
            peso--;
        }
        int segundoDigito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);
        // se o resto da divisão de soma por 11 for menor que 2, segundoDigito recebe 0. caso contrario, segundoDigito recebe 11 - soma % 11

        return cpfArray[9] == primeiroDigito && cpfArray[10] == segundoDigito;
        // verifica se as variaveis primeiroDigito e segundoDigito sao iguais aos digitos verificadores do CPF, que estao na posicao 9 e 10 do array
    }
}
