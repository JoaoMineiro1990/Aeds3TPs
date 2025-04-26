import Classes.PokemonNoArquivo;
import Classes.criarArquivo;
import java.util.List;
import Classes.Pokemon;

import static Classes.criarArquivo.escreverArquivoFinal;
import static Classes.CRUDbinario.Criar.criarPokemonNoArquivoDeFormaAleatoria;
import static Classes.CRUDbinario.Ler.menuDeLeitura;
import static Classes.CRUDbinario.Delete.menuDeletar;
import static Classes.CRUDbinario.Update.menuAtualizarPokemon;
public class Main {
    public static void main(String[] args) {
        // Caminho do arquivo binário onde vamos salvar
        String caminhoArquivo = "data/aquivaiseroarquivonovo";

        // 1. Criar os Pokémons
        List<Pokemon> pokemons = criarArquivo.criarPokemonDoCSV("data/dados_modificados.csv");
        List<PokemonNoArquivo> pokemonsBytes = criarArquivo.criarPokemonParaOArquivo(pokemons);
        escreverArquivoFinal(pokemonsBytes, "data/arquivo.bin");
        //debugLer7PrimeirosPokemons("data/arquivo.bin");
        // 2. Criar Pokémon aleatório
        criarPokemonNoArquivoDeFormaAleatoria("data/dados_modificados.csv", "data/arquivo.bin");
        //criarArquivo.debugLer5UltimosPokemons("data/arquivo.bin");
        // 3. Ler os Pokémons
        menuAtualizarPokemon("data/arquivo.bin");
        menuDeletar("data/arquivo.bin");
        menuDeLeitura("data/arquivo.bin");

    }
}