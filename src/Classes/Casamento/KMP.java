package Classes.Casamento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KMP {

    public static void kmpStart() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDigite o padrão a ser buscado: ");
        String padrao = scanner.nextLine();
        System.out.print("\n");


        // Verifica se o padrão é vazio
        if (padrao.isEmpty()) {
            System.out.println("Padrão vazio!");
        }

        scanner.close();

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

                // Trata vírgulas dentro de aspas para garantir que o split funcione corretamente
                String[] campos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (campos.length > 1) {
                    String nome = campos[1].trim();
                    if (kmpBusca(nome.toLowerCase(), padrao.toLowerCase())) {
                        resultados.add(nome);
                    }
                }
            }

            if (resultados.isEmpty()) {
                System.out.println("Nenhum nome encontrado contendo o padrão.");
            } else {
                System.out.println("Encontrado em:");
                for (String nome : resultados) {
                    System.out.println("- " + nome);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // Algoritmo KMP
    public static boolean kmpBusca(String texto, String padrao) {
        int[] lps = calcularLPS(padrao);
        int i = 0; // índice para texto
        int j = 0; // índice para padrao

        while (i < texto.length()) {
            if (padrao.charAt(j) == texto.charAt(i)) {
                i++;
                j++;
            }

            if (j == padrao.length()) {
                return true; // padrão encontrado
            } else if (i < texto.length() && padrao.charAt(j) != texto.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return false;
    }

    // Pré-processamento do padrão para o algoritmo KMP
    public static int[] calcularLPS(String padrao) {
        int[] lps = new int[padrao.length()];
        int len = 0;
        int i = 1;

        while (i < padrao.length()) {
            if (padrao.charAt(i) == padrao.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}