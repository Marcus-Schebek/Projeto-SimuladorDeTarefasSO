// Classe que representa um processo, subclasse de Evento
class Processo extends Evento {
    private int prioridade;
    private int milhoesInstrucoes;
    private int ram;
    private double ioRate;

    // Construtor da classe Processo
    public Processo(int timeStamp, int prioridade, int milhoesInstrucoes, int ram, double ioRate) {
        // Chama o construtor da superclasse (Evento)
        super(timeStamp);
        this.prioridade = prioridade;
        this.milhoesInstrucoes = milhoesInstrucoes;
        this.ram = ram;
        this.ioRate = ioRate;
    }

    // Getter para prioridade
    public int getPrioridade() {
        return prioridade;
    }

    // Setter para prioridade
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    // Getter para milhoesInstrucoes
    public int getMilhoesInstrucoes() {
        return milhoesInstrucoes;
    }

    // Setter para milhoesInstrucoes
    public void setMilhoesInstrucoes(int milhoesInstrucoes) {
        this.milhoesInstrucoes = milhoesInstrucoes;
    }

    // Getter para ram
    public int getRam() {
        return ram;
    }

    // Setter para ram
    public void setRam(int ram) {
        this.ram = ram;
    }

    // Getter para ioRate
    public double getIoRate() {
        return ioRate;
    }

    // Setter para ioRate
    public void setIoRate(double ioRate) {
        this.ioRate = ioRate;
    }

    // Implementação do método execute da superclasse
    public void execute() {
        System.out.println("Executando processo no timestamp: " + getTimeStamp());

        // Cálculo do tempo de execução com base nas instruções e na velocidade de cada CPU
        int velocidadeCPU = 100;  // Substitua pelo valor real da velocidade da CPU
        int tempoExecucao = (milhoesInstrucoes * 1000) / velocidadeCPU;  // Convertido para milissegundos

        // Atualização do relógio global com o tempo de execução do processo
        RelogioGlobal.setData(getTimeStamp() + tempoExecucao);
    }
}
