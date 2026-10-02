import static java.lang.IO.println;
import static java.lang.IO.readln;

/** Helpers de terminal usados por Combate e Campanha. */
public final class Ui {

    private Ui() {}

    /** Le uma linha; encerra o programa se a entrada acabar (EOF). */
    public static String ler(String prompt) {
        String s = readln(prompt);
        if (s == null) System.exit(0);
        return s.trim();
    }

    public static void enter() {
        ler("\n[ENTER para continuar]");
    }

    public static void titulo(String texto) {
        println("\n==================================");
        println(texto);
        println("==================================");
    }

    public static void fala(String quem, String texto) {
        println("\n" + quem.toUpperCase() + ":");
        println(texto);
    }

    /** Mostra opcoes numeradas e retorna a escolhida (1..n). */
    public static int escolha(String titulo, String... opcoes) {
        println("\n" + titulo);
        for (int i = 0; i < opcoes.length; i++) println("[" + (i + 1) + "] " + opcoes[i]);
        while (true) {
            try {
                int n = Integer.parseInt(ler("Opcao: "));
                if (n >= 1 && n <= opcoes.length) return n;
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            println("Escolha entre 1 e " + opcoes.length + ".");
        }
    }

    public static boolean chance(double p) {
        return Math.random() < p;
    }

    public static int rand(int n) {
        return (int) (Math.random() * n);
    }
}