package Classes.CRUDListaInvertida;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Delete {
    public static void deletarPokemonListaInvertida(String caminhoListaInvertida) {
        Scanner scanner = new Scanner(System.in);

        try (RandomAccessFile rafIndice = new RandomAccessFile(caminhoListaInvertida, "rw")) {

            System.out.print("Digite o ID do Pokémon na lista invertida que deseja deletar: ");
            int idProcurado = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            boolean encontrado = false;

            while (rafIndice.getFilePointer() < rafIndice.length()) {
                long posInicioRegistro = rafIndice.getFilePointer();

                int idAtual = rafIndice.readInt();
                String nome = lerString(rafIndice);

                long posOffset = rafIndice.getFilePointer(); // Posição atual é onde está o offset

                long offsetAtual = rafIndice.readLong(); // lê o offset atual (só pra mover o ponteiro)

                if (idAtual == idProcurado) {
                    rafIndice.seek(posOffset); // volta na posição do offset
                    rafIndice.writeLong(0); // escreve 0 para marcar como deletado
                    encontrado = true;
                    System.out.println("✅ Pokémon '" + nome + "' (ID Lista: " + idAtual + ") deletado com sucesso!");
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("❌ Pokémon com ID " + idProcurado + " não encontrado na lista invertida.");
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao deletar Pokémon da lista invertida", e);
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
