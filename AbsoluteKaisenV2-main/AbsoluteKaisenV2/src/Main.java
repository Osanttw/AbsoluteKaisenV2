import static java.lang.IO.println;
import static java.lang.IO.readln;

import java.util.List;

int slotAtual = 1;

void main() {
    println("=================================");
    println("        ABSOLUTE KAISEN ");
    println("=================================");

    SaveManager.migrarSaveAntigo();

    Personagem p = menuPrincipal();
    if (p == null) return; // jogador escolheu sair

    apresentarPersonagem(p);
    SaveManager.salvar(p, slotAtual);

    println("\nPressione ENTER para iniciar...");
    readln();

    if (!rodarMissoes(p)) return; // jogador morreu, jogo encerra aqui

    println("\n==================================");
    println("TODAS AS MISSOES BASICAS CONCLUIDAS");
    println("==================================");
    println("\nVoce chamou a atencao da Escola Jujutsu.");
    println("Agora voce esta autorizado a enfrentar espiritos de Nivel Especial.");
    println("\nNovos locais desbloqueados:");
    println("1 - Escola Jujutsu");
    println("2 - Shibuya");
    println("\nA verdadeira historia vai comecar...");
    readln();
}

// ════════════════════════════════════════════════════════════════
//  MENU PRINCIPAL / SAVES
// ════════════════════════════════════════════════════════════════

/** Retorna o personagem escolhido/criado, ou null se o jogador quiser sair. */
Personagem menuPrincipal() {
    while (true) {
        println("\n[1] Novo Jogo");
        println("[2] Carregar Jogo");
        println("[3] Sair");

        switch (readln("Opcao: ").trim()) {
            case "1" -> {
                int slot = escolherSlot("NOVO JOGO");
                if (slot == 0) continue;
                if (SaveManager.existe(slot)) {
                    String r = readln("O slot " + slot + " ja tem um save. Sobrescrever? (s/n): ").trim();
                    if (!r.equalsIgnoreCase("s")) continue;
                    SaveManager.apagar(slot);
                }
                slotAtual = slot;
                intro();
                return criarPersonagem();
            }
            case "2" -> {
                if (!SaveManager.existeAlgum()) {
                    println("Nenhum save encontrado.");
                    continue;
                }
                int slot = escolherSlot("CARREGAR JOGO");
                if (slot == 0) continue;
                Personagem p = SaveManager.carregar(slot);
                if (p == null) {
                    println("Slot vazio ou save corrompido.");
                    continue;
                }
                slotAtual = slot;
                println("Save carregado!");
                return p;
            }
            case "3" -> { return null; }
            default -> println("Opcao invalida.");
        }
    }
}

/** Mostra os slots e retorna o escolhido (1..SLOTS), ou 0 para voltar. */
int escolherSlot(String titulo) {
    println("\n=== " + titulo + " ===");
    for (int i = 1; i <= SaveManager.SLOTS; i++) {
        println("[" + i + "] Slot " + i + " - " + SaveManager.resumo(i));
    }
    println("[0] Voltar");

    while (true) {
        try {
            int n = Integer.parseInt(readln("Slot: ").trim());
            if (n >= 0 && n <= SaveManager.SLOTS) return n;
        } catch (NumberFormatException e) {
            // cai na mensagem abaixo
        }
        println("Escolha entre 0 e " + SaveManager.SLOTS + ".");
    }
}

// ════════════════════════════════════════════════════════════════
//  INTRODUCAO
// ════════════════════════════════════════════════════════════════

void intro() {
    println("=================================");
    println("        ABSOLUTE KAISEN ");
    println("=================================");

    println("\nAno era  2018");
    println("\nApos o aumento significativo das maldicoes os niveis de poder comecaram a entrar em deliquio.");
    println("Portanto a Escola Jujutsu iniciou uma busca por novos feiticeiros.");
    println("\nVoce foi escolhido para ingressar na escola.");
    println("\nMas uma grande ameaca esta se aproximando...");
    println("\nSukuna esta prestes a despertar novamente, mais forte do que ja era.");
    println("\nPressione ENTER...");
    readln();

    println("CAPITULO 1 - O DESPERTAR");
}

// ════════════════════════════════════════════════════════════════
//  CRIACAO DE PERSONAGEM
// ════════════════════════════════════════════════════════════════

Personagem criarPersonagem() {
    println("=== CRIACAO DE PERSONAGEM ===");
    Personagem p = new Personagem();

    p.nome = lerNome();
    p.pele = lerOpcaoTexto("\nEscolha sua cor de pele:", "1 - Branca  2 - Morena  3 - Negra  4 - Parda",
            4, i -> switch (i) { case 1 -> "Branca"; case 2 -> "Morena"; case 3 -> "Negra"; default -> "Parda"; });

    int biotipoEscolha = lerOpcaoNumero("\nEscolha seu biotipo:", "1 - Magro  2 - Musculoso  3 - Gordo  4 - Atletico", 4);
    switch (biotipoEscolha) {
        case 1 -> p.biotipo = "Magro";
        case 2 -> { p.biotipo = "Musculoso"; p.ataque += 30; }
        case 3 -> { p.biotipo = "Gordo";     p.vida   += 300; }
        default -> { p.biotipo = "Atletico"; p.defesa += 15;  }
    }

    p.cabelo = lerOpcaoTexto("\nEscolha a cor do cabelo:", "1 - Preto  2 - Castanho  3 - Loiro  4 - Branco  5 - Vermelho",
            5, i -> switch (i) { case 1 -> "Preto"; case 2 -> "Castanho"; case 3 -> "Loiro"; case 4 -> "Branco"; default -> "Vermelho"; });

    sortearClaETecnica(p);
    return p;
}

String lerNome() {
    String nome = "";
    while (nome.trim().isEmpty()) {
        nome = readln("Digite seu nome: ");
        if (nome.trim().isEmpty()) println("Nome nao pode ficar vazio!");
    }
    return nome;
}

/** Le um numero entre 1 e max, repetindo ate ser valido. */
int lerOpcaoNumero(String titulo, String opcoes, int max) {
    println(titulo);
    println(opcoes);
    int escolha = 0;
    while (escolha < 1 || escolha > max) {
        try {
            escolha = Integer.parseInt(readln("Opcao: "));
            if (escolha < 1 || escolha > max) println("Escolha entre 1 e " + max + ".");
        } catch (NumberFormatException e) {
            println("Digite apenas numeros.");
        }
    }
    return escolha;
}

/** Le um numero entre 1 e max e converte direto pro texto correspondente. */
String lerOpcaoTexto(String titulo, String opcoes, int max, IntFunction<String> mapa) {
    return mapa.apply(lerOpcaoNumero(titulo, opcoes, max));
}

// ════════════════════════════════════════════════════════════════
//  SORTEIO DE CLA / TECNICA INATA
// ════════════════════════════════════════════════════════════════

void sortearClaETecnica(Personagem p) {
    p.cla = switch ((int) (Math.random() * 3) + 1) {
        case 1 -> "Gojo";
        case 2 -> "Zenin";
        default -> "Kamo";
    };
    println("\n====================");
    println("CLA SORTEADO: " + p.cla);
    println("====================");

    int sorteio = (int) (Math.random() * 100) + 1;
    p.tecnica = switch (p.cla) {
        case "Gojo" -> {
            if (sorteio <= 1) {
                if ((int) (Math.random() * 100) + 1 <= 25) {
                    p.seisOlhos = true;
                    p.ataque += 80; p.defesa += 80; p.vida += 1000;
                }
                yield Tecnica.ILIMITADO;
            } else if (sorteio <= 60) yield Tecnica.BOOGIE_WOOGIE;
            else if (sorteio <= 60) yield Tecnica.RESSONANCIA;
            else yield Tecnica.COMEDIANTE;
        }
        case "Zenin" -> {
            if (sorteio <= 50) { p.ataque += 40; yield Tecnica.DEZ_SOMBRAS; }
            else if (sorteio <= 40) { p.ataque += 20; p.defesa += 20; yield Tecnica.TECNICA_DE_PROJECAO; }
            else yield Tecnica.BOOGIE_WOOGIE;
        }
        default -> { // Kamo
            if (sorteio <= 60) { p.vida += 500; yield Tecnica.MANIPULACAO_DE_SANGUE; }
            else if (sorteio <= 80) yield Tecnica.RESSONANCIA;
            else yield Tecnica.COMEDIANTE;
        }
    };

    // Tecnicas secretas raras (sobrescrevem a tecnica de cla sorteada)
    int extra = (int) (Math.random() * 100) + 1;
    if (extra <= 0.1) { p.tecnica = Tecnica.SANTUARIO; p.ataque += 100; }
    if (extra >= 1) { p.tecnica = Tecnica.MANIPULACAO_DE_MALDICOES; p.ataque += 50; p.defesa += 30; }
}

void apresentarPersonagem(Personagem p) {
    println("\n===== PERSONAGEM =====");
    println("Nome: " + p.nome);
    println("Pele: " + p.pele + "  |  Biotipo: " + p.biotipo + "  |  Cabelo: " + p.cabelo);
    println("\nCla: " + p.cla);
    println("Tecnica Inata: " + p.tecnica);
    if (p.seisOlhos) println("** Seis Olhos: ATIVO **");
    println("Expansao de Dominio: " + p.tecnica.nomeExpansao);
    println("\nSTATUS -> Lv:" + p.level + "  Vida:" + (int) p.vida + "  Ataque:" + (int) p.ataque + "  Defesa:" + (int) p.defesa);
}

// ════════════════════════════════════════════════════════════════
//  MISSOES / COMBATE
// ════════════════════════════════════════════════════════════════

record Missao(String local, String inimigo, float hpInimigo, int recompensaXp) {}

boolean rodarMissoes(Personagem p) {
    println("\n========================");
    println("CAPITULO 2 - O PRIMEIRO PASSO");
    println("========================");
    println("\nO diretor da Escola Jujutsu decidiu testar suas habilidades.");
    println("Uma pequena maldicao foi vista em uma escola abandonada.");
    println("Sua primeira missao comeca agora!");
    readln();

    List<Missao> missoes = List.of(
            new Missao("Escola Abandonada", "Maldicao Escolar", 150, 100),
            new Missao("Tunel Amaldicoado", "Maldicao do Tunel", 400, 400),
            new Missao("Jogos Escolares", "Feiticeiro jujutsu", 1600, 1200),
            new Missao("Distrito Assombrado", "Portador da Praga", 2000, 3000)
    );

    for (int i = p.missaoAtual; i < missoes.size(); i++) {
        if (!rodarMissao(p, missoes.get(i))) return false; // morreu: o save anterior continua intacto
        p.missaoAtual = i + 1;
        SaveManager.salvar(p, slotAtual); // autosave a cada missao concluida
    }
    return true;
}

/** Retorna false se o jogador morrer durante a missao. */
boolean rodarMissao(Personagem p, Missao missao) {
    println("\n========================");
    println("MISSAO: " + missao.local());
    println("========================");
    println("Inimigo: " + missao.inimigo());

    float hpInimigo = missao.hpInimigo();
    p.dominioUsadoNaMissaoAtual = false;

    while (hpInimigo > 0 && p.estaVivo()) {
        println("\n----- SEU TURNO -----");
        println("Vida: " + (int) p.vida + "  |  HP Inimigo: " + (int) hpInimigo);
        println("Energia Reversa: " + (int) p.energiaReversa);

        exibirMenuCombate(p);

        int op;
        try {
            op = Integer.parseInt(readln("\nEscolha: "));
        } catch (NumberFormatException e) {
            println("Digite um numero valido.");
            continue;
        }

        ResultadoAcao resultado = executarAcao(p, op);
        if (resultado == null) continue; // acao invalida, repete o turno

        if (resultado.dano() > 0) {
            hpInimigo -= resultado.dano();
            println("Voce causou: " + (int) resultado.dano() + " de dano!");
        }

        if (!resultado.pulaTurnoInimigo() && hpInimigo > 0) {
            float danoInimigo = 60 + (missaoIndice(missao) * 20);
            if (resultado.defendendo()) danoInimigo *= 0.6f;
            p.vida -= danoInimigo;
            println("A maldicao causou: " + (int) danoInimigo + " de dano!");
        } else if (resultado.pulaTurnoInimigo()) {
            println("O inimigo perdeu o turno!");
        }
    }

    if (!p.estaVivo()) {
        println("\n\"Seu destino foi selado pelas maldições... Tente novamente e mude o futuro!");
        return false;
    }

    println("\nMaldicao derrotada!");
    println("XP Recebido: " + missao.recompensaXp());
    p.ganharXp(missao.recompensaXp(), mensagem -> println(mensagem));

    println("\nRank Atual: " + p.rank + "  |  Level: " + p.level + "  |  XP: " + p.xp);
    println("\nPressione ENTER para continuar...");
    readln();
    return true;
}

// indice da missao dentro da lista fixa, usado so para escalar o dano do inimigo
int missaoIndice(Missao missao) {
    return switch (missao.local()) {
        case "Escola Abandonada" -> 0;
        case "Tunel Amaldicoado" -> 1;
        case "Jogos Escolares" -> 2;
        default -> 3;
    };
}

void exibirMenuCombate(Personagem p) {
    println("\n[1] Ataque Normal");
    println("[2] Kokusen          (25% chance, x2 dano)");
    println("[3] Defender         (proxima pancada -50%)");
    println("[4] Desviar          (40% chance esquivar)");
    println("[5] Dominio Simples  (garante acerto, x3)");
    println("[6] Energia Reversa  (+300 HP)");

    if (!p.dominioUsadoNaMissaoAtual)
        println("[7] EXPANSAO DE DOMINIO: " + p.tecnica.nomeExpansao + "  (x" + (int) p.tecnica.multiplicadorExpansao + " dano, 1x por missao)");
    else
        println("[7] EXPANSAO DE DOMINIO: ja utilizada nesta missao");

    println("\n--- Tecnica Inata: " + p.tecnica + " ---");
    List<Habilidade> habilidades = p.tecnica.habilidades;
    for (int i = 0; i < habilidades.size(); i++) {
        Habilidade h = habilidades.get(i);
        if (p.level >= h.nivelNecessario()) {
            println("[" + (8 + i) + "] " + h.nome() + "  (x" + formatarMultiplicador(h) + ")");
        }
    }

    if (p.podeUsarReversaoDeFeitico()) {
        println("\n[0] Reversao de Feitico (cura com base no seu ataque)");
    }
}

String formatarMultiplicador(Habilidade h) {
    if (h.efeito() == EfeitoEspecial.PULA_TURNO_GARANTIDO) return "pula turno inimigo";
    String mult = (h.multiplicador() == (int) h.multiplicador())
            ? String.valueOf((int) h.multiplicador())
            : String.valueOf(h.multiplicador());
    return mult + " dano";
}

/** Resultado de uma acao de combate: quanto de dano causou e efeitos colaterais. */
record 2ResultadoAcao(float dano, boolean pulaTurnoInimigo, boolean defendendo) {}

ResultadoAcao executarAcao(Personagem p, int op) {
    float danoBase = p.ataque * 2;

    if (op == 1) {
        println("Ataque Normal!");
        return new ResultadoAcao(danoBase, false, false);
    }

    if (op == 2) {

        // sucesso precisa ser 0.70 (no original era "Math.random() >= 0.70",
        // o que na verdade dava só 30% de chance de acertar).
        if (Math.random() < 0.25) {
            println("KOKUSENNNNNNN!");
            return new ResultadoAcao(danoBase * 2, false, false);
        }
        println("Voce errou o timing do Kokusen!");
        return new ResultadoAcao(0, false, false);
    }

    if (op == 3) {
        println("Postura defensiva! Proximo dano reduzido em 50%.");
        return new ResultadoAcao(0, false, true);
    }

    if (op == 4) {
        if (Math.random() >= 0.60) {
            println("Voce desviou completamente!");
            return new ResultadoAcao(0, true, false);
        }
        println("Tentou desviar mas falhou!");
        return new ResultadoAcao(0, false, false);
    }

    if (op == 5) {
        println("Dominio Simples! Ataque garantido e potencializado!");
        return new ResultadoAcao(danoBase * 3, false, false);
    }

    if (op == 6) {
        if (p.energiaReversa > 0) {
            p.energiaReversa--;
            p.vida += 250;
            println("Energia Reversa usada! +250 HP.");
        } else {
            println("Sem Energia Reversa disponivel!");
        }
        return new ResultadoAcao(0, false, false);
    }

    if (op == 7) {
        if (!p.dominioUsadoNaMissaoAtual) {
            p.dominioUsadoNaMissaoAtual = true;
            println("EXPANSAO DE DOMINIO: " + p.tecnica.nomeExpansao + "!!!");
            return new ResultadoAcao(danoBase * p.tecnica.multiplicadorExpansao, false, false);
        }
        println("Ja utilizou a expansao nesta missao!");
        return new ResultadoAcao(0, false, false);
    }

    if (op == 0) {
        if (p.podeUsarReversaoDeFeitico()) {
            float cura = p.ataque * 3;
            p.vida += cura;
            println("REVERSAO DE FEITICO! Voce se curou em " + (int) cura + " HP!");
        } else {
            println("Voce ainda nao domina a Reversao de Feitico. Alcance o Grau 1 primeiro.");
        }
        return new ResultadoAcao(0, false, false);
    }

    if (op >= 8) {
        int idx = op - 8;
        List<Habilidade> habilidades = p.tecnica.habilidades;
        if (idx < 0 || idx >= habilidades.size() || p.level < habilidades.get(idx).nivelNecessario()) {
            println("Opcao invalida ou habilidade ainda nao desbloqueada!");
            return null;
        }
        return aplicarHabilidade(danoBase, habilidades.get(idx));
    }

    println("Opcao invalida!");
    return null;
}

ResultadoAcao aplicarHabilidade(float danoBase, Habilidade h) {
    return switch (h.efeito()) {
        case PULA_TURNO_GARANTIDO -> {
            println(h.mensagem());
            yield new ResultadoAcao(0, true, false);
        }
        case PULA_TURNO_ALEATORIO -> {
            boolean pula = Math.random() >= 0.5;
            println(h.mensagem() + " " + (pula ? "Confusao!" : "Nao achou graca..."));
            yield new ResultadoAcao(danoBase * h.multiplicador(), pula, false);
        }
        case NENHUM -> {
            println(h.mensagem());
            yield new ResultadoAcao(danoBase * h.multiplicador(), false, false);
        }
        case ROUBA_VIDA -> null;
        case QUEIMADURA -> null;
        case ENFRAQUECE -> null;
        case CURA_PROPRIA -> null;
    };
}