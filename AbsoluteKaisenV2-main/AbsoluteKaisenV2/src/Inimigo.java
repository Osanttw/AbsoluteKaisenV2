import java.util.List;

/**
 * Um inimigo pode ter varias fases (chefes). Cada fase tem seu proprio HP,
 * dano e comportamento: a cada "golpeForteACada" turnos o inimigo usa um golpe
 * devastador (dano x2), sempre avisado com um turno de antecedencia.
 *
 * Os numeros sao gerados a partir de um nivel de referencia, entao basta dizer
 * "este chefe e nivel 28" e o HP/dano acompanham a curva de poder do jogador.
 */
public record Inimigo(String nome, int xp, int yen, List<Fase> fases) {

    public record Fase(String titulo, String fala, float hp, float dano, int golpeForteACada) {}

    public static float hpBase(int nivel)   { return 450f + 105f * nivel; }
    public static float danoBase(int nivel) { return 40f + 8f * nivel; }
    public static int xpNivel(int nivel)    { return (int) (2 * Math.pow(nivel, 2.3) + 84); }

    public static Fase fase(String titulo, String fala, int nivel, float fatorHp, float fatorDano, int golpeForte) {
        return new Fase(titulo, fala, hpBase(nivel) * fatorHp, danoBase(nivel) * fatorDano, golpeForte);
    }

    /** Inimigo comum de uma fase so, escalado pelo nivel. */
    public static Inimigo comum(String nome, int nivel) {
        nivel = Math.max(1, nivel);
        return new Inimigo(nome, (int) (xpNivel(nivel) * 0.35), 40 + nivel * 12,
                List.of(fase("", "", nivel, 1f, 1f, 0)));
    }

    /** Chefe: xpFator e quantos "niveis de XP" ele vale. */
    public static Inimigo chefe(String nome, int nivel, float xpFator, List<Fase> fases) {
        return new Inimigo(nome, (int) (xpNivel(nivel) * xpFator), 300 + nivel * 50, fases);
    }
}