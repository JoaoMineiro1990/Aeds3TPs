package Classes.CRUDbinario;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static Classes.criarArquivo.debugLer5UltimosPokemons;
import static Classes.criarArquivo.debugLer7PrimeirosPokemons;

public class Ler {

    public static void menuDeLeitura(String caminhoArquivo) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU DE LEITURA ---");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Ler todos os Pokémons");
            System.out.println("2 - Ler os 7 primeiros Pokémons");
            System.out.println("3 - Ler os 5 últimos Pokémons");
            System.out.println("4 - Ler Pokémon por ID");
            System.out.println("0 - Sair");

            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    lerTodosPokemons(caminhoArquivo);
                    break;
                case 2:
                    debugLer7PrimeirosPokemons(caminhoArquivo);
                    break;
                case 3:
                    debugLer5UltimosPokemons(caminhoArquivo);
                    break;
                case 4:
                    System.out.print("Digite o ID do Pokémon que deseja ler: ");
                    int id = scanner.nextInt();
                    lerPokemonPorId(caminhoArquivo, id);
                    break;
                case 0:
                    System.out.println("Saindo do menu de leitura...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void lerTodosPokemons(String caminhoArquivo) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "r")) {
            System.out.println("--- LEITURA DE TODOS OS POKÉMONS ---");

            int ultimoId = raf.readInt(); // lê o ID do começo
            System.out.println("(Info) Último ID salvo: " + ultimoId);
            System.out.println("-----------------------------------\n");

            while (raf.getFilePointer() < raf.length()) {
                int id = raf.readInt();
                boolean cova = raf.readBoolean();
                int tamanhoBytes = raf.readInt();

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

                System.out.println("ID: " + id);
                System.out.println("Cova: " + cova);
                System.out.println("TamanhoBytes: " + tamanhoBytes);
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
            }

            System.out.println("--- FIM DA LEITURA ---");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler Pokémon do arquivo", e);
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

    public static void lerPokemonPorId(String caminhoArquivo, int idProcurado) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "r")) {
            int ultimoId = raf.readInt();

            if (idProcurado <= 0 || idProcurado > ultimoId) {
                System.out.println("ID inválido. Escolha entre 1 e " + ultimoId);
                return;
            }

            while (raf.getFilePointer() < raf.length()) {
                long posAntesDoId = raf.getFilePointer();
                int id = raf.readInt();

                if (id == idProcurado) {
                    boolean cova = raf.readBoolean();
                    int tamanhoBytes = raf.readInt();

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

                    System.out.println("--- POKÉMON ENCONTRADO ---");
                    System.out.println("ID: " + id);
                    System.out.println("Cova: " + cova);
                    System.out.println("TamanhoBytes: " + tamanhoBytes);
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
                    return;
                } else {
                    boolean cova = raf.readBoolean();
                    int tamanhoBytes = raf.readInt();

                    raf.seek(posAntesDoId + 4 + 1 + 4 + tamanhoBytes);
                }
            }
            System.out.println("Pokémon com ID " + idProcurado + " não encontrado.");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler Pokémon do arquivo", e);
        }
    }

}
