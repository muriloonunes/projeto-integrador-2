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

    public void guardarIngresso(Pessoa pessoa, ArrayList<String> lista) throws IOException {
        File file = new File("src\\main\\java\\hmd\\teatroABC\\model\\database\\ingressoImpresso.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            //guarda pessoa na lista de ingresso
            String[] lines = new String[]{String.valueOf(pessoa.getCpf()), String.valueOf(lista)};
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    public void imprimeIngresso(String cpf) {
        File file = new File("src\\main\\java\\hmd\\teatroABC\\model\\database\\ingressoImpresso.csv");

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String linha;
            boolean encontrou = false;

            // Lê o arquivo linha por linha
            while ((linha = br.readLine()) != null) {
                // Supondo que as linhas do CSV estejam no formato: "CPF,area,peça"
                String[] partes = linha.split(",");

                if (partes.length > 0 && partes[0].equals(cpf)) { // Verifica se o CPF corresponde
                    /*outputArea.appendText("Ingresso encontrado:\n");
                    outputArea.appendText("CPF: " + partes[0] + "\n");
                    outputArea.appendText("Área: " + partes[1] + "\n");
                    outputArea.appendText("Peça: " + partes[2] + "\n");*/
                    encontrou = true;
                    break;
                }
            }

            if (!encontrou) {
                System.out.println("Nenhum ingresso encontrado para o CPF: " + cpf);
            }

        } catch (Exception e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
            }
        }
    }
}
