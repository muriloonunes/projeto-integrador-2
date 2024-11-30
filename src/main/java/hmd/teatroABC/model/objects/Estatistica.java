package hmd.teatroABC.model.objects;

import hmd.teatroABC.controller.TelaIngressoController;
import hmd.teatroABC.model.entities.Peca;
import hmd.teatroABC.model.entities.Teatro;

import java.util.List;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 22/11/2024
 * @brief Class Estatistica
 */

public class Estatistica {
    private List<Peca> pecasEstatisticas;
    private int vendasWicked = 0;
    private int vendasReiLeao = 0;
    private int vendasAuto = 0;
    private double lucroWicked = 0;
    private double lucroReiLeao = 0;
    private double lucroAuto = 0;
    private double lucroMedioWicked = 0;
    private double lucroMedioReiLeao = 0;
    private double lucroMedioAuto = 0;
    private int vendasManha = 0;
    private int vendasTarde = 0;
    private int vendasNoite = 0;

    public void carregarEstatisticas() {
        this.pecasEstatisticas = Teatro.getPecas();
        calcularVendas();
        calcularLucro();
        lucroMedioWicked = calcularLucroMedioPeca(lucroWicked, vendasWicked);
        lucroMedioReiLeao = calcularLucroMedioPeca(lucroReiLeao, vendasReiLeao);
        lucroMedioAuto = calcularLucroMedioPeca(lucroAuto, vendasAuto);
        calcularSessoesMaisMenosPorPeca();
    }

    private void calcularVendas() {
        for (Peca peca : pecasEstatisticas) {
            String nome = peca.getNome();
            String sessao = peca.getSessao().getNome();
            if (nome.equals("Wicked")) {
                vendasWicked += peca.getIngressosVendidos();
            }
            if (nome.equals("Rei Leao")) {
                vendasReiLeao += peca.getIngressosVendidos();
            }
            if (nome.equals("Auto da Compadecida")) {
                vendasAuto += peca.getIngressosVendidos();
            }

            if (sessao.equals("Manha")) {
                vendasManha += peca.getIngressosVendidos();
            }
            if (sessao.equals("Tarde")) {
                vendasTarde += peca.getIngressosVendidos();
            }
            if (sessao.equals("Noite")) {
                vendasNoite += peca.getIngressosVendidos();
            }
        }
    }

    private void calcularLucro() {
        for (Peca peca : pecasEstatisticas) {
            String nome = peca.getNome();
            List<String> assentosVendidos = peca.getAssentos();
            if (nome.equals("Wicked")) {
                for (String assento : assentosVendidos) {
                    char identificador = assento.charAt(0);
                    lucroWicked += TelaIngressoController.getPrecoPorIdentificador(identificador);
                }
            }
            if (nome.equals("Rei Leao")) {
                for (String assento : assentosVendidos) {
                    char identificador = assento.charAt(0);
                    lucroReiLeao += TelaIngressoController.getPrecoPorIdentificador(identificador);
                }
            }
            if (nome.equals("Auto da Compadecida")) {
                for (String assento : assentosVendidos) {
                    char identificador = assento.charAt(0);
                    lucroAuto += TelaIngressoController.getPrecoPorIdentificador(identificador);
                }
            }
        }
    }

    public int calcularTotalVendas() {
        return vendasWicked + vendasReiLeao + vendasAuto;
    }

    public String calcularPecaMaisVendida() {
        String pecaMaisVendida;
        if (vendasWicked >= vendasReiLeao && vendasWicked >= vendasAuto) {
            pecaMaisVendida = "Wicked";
        } else if (vendasReiLeao >= vendasWicked && vendasReiLeao >= vendasAuto) {
            pecaMaisVendida = "Rei Leao";
        } else {
            pecaMaisVendida = "Auto da Compadecida";
        }
        return pecaMaisVendida;
    }

    public String calcularPecaMenosVendida() {
        String pecaMenosVendida;
        if (vendasWicked <= vendasReiLeao && vendasWicked <= vendasAuto) {
            pecaMenosVendida = "Wicked";
        } else if (vendasReiLeao <= vendasWicked && vendasReiLeao <= vendasAuto) {
            pecaMenosVendida = "Rei Leao";
        } else {
            pecaMenosVendida = "Auto da Compadecida";
        }
        return pecaMenosVendida;
    }

    public String calcularSessaoMaisOcupada() {
        String sessaoMaisOcupada;
        if (vendasManha >= vendasTarde && vendasManha >= vendasNoite) {
            sessaoMaisOcupada = "Manha";
        } else if (vendasTarde >= vendasManha && vendasTarde >= vendasNoite) {
            sessaoMaisOcupada = "Tarde";
        } else {
            sessaoMaisOcupada = "Noite";
        }
        return sessaoMaisOcupada;
    }

    public String calcularSessaoMenosOcupada() {
        String sessaoMenosOcupada;
        if (vendasManha <= vendasTarde && vendasManha <= vendasNoite) {
            sessaoMenosOcupada = "Manha";
        } else if (vendasTarde <= vendasManha && vendasTarde <= vendasNoite) {
            sessaoMenosOcupada = "Tarde";
        } else {
            sessaoMenosOcupada = "Noite";
        }
        return sessaoMenosOcupada;
    }

    public String calcularSessaoMaisLucrativaPorPeca(Peca peca) {
        double lucroManha = 0, lucroTarde = 0, lucroNoite = 0;

        for (String assento : peca.getAssentos()) {
            char identificador = assento.charAt(0);
            double preco = TelaIngressoController.getPrecoPorIdentificador(identificador);

            String sessao = peca.getSessao().getNome();
            switch (sessao) {
                case "Manha":
                    lucroManha += preco;
                    break;
                case "Tarde":
                    lucroTarde += preco;
                    break;
                case "Noite":
                    lucroNoite += preco;
                    break;
            }
        }

        if (lucroManha >= lucroTarde && lucroManha >= lucroNoite) {
            return "Manha";
        } else if (lucroTarde >= lucroManha && lucroTarde >= lucroNoite) {
            return "Tarde";
        } else {
            return "Noite";
        }
    }

    public String calcularSessaoMenosLucrativaPorPeca(Peca peca) {
        double lucroManha = 0, lucroTarde = 0, lucroNoite = 0;

        for (String assento : peca.getAssentos()) {
            char identificador = assento.charAt(0);
            double preco = TelaIngressoController.getPrecoPorIdentificador(identificador);

            String sessao = peca.getSessao().getNome();
            switch (sessao) {
                case "Manha":
                    lucroManha += preco;
                    break;
                case "Tarde":
                    lucroTarde += preco;
                    break;
                case "Noite":
                    lucroNoite += preco;
                    break;
            }
        }

        if (lucroManha <= lucroTarde && lucroManha <= lucroNoite) {
            return "Manha";
        } else if (lucroTarde <= lucroManha && lucroTarde <= lucroNoite) {
            return "Tarde";
        } else {
            return "Noite";
        }
    }

    public void calcularSessoesMaisMenosPorPeca() {
        for (Peca peca : pecasEstatisticas) {
            String maisLucrativa = calcularSessaoMaisLucrativaPorPeca(peca);
            String menosLucrativa = calcularSessaoMenosLucrativaPorPeca(peca);
            System.out.println("Peça: " + peca.getNome());
            System.out.println("Sessão mais lucrativa: " + maisLucrativa);
            System.out.println("Sessão menos lucrativa: " + menosLucrativa);
        }
    }


    public double calcularLucroMedioPeca(double lucro, int quantidadeVendas) {
        return lucro / quantidadeVendas;
    }

    public double getLucroMedioWicked() {
        return lucroMedioWicked;
    }

    public double getLucroMedioReiLeao() {
        return lucroMedioReiLeao;
    }

    public double getLucroMedioAuto() {
        return lucroMedioAuto;
    }
}