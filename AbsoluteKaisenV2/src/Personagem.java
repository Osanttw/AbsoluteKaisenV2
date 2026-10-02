import java.io.Serializable;
import java.util.function.Consumer;

public class Personagem implements Serializable {

    private static final long serialVersionUID = 1L;

    public String nome;
    public String pele;
    public String biotipo;
    public String cabelo;
    public String cla;
    public Tecnica tecnica;
    public boolean seisOlhos;

    public int level = 1;
    public float ataque = 40;
    public float defesa = 30;
    public float vida = 100;
    public float energiaReversa = 3;

    public int xp = 0;
    public String rank = "Grau 4";

    public boolean dominioUsadoNaMissaoAtual = false;

    public boolean estaVivo() {
        return vida > 0;
    }

    public boolean podeUsarReversaoDeFeitico() {
        return level >= 20;
    }

    /**
     * Aplica XP e processa quantos level ups forem necessarios,
     * chamando "log" para cada mensagem que precisa ser exibida na tela
     * (mantem esta classe independente de println/IO).
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

            for (Habilidade h : tecnica.habilidades) {
                if (h.nivelNecessario() == level) {
                    log.accept(">> Desbloqueado: " + h.nome().toUpperCase() + "!");
                }
            }
            if (level == 20) {
                log.accept(">> REVERSAO DE FEITICO DESBLOQUEADA! (Grau 1 atingido)");
            }
        }
        atualizarRank();
    }

    private void atualizarRank() {
        if (level >= 750)      rank = "Grau Especial";
        else if (level >= 450) rank = "Grau Semi Especial";
        else if (level >= 150) rank = "Grau 1";
        else if (level >= 50) rank = "Grau 2";
        else if (level >= 5)  rank = "Grau 3";
    }
}
