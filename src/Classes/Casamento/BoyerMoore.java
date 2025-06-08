package Classes.Casamento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
 * Não distingue maiúsculas de minúsculas
 * Booyer Moore por bad character
*/
public class BoyerMoore {

    public static void boyerMooreStart() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n🔍 Digite o padrão a ser buscado: ");
        String padrao = scanner.nextLine().trim();
        System.out.println();

        if (padrao.isEmpty()) {
            System.out.println("⚠️ Padrão vazio! Retornando ao menu...");
            return;
        }

        String arquivo = "data/dados_modificados.csv";
        List<String> resultados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            boolean cabecalho = true;

            while ((linha = br.readLine()) != null) {
                if (cabecalho) {
                    cabecalho = false;
                    continue;
                }

                // Trata vírgulas dentro de aspas
                String[] campos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (campos.length > 1) {
                    String nome = campos[1].trim();
                    if (boyerMooreBusca(nome.toLowerCase(), padrao.toLowerCase())) {
                        resultados.add(nome);
                    }
                }
            }

            System.out.println("📄 Resultado da busca por \"" + padrao + "\":\n");

            if (resultados.isEmpty()) {
                System.out.println("❌ Nenhum nome encontrado contendo o padrão.");
            } else {
                System.out.println("✅ Padrão encontrado nos seguintes nomes:");
                for (String nome : resultados) {
                    System.out.println(" - " + nome);
                }
            }

        } catch (IOException e) {
            System.out.println("❗ Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // Algoritmo Boyer-Moore
    public static boolean boyerMooreBusca(String texto, String padrao) {
        Map<Character, Integer> badCharTable = construirBadCharTable(padrao);
        int tamanhoTexto = texto.length();
        int tamanhoPadrao = padrao.length();
        int deslocamento = 0;

        while (deslocamento <= (tamanhoTexto - tamanhoPadrao)) {
            int j = tamanhoPadrao - 1;

            while (j >= 0 && padrao.charAt(j) == texto.charAt(deslocamento + j)) {
                j--;
            }

            if (j < 0) {
                return true; // padrão encontrado
            } else {
                char caractereRuim = texto.charAt(deslocamento + j);
                int ultimaOcorrencia = badCharTable.getOrDefault(caractereRuim, -1);
                deslocamento += Math.max(1, j - ultimaOcorrencia);
            }
        }

        return false; // padrão não encontrado
    }

    // Tabela de caracteres ruins
    public static Map<Character, Integer> construirBadCharTable(String padrao) {
        Map<Character, Integer> tabela = new HashMap<>();

        for (int i = 0; i < padrao.length(); i++) {
            tabela.put(padrao.charAt(i), i);
        }

        return tabela;
    }
}
