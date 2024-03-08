class Processo extends Evento {
    private int prioridade;
    private int milhoesInstrucoes;
    private int ram;
    private double ioRate;

    public Processo(int timeStamp, int prioridade, int milhoesInstrucoes, int ram, double ioRate) {
        super(timeStamp);
        this.prioridade = prioridade;
        this.milhoesInstrucoes = milhoesInstrucoes;
        this.ram = ram;
        this.ioRate = ioRate;
    }
    public int getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    public int getMilhoesInstrucoes() {
        return milhoesInstrucoes;
    }
    public void setMilhoesInstrucoes(int milhoesInstrucoes) {
        this.milhoesInstrucoes = milhoesInstrucoes;
    }

    public int getRam() {
        return ram;
    }
    public void setRam(int ram) {
        this.ram = ram;
    }
    public double getIoRate() {
        return ioRate;
    }
    public void setIoRate(double ioRate) {
        this.ioRate = ioRate;
    }
    public void execute() {
        System.out.println("Executando processo no timestamp: " + getTimeStamp());
        // Cálculo do tempo de execução com base nas instruções e na velocidade de cada CPU
        int velocidadeCPU = 100;  // Substitua pelo valor real da velocidade da CPU
        long tempoExecucao = (milhoesInstrucoes * 1000) / velocidadeCPU;  // Convertido para milissegundos
        System.out.println(tempoExecucao);

        // Atualização do relógio global com o tempo de execução do processo
        RelogioGlobal.setData(getTimeStamp() + tempoExecucao);
    }
}
