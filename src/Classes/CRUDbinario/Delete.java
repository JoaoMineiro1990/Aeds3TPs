package Classes.CRUDbinario;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Delete {

    public static void menuDeletar(String caminhoArquivo) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU DE EXTERMÍNIO DE POKÉMONS ---");
            System.out.println("1 - Matar um Pokémon");
            System.out.println("2 - Listar Pokémons mortos");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Qual Pokémon você quer matar? (Digite o ID): ");
                    int id = scanner.nextInt();
                    deletarPokemonPorId(caminhoArquivo, id);
                    break;
                case 2:
                    mostrarPokemonsMortos(caminhoArquivo);
                    break;
                case 0:
                    System.out.println("Você poupou o que sobrou... Encerrando o menu de extermínio!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha 1, 2 ou 0.");
            }
        }
    }
    private static void deletarPokemonPorId(String caminhoArquivo, int idProcurado) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "rw")) {
            int ultimoId = raf.readInt();
            if (idProcurado <= 0 || idProcurado > ultimoId) {
                System.out.println("ID inválido. Escolha entre 1 e " + ultimoId);
                return;
            }

            while (raf.getFilePointer() < raf.length()) {
                long posInicio = raf.getFilePointer();
                int id = raf.readInt();

                if (id == idProcurado) {
                    raf.writeBoolean(true);
                    System.out.println("✅ Pokémon com ID " + idProcurado + " marcado como removido.");
                    return;
                } else {
                    boolean cova = raf.readBoolean(); // lê cova
                    int tamanhoBytes = raf.readInt(); // lê tamanho

                    raf.seek(posInicio + 4 + 1 + 4 + tamanhoBytes);
                }
            }

            System.out.println("Pokémon com ID " + idProcurado + " não encontrado.");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao deletar Pokémon do arquivo", e);
        }
    }

    public static void mostrarPokemonsMortos(String caminhoArquivo) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "r")) {
            System.out.println("--- LISTA DE POKÉMONS MORTOS ---");

            int ultimoId = raf.readInt();

            boolean encontrouMortos = false;

            while (raf.getFilePointer() < raf.length()) {
                long posAntesDoId = raf.getFilePointer();

                int id = raf.readInt();
                boolean cova = raf.readBoolean();
                int tamanhoBytes = raf.readInt();

                if (cova) {
                    int numberPokedex = raf.readInt();
                    String name = lerString(raf);
                    String type1 = lerString(raf);
                    String type2 = lerString(raf);
                    String abilities = lerString(raf);

                    int hp = raf.readInt();
                    int att = raf.readInt();
                    int def = raf.readInt();
                    int spa = raf.readInt();
                    int spd = raf.readInt();
                    int spe = raf.readInt();
                    int bst = raf.readInt();

                    long dataEpoch = raf.readLong();

                    System.out.println("--- POKÉMON MORTO ---");
                    System.out.println("ID: " + id);
                    System.out.println("NumberPokedex: " + numberPokedex);
                    System.out.println("Name: " + name);
                    System.out.println("Type1: " + (type1.isEmpty() ? "null" : type1));
                    System.out.println("Type2: " + (type2.isEmpty() ? "null" : type2));
                    System.out.println("Abilities: " + abilities);
                    System.out.println("HP: " + hp);
                    System.out.println("ATT: " + att);
                    System.out.println("DEF: " + def);
                    System.out.println("SPA: " + spa);
                    System.out.println("SPD: " + spd);
                    System.out.println("SPE: " + spe);
                    System.out.println("BST: " + bst);
                    System.out.println("DataEpoch: " + dataEpoch);
                    System.out.println("-----------------------------\n");

                    encontrouMortos = true;
                } else {

                    raf.seek(posAntesDoId + 4 + 1 + 4 + tamanhoBytes);

                }
            }

            if (!encontrouMortos) {
                System.out.println("Nenhum Pokémon morto encontrado. Todos estão vivos! ✨");
            }

            System.out.println("--- FIM DA LISTA ---");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler Pokémons mortos do arquivo", e);
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
