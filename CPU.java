public class CPU {
    private int velQuantumCPU;
    private long tempoOciosidade;
    private long tempoOcupacao;
    private long ultimoUpdate;
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

    public long getTempoOcioso(){
        return this.tempoOciosidade;
    }

    public long getTempoOcupado(){
        return this.tempoOcupacao;
    }

    public void setTempoOcupado(int tempoOcupado){
        this.tempoOcupacao += tempoOcupado;
    }

    public void setVelQuantumCPU(int velQuantumCPU){
        this.velQuantumCPU = velQuantumCPU;
    }

    public void atualizaTempos(boolean ociosidade) {
        @SuppressWarnings("static-access")
        long tempoAtual = relGlobal.getData();
        long deltaTempo = tempoAtual - ultimoUpdate;
        if (ociosidade) {
            tempoOciosidade += deltaTempo;
        } else {
            tempoOcupacao += deltaTempo;
        }
        ultimoUpdate = tempoAtual;
    }
}
