package Classes.ArvoreBMais;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class CriarIndiceBMais {

    public static void lerSalvandoPares(String caminhoArquivoBinario, String caminhoIndice) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivoBinario, "r")) {

            // Comentado para teste:
            ArvoreBMais_String_Int arvore = new ArvoreBMais_String_Int(4, caminhoIndice);

            int ultimoId = raf.readInt();

            while (raf.getFilePointer() < raf.length()) {
                long posicao = raf.getFilePointer();

                int id = raf.readInt();
                boolean cova = raf.readBoolean();
                int tamanhoBytes = raf.readInt();

                int numeroPokedex = raf.readInt();
                String nome = lerString(raf);


                pularString(raf); // type1
                pularString(raf); // type2
                pularString(raf); // abilities

                raf.skipBytes(4 * 7);
                raf.skipBytes(8);

                if (!cova) {
                    // Comentado para teste:
                    arvore.create(nome, (int) posicao);
                }
            }

            System.out.println("✅ Teste finalizado. Pares listados acima.");

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler e testar pares para índice B+", e);
        }
    }

    private static String lerString(RandomAccessFile raf) throws IOException {
        int tamanho = raf.readShort();
        if (tamanho > 0) {
            byte[] bytes = new byte[tamanho];
            raf.readFully(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        }
        return "";
    }

    private static void pularString(RandomAccessFile raf) throws IOException {
        int tamanho = raf.readShort();
        if (tamanho > 0) {
            raf.skipBytes(tamanho);
        }
    }


}
