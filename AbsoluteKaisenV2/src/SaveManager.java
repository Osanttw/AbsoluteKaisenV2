import java.io.*;

public class SaveManager {

    private static final String SAVE = "save.dat";

    public static void salvar(Personagem personagem) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(SAVE));

            out.writeObject(personagem);

            out.close();

            System.out.println("Jogo salvo com sucesso!");

        } catch (Exception e) {

            System.out.println("Erro ao salvar.");
        }
    }

    public static Personagem carregar() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(SAVE));

            Personagem p = (Personagem) in.readObject();

            in.close();

            return p;

        } catch (Exception e) {

            return null;

        }

    }

}