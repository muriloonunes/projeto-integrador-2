package hmd.teatroABC.model.objects;

/**
 * @author Murilo Nunes <murilo_no@outlook.com>
 * @date 22/11/2024
 * @brief Class Estatistica
 */
public class Estatistica{

    //Ingresso ingresso = new Ingresso();

    // só peguei do gepeto bem bobamente pra poder ter alguma coisa qnd tiver tudo pronto
    //public void calcularEstatisticas() {
    //        List<Peca> pecas = Teatro.getPecas();
    //
    //        int[] vendasPorPeca = new int[pecas.size()];
    //        int[] vendasPorSessao = new int[Sessao.values().length];
    //        double[] lucroPorPeca = new double[pecas.size()];
    //        double[] lucroMaximoPorPeca = new double[pecas.size()];
    //        int[] sessaoMaisLucrativaPorPeca = new int[pecas.size()];
    //        double[] lucroMinimoPorPeca = new double[pecas.size()];
    //        int[] sessaoMenosLucrativaPorPeca = new int[pecas.size()];
    //
    //        for (int i = 0; i < pecas.size(); i++) {
    //            Peca peca = pecas.get(i);
    //            Sessao sessao = peca.getSessao();
    //
    //            // Assume que a lista de assentos ocupados da peça corresponde às vendas realizadas
    //            int vendasPorSessaoAtual = peca.getAssentos().size();
    //            vendasPorPeca[i] += vendasPorSessaoAtual;
    //            vendasPorSessao[sessao.ordinal()] += vendasPorSessaoAtual;
    //
    //            double lucroSessao = 0;
    //            for (String assento : peca.getAssentos()) {
    //                // Supondo que o preço seja derivado do assento (ou outra lógica)
    //                Area area = Area.valueOf(assento);
    //                lucroSessao += area.getPreco();
    //            }
    //
    //            lucroPorPeca[i] += lucroSessao;
    //
    //            if (lucroSessao > lucroMaximoPorPeca[i]) {
    //                lucroMaximoPorPeca[i] = lucroSessao;
    //                sessaoMaisLucrativaPorPeca[i] = sessao.ordinal();
    //            }
    //
    //            if (lucroSessao < lucroMinimoPorPeca[i] || lucroMinimoPorPeca[i] == 0) {
    //                lucroMinimoPorPeca[i] = lucroSessao;
    //                sessaoMenosLucrativaPorPeca[i] = sessao.ordinal();
    //            }
    //        }
    //
    //        int pecaMaisVendida = maisVendido(vendasPorPeca);
    //        int pecaMenosVendida = menosVendido(vendasPorPeca);
    //        int sessaoMaisOcupada = maisVendido(vendasPorSessao);
    //        int sessaoMenosOcupada = menosVendido(vendasPorSessao);
    //
    //        double lucroMedioPorPeca1 = vendasPorPeca[0] != 0 ? lucroPorPeca[0] / vendasPorPeca[0] : 0;
    //        double lucroMedioPorPeca2 = vendasPorPeca[1] != 0 ? lucroPorPeca[1] / vendasPorPeca[1] : 0;
    //        double lucroMedioPorPeca3 = vendasPorPeca[2] != 0 ? lucroPorPeca[2] / vendasPorPeca[2] : 0;
    //
    //        // Exibição dos dados (simples, pode ser ajustado conforme a interface do projeto)
    //        System.out.println("Peça mais vendida: " + pecaMaisVendida);
    //        System.out.println("Peça menos vendida: " + pecaMenosVendida);
    //        System.out.println("Sessão mais ocupada: " + sessaoMaisOcupada);
    //        System.out.println("Sessão menos ocupada: " + sessaoMenosOcupada);
    //        System.out.println("Lucro médio por peça: " + lucroMedioPorPeca1 + ", " + lucroMedioPorPeca2 + ", " + lucroMedioPorPeca3);
    //    }
    //
    //    private int maisVendido(int[] vendas) {
    //        int maxIndex = 0;
    //        for (int i = 1; i < vendas.length; i++) {
    //            if (vendas[i] > vendas[maxIndex]) {
    //                maxIndex = i;
    //            }
    //        }
    //        return maxIndex;
    //    }
    //
    //    private int menosVendido(int[] vendas) {
    //        int minIndex = 0;
    //        for (int i = 1; i < vendas.length; i++) {
    //            if (vendas[i] < vendas[minIndex]) {
    //                minIndex = i;
    //            }
    //        }
    //        return minIndex;
    //    }
    //}





        //private void estatisticasVendas(Stage primaryStage) {
        //        primaryStage.setTitle("Estatísticas de Vendas");
        //
        //        AnchorPane anchorPane = new AnchorPane();
        //        anchorPane.setPrefSize(1280, 750);
        //        anchorPane.setPadding(new Insets(15, 15, 15, 15));
        //
        //        // vetores com 3 linhas, cada linha representa as estatísticas de uma peça
        //        int[] vendasPorPeca = new int[3];
        //        int[] vendasPorSessao = new int[3];
        //        double[] lucroPorPeca = new double[3];
        //        double[] lucroMaximoPorPeca = new double[3];
        //        int[] sessaoMaisLucrativaPorPeca = new int[3];
        //        double[] lucroMinimoPorPeca = new double[3];
        //        int[] sessaoMenosLucrativaPorPeca = new int[3];
        //
        //        // seleciona a matriz da peça correspondente à linha do vetor
        //        for (int i = 0; i < 3; i++) {
        //            long[][] peca = switch (i) {
        //                case 0 -> p1;
        //                case 1 -> p2;
        //                case 2 -> p3;
        //                default -> null;
        //            };
        //
        //            for (int j = 0; j < peca.length; j++) {
        //                double lucroSessao = 0;
        //                for (int k = 0; k < peca[j].length; k++) {
        //                    if (peca[j][k] != 0) { // peca[j][k] representa a poltrona k na sessão j da peça atual
        //                        // peca[j][k] != 0 verifica se a poltrona está ocupada
        //                        vendasPorPeca[i]++; // aumenta o contador de vendas da peça
        //                        lucroPorPeca[i] += precoPorPoltrona(k + 1); // adiciona o preço da poltrona ao lucro da peça
        //                        vendasPorSessao[j]++; // aumenta o contador de vendas da peça
        //                        lucroSessao += precoPorPoltrona(k + 1); // adiciona o preço da poltrona ao lucro da sessão atual
        //                    }
        //                }
        //                if (lucroSessao > lucroMaximoPorPeca[i]) {
        //                    lucroMaximoPorPeca[i] = lucroSessao;
        //                    sessaoMaisLucrativaPorPeca[i] = j;
        //                    // se o lucro da sessao atual for maior que o lucro maximo que ja tava registrado, o lucro máximo da peça é atualizado para armazenar o lucro atual
        //                    // e tambem armazena o indice da sessao atual como a mais lucrativa
        //                }
        //                if (lucroSessao < lucroMinimoPorPeca[i] || lucroMinimoPorPeca[i] == 0) {
        //                    lucroMinimoPorPeca[i] = lucroSessao;
        //                    sessaoMenosLucrativaPorPeca[i] = j;
        //                    // se o lucro da sessão atual é menor que o lucro mínimo já registrado ou se ainda não foi definido (igual a 0)
        //                    // o lucro minimo da peça é atualizado para armazenar o lucro atual e armazena essa sessao como a menos lucrativa
        //                }
        //            }
        //        }
        //
        //        int pecaMaisVendida = maisVendido(vendasPorPeca);
        //        int pecaMenosVendida = menosVendido(vendasPorPeca);
        //        int sessaoMaisOcupada = maisVendido(vendasPorSessao);
        //        int sessaoMenosOcupada = menosVendido(vendasPorSessao);
        //
        //        // verifica se o numero de vendas da peça é diferente de 0, se for, o lucro medio é calculado dividindo o lucro total pelo numero de ingressos vendidos
        //        double lucroMedioPorPeca1 = vendasPorPeca[0] != 0 ? lucroPorPeca[0] / vendasPorPeca[0] : 0;
        //        double lucroMedioPorPeca2 = vendasPorPeca[1] != 0 ? lucroPorPeca[1] / vendasPorPeca[1] : 0;
        //        double lucroMedioPorPeca3 = vendasPorPeca[2] != 0 ? lucroPorPeca[2] / vendasPorPeca[2] : 0;
        //
        //
        //        });
        //    }

}
