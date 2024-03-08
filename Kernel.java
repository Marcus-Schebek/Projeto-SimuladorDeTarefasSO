import java.util.PriorityQueue;
import java.util.Queue;

public class Kernel {
    // Fila de eventos a serem processados
    private PriorityQueue<Processo> filaEventos;
    private Queue<CPU> cpus;
    // Variáveis para estatísticas
    private RelogioGlobal relGlobal;  
    private int tempoExecucaoTotal;
    private int tempoOcupacaoCPU;
    private int tempoOciosidadeCPU;

    // Tamanho da RAM e do Swap
    private int tamanhoRAM;
    private int tamanhoSwap;



    // Construtor da classe Kernel
    public Kernel(PriorityQueue<Processo> filaEventos, RelogioGlobal relGlobal, int tamanhoRAM, Queue<CPU> cpus) {
        this.filaEventos = filaEventos;
        this.relGlobal = relGlobal;
        this.tempoExecucaoTotal = 0;
        this.tempoOcupacaoCPU = 0;
        this.tempoOciosidadeCPU = 0;
        this.cpus = cpus;

        // Configuração do tamanho da RAM
        this.tamanhoRAM = tamanhoRAM;

        // Calcula o tamanho do swap (50% do tamanho da RAM)
        this.tamanhoSwap = (int) (tamanhoRAM * 0.5);

        // Inicializa a memória disponível (RAM + Swap)
        this.tamanhoRAM = tamanhoRAM + tamanhoSwap;
    }

    // Método principal para executar a simulação
    @SuppressWarnings("static-access")
    public void run() {
        while (!filaEventos.isEmpty()) {
            // Retira o próximo evento da fila de eventos
            Processo evento = filaEventos.poll();    
            // Atualiza o relógio global com o timestamp do evento
            relGlobal.setData(evento.getTimeStamp());
            // Verifica se há alguma CPU disponível
            if (cpus.isEmpty()) {
                System.out.println("Nenhuma CPU disponível.");
                continue;
            }
        
            // Retira a próxima CPU da fila de CPUs
            CPU cpu = cpus.poll();
        
            // Registra o início da execução do evento
            int inicioExecucao = relGlobal.getData();
        
            if (verificarMemoriaDisponivel(evento)) {
                cpu.atualizaTempos(false);
                evento.execute();
                cpu.atualizaTempos(true);
            } else {
                // Se não houver memória disponível, o evento é ignorado
                System.out.println("Ignorando evento devido à falta de memória.");
            }
            
        
            // Registra o fim da execução do evento
            int fimExecucao = relGlobal.getData();
            int tempoExecucaoEvento = fimExecucao - inicioExecucao;
        
            // Atualiza as estatísticas
            tempoExecucaoTotal += tempoExecucaoEvento;
        
            // Coloca a CPU de volta na fila
            cpus.add(cpu);
        }

        apresentarEstatisticas();
    }
    
    // Método para verificar se há memória disponível para o evento
    private boolean verificarMemoriaDisponivel(Processo processo) {
        // Verifica se a memória RAM necessária para o processo é maior que a memória RAM disponível
        if (processo.getRam() > this.tamanhoRAM) {
            return false;
        } else {
            // Se a memória RAM necessária para o processo for menor ou igual à memória RAM disponível,
            // subtrai a memória RAM necessária para o processo da memória RAM disponível
            this.tamanhoRAM -= processo.getRam();
            return true;
        }
    }
    
    // Método para liberar memória após a execução do evento
    public void liberarMemoria(int tamanho) {
        tamanhoRAM += tamanho;
    }

    // Método para alocar memória para o evento
    public boolean alocarMemoria(int tamanho) {
        if (tamanho <= tamanhoRAM) {
            tamanhoRAM -= tamanho;
            return true;
        } else {
            return false;
        }
    }

    // Método para apresentar estatísticas ao final da simulação
    private void apresentarEstatisticas() {
        System.out.println("Estatísticas coletadas:");
        System.out.println("Tempo total de execução simulada: " + tempoExecucaoTotal + " unidades de tempo");
        for (CPU cpu : cpus) {
            int tempoOcupacao = cpu.getTempoOcupado();
            int tempoOciosidade = cpu.getTempoOcioso();
            System.out.println("Tempo de ocupação da CPU: " + tempoOcupacao);
            System.out.println("Tempo de ociosidade da CPU: " + tempoOciosidade);
        }
    }
}
