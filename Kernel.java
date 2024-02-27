import java.util.PriorityQueue;

public class Kernel {
    // Fila de eventos a serem processados
    private PriorityQueue<Evento> filaEventos;
    // Variáveis para estatísticas
    private RelogioGlobal relGlobal;  
    private int tempoExecucaoTotal;
    private int tempoOcupacaoCPU;
    private int tempoOciosidadeCPU;

    // Tamanho da RAM e do Swap
    private int tamanhoRAM;
    private int tamanhoSwap;



    // Construtor da classe Kernel
    public Kernel(PriorityQueue<Evento> filaEventos, RelogioGlobal relGlobal, int tamanhoRAM) {
        this.filaEventos = filaEventos;
        this.relGlobal = relGlobal;
        this.tempoExecucaoTotal = 0;
        this.tempoOcupacaoCPU = 0;
        this.tempoOciosidadeCPU = 0;

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
            Evento evento = filaEventos.poll();
            tempoExecucaoTotal += 20; //Adicione 20 unidades de tempo de Delay entre cada processo
            // Atualiza o relógio global com o timestamp do evento
            relGlobal.setData(evento.getTimeStamp());

            // Registra o início da execução do evento
            int inicioExecucao = relGlobal.getData();

            // Executa o evento apenas se houver memória disponível
            if (verificarMemoriaDisponivel()) {
                evento.execute();
            } else {
                // Se não houver memória disponível, o evento é ignorado
                System.out.println("Ignorando evento devido à falta de memória.");
            }

            // Registra o fim da execução do evento
            int fimExecucao = relGlobal.getData();
            int tempoExecucaoEvento = fimExecucao - inicioExecucao;

            // Atualiza as estatísticas
            tempoExecucaoTotal += tempoExecucaoEvento;
            
            
            // Atualiza estatísticas de ocupação e ociosidade da CPU
            if (evento instanceof Processo) {
                tempoOcupacaoCPU += tempoExecucaoEvento;
            } else {
                tempoOciosidadeCPU += tempoExecucaoEvento;
            }
            
            tempoOciosidadeCPU = tempoExecucaoTotal - tempoOcupacaoCPU;
         
        }
        apresentarEstatisticas();
    }

    // Método para verificar se há memória disponível para o evento
    private boolean verificarMemoriaDisponivel() {
        return tamanhoRAM > 0;
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
        System.out.println("Tempo de ocupação da CPU: " + tempoOcupacaoCPU + " unidades de tempo");
        System.out.println("Tempo de ociosidade da CPU: " + tempoOciosidadeCPU + " unidades de tempo");
    }
}
