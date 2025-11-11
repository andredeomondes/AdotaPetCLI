package utils;

/**
 * Classe Style
 *
 * Biblioteca para estilizar e padronizar mensagens no terminal (CLI).
 *
 * Fornece:
 *  - Cores e estilos ANSI
 *  - Mensagens padronizadas (boas-vindas, sucesso, erro, informação)
 *
 * Uso:
 *   System.out.println(Style.msgSucesso("Pet cadastrado com sucesso!"));
 *   System.out.println(Style.msgErro("Falha ao salvar o arquivo."));
 *   System.out.println(Style.msgBoasVindas("AdotaPetCLI"));
 */
public class Style {

    // ==============================================================================================================
    // Códigos ANSI
    // ==============================================================================================================
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String ITALIC = "\u001B[3m";
    private static final String UNDERLINE = "\u001B[4m";

    private static final String FG_RED = "\u001B[31m";
    private static final String FG_GREEN = "\u001B[32m";
    private static final String FG_BLACK = "\u001B[30m";
    private static final String FG_YELLOW = "\u001B[33m";
    private static final String FG_CYAN = "\u001B[36m";
    private static final String FG_WHITE = "\u001B[37m";

    private static final String BG_BLUE = "\u001B[44m";
    private static final String BG_BLACK = "\u001B[40m";
    private static final String BG_WHITE = "\u001B[47m";
    private static final String BG_GREEN = "\u001B[42m";
    private static final String BG_RED = "\u001B[41m";

    // ==============================================================================================================
    // Estilos básicos
    // ==============================================================================================================
    public static String bold(String text) { return BOLD + text + RESET; }
    public static String italic(String text) { return ITALIC + text + RESET; }
    public static String underline(String text) { return UNDERLINE + text + RESET; }

    // ==============================================================================================================
    // Mensagens padronizadas
    // ==============================================================================================================

    /**
     * Mensagem de boas-vindas (banner principal)
     */
    public static String msgBoasVindas(String sistemaNome) {
        String titulo = "🐾 " + sistemaNome + " 🐾";
        String barra = "==============================================================";
        return "\n" +
                BG_BLUE + FG_WHITE + BOLD +
                barra + "\n" +
                centralizar(titulo, barra.length()) + "\n" +
                barra + "\n" + RESET ;
    }

    /**
     * Mensagem de sucesso
     */
    public static String msgSucesso(String mensagem) {
        return BG_GREEN + FG_WHITE + " ✅ SUCESSO: " + RESET +
                FG_GREEN + mensagem + RESET;
    }

    /**
     * Mensagem de erro
     */
    public static String msgErro(String mensagem) {
        return BG_RED + FG_WHITE + " ❌ ERRO: " + RESET +
                FG_RED + mensagem + RESET;
    }

    /**
     * Mensagem de informação
     */
    public static String msgInfo(String mensagem) {
        return BG_BLUE + FG_WHITE + " ℹ INFO: " + RESET + " " +
                FG_CYAN + mensagem + RESET;
    }

    /**
     * Mensagem de aviso
     */
    public static String msgAviso(String mensagem) {
        return BG_BLACK + FG_YELLOW + " ⚠ AVISO: " + RESET +
                FG_YELLOW + mensagem + RESET;
    }

    // ==============================================================================================================
    // Função auxiliar
    // ==============================================================================================================
    private static String centralizar(String texto, int largura) {
        int espacos = (largura - texto.length()) / 2;
        if (espacos <= 0) return texto;
        return " ".repeat(espacos) + texto;
    }

}
