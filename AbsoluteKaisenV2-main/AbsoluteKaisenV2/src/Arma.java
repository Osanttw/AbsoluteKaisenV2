/**
 * Ferramentas amaldicoadas. Quando equipada, a arma da bonus de ataque/defesa
 * e libera uma habilidade propria (tecla [A] no combate), com usos limitados por luta.
 */
public enum Arma {

    Abatedora_de_Demônios("Faca Amaldicoada", "Lamina simples banhada em energia amaldicoada.", 15, 0, 4,
            new Habilidade("Corte Rapido", 1, 2.5f, "CORTE RAPIDO! A lamina risca o ar!")),

    CORDA_NEGRA("Corda Negra", "Corda que drena a energia amaldicoada de quem ela prende.", 10, 10, 3,
            new Habilidade("Amarracao", 1, 2f, "AMARRACAO! A corda suga a forca do inimigo!", EfeitoEspecial.ENFRAQUECE)),

    CORRENTE_DE_MIL_MILHAS("Corrente de Mil Milhas", "Corrente de alcance absurdo e movimentos imprevisiveis.", 20, 20, 3,
            new Habilidade("Corrente Infinita", 1, 3.5f, "CORRENTE INFINITA! Ela vem de todos os lados!", EfeitoEspecial.PULA_TURNO_ALEATORIO)),

    FACAO_DE_NANAMI("Facao de Nanami", "O facao cego do feiticeiro de Grau 1. Golpeia o ponto fraco.", 35, 5, 3,
            new Habilidade("Hora Extra", 1, 5.5f, "HORA EXTRA! Golpe certeiro no ponto fraco!")),

    NUVEM_BRINCALHONA("Nuvem Brincalhona", "Bastao de tres secoes, arma de Grau Especial.", 30, 15, 3,
            new Habilidade("Golpe de Bastao", 1, 4f, "GOLPE DE BASTAO! Tres secoes, um impacto!")),

    MATADOR_DE_DEMONIOS("Matador de Demonios", "Espada feita para exorcizar maldicoes poderosas.", 40, 10, 3,
            new Habilidade("Lamina Exorcista", 1, 5.5f, "LAMINA EXORCISTA! O aco queima a maldicao!", EfeitoEspecial.QUEIMADURA)),

    ESPADA_DE_ALMA_DIVIDIDA("Espada de Alma Dividida", "Corta a alma diretamente, ignorando o corpo.", 45, 0, 2,
            new Habilidade("Corte de Alma", 1, 6f, "CORTE DE ALMA! A lamina atravessa o espirito!", EfeitoEspecial.ROUBA_VIDA)),

    LANCA_INVERTIDA_DO_CEU("Lanca Invertida do Ceu", "Anula qualquer tecnica amaldicoada que ela toque.", 70, 0, 2,
            new Habilidade("Anulacao Total", 1, 8f, "ANULACAO TOTAL! A tecnica inimiga se desfaz!", EfeitoEspecial.ENFRAQUECE));

    public final String nome;
    public final String descricao;
    public final int bonusAtaque;
    public final int bonusDefesa;
    public final int usosPorCombate;
    public final Habilidade habilidade;

    Arma(String nome, String descricao, int bonusAtaque, int bonusDefesa, int usosPorCombate, Habilidade habilidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
        this.usosPorCombate = usosPorCombate;
        this.habilidade = habilidade;
    }

    @Override
    public String toString() {
        return nome;
    }
}