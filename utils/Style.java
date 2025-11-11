package utils;

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

    public static String msgWelcome(String systemName) {
        String titulo = "🐾 " + systemName + " 🐾";
        String barra = "==============================================================";
        return "\n" +
                BG_BLUE + FG_WHITE + BOLD +
                barra + "\n" +
                center(titulo, barra.length()) + "\n" +
                barra + "\n" + RESET ;
    }

    /**
     * Mensagem de sucesso
     */
    public static String msgOk(String mensagem) {
        return BG_GREEN + FG_WHITE + " ✅ SUCCESS: " + RESET + " " +
                FG_GREEN + mensagem + RESET  + " \n";
    }

    /**
     * Mensagem de erro
     */
    public static String msgError(String mensagem) {
        return BG_RED + FG_WHITE + " ❌ ERROR: " + RESET +
                FG_RED + mensagem + RESET + " \n";
    }

    /**
     * Mensagem de informação
     */
    public static String msgInfo(String msg) {
        return BG_BLUE + FG_WHITE + " ℹ INFO: " + RESET + " " +
                FG_CYAN + msg + RESET + " \n";
    }

    public static String msgQuestion(String msg) {
        return BG_BLUE + FG_WHITE + " [?] QUESTION:" + RESET + " " +
                FG_CYAN + msg + RESET + " "; }

    /**
     * Mensagem de aviso
     */
    public static String msgAdvice(String msg) {
        return BG_BLACK + FG_YELLOW + " ⚠ ADVICE: " + RESET +
                FG_YELLOW + msg + RESET + " \n";
    }

    // ==============================================================================================================
    // Função auxiliar
    // ==============================================================================================================
    private static String center(String text, int weight) {
        int spaces = (weight - text.length()) / 2;
        if (spaces <= 0) return text;
        return " ".repeat(spaces) + text;
    }

}
