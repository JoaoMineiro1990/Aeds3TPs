import Classes.ListaInvertidaBinaria;
import Classes.HashExtensivel;
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
    static HashExtensivel hash = new HashExtensivel("data/dados_modificados.csv", "data/hash.bin");

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
                System.out.println(RED + ">>> Operação cancelada! Retornando ao menu principal..." + RESET);
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
        
        //LISTA INVERTIDA
        String caminhoListaInvertida = listaInvertida.getCaminhoListaInvertida();
        String caminhoDoArquivoBinarioPokemons = "data/arquivo.bin"; // Caminho do arquivo binário de Pokémons

        //HASH
        //String caminhoArquivoPokemons = HashExtensivel.getCaminhoArquivoPokemons();
        //? Não sei de onde vc ta pegando o caminhoArquivoPokemons e o que ele deveria ser,
        //? não to sabendo o que mandar no contrutor padrão do HashExtensivel

        //ÁRVORE B
        

        switch (codigo) {
            case 2:
                // CREATE em Árvore B
                System.out.println(GREEN + "\n>>> CREATE Árvore B" + RESET);
                break;
            case 6:
                // CREATE em Hash
                System.out.println(GREEN + "\n>>> CREATE Hash" + RESET);
                //HashExtensivel.apagarTudoAntesDeCriar(); // 💣 Nova função que apaga tudo
                //HashExtensivel.criarAPartirDoArquivoBinario(caminhoDoArquivoBinarioPokemons); // 💥 Cria do zero
                break;
            case 10:
                // CREATE em Lista Invertida
                System.out.println(GREEN + "\n>>> CREATE Lista Invertida" + RESET);
                Criar.criarLista(caminhoListaInvertida, caminhoDoArquivoBinarioPokemons);
                break;
            case 3:
                // READ em Árvore B
                System.out.println(BLUE + "\n>>> READ em Árvore B" + RESET);
                break;
            case 7:
                // READ em Hash
                System.out.println(BLUE + "\n>>> READ em Hash" + RESET);
                break;
            case 11:
                // READ em Lista Invertida
                System.out.println(BLUE + "\n>>> READ em Lista Invertida" + RESET);
                Ler.lerListaInvertida(caminhoListaInvertida);
                break;
            case 4:
                // UPDATE em Árvore B
                System.out.println(YELLOW + "\n>>> UPDATE em Árvore B" + RESET);
                break;
            case 8:
                // UPDATE em Hash
                System.out.println(YELLOW + "\n>>> UPDATE em Hash" + RESET);
                break;
            case 12:
                // UPDATE em Lista Invertida
                System.out.println(YELLOW + "\n>>> UPDATE em Lista Invertida" + RESET);
                Update.procurarPokemonParaAtualizar(caminhoListaInvertida,caminhoDoArquivoBinarioPokemons);
                break;
            case 5:
                // DELETE em Árvore B
                System.out.println(RED + "\n>>> DELETE em Árvore B" + RESET);
                break;
            case 9:
                // DELETE em Hash
                System.out.println(RED + "\n>>> DELETE em Hash " + RESET);
                break;
            case 13:
                // DELETE em Lista Invertida
                System.out.println(RED + "\n>>> DELETE em Lista Invertida " + RESET);
                Delete.deletarPokemonListaInvertida(caminhoListaInvertida);
                break;
            default:
                System.out.println(RED + "\n>>> Código inválido! Retornando ao menu principal..." + RESET);
                break;
        }
    }
}