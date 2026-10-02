/**
 * Efeitos especiais que uma habilidade pode ter alem de causar dano puro.
 */
public enum EfeitoEspecial {
    NENHUM,
    PULA_TURNO_GARANTIDO,   // ex: Troca Rapida (Boogie Woogie)
    PULA_TURNO_ALEATORIO,   // 50% de chance de o inimigo perder o turno
    ROUBA_VIDA,             // cura 35% do dano causado
    QUEIMADURA,             // dano residual (30% do golpe) por 3 turnos
    ENFRAQUECE,             // inimigo causa -40% de dano por 2 turnos
    CURA_PROPRIA            // nao causa dano: cura (dano base x multiplicador)
}