package Classes.CRUDListaInvertida;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class Ler {

    public static void lerListaInvertida(String caminhoListaInvertida) {
        try (RandomAccessFile rafIndice = new RandomAccessFile(caminhoListaInvertida, "r")) {
            System.out.println("\n--- LISTA INVERTIDA (Somente Pokémons vivos) ---");

            boolean encontrouAlgum = false;

            while (rafIndice.getFilePointer() < rafIndice.length()) {
                int id = rafIndice.readInt();
                String nome = lerString(rafIndice);
                long offset = rafIndice.readLong();

                if (offset > 0) { // Só exibe quem não foi deletado
                    encontrouAlgum = true;
                    System.out.println("ID Lista: " + id);
                    System.out.println("Nome: " + nome);
                    System.out.println("Offset: " + offset);
                    System.out.println("-----------------------------");
                }
            }

            if (!encontrouAlgum) {
                System.out.println("Nenhum Pokémon ativo encontrado na lista invertida.");
            }

            System.out.println("--- FIM DA LISTA ---\n");

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler a lista invertida", e);
        }
    }

    private static String lerString(RandomAccessFile raf) throws IOException {
        int tamanho = raf.readShort();
        if (tamanho > 0) {
            byte[] bytes = new byte[tamanho];
            raf.readFully(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        } else {
            return "";
        }
    }

}
