import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

// Classe principal que contém o método main
public class Main {
    public static void main(String[] args) {
        // Verifica se o número de argumentos é válido
        if (args.length != 1) {
            System.out.println("Uso: java Main <arquivo.csv>");
            System.exit(1);
        }

        // Obtém o caminho do arquivo CSV a partir dos argumentos
        String arquivoCSV = args[0];
        // Lê o arquivo CSV e cria a fila de eventos
        PriorityQueue<Processo> filaEventos = lerArquivoCSV(arquivoCSV);
        // Instancia o Relogio Global
        RelogioGlobal relGlobal = new RelogioGlobal();
        //Instancia a lista de CPUS
        Queue<CPU> cpus = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            cpus.add(new CPU(4, relGlobal)); // Substitua 1 pela velocidade desejada da CPU
        }
        // Cria uma instância do Kernel com a fila de eventos
        Kernel kernel = new Kernel(filaEventos, relGlobal, 8, cpus);
        // Executa a simulação
        kernel.run();
    }

    // Método para ler o arquivo CSV e criar a fila de eventos
    private static PriorityQueue<Processo> lerArquivoCSV(String arquivoCSV) {
        PriorityQueue<Processo> filaEventos = new PriorityQueue<>((p1, p2) -> {
            if (p1.getPrioridade() != p2.getPrioridade()) {
                // Se as prioridades são diferentes, dê preferência ao processo com maior prioridade
                return Integer.compare(p2.getPrioridade(), p1.getPrioridade());
            } else {
                // Se as prioridades são iguais, use o timestamp para ordenar
                return Integer.compare(p1.getTimeStamp(), p2.getTimeStamp());
            }
        });

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Ignora linhas que começam com #
                if (!linha.trim().startsWith("#")) {
                    // Divide os dados da linha
                    String[] dados = linha.split(",");
                    // Converte os dados para os tipos apropriados
                    int timestamp = Integer.parseInt(dados[0].trim());
                    int prioridade = Integer.parseInt(dados[1].trim());
                    int mi = Integer.parseInt(dados[2].trim());
                    int ram = Integer.parseInt(dados[3].trim());
                    double ioRate = Double.parseDouble(dados[4].trim());

                    // Adiciona um novo evento (Processo) à fila
                    filaEventos.add(new Processo(timestamp, prioridade, mi, ram, ioRate));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return filaEventos;
    }
}
