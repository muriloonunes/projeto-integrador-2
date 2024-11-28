package hmd.teatroABC.model.entities;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Davy Lopes, Murilo Nunes, Hartur Sales
 * @date 22/11/2024
 * @brief Class Teatro
 */

public class Teatro {
    private static List<Peca> pecas = new ArrayList<>();
    private static List<Pessoa> pessoas = new ArrayList<>();
    public static List<String> log = new ArrayList<>();
    File pecasFile = new File("src/main/java/hmd/teatroABC/model/database/pecas.txt");
    File pessoasFile = new File("src/main/java/hmd/teatroABC/model/database/pessoas.txt");

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

//    public void escreverPessoaEIngresso(){
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(, true))) {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void carregarPessoas() {
        //TODO

        try (BufferedReader br = new BufferedReader(new FileReader(pessoasFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(",", -1);
                long cpf = Long.parseLong(partes[0]);
                boolean ehFidelidade = Boolean.parseBoolean(partes[1]);
                String nome = partes[2];
                String telefone = partes[3];
                String endereco = partes[4];
                LocalDate dataNascimento = LocalDate.parse(partes[5]);
                Pessoa pessoa = new Pessoa(cpf, ehFidelidade, nome, telefone, endereco, dataNascimento);

                pessoas.add(pessoa);

            }
        } catch (IOException e) {
            System.out.println("erro");
        }

    }

    public void adicionarPessoas(Pessoa pessoa) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pessoasFile, true))) {
            bw.write(pessoa.getNome() + "," + pessoa.getCpf());
            bw.newLine();
            pessoas.add(pessoa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Peca> getPecas() {
        return new ArrayList<>(pecas);
    }
}
