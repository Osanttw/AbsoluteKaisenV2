/**
 * Itens do inventario.
 *  CONSUMIVEL: usado em combate (ou fora, se curar).
 *  PERMANENTE: objeto amaldicoado que, ao ser usado, muda o personagem para sempre.
 *  CHAVE: item de historia.
 */
public enum Item {

    POCAO("Pocao de Cura", "Recupera 500 HP.", Tipo.CONSUMIVEL, 0, 0, 0, 500, 0, 0),
    TALISMA_EXPLOSIVO("Talisma Explosivo", "Explode causando 3x o seu dano base.", Tipo.CONSUMIVEL, 0, 0, 0, 0, 3, 0),
    TONICO_REVERSO("Tonico de Energia Reversa", "Restaura 1 uso de Energia Reversa.", Tipo.CONSUMIVEL, 0, 0, 0, 0, 0, 1),

    OLHO_AMALDICOADO("Olho Amaldicoado", "Objeto amaldicoado. Uso: +30 de ataque permanente.", Tipo.PERMANENTE, 30, 0, 0, 0, 0, 0),
    FRAGMENTO_DE_ALMA("Fragmento de Alma", "Objeto amaldicoado. Uso: +400 de vida maxima permanente.", Tipo.PERMANENTE, 0, 0, 400, 0, 0, 0),
    TALISMA_ANCESTRAL("Talisma Ancestral", "Objeto amaldicoado. Uso: +15 de ataque e +20 de defesa permanentes.", Tipo.PERMANENTE, 15, 20, 0, 0, 0, 0),

    DEDO_DE_SUKUNA("Dedo de Sukuna", "Um dedo mumificado que pulsa com poder proibido.", Tipo.CHAVE, 0, 0, 0, 0, 0, 0);

    public enum Tipo { CONSUMIVEL, PERMANENTE, CHAVE }

    public final String nome;
    public final String descricao;
    public final Tipo tipo;
    public final int bonusAtaque;
    public final int bonusDefesa;
    public final int bonusVida;
    public final int cura;        // HP curado ao usar
    public final int danoMult;    // causa (danoBase x danoMult) no inimigo ao usar em combate
    public final int energia;     // usos de Energia Reversa restaurados

    Item(String nome, String descricao, Tipo tipo, int bonusAtaque, int bonusDefesa, int bonusVida,
         int cura, int danoMult, int energia) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
        this.bonusVida = bonusVida;
        this.cura = cura;
        this.danoMult = danoMult;
        this.energia = energia;
    }

    @Override
    public String toString() {
        return nome;
    }
}