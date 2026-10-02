/**
  Aliados que acompanham o jogador em missoes importantes.
  A cada turno o companheiro ataca sozinho, e pode ter um efeito passivo.
 */
public enum Companheiro {

    YUJI("Yuji", "Yuji Itadori", "Dano fisico", 0.80f, 0f, 0f, "Yuji acerta um Soco Divergente!"),
    MEGUMI("Megumi", "Megumi Fushiguro", "Estrategia (30% de chance de atrapalhar o inimigo)", 0.35f, 0.30f, 0f, "Megumi ataca com as sombras!"),
    NOBARA("Nobara", "Nobara Kugisaki", "Dano a distancia", 0.60f, 0.10f, 0f, "Nobara crava um prego amaldicoado no oponente!"),
    MAKI("Maki", "Maki Zenin", "Combate fisico (reduz 15% do dano recebido)", 0.70f, 0f, 0.15f, "Maki golpeia com a Nuvem Brincalhona!"),
    PANDA("Panda", "Panda", "Tanque (reduz 30% do dano recebido)", 0.40f, 0f, 0.30f, "Panda bloqueia e contra-ataca!");

    public final String nome;           // nome curto, tambem usado como chave de afinidade
    public final String nomeCompleto;
    public final String papel;
    public final float fatorDano;       // fracao do dano base do jogador
    public final float chancePular;     // chance de o inimigo perder o turno
    public final float reducaoDano;     // reducao do dano recebido pelo jogador
    public final String frase;

    Companheiro(String nome, String nomeCompleto, String papel, float fatorDano,
                float chancePular, float reducaoDano, String frase) {
        this.nome = nome;
        this.nomeCompleto = nomeCompleto;
        this.papel = papel;
        this.fatorDano = fatorDano;
        this.chancePular = chancePular;
        this.reducaoDano = reducaoDano;
        this.frase = frase;
    }
}