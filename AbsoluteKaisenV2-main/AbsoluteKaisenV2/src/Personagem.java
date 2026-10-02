import java.io.Serializable;
import java.util.function.Consumer;

public class Personagem implements Serializable {

    private static final long serialVersionUID = 1L;

    public String nome;
    public String pele;
    public String biotipo;
    public String cabelo;
    public String cla;

    // Tecnica atual do personagem
    // Deixei uma tecnica inicial para não acontecer NullPointerException
    public static Tecnica tecnica = Tecnica.ILIMITADO;

    public boolean seisOlhos;

    public int level = 1;
    public float ataque = 40;
    public float defesa = 30;
    public float vida = 100;
    public float energiaReversa = 3;

    public int xp = 0;
    public String rank = "Grau 4";

    public boolean dominioUsadoNaMissaoAtual = false;
    public int missaoAtual = 0;

    public boolean estaVivo() {
        return vida > 0;
    }

    public boolean podeUsarReversaoDeFeitico() {
        return level >= 20;
    }

    // Verifica se o personagem já pode usar a Expansão de Domínio
    public boolean podeUsarExpansao() {
        return tecnica != null && level >= tecnica.nivelExpansao;
    }

    /**
     * Aplica XP e processa os level ups.
     */
    public void ganharXp(int quantidade, Consumer<String> log) {

        xp += quantidade;

        double xpCalculado = 2 * Math.pow(level, 2.3) + 84;

        while (xp >= xpCalculado) {

            xp = (int) (xp - xpCalculado);

            level++;

            ataque += 7.5;
            defesa += 5;
            vida += 50;

            log.accept("\n*** LEVEL UP! Nivel " + level + " ***");

            // Verifica habilidades desbloqueadas
            if (tecnica != null) {

                for (Habilidade h : tecnica.habilidades) {

                    if (h.nivelNecessario() == level) {

                        log.accept(
                                ">> Desbloqueado: "
                                        + h.nome().toUpperCase()
                                        + "!"
                        );
                    }
                }
            }

            // Reversão de Feitiço
            if (level == 20) {

                log.accept(
                        ">> REVERSAO DE FEITICO DESBLOQUEADA! (Grau 1 atingido)"
                );
            }

            // Aviso quando liberar a Expansão
            if (tecnica != null && level == tecnica.nivelExpansao) {

                log.accept(
                        ">> EXPANSAO DE DOMINIO DESBLOQUEADA: "
                                + tecnica.nomeExpansao.toUpperCase()
                                + "!"
                );
            }
        }

        atualizarRank();
    }

    private void atualizarRank() {

        if (level >= 750)
            rank = "Grau Especial";

        else if (level >= 450)
            rank = "Grau Semi Especial";

        else if (level >= 150)
            rank = "Grau 1";

        else if (level >= 50)
            rank = "Grau 2";

        else if (level >= 5)
            rank = "Grau 3";
    }
}