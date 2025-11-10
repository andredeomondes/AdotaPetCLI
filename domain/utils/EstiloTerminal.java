package utils;

public class EstiloTerminal {

    public static final String RESET = "\033[0m";
    public static final String BOLD = "\033[1m";
    public static final String ITALIC = "\033[3m";

    public static final String AZUL = "\033[38;5;39m";
    public static final String VERDE = "\033[38;5;46m";
    public static final String AMARELO = "\033[38;5;226m";
    public static final String ROXO = "\033[38;5;141m";
    public static final String CIANO = "\033[38;5;51m";
    public static final String VERMELHO = "\033[38;5;203m";
    public static final String CINZA = "\033[38;5;240m";

    public static String estilizar(String texto, boolean bold, boolean italic, String cor) {
        StringBuilder sb = new StringBuilder();
        if (bold) sb.append(BOLD);
        if (italic) sb.append(ITALIC);
        if (cor != null) sb.append(cor);
        sb.append(texto).append(RESET);
        return sb.toString();
    }

    public static void spinner(String msg, int tempoMs) {
        try {
            String[] spin = {"⠋", "⠙", "⠸", "⠴", "⠦", "⠇"};
            long fim = System.currentTimeMillis() + tempoMs;
            int i = 0;
            while (System.currentTimeMillis() < fim) {
                String simbolo = spin[i++ % spin.length];
                System.out.print("\r" + estilizar(msg, true, false, CIANO) + " " +
                        estilizar(simbolo, false, true, ROXO));
                Thread.sleep(120);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.print("\r" + estilizar("✔ Concluído!", true, false, VERDE) + "\n");
    }

    public static void barraProgresso(String msg, int duracaoMs) throws InterruptedException {
        int total = 25;
        for (int i = 0; i <= total; i++) {
            int pct = (i * 100) / total;
            String barra = "█".repeat(i) + "-".repeat(total - i);
            System.out.print("\r" + estilizar(msg, true, false, AZUL) + " [" +
                    estilizar(barra, false, false, CINZA) + "] " +
                    estilizar(pct + "%", true, true, AMARELO));
            Thread.sleep(duracaoMs / total);
        }
        System.out.print("\r" + estilizar("✔ Tudo pronto!", true, false, VERDE) + "\n");
    }
}
