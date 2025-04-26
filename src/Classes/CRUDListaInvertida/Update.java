package Classes.CRUDListaInvertida;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Update {

    public static void procurarPokemonParaAtualizar(String caminhoListaInvertida, String caminhoArquivoBinario) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoListaInvertida, "rw")) {
            Scanner scanner = new Scanner(System.in);

            boolean cancelado = false;

            while (true) {
                System.out.print("Digite o ID do Pokémon na lista invertida que deseja atualizar (ou 0 para cancelar): ");
                int idProcurado = scanner.nextInt();
                scanner.nextLine();
                if (idProcurado == 0) {
                    System.out.println("❌ Atualização cancelada pelo usuário.");
                    cancelado = true;
                    break;
                }
                raf.seek(0);
                boolean encontrado = false;
                while (raf.getFilePointer() < raf.length()) {
                    int idLista = raf.readInt();
                    String nome = lerString(raf);
                    long offset = raf.readLong();

                    if (idLista == idProcurado) {
                        System.out.println("\n✅ ID encontrado!");
                        System.out.println("Nome: " + nome);
                        System.out.println("Offset: " + offset);

                        System.out.print("Deseja atualizar esse Pokémon? (s/n): ");
                        String resposta = scanner.nextLine();

                        if (resposta.equalsIgnoreCase("s")) {
                            System.out.println("✅ Pokémon escolhido para atualização!");
                            editarPokemonListaAtualizar(caminhoArquivoBinario, caminhoListaInvertida, nome, idLista);
                            encontrado = true;
                            break;
                        } else {
                            System.out.println("🔄 OK, escolha outro ID ou digite 0 para cancelar.");
                            break;
                        }
                    }
                }
                if (encontrado) {
                    break;
                }

                System.out.println("⚠️ ID não encontrado ou não confirmado. Vamos tentar de novo.\n");
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao procurar Pokémon na lista invertida", e);
        }
    }

    private static void editarPokemonListaAtualizar(String caminhoArquivoBinario, String caminhoListaInvertida, String nomeProcurado, int idLista) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivoBinario, "rw")) {
            raf.readInt();

            int idNoArquivo = -1;

            while (raf.getFilePointer() < raf.length()) {
                int id = raf.readInt();
                boolean cova = raf.readBoolean();
                int tamanhoBytes = raf.readInt();

                long posInicioCampos = raf.getFilePointer();

                int numberPokedex = raf.readInt();
                String nome = lerString(raf);

                if (nome.equalsIgnoreCase(nomeProcurado)) {
                    idNoArquivo = id;
                    System.out.println("✅ Pokémon encontrado no arquivo! ID: " + idNoArquivo);
                    break;
                }

                raf.seek(posInicioCampos + tamanhoBytes);
            }

            if (idNoArquivo == -1) {
                System.out.println("❌ Pokémon com nome \"" + nomeProcurado + "\" não encontrado no arquivo binário.");
                return;
            }

            System.out.println("Chamando Update no CRUDbinario...");
            Classes.CRUDbinario.Update.atualizarPokemonPorId(caminhoArquivoBinario, idNoArquivo);

            long novoOffset = buscarNovoOffsetDoPokemonAtualizado(caminhoArquivoBinario, nomeProcurado);
            atualizarOffsetNaListaInvertida(caminhoListaInvertida, idLista, novoOffset);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar Pokémon na lista invertida", e);
        }
    }
    private static long buscarNovoOffsetDoPokemonAtualizado(String caminhoArquivoBinario, String nomeProcurado) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivoBinario, "r")) {
            raf.readInt(); // pula o último ID

            System.out.println("\n🔎 Começando a busca do novo Pokémon atualizado...");
            System.out.println("🎯 Nome Procurado: " + nomeProcurado + "\n");

            while (raf.getFilePointer() < raf.length()) {
                long posInicio = raf.getFilePointer();

                int id = raf.readInt();
                boolean cova = raf.readBoolean();
                int tamanhoBytes = raf.readInt();

                long posInicioCampos = raf.getFilePointer(); // Onde começam os campos numberPokedex, name...

                int numberPokedex = raf.readInt();
                String nome = lerString(raf);

                if (nome.equalsIgnoreCase(nomeProcurado) && !cova) {
                    System.out.println("✅ Pokémon vivo encontrado! Offset: " + posInicioCampos);
                    return posInicioCampos; // Retorna onde começa numberPokedex (o novo Pokémon)
                }

                raf.seek(posInicio + 4 + 1 + 4 + tamanhoBytes); // pular para o próximo
            }

            throw new RuntimeException("❌ Novo Pokémon atualizado não encontrado no arquivo.");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao buscar novo Pokémon atualizado", e);
        }
    }


    private static void atualizarOffsetNaListaInvertida(String caminhoListaInvertida, int idLista, long novoOffset) {
        try (RandomAccessFile rafIndice = new RandomAccessFile(caminhoListaInvertida, "rw")) {
            while (rafIndice.getFilePointer() < rafIndice.length()) {
                int idAtual = rafIndice.readInt();
                String nome = lerString(rafIndice);
                long posOffset = rafIndice.getFilePointer();

                long offsetAtual = rafIndice.readLong();

                if (idAtual == idLista) {
                    rafIndice.seek(posOffset);
                    rafIndice.writeLong(novoOffset);
                    System.out.println("✅ Offset atualizado na lista invertida!");
                    return;
                }
            }

            System.out.println("❌ ID " + idLista + " não encontrado na lista invertida.");

        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar o offset na lista invertida", e);
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
