// Classe que representa o relógio global
class RelogioGlobal {
    // Variável estática para armazenar a data atual
    private static long dataAtual;
    public static void setData(long newData) {
        dataAtual = newData;
    }
    public static long getData() {
        return dataAtual;
    }
}