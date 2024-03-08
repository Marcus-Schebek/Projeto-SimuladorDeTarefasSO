public class CPU {
    private int velQuantumCPU;
    private int tempoOciosidade;
    private int tempoOcupacao;
    private int ultimoUpdate;
    private RelogioGlobal relGlobal;

    public CPU(int velCpu, RelogioGlobal relGlobal){
        this.velQuantumCPU = velCpu;
        this.ultimoUpdate = 0;
        this.tempoOciosidade = 0;
        this.tempoOcupacao = 0;
        this.relGlobal = relGlobal;
    }

    public int getVelQuantumCPU(){
        return this.velQuantumCPU;
    }

    public int getTempoOcioso(){
        return this.tempoOciosidade;
    }

    public int getTempoOcupado(){
        return this.tempoOcupacao;
    }

    public void setVelQuantumCPU(int velQuantumCPU){
        this.velQuantumCPU = velQuantumCPU;
    }

    public void atualizaTempos(boolean ociosidade) {
        @SuppressWarnings("static-access")
        int tempoAtual = relGlobal.getData();
        int deltaTempo = tempoAtual - ultimoUpdate;
        if (ociosidade) {
            tempoOciosidade += deltaTempo;
        } else {
            tempoOcupacao += deltaTempo;
        }
        ultimoUpdate = tempoAtual;
    }
}
