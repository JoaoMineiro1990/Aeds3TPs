import Classes.ListaInvertidaBinaria;
import Classes.CRUDListaInvertida.Criar;
import Classes.CRUDListaInvertida.Delete;
import Classes.CRUDListaInvertida.Ler;
import Classes.CRUDListaInvertida.Update;
import java.util.Scanner;


public class MenuPrincipal{

    // Códigos ANSI para cores pra ficar bunitinho - no vs code tenho certeza que funciona 
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";


    static ListaInvertidaBinaria listaInvertida = new ListaInvertidaBinaria("data/listaInvertida.bin");
    // futuramente
    //* static ArvoreB arvoreB = new ArvoreB("data/arvoreB.bin");
    //* static Hash hash = new Hash("data/hash.bin");

    public static void mainMenu(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        // Cabeçalho do TP
        System.out.println(CYAN + "\nTP2 - AEDsIII");
        System.out.println("Dupla: João Vitor Mendes e Gabriela Lacerda" + RESET);

        // Loop principal do meu Menu
        while (flag) {

            Desenhar.desenharMenuPrincipal();
            int operacao = scanner.nextInt();

            if (operacao == 0) {
                System.out.println("\n" + PURPLE + ">>> Saindo do programa... Até mais!" + RESET);
                Desenhar.desenharPikachu();
                break;
            }

            int pesoEstrutura = Desenhar.desenharMenuEstruturas(scanner);

            if (pesoEstrutura == -1) {
                System.out.println(YELLOW + ">>> Operação cancelada! Retornando ao menu principal..." + RESET);
                continue;
            }

            int codigo = operacao + pesoEstrutura;
            executarOperacao(codigo);
        }

        scanner.close();
    }

    private static void executarOperacao(int codigo) {
        System.out.print(CYAN + "\nProcessando" + RESET);
        Desenhar.carregarPontos();
        
        String caminhoListaInvertida = listaInvertida.getCaminhoListaInvertida();
        String caminhoDoArquivoBinarioPokemons = "data/arquivo.bin"; // Caminho do arquivo binário de Pokémons


        switch (codigo) {
            case 2:
                // Chamada da função CREATE em Árvore B
                System.out.println(GREEN + "\n>>> CREATE em Árvore B" + RESET);
                break;
            case 6:
                // Chamada da função CREATE em Hash
                System.out.println(GREEN + "\n>>> CREATE em Hash" + RESET);
                break;
            case 10:
                // Chamada da função CREATE em Lista Invertida
                System.out.println(GREEN + "\n>>> CREATE em Lista Invertida" + RESET);
                Criar.criarLista(caminhoListaInvertida, caminhoDoArquivoBinarioPokemons);
                break;
            case 3:
                // Chamada da função READ em Árvore B
                System.out.println(BLUE + "\n>>> READ em Árvore B" + RESET);
                break;
            case 7:
                // Chamada da função READ em Hash
                System.out.println(BLUE + "\n>>> READ em Hash" + RESET);
                break;
            case 11:
                // Chamada da função READ em Lista Invertida
                System.out.println(BLUE + "\n>>> READ em Lista Invertida" + RESET);
                Ler.lerListaInvertida(caminhoListaInvertida);
                break;
            case 4:
                // Chamada da função UPDATE em Árvore B
                System.out.println(YELLOW + "\n>>> UPDATE em Árvore B" + RESET);
                break;
            case 8:
                // Chamada da função UPDATE em Hash
                System.out.println(YELLOW + "\n>>> UPDATE em Hash" + RESET);
                break;
            case 12:
                // Chamada da função UPDATE em Lista Invertida
                System.out.println(YELLOW + "\n>>> UPDATE em Lista Invertida" + RESET);
                Update.procurarPokemonParaAtualizar(caminhoListaInvertida,caminhoDoArquivoBinarioPokemons);
                break;
            case 5:
                // Chamada da função DELETE em Árvore B
                System.out.println(RED + "\n>>> DELETE em Árvore B" + RESET);
                break;
            case 9:
                // Chamada da função DELETE em Hash
                System.out.println(RED + "\n>>> DELETE em Hash " + RESET);
                break;
            case 13:
                // Chamada da função DELETE em Lista Invertida
                System.out.println(RED + "\n>>> DELETE em Lista Invertida " + RESET);
                Delete.deletarPokemonListaInvertida(caminhoListaInvertida);
                break;
            default:
                System.out.println(RED + "\n>>> Código inválido! Retornando ao menu principal..." + RESET);
                break;
        }
    }
}