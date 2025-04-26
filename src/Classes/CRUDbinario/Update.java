package Classes.CRUDbinario;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import Classes.Pokemon;
import Classes.PokemonNoArquivo;
import static Classes.PokemonNoArquivo.contarBytes;


public class Update {
    public static void menuAtualizarPokemon(String caminhoArquivo) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU DE ATUALIZAÇÃO DE POKÉMONS ---");
            System.out.println("digite qualquer coisa menos 0 (ou 0 para sair): ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Saindo do menu de atualização!");
                break;
            }

            atualizarPokemon(caminhoArquivo);
        }
    }
    private static void atualizarPokemon(String caminhoArquivo) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "rw")) {
            Scanner scanner = new Scanner(System.in);

            // Pergunta o ID
            System.out.print("Digite o ID do Pokémon que deseja atualizar: ");
            int idProcurado = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer

            // Posição inicial (antes de ler o último ID)
            long posInicio = raf.getFilePointer();
            int ultimoId = raf.readInt();

            if (idProcurado <= 0 || idProcurado > ultimoId) {
                System.out.println("ID inválido. Escolha entre 1 e " + ultimoId);
                return;
            }

            Pokemon pokemonAntigo = null;

            // Percorrer o arquivo
            while (raf.getFilePointer() < raf.length()) {
                long posAntesId = raf.getFilePointer();
                int id = raf.readInt();

                if (id == idProcurado) {
                    raf.writeBoolean(true); // marca como morto

                    int tamanhoBytes = raf.readInt();

                    // Pega os dados do Pokémon
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

                    // Cria objeto com os dados antigos
                    pokemonAntigo = new Pokemon();
                    pokemonAntigo.setNumberPokedex(numberPokedex);
                    pokemonAntigo.setName(name);
                    pokemonAntigo.setType1(type1);
                    pokemonAntigo.setType2(type2);
                    pokemonAntigo.setAbilities(abilities);
                    pokemonAntigo.setHp(hp);
                    pokemonAntigo.setAtt(att);
                    pokemonAntigo.setDef(def);
                    pokemonAntigo.setSpa(spa);
                    pokemonAntigo.setSpd(spd);
                    pokemonAntigo.setSpe(spe);
                    pokemonAntigo.setBst(bst);
                    pokemonAntigo.setDataEpoch(dataEpoch);

                    break;
                } else {
                    boolean cova = raf.readBoolean();
                    int tamanhoBytes = raf.readInt();
                    raf.seek(posAntesId + 4 + 1 + 4 + tamanhoBytes);
                }
            }

            if (pokemonAntigo == null) {
                System.out.println("Pokémon com ID " + idProcurado + " não encontrado.");
                return;
            }

            System.out.println("\n--- O que você deseja mudar?");
            System.out.println("1 - Nome");
            System.out.println("2 - Tipo");
            System.out.print("Escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                System.out.print("Digite o novo nome para o Pokémon \"" + pokemonAntigo.getName() + "\": ");
                String novoNome = scanner.nextLine();
                pokemonAntigo.setName(novoNome);
            } else if (escolha == 2) {
                System.out.print("Digite o novo tipo 1: ");
                String novoTipo1 = scanner.nextLine();
                System.out.print("Digite o novo tipo 2 (ou ENTER para deixar null): ");
                String novoTipo2 = scanner.nextLine();
                pokemonAntigo.setType1(novoTipo1);
                pokemonAntigo.setType2(novoTipo2.isEmpty() ? null : novoTipo2);
            } else {
                System.out.println("Opção inválida.");
                return;
            }

            PokemonNoArquivo novoPokemon = new PokemonNoArquivo();
            novoPokemon.setId(ultimoId + 1); // novo ID
            novoPokemon.setCova(false);
            novoPokemon.setPokemon(pokemonAntigo);
            novoPokemon.setTamanhoBytes(contarBytes(pokemonAntigo));

            raf.seek(raf.length());

            raf.writeInt(novoPokemon.getId());
            raf.writeBoolean(novoPokemon.isCova());
            raf.writeInt(novoPokemon.getTamanhoBytes());

            Pokemon p = novoPokemon.getPokemon();
            raf.writeInt(p.getNumberPokedex());
            escreverString(raf, p.getName());
            escreverString(raf, p.getType1());
            escreverString(raf, p.getType2());
            escreverString(raf, p.getAbilities());
            raf.writeInt(p.getHp());
            raf.writeInt(p.getAtt());
            raf.writeInt(p.getDef());
            raf.writeInt(p.getSpa());
            raf.writeInt(p.getSpd());
            raf.writeInt(p.getSpe());
            raf.writeInt(p.getBst());
            raf.writeLong(p.getDataEpoch());

            raf.seek(posInicio);
            raf.writeInt(novoPokemon.getId());

            System.out.println("✅ Pokémon atualizado com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar Pokémon do arquivo", e);
        }
    }
    public static void atualizarPokemonPorId(String caminhoArquivo, int idProcurado) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "rw")) {
            Scanner scanner = new Scanner(System.in);

            // Posição inicial (antes de ler o último ID)
            long posInicio = raf.getFilePointer();
            int ultimoId = raf.readInt();

            if (idProcurado <= 0 || idProcurado > ultimoId) {
                System.out.println("ID inválido. Escolha entre 1 e " + ultimoId);
                return;
            }

            Pokemon pokemonAntigo = null;

            // Percorrer o arquivo
            while (raf.getFilePointer() < raf.length()) {
                long posAntesId = raf.getFilePointer();
                int id = raf.readInt();

                if (id == idProcurado) {
                    raf.writeBoolean(true); // marca como morto

                    int tamanhoBytes = raf.readInt();

                    // Pega os dados do Pokémon
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

                    // Cria objeto com os dados antigos
                    pokemonAntigo = new Pokemon();
                    pokemonAntigo.setNumberPokedex(numberPokedex);
                    pokemonAntigo.setName(name);
                    pokemonAntigo.setType1(type1);
                    pokemonAntigo.setType2(type2);
                    pokemonAntigo.setAbilities(abilities);
                    pokemonAntigo.setHp(hp);
                    pokemonAntigo.setAtt(att);
                    pokemonAntigo.setDef(def);
                    pokemonAntigo.setSpa(spa);
                    pokemonAntigo.setSpd(spd);
                    pokemonAntigo.setSpe(spe);
                    pokemonAntigo.setBst(bst);
                    pokemonAntigo.setDataEpoch(dataEpoch);

                    break;
                } else {
                    boolean cova = raf.readBoolean();
                    int tamanhoBytes = raf.readInt();
                    raf.seek(posAntesId + 4 + 1 + 4 + tamanhoBytes);
                }
            }

            if (pokemonAntigo == null) {
                System.out.println("Pokémon com ID " + idProcurado + " não encontrado.");
                return;
            }

            while (true) {
                System.out.println("\n--- O que você deseja mudar?");
                System.out.println("1 - Tipo");
                System.out.print("Escolha: ");
                int escolha = scanner.nextInt();
                scanner.nextLine(); // limpar o buffer

                if (escolha == 1) {
                    System.out.print("Digite o novo tipo 1: ");
                    String novoTipo1 = scanner.nextLine();
                    System.out.print("Digite o novo tipo 2 (ou ENTER para deixar null): ");
                    String novoTipo2 = scanner.nextLine();

                    pokemonAntigo.setType1(novoTipo1);
                    pokemonAntigo.setType2(novoTipo2.isEmpty() ? null : novoTipo2);
                    break; // saiu do while, porque deu update
                } else {
                    System.out.println("⚠️ Você precisa mudar o tipo! Tente novamente.");
                }
            }

            PokemonNoArquivo novoPokemon = new PokemonNoArquivo();
            novoPokemon.setId(ultimoId + 1); // novo ID
            novoPokemon.setCova(false);
            novoPokemon.setPokemon(pokemonAntigo);
            novoPokemon.setTamanhoBytes(contarBytes(pokemonAntigo));

            raf.seek(raf.length());

            raf.writeInt(novoPokemon.getId());
            raf.writeBoolean(novoPokemon.isCova());
            raf.writeInt(novoPokemon.getTamanhoBytes());

            Pokemon p = novoPokemon.getPokemon();
            raf.writeInt(p.getNumberPokedex());
            escreverString(raf, p.getName());
            escreverString(raf, p.getType1());
            escreverString(raf, p.getType2());
            escreverString(raf, p.getAbilities());
            raf.writeInt(p.getHp());
            raf.writeInt(p.getAtt());
            raf.writeInt(p.getDef());
            raf.writeInt(p.getSpa());
            raf.writeInt(p.getSpd());
            raf.writeInt(p.getSpe());
            raf.writeInt(p.getBst());
            raf.writeLong(p.getDataEpoch());

            raf.seek(posInicio);
            raf.writeInt(novoPokemon.getId());

            System.out.println("✅ Pokémon atualizado com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar Pokémon do arquivo", e);
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
