import Classes.PokemonNoArquivo;
import Classes.criarArquivo;
import java.util.List;
import Classes.Pokemon;

import static Classes.PokemonNoArquivo.contarBytesComDebug;
import static Classes.criarArquivo.debugLer7PrimeirosPokemons;
import static Classes.criarArquivo.escreverArquivoFinal;


public class Main {
    public static void main(String[] args) {
        // Caminho do arquivo binário onde vamos salvar
        String caminhoArquivo = "data/aquivaiseroarquivonovo";

        // 1. Criar os Pokémons
        List<Pokemon> pokemons = criarArquivo.criarPokemonDoCSV("data/dados_modificados.csv");
        List<PokemonNoArquivo> pokemonsBytes = criarArquivo.criarPokemonParaOArquivo(pokemons);
        escreverArquivoFinal(pokemonsBytes, "data/arquivo.bin");
        debugLer7PrimeirosPokemons("data/arquivo.bin");

        MenuPrincipal.mainMenu(args);
    }
}