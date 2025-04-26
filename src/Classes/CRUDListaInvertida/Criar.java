package Classes.CRUDListaInvertida;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class Criar {

    public static void criarLista(String lista, String caminhoArquivoBinarioPokemons) {
        try (RandomAccessFile rafPokemon = new RandomAccessFile(caminhoArquivoBinarioPokemons, "r");
             RandomAccessFile rafIndice = new RandomAccessFile(lista, "rw")) {

            rafIndice.setLength(0);
            rafPokemon.readInt();

            int idLista = 1;

            while (rafPokemon.getFilePointer() < rafPokemon.length()) {
                int id = rafPokemon.readInt();
                boolean cova = rafPokemon.readBoolean();
                int tamanhoBytes = rafPokemon.readInt();

                long posInicioPokemonReal = rafPokemon.getFilePointer();

                if (!cova) {
                    int numberPokedex = rafPokemon.readInt();
                    String nome = lerString(rafPokemon);
                    rafIndice.writeInt(idLista);
                    escreverString(rafIndice, nome);
                    rafIndice.writeLong(posInicioPokemonReal);
                    idLista++;
                }

                rafPokemon.seek(posInicioPokemonReal + tamanhoBytes);
            }

            System.out.println("✅ Lista invertida criada com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar a lista invertida", e);
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

    private static void escreverString(RandomAccessFile raf, String s) throws IOException {
        if (s != null) {
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            raf.writeShort(bytes.length);
            raf.write(bytes);
        } else {
            raf.writeShort(0);
        }
    }
}
