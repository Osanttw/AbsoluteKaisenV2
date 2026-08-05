import java.util.List;

/**
 * Cada tecnica inata carrega: nome de exibicao, nome/multiplicador da
 * Expansao de Dominio e a lista de habilidades que ela desbloqueia por nivel.
 *
 * No codigo original essa informacao estava duplicada em 3 lugares:
 *  1) switch que montava o menu de combate
 *  2) switch que aplicava o dano da habilidade escolhida
 *  3) switch que anunciava "Desbloqueado: X!" no level up
 * Qualquer alteracao (um multiplicador, um nivel, uma mensagem) exigia
 * editar os 3 ao mesmo tempo - fonte comum de bugs. Agora e um unico lugar.
 */
public enum Tecnica {

    ILIMITADO("Ilimitado", "Vazio Infinito", 20f, List.of(
            new Habilidade("Azul", 5, 3f, "AZUL! Ataque concentrado do infinito!"),
            new Habilidade("Vermelho", 15, 5f, "VERMELHO! Explosao divergente!"),
            new Habilidade("Roxo", 25, 10f, "ROXO! A fenda hollow rasga tudo!")
    )),

    DEZ_SOMBRAS("Dez Sombras", "Corte do Mundo", 16f, List.of(
            new Habilidade("Caes Divinos", 5, 2.5f, "CAES DIVINOS! Ataque em matilha!"),
            new Habilidade("Nue", 10, 4f, "NUE! Raio eletrico devastador!"),
            new Habilidade("Max Elephant", 20, 6f, "MAX ELEPHANT! Esmagamento colossal!"),
            new Habilidade("Mahoraga", 40, 15f, "MAHORAGA! A divindade das dez sombras!")
    )),

    MANIPULACAO_DE_SANGUE("Manipulacao de Sangue", "Tempestade de Sangue Rubro", 17f, List.of(
            new Habilidade("Piercing Blood", 5, 3f, "PIERCING BLOOD! Lanca de sangue!"),
            new Habilidade("Fluxo Vermelho", 15, 5f, "FLUXO VERMELHO! Onda de sangue!"),
            new Habilidade("Supernova", 30, 9f, "SUPERNOVA! Explosao de sangue total!")
    )),

    SANTUARIO("Santuario", "Jogo Fechado", 18f, List.of(
            new Habilidade("Desmantelar", 5, 2.5f, "DESMANTELAR! Corte que apaga!"),
            new Habilidade("Clivar", 15, 5f, "CLIVAR! Corte que se adapta!"),
            new Habilidade("Fuga", 30, 8f, "FUGA! Corte omnidirecional!")
    )),

    MANIPULACAO_DE_MALDICOES("Manipulacao de Maldicoes", "Tempo Morto", 17f, List.of(
            new Habilidade("Invocar Maldicao", 5, 3f, "INVOCAR MALDICAO!"),
            new Habilidade("Exercito de Maldicoes", 15, 6f, "EXERCITO DE MALDICOES!"),
            new Habilidade("Maximum Uzumaki", 35, 12f, "MAXIMUM UZUMAKI!")
    )),

    BOOGIE_WOOGIE("Boogie Woogie", "Palmas do Caos", 14f, List.of(
            new Habilidade("Troca Rapida", 5, 0f, "TROCA RAPIDA! Inimigo perde o turno!", EfeitoEspecial.PULA_TURNO_GARANTIDO),
            new Habilidade("Troca Multipla", 20, 4f, "TROCA MULTIPLA! Golpes em sequencia!")
    )),

    RESSONANCIA("Ressonancia", "Campo de Pinos", 15f, List.of(
            new Habilidade("Prego Amaldicoado", 5, 3f, "PREGO AMALDICOADO! Dor que ecoa!"),
            new Habilidade("Ressonancia Suprema", 20, 7f, "RESSONANCIA SUPREMA!")
    )),

    COMEDIANTE("Comediante", "Universo", 16f, List.of(
            new Habilidade("Piada Fraca", 10, 2f, "PIADA FRACA!", EfeitoEspecial.PULA_TURNO_ALEATORIO),
            new Habilidade("Realidade Distorcida", 35, 11f, "REALIDADE DISTORCIDA!")
    )),

    TECNICA_DE_PROJECAO("Tecnica de Projecao", "Fotografia de 1/24 s", 15f, List.of(
            new Habilidade("Projecao Rapida", 5, 3f, "PROJECAO RAPIDA!"),
            new Habilidade("Projecao Perfeita", 20, 6f, "PROJECAO PERFEITA!")
    ));

    public final String nomeExibicao;
    public final String nomeExpansao;
    public final float multiplicadorExpansao;
    public final List<Habilidade> habilidades;

    Tecnica(String nomeExibicao, String nomeExpansao, float multiplicadorExpansao, List<Habilidade> habilidades) {
        this.nomeExibicao = nomeExibicao;
        this.nomeExpansao = nomeExpansao;
        this.multiplicadorExpansao = multiplicadorExpansao;
        this.habilidades = habilidades;
    }

    @Override
    public String toString() {
        return nomeExibicao;
    }
}
