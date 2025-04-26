package Classes.CRUDbinario;

import Classes.Pokemon;
import Classes.PokemonNoArquivo;
import Classes.CriarArquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

import static Classes.PokemonNoArquivo.contarBytes;

public class Criar {

    public static void criarPokemonNoArquivoDeFormaAleatoria(String caminhoCSV, String caminhoBinario) {
        try {
            Random random = new Random();
            int linhaAleatoria = random.nextInt(1030); // número entre 0 e 1029
            Pokemon pokemonSelecionado = null;
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {
                br.readLine(); // Pular cabeçalho

                for (int i = 0; i <= linhaAleatoria; i++) {
                    String linha = br.readLine();
                    if (linha == null) {
                        throw new RuntimeException("Linha aleatória fora do tamanho do arquivo!");
                    }
                    if (i == linhaAleatoria) {
                        linha = CriarArquivo.posicoesVazias(linha);
                        List<String> dados = CriarArquivo.SplitInteligente(linha);
                        pokemonSelecionado = CriarArquivo.criarPokemonDoSplit(dados);
                    }
                }
            }
            if(pokemonSelecionado!= null){
                printarPokemon(pokemonSelecionado);
            }

            // 3. Abrir o arquivo binário
            try (RandomAccessFile raf = new RandomAccessFile(caminhoBinario, "rw")) {
                long ponteiroInicio = raf.getFilePointer();
                // 4. Ler o último ID
                int ultimoId = raf.readInt();

                // 5. Criar o novo PokémonNoArquivo
                PokemonNoArquivo novoPokemon = new PokemonNoArquivo();
                novoPokemon.setId(ultimoId + 1);
                novoPokemon.setCova(false);
                novoPokemon.setPokemon(pokemonSelecionado);
                novoPokemon.setTamanhoBytes(contarBytes(pokemonSelecionado));

                // 6. Ir para o final do arquivo
                raf.seek(raf.length());

                // 7. Escrever o novo PokémonNoArquivo
                raf.writeInt(novoPokemon.getId());
                raf.writeBoolean(novoPokemon.isCova());
                raf.writeInt(novoPokemon.getTamanhoBytes());

                Pokemon p = novoPokemon.getPokemon();
                raf.writeInt(p.getNumberPokedex());
                escreverString(raf, p.getName());
                escreverString(raf, p.getType1());
                escreverString(raf, p.getType2());
                escreverString(raf, p.getAbilities());
                raf.writeInt(p.getHp());
                raf.writeInt(p.getAtt());
                raf.writeInt(p.getDef());
                raf.writeInt(p.getSpa());
                raf.writeInt(p.getSpd());
                raf.writeInt(p.getSpe());
                raf.writeInt(p.getBst());
                raf.writeLong(p.getDataEpoch());

                raf.seek(ponteiroInicio);
                raf.writeInt(novoPokemon.getId());

                // 9. Mensagem de sucesso
                System.out.println("✅ Pokémon adicionado com sucesso!");
                printarPokemon(novoPokemon.getPokemon());
            }

        } catch (Exception e) {
            System.err.println("Erro ao criar Pokémon aleatório: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void escreverString(RandomAccessFile raf, String s) throws IOException {
        if (s != null) {
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            raf.writeShort(bytes.length);
            raf.write(bytes);
        } else {
            raf.writeShort(0);
        }
    }

    public static void printarPokemon(Pokemon pokemon) {
        if (pokemon == null) {
            System.out.println("Pokémon nulo.");
            return;
        }
        System.out.println("--- INFORMAÇÕES DO POKÉMON ---");
        System.out.println("NumberPokedex: " + pokemon.getNumberPokedex());
        System.out.println("Name: " + pokemon.getName());
        System.out.println("Type1: " + (pokemon.getType1() != null ? pokemon.getType1() : "null"));
        System.out.println("Type2: " + (pokemon.getType2() != null ? pokemon.getType2() : "null"));
        System.out.println("Abilities: " + pokemon.getAbilities());
        System.out.println("HP: " + pokemon.getHp());
        System.out.println("ATT: " + pokemon.getAtt());
        System.out.println("DEF: " + pokemon.getDef());
        System.out.println("SPA: " + pokemon.getSpa());
        System.out.println("SPD: " + pokemon.getSpd());
        System.out.println("SPE: " + pokemon.getSpe());
        System.out.println("BST: " + pokemon.getBst());
        System.out.println("DataEpoch: " + pokemon.getDataEpoch());
        System.out.println("--- FIM ---\n");
    }
}
