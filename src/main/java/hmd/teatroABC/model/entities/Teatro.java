package hmd.teatroABC.model.entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 22/11/2024
 * @brief Class Teatro
 */
public class Teatro {
    private static List<Peca> pecas = new ArrayList<>();
    File pecasFile = new File("src/main/java/hmd/teatroABC/model/database/pecas.txt");

    public void carregarPecas() {
        try (BufferedReader br = new BufferedReader(new FileReader(pecasFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(",", -1);
                String nome = partes[0];
                Sessao turno = Sessao.valueOf(partes[1]);
                int ingressosVendidos = Integer.parseInt(partes[2]);
                List<String> assentosOcupados = partes[3].isEmpty()
                        ? new ArrayList<>()
                        : List.of(partes[3].split(";"));
                File poster = new File(partes[4]);
                Peca peca = new Peca(poster, turno, nome);
                peca.adicionarAssentos(assentosOcupados);
                pecas.add(peca);
            }
        } catch (IOException e) {
            System.out.println("erro");
        }
    }

    public static List<Peca> getPecas() {
        return new ArrayList<>(pecas);
    }
}
