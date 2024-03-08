// Classe que representa o relógio global
class RelogioGlobal {
    // Variável estática para armazenar a data atual
    private static long dataAtual;

    // Setter para atualizar a data atual
    public static void setData(long newData) {
        dataAtual = newData;
    }

    // Getter para obter a data atual
    public static long getData() {
        return dataAtual;
    }
}