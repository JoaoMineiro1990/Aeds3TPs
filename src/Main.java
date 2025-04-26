import Classes.ListaInvertidaBinaria;
import Classes.PokemonNoArquivo;
import Classes.CriarArquivo;
import java.util.List;
import Classes.Pokemon;

public class Main {
    public static void main(String[] args) {

        String caminhoArquivo = "data/arquivo.bin";
        String caminhoListaInvertida = "data/lista_invertida.bin";
        // 1. Criar os Pokémons
        List<Pokemon> pokemons = CriarArquivo.criarPokemonDoCSV("data/dados_modificados.csv");
        List<PokemonNoArquivo> pokemonsBytes = CriarArquivo.criarPokemonParaOArquivo(pokemons);
        // 3. Criar lista invertida binária
        ListaInvertidaBinaria listaInvertida = new ListaInvertidaBinaria(caminhoListaInvertida);
        listaInvertida.menuListaInvertida(caminhoArquivo);

    }
}