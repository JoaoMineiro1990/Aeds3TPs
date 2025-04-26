package Classes;

import Classes.CRUDListaInvertida.Criar;
import Classes.CRUDListaInvertida.Delete;
import Classes.CRUDListaInvertida.Ler;
import Classes.CRUDListaInvertida.Update;

import java.util.Scanner;

public class ListaInvertidaBinaria {

    private String caminhoListaInvertida;

    public ListaInvertidaBinaria(String caminhoListaInvertida) {
        this.caminhoListaInvertida = caminhoListaInvertida;
    }

    public String getCaminhoListaInvertida() {
        return caminhoListaInvertida;
    }

    public void setCaminhoListaInvertida(String caminhoListaInvertida) {
        this.caminhoListaInvertida = caminhoListaInvertida;
    }

    public void menuListaInvertida(String caminhoDoArquivoBinarioPokemons) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU CRUD LISTA INVERTIDA ---");
            System.out.println("1 - Criar Lista Invertida (CREATE)");
            System.out.println("2 - Mostrar lista invertida (READ)");
            System.out.println("3 - Atualizar entrada (UPDATE)");
            System.out.println("4 - Deletar entrada (DELETE)");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    Criar.criarLista(caminhoListaInvertida, caminhoDoArquivoBinarioPokemons);
                    break;
                case 2:
                    Ler.lerListaInvertida(caminhoListaInvertida);
                    break;
                case 3:
                    Update.procurarPokemonParaAtualizar(caminhoListaInvertida,caminhoDoArquivoBinarioPokemons);
                    break;
                case 4:
                    Delete.deletarPokemonListaInvertida(caminhoListaInvertida);
                    break;
                case 0:
                    System.out.println("Saindo do menu da lista invertida...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

}

