/**
 * Descreve uma habilidade desbloqueavel de uma tecnica inata.
 * Antes essa informacao (nome, nivel, multiplicador, mensagem) ficava
 * espalhada e duplicada em 3 switches diferentes (menu, dano e level up).
 * Agora ela existe em um unico lugar (Tecnica.java) e é reaproveitada.
 */
public record Habilidade(String nome, int nivelNecessario, float multiplicador, String mensagem, EfeitoEspecial efeito) {

    // Construtor de conveniencia para habilidades sem efeito especial (so dano)
    public Habilidade(String nome, int nivelNecessario, float multiplicador, String mensagem) {
        this(nome, nivelNecessario, multiplicador, mensagem, EfeitoEspecial.NENHUM);
    }
}