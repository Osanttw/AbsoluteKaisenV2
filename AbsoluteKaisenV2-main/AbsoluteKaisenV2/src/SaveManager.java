import java.io.*;

public class SaveManager {

    public static final int SLOTS = 3;
    private static final String LEGADO = "save.dat"; // save antigo (antes dos slots)

    private static String arquivo(int slot) {
        return "save" + slot + ".dat";
    }

    /** Se existir o save antigo (save.dat) e o slot 1 estiver livre, aproveita ele como slot 1. */
    public static void migrarSaveAntigo() {
        File antigo = new File(LEGADO);
        File slot1 = new File(arquivo(1));
        if (antigo.exists() && !slot1.exists()) {
            antigo.renameTo(slot1);
        }
    }

    public static boolean existe(int slot) {
        return new File(arquivo(slot)).exists();
    }

    public static boolean existeAlgum() {
        for (int i = 1; i <= SLOTS; i++) if (existe(i)) return true;
        return false;
    }

    public static void salvar(Personagem personagem, int slot) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo(slot)))) {
            out.writeObject(personagem);
            System.out.println("Jogo salvo no slot " + slot + "!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    /** Retorna null se o slot estiver vazio ou o arquivo estiver corrompido. */
    public static Personagem carregar(int slot) {
        if (!existe(slot)) return null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo(slot)))) {
            return (Personagem) in.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    /** Texto curto pra mostrar no menu de slots. */
    public static String resumo(int slot) {
        if (!existe(slot)) return "Vazio";
        Personagem p = carregar(slot);
        if (p == null) return "Save corrompido";
        return p.nome + " | Lv " + p.level + " | " + p.rank + " | " + p.cla + " | " + p.tecnica;
    }

    public static void apagar(int slot) {
        new File(arquivo(slot)).delete();
    }
}