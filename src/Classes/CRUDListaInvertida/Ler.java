package Classes.CRUDListaInvertida;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

import static Classes.Menu.MenuPrincipal.*;

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

    public static void lerUmaEntradaAleatoriaDaLista(String caminhoListaInvertida, String caminhoCSV) {
        System.out.println("\n🎲 Sorteando um Pokémon aleatório para buscar na Lista Invertida...");
        System.out.println("✨ Estamos sorteando para você não precisar acertar o nome perfeitamente!");

        try {
            // 1️⃣ Ler o CSV
            List<String> linhas = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(caminhoCSV));

            if (linhas.size() <= 1) {
                System.out.println("⚠️ CSV vazio ou sem Pokémons suficientes para sortear.");
                return;
            }

            // 2️⃣ Sortear uma linha
            Random random = new Random();
            int linhaSorteada = random.nextInt(linhas.size() - 1) + 1; // pular cabeçalho
            String linha = linhas.get(linhaSorteada);

            // 3️⃣ Extrair o nome
            String[] campos = linha.split(",");
            if (campos.length < 2) {
                System.out.println("⚠️ Linha inválida no CSV sorteado!");
                return;
            }

            String nomeBuscado = campos[1].trim();
            System.out.println("🎯 Pokémon sorteado: " + nomeBuscado);

            // 4️⃣ Agora abrir a lista invertida
            File file = new File(caminhoListaInvertida);
            if (!file.exists() || file.length() == 0) {
                System.out.println(RED + "\n🚨 Lista Invertida não encontrada ou vazia!");
                System.out.println(YELLOW + "💡 Crie a Lista Invertida primeiro usando a opção de CREATE.\n" + RESET);
                return;
            }

            boolean encontrou = false;
            try (RandomAccessFile rafIndice = new RandomAccessFile(file, "r")) {
                System.out.println("\n🔍 Iniciando busca na Lista Invertida...");

                while (rafIndice.getFilePointer() < rafIndice.length()) {
                    int id = rafIndice.readInt();
                    String nome = lerString(rafIndice);
                    long offset = rafIndice.readLong();

                    if (nome.equalsIgnoreCase(nomeBuscado)) {
                        if (offset > 0) {
                            encontrou = true;
                            System.out.println("\n✅ Pokémon encontrado!");
                            System.out.println("ID na Lista: " + id);
                            System.out.println("Nome: " + nome);
                            System.out.println("Offset: " + offset);
                            break;
                        } else {
                            System.out.println("\n⚰️ Pokémon \"" + nome + "\" foi deletado (offset = 0).");
                            encontrou = true;
                            break;
                        }
                    }
                }
            }

            if (!encontrou) {
                System.out.println("\n❌ Pokémon \"" + nomeBuscado + "\" não encontrado na lista invertida!");
                System.out.println(YELLOW + "💡 Se necessário, RECRIE a Lista Invertida.\n" + RESET);
            }

        } catch (IOException e) {
            System.out.println(RED + "\n🚨 Erro ao acessar arquivos: " + e.getMessage() + RESET);
        }
    }


}
