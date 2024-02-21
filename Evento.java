
// Classe abstrata que representa um evento no simulador
abstract class Evento {
    // Relógio global compartilhado por todos os eventos
    protected static int relGlobal;
    // Timestamp do evento
    protected int timeStamp;

    // Construtor da classe Evento
    public Evento(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    // Getter para obter o timestamp do evento
    public int getTimeStamp() {
        return timeStamp;
    }

    // Método abstrato que será implementado nas subclasses
    public abstract void execute();
}



