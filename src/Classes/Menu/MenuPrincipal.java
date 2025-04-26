package Classes.Menu;

import Classes.CRUDListaInvertida.*;
import Classes.Hash.HashExtensivel;
import Classes.Pokemon.Pokemon;
import Classes.Pokemon.PokemonNoArquivo;

import java.io.File;
import java.util.List;
import java.util.Scanner;


public class MenuPrincipal{

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CAMINHO_LISTA_INVERTIDA = "data/listaInvertida.bin";
    public static final String CAMINHO_ARQUIVO_BINARIO = "data/arquivo.bin";
    public static final String CAMINHO_CSV_POKEMONS = "data/dados_modificados.csv";
    public static final String CAMINHO_PASTA_BUCKETS = "data/buckets";
    public static final String CAMINHO_DIRETORIO_HASH = "data/diretorioHash.bin";

    static ListaInvertidaBinaria listaInvertida = new ListaInvertidaBinaria("data/listaInvertida.bin");

    public static void mainMenu(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        // Cabeçalho do TP
        System.out.println(CYAN + "\nTP2 - AEDsIII");
        System.out.println("Dupla: João Vitor Mendes e Gabriela Lacerda" + RESET);

        // === Sempre criar (ou recriar) o arquivo binário ===
        try {
            File arquivoBinario = new File(CAMINHO_ARQUIVO_BINARIO);
            if (arquivoBinario.exists()) {
                arquivoBinario.delete(); // 💣 Deleta o arquivo antigo se existir
            }

            System.out.println(YELLOW + "\n>>> Criando novo arquivo binário..." + RESET);

            List<Pokemon> pokemons = Classes.Arquivo.CriarArquivo.criarPokemonDoCSV(CAMINHO_CSV_POKEMONS);
            List<PokemonNoArquivo> registros = Classes.Arquivo.CriarArquivo.criarPokemonParaOArquivo(pokemons);
            Classes.Arquivo.CriarArquivo.escreverArquivoFinal(registros, CAMINHO_ARQUIVO_BINARIO);

            System.out.println(GREEN + "✅ Novo arquivo binário criado com sucesso!" + RESET);
        } catch (Exception e) {
            System.out.println(RED + "\n>>> Erro ao criar o arquivo binário!" + RESET);
            System.out.println(YELLOW + ">>> Certifique-se que o arquivo " + CAMINHO_CSV_POKEMONS + " existe e está correto." + RESET);
            return;
        }

        // Loop principal do Menu
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
        HashExtensivel hashExtensivel = new HashExtensivel(CAMINHO_PASTA_BUCKETS, CAMINHO_DIRETORIO_HASH);
        System.out.print(CYAN + "\nProcessando" + RESET);
        Desenhar.carregarPontos();
        List<Pokemon> pokemons = null;
        List<PokemonNoArquivo> registros = null;

        switch (codigo) {
            case 1:
                break;
            case 2:
                System.out.println(RED + "\nFiz nao, nao consegui" + RESET);
                break;
            case 6:
                System.out.println(GREEN + "\n>>> Criando Hash Extensível a partir do Arquivo Binário" + RESET);

                try {
                    // Apaga se existir e recria
                    hashExtensivel.apagarTudoAntesDeCriar();
                    hashExtensivel.criarAPartirDoArquivoBinario(CAMINHO_ARQUIVO_BINARIO);

                    System.out.println(GREEN + "✅ Hash criada com sucesso!" + RESET);
                } catch (Exception e) {
                    System.out.println(RED + "\n>>> Erro ao criar a Hash Extensível!" + RESET);
                    e.printStackTrace();
                }
                break;
            case 10:
                System.out.println(GREEN + "\n>>> Criando Lista Invertida" + RESET);

                try {
                    Criar.criarLista(CAMINHO_LISTA_INVERTIDA, CAMINHO_ARQUIVO_BINARIO);
                    System.out.println(GREEN + "✅ Lista Invertida criada com sucesso!" + RESET);
                } catch (Exception e) {
                    System.out.println(RED + "\n>>> Erro ao criar a Lista Invertida!" + RESET);
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println(BLUE + "\n>>> READ em Árvore B" + RESET);
                break;
            case 7:
                System.out.println(BLUE + "\n>>> READ em Hash" + RESET);
                try {

                    hashExtensivel.buscarPokemonAleatorioNaHash(CAMINHO_CSV_POKEMONS);
                    // hashExtensivel.mostrarEstadoDaHash();
                    System.out.println(BLUE + "\n>>> Temos o codigo para mostrar todos, se quizer descomente a linha" + RESET);
                    System.out.println(YELLOW + "\n>>> se o codigo nao achou o pokemon n esqueca de CRIAR a hash" + RESET);
                } catch (Exception e) {
                    System.out.println(RED + "\n>>> Erro ao mostrar o estado da Hash!" + RESET);
                    e.printStackTrace();
                }
                break;
            case 11:
                System.out.println(BLUE + "\n>>> READ em Lista Invertida" + RESET);
                Ler.lerUmaEntradaAleatoriaDaLista(CAMINHO_LISTA_INVERTIDA,CAMINHO_CSV_POKEMONS);
                // Ler.lerListaInvertida(CAMINHO_LISTA_INVERTIDA);
                System.out.println(BLUE + "\n>>> Temos o codigo para mostrar todos, se quizer descomente a linha" + RESET);
                System.out.println(YELLOW + "\n>>> se o codigo nao achou o pokemon n esqueca de CRIAR a lista" + RESET);
                break;
            case 4:
                System.out.println(YELLOW + "\n>>> UPDATE em Árvore B" + RESET);
                break;
            case 8:
                System.out.println(YELLOW + "\n>>> UPDATE em Hash" + RESET);
                try {
                    File diretorioHash = new File(CAMINHO_DIRETORIO_HASH);
                    if (!diretorioHash.exists() || diretorioHash.length() == 0) {
                        System.out.println(RED + "\n🚨 Hash não encontrada ou vazia!" + RESET);
                        System.out.println(YELLOW + "💡 Crie a Hash primeiro usando a opção 'Criar Hash a partir do arquivo de Pokémons'." + RESET);
                    } else {
                        hashExtensivel.atualizarPokemonNaHashPorId(CAMINHO_ARQUIVO_BINARIO);
                    }
                } catch (Exception e) {
                    System.out.println(RED + "\n>>> Erro ao atualizar Pokémon na Hash!" + RESET);
                    e.printStackTrace();
                }
                break;
            case 12:
                System.out.println(YELLOW + "\n>>> UPDATE em Lista Invertida" + RESET);

                File arquivoLista = new File(CAMINHO_LISTA_INVERTIDA);

                if (!arquivoLista.exists() || arquivoLista.length() == 0) {
                    System.out.println(RED + "\n🚨 Lista Invertida não encontrada ou está vazia!" + RESET);
                    System.out.println(YELLOW + "💡 Crie a lista invertida primeiro usando a opção de CREATE." + RESET);
                    break;
                }

                try {
                    Update.procurarPokemonParaAtualizar(CAMINHO_LISTA_INVERTIDA, CAMINHO_ARQUIVO_BINARIO);
                } catch (Exception e) {
                    System.out.println(RED + "\n>>> Erro ao atualizar Pokémon na Lista Invertida!" + RESET);
                    e.printStackTrace();
                }
                break;
            case 5:
                System.out.println(RED + "\n>>> DELETE em Árvore B" + RESET);
                break;
            case 9:
                System.out.println(RED + "\n>>> DELETE em Hash" + RESET);
                try {
                    File diretorioHash = new File(CAMINHO_DIRETORIO_HASH);
                    if (!diretorioHash.exists() || diretorioHash.length() == 0) {
                        System.out.println(RED + "\n🚨 Hash não encontrada ou vazia!" + RESET);
                        System.out.println(YELLOW + "💡 Crie a Hash primeiro usando a opção 'Criar Hash a partir do arquivo de Pokémons'." + RESET);
                    } else {
                        System.out.println("\n--- Escolha um Pokémon para deletar ---");
                        System.out.println("1 - Exeggcute  2 - Exeggutor  3 - Cubone");
                        System.out.println("4 - Marowak    5 - Hitmonlee 6 - Hitmonchan");
                        System.out.println("7 - Lickitung  8 - Koffing   9 - Weezing");
                        System.out.println("10 - Rhyhorn   11 - Rhydon   12 - Chansey");
                        System.out.println("13 - Tangela   14 - Kangaskhan 15 - Horsea");
                        System.out.println("16 - Seadra    17 - Goldeen  18 - Seaking");
                        System.out.println("19 - Staryu");

                        System.out.print("\nDigite o número do Pokémon que deseja deletar: ");
                        Scanner scannerDelete = new Scanner(System.in);
                        int escolha = scannerDelete.nextInt();
                        scannerDelete.nextLine(); // limpar buffer

                        String nomeProcurado = switch (escolha) {
                            case 1 -> "Exeggcute";
                            case 2 -> "Exeggutor";
                            case 3 -> "Cubone";
                            case 4 -> "Marowak";
                            case 5 -> "Hitmonlee";
                            case 6 -> "Hitmonchan";
                            case 7 -> "Lickitung";
                            case 8 -> "Koffing";
                            case 9 -> "Weezing";
                            case 10 -> "Rhyhorn";
                            case 11 -> "Rhydon";
                            case 12 -> "Chansey";
                            case 13 -> "Tangela";
                            case 14 -> "Kangaskhan";
                            case 15 -> "Horsea";
                            case 16 -> "Seadra";
                            case 17 -> "Goldeen";
                            case 18 -> "Seaking";
                            case 19 -> "Staryu";
                            default -> {
                                System.out.println(RED + "\n❌ Número inválido! Cancelando operação." + RESET);
                                yield null;
                            }
                        };

                        if (nomeProcurado != null) {
                            hashExtensivel.deletarPorNome(nomeProcurado, CAMINHO_ARQUIVO_BINARIO);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(RED + "\n>>> Erro ao deletar Pokémon na Hash!" + RESET);
                    e.printStackTrace();
                }
                break;
            case 13:
                System.out.println(RED + "\n>>> DELETE em Lista Invertida" + RESET);
                try {
                    File listaArquivo = new File(CAMINHO_LISTA_INVERTIDA);
                    if (!listaArquivo.exists() || listaArquivo.length() == 0) {
                        System.out.println(RED + "\n🚨 Lista Invertida não encontrada ou vazia!" + RESET);
                        System.out.println(YELLOW + "💡 Crie a Lista Invertida primeiro usando a opção 'Criar Lista Invertida'." + RESET);
                    } else {
                        Delete.deletarPokemonListaInvertida(CAMINHO_LISTA_INVERTIDA);
                    }
                } catch (Exception e) {
                    System.out.println(RED + "\n>>> Erro ao deletar Pokémon na Lista Invertida!" + RESET);
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println(RED + "\n>>> Código inválido! Retornando ao menu principal..." + RESET);
                break;
        }
    }

}