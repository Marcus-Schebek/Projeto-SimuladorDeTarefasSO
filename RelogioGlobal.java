// Classe que representa o relógio global
class RelogioGlobal {
    // Variável estática para armazenar a data atual
    private static int dataAtual;

    // Setter para atualizar a data atual
    public static void setData(int newData) {
        dataAtual = newData;
    }

    // Getter para obter a data atual
    public static int getData() {
        return dataAtual;
    }
}