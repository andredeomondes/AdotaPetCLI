package utils;

/**
 * LoadingAnimation
 *
 * Simple and clean terminal loading animations (no text, just animation).
 *
 * Examples:
 *   LoadingAnimation.dots(3000);
 *   LoadingAnimation.spinner(4000);
 *   LoadingAnimation.bar(3500);
 */
public class LoadingAnimation {

    // =========================================================================================
    // DOTS animation ( . .. ... .... looping )
    // =========================================================================================
    public static void dots(int durationMs) {
        long end = System.currentTimeMillis() + durationMs;
        int i = 0;
        while (System.currentTimeMillis() < end) {
            String dots = ".".repeat(i % 4);
            System.out.print("\r" + dots + "   ");
            sleep(400);
            i++;
        }
        clearLine();
    }

    // =========================================================================================
    // SPINNER animation (classic rotating bar)
    // =========================================================================================
    public static void spinner(int durationMs) {
        String[] frames = {"|", "/", "-", "\\"};
        long end = System.currentTimeMillis() + durationMs;
        int i = 0;
        while (System.currentTimeMillis() < end) {
            System.out.print("\r" + frames[i % frames.length]);
            sleep(100);
            i++;
        }
        clearLine();
    }

    // =========================================================================================
    // MODERN SPINNER (Unicode smooth loader)
    // =========================================================================================
    public static void smoothSpinner(int durationMs) {
        String[] frames = {"⠋","⠙","⠸","⠴","⠦","⠇","⠋"};
        long end = System.currentTimeMillis() + durationMs;
        int i = 0;
        while (System.currentTimeMillis() < end) {
            System.out.print("\r" + frames[i % frames.length]);
            sleep(100);
            i++;
        }
        clearLine();
    }

    // =========================================================================================
    // PROGRESS BAR animation
    // =========================================================================================
    public static void bar(int durationMs) {
        int total = 30;
        long start = System.currentTimeMillis();
        long end = start + durationMs;

        while (System.currentTimeMillis() < end) {
            double progress = (double)(System.currentTimeMillis() - start) / (end - start);
            int filled = (int)(progress * total);

            String bar = "[" + "=".repeat(filled) + " ".repeat(total - filled) + "]";
            System.out.print("\r" + bar);
            sleep(100);
        }
        clearLine();
    }

    // =========================================================================================
    // Helper methods
    // =========================================================================================
    private static void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    private static void clearLine() {
        System.out.print("\r" + " ".repeat(50) + "\r");
    }
}
