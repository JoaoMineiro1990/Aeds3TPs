package Classes.Arquivo;

import Classes.Pokemon.Pokemon;
import Classes.Pokemon.PokemonNoArquivo;

import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static Classes.Pokemon.PokemonNoArquivo.contarBytes;

public class CriarArquivo {

    public static long dataParaEpoch(String dataStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataStr, formatter);
        return data.atStartOfDay(ZoneId.of("UTC")).toEpochSecond();
    }

    public static List<String> SplitInteligente(String linha) {
        List<String> items = new ArrayList<>();
        boolean entreAspas = false;
        StringBuilder buffer = new StringBuilder();

        for (char c : linha.toCharArray()) {
            if (c == '"') {
                entreAspas = !entreAspas;
            } else if (c == ',' && !entreAspas) {
                items.add(buffer.toString().trim());
                buffer = new StringBuilder();
            } else {
                buffer.append(c);
            }
        }
        items.add(buffer.toString().trim());

        return items;
    }

    public static String posicoesVazias(String linha) {
        return linha.replaceAll(",,", ",null,");
    }

    public static List<Pokemon> criarPokemonDoCSV(String caminhoArquivoCSV) {
        List<Pokemon> pokemons = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoCSV))) {
            // Pular cabeçalho
            br.readLine();

            String linha;
            while ((linha = br.readLine()) != null) {
                // 1. Tratar campos vazios
                linha = posicoesVazias(linha);

                // 2. Fazer split inteligente
                List<String> dados = SplitInteligente(linha);

                // 3. Criar o Pokémon
                Pokemon pokemon = criarPokemonDoSplit(dados);

                // 4. Adicionar à lista
                pokemons.add(pokemon);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo CSV: " + e.getMessage());
            throw new RuntimeException("Falha ao processar arquivo CSV", e);
        }

        return pokemons;
    }

    public static Pokemon criarPokemonDoSplit(List<String> dados) {
        Pokemon pokemon = new Pokemon();

        try {
            // Atributos básicos com tratamento seguro
            pokemon.setNumberPokedex(safeParseInt(dados.get(0), 0));
            pokemon.setName(dados.get(1).trim());
            // Tipos com tratamento de valores nulos
            pokemon.setType1(dados.get(2).equalsIgnoreCase("null") ? null : dados.get(2).trim());
            pokemon.setType2(dados.get(3).equalsIgnoreCase("null") ? null : dados.get(3).trim());
            // Habilidades com limpeza de aspas extras
            String habilidadesBrutas = dados.get(4);
            String habilidadesFormatadas = habilidadesBrutas
                    .replaceAll("^\\[|\\]$", "")  // Remove colchetes
                    .replaceAll("'", "")           // Remove aspas simples
                    .trim();

            pokemon.setAbilities(habilidadesFormatadas);
            String abilities = dados.get(4).replaceAll("^\\[|\\]$", "").replaceAll("'", "");
            pokemon.setAbilities(abilities);
            // Estatísticas com valores padrão seguros
            pokemon.setHp(safeParseInt(dados.get(5), 0));
            pokemon.setAtt(safeParseInt(dados.get(6), 0));
            pokemon.setDef(safeParseInt(dados.get(7), 0));
            pokemon.setSpa(safeParseInt(dados.get(8), 0));
            pokemon.setSpd(safeParseInt(dados.get(9), 0));
            pokemon.setSpe(safeParseInt(dados.get(10), 0));
            pokemon.setBst(safeParseInt(dados.get(11), 0));

            // Conversão segura da data
            try {
                if (!dados.get(14).equalsIgnoreCase("null")) {
                    pokemon.setDataEpoch(dataParaEpoch(dados.get(14)));
                } else {
                    pokemon.setDataEpoch(0L); // Data padrão se for nula
                }
            } catch (DateTimeParseException e) {
                System.err.println("Formato de data inválido: " + dados.get(14));
                pokemon.setDataEpoch(0L);
            }

        } catch (Exception e) {
            System.err.println("Erro crítico ao criar Pokémon: " + e.getMessage());

            throw new RuntimeException("Falha ao processar linha do CSV", e);
        }

        return pokemon;
    }

    private static int safeParseInt(String value, int defaultValue) {
        if (value == null || value.equalsIgnoreCase("null")) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static List<PokemonNoArquivo> criarPokemonParaOArquivo(List<Pokemon> pokemons) {
        List<PokemonNoArquivo> registros = new ArrayList<>();
        int id = 1;

        for (Pokemon pokemon : pokemons) {
            PokemonNoArquivo registro = new PokemonNoArquivo();
            registro.setId(id++);
            registro.setPokemon(pokemon);
            registro.setCova(false);
            // Calcula o tamanho em bytes
            int tamanho = contarBytes(pokemon);
            registro.setTamanhoBytes(tamanho);
            registros.add(registro);
        }

        return registros;
    }

    public static void escreverArquivoFinal(List<PokemonNoArquivo> pokemonsBytes, String caminhoArquivo) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "rw")) {
            raf.setLength(0);
            raf.writeInt(0);
            int ultimoId = 0;

            for (PokemonNoArquivo pna : pokemonsBytes) {
                ultimoId = pna.getId();

                raf.writeInt(pna.getId());
                raf.writeBoolean(pna.isCova());
                raf.writeInt(pna.getTamanhoBytes());

                Pokemon pokemon = pna.getPokemon();
                raf.writeInt(pokemon.getNumberPokedex());
                escreverString(raf, pokemon.getName());
                escreverString(raf, pokemon.getType1());
                escreverString(raf, pokemon.getType2());
                escreverString(raf, pokemon.getAbilities());

                raf.writeInt(pokemon.getHp());
                raf.writeInt(pokemon.getAtt());
                raf.writeInt(pokemon.getDef());
                raf.writeInt(pokemon.getSpa());
                raf.writeInt(pokemon.getSpd());
                raf.writeInt(pokemon.getSpe());
                raf.writeInt(pokemon.getBst());

                raf.writeLong(pokemon.getDataEpoch());
            }

            raf.seek(0);
            raf.writeInt(ultimoId);

            System.out.println("✅ Todos os Pokémons foram gravados no arquivo: " + caminhoArquivo);
            System.out.println("📌 Último ID gravado no início do arquivo: " + ultimoId);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar Pokémons no arquivo", e);
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
    public static void debugLer7PrimeirosPokemons(String caminhoArquivo) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "r")) {
            System.out.println("--- DEBUG LEITURA DO ARQUIVO ---");

            int ultimoId = raf.readInt();
            System.out.println("(Info) Último ID salvo no começo do arquivo: " + ultimoId + "\n");

            for (int i = 0; i < 7; i++) {
                System.out.println("Pokémon #" + (i + 1));

                int id = raf.readInt();
                boolean cova = raf.readBoolean();
                int tamanhoBytes = raf.readInt();

                System.out.println("ID: " + id);
                System.out.println("Cova: " + cova);
                System.out.println("TamanhoBytes: " + tamanhoBytes);

                int numberPokedex = raf.readInt();
                String name = lerString(raf);
                String type1 = lerString(raf);
                String type2 = lerString(raf);
                String abilities = lerString(raf);

                int hp = raf.readInt();
                int att = raf.readInt();
                int def = raf.readInt();
                int spa = raf.readInt();
                int spd = raf.readInt();
                int spe = raf.readInt();
                int bst = raf.readInt();

                long dataEpoch = raf.readLong();

                System.out.println("NumberPokedex: " + numberPokedex);
                System.out.println("Name: " + name);
                System.out.println("Type1: " + type1);
                System.out.println("Type2: " + (type2.isEmpty() ? "null" : type2));
                System.out.println("Abilities: " + abilities);
                System.out.println("HP: " + hp);
                System.out.println("ATT: " + att);
                System.out.println("DEF: " + def);
                System.out.println("SPA: " + spa);
                System.out.println("SPD: " + spd);
                System.out.println("SPE: " + spe);
                System.out.println("BST: " + bst);
                System.out.println("DataEpoch: " + dataEpoch);
                System.out.println("-----------------------------\n");
            }

            System.out.println("--- FIM DEBUG ---");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler Pokémon do arquivo", e);
        }
    }

    private static String lerString(RandomAccessFile raf) throws IOException {
        int tamanho = raf.readShort();
        if (tamanho > 0) {
            byte[] bytes = new byte[tamanho];
            raf.readFully(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        } else {
            return "";
        }
    }

    public static void debugLer5UltimosPokemons(String caminhoArquivo) {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "r")) {
            System.out.println("--- DEBUG: 5 ÚLTIMOS POKÉMONS ---");

            // 1. Ler o último ID salvo no início do arquivo
            int ultimoId = raf.readInt();
            System.out.println("ULTIMO ID DEBBUG " + ultimoId);
            int idParaComecar = ultimoId - 4;

            if (idParaComecar < 0) idParaComecar = 0;

            boolean comecarAImprimir = false;
            int pokemonsImpressos = 0;

            while (raf.getFilePointer() < raf.length()) {
                // Ler os campos
                int id = raf.readInt();
                boolean cova = raf.readBoolean();
                int tamanhoBytes = raf.readInt();

                int numberPokedex = raf.readInt();
                String name = lerString(raf);
                String type1 = lerString(raf);
                String type2 = lerString(raf);
                String abilities = lerString(raf);

                int hp = raf.readInt();
                int att = raf.readInt();
                int def = raf.readInt();
                int spa = raf.readInt();
                int spd = raf.readInt();
                int spe = raf.readInt();
                int bst = raf.readInt();

                long dataEpoch = raf.readLong();

                // Verificar se deve começar a imprimir
                if (id >= idParaComecar) {
                    comecarAImprimir = true;
                }

                if (comecarAImprimir) {
                    System.out.println("ID: " + id);
                    System.out.println("Cova: " + cova);
                    System.out.println("TamanhoBytes: " + tamanhoBytes);
                    System.out.println("NumberPokedex: " + numberPokedex);
                    System.out.println("Name: " + name);
                    System.out.println("Type1: " + (type1.isEmpty() ? "null" : type1));
                    System.out.println("Type2: " + (type2.isEmpty() ? "null" : type2));
                    System.out.println("Abilities: " + abilities);
                    System.out.println("HP: " + hp);
                    System.out.println("ATT: " + att);
                    System.out.println("DEF: " + def);
                    System.out.println("SPA: " + spa);
                    System.out.println("SPD: " + spd);
                    System.out.println("SPE: " + spe);
                    System.out.println("BST: " + bst);
                    System.out.println("DataEpoch: " + dataEpoch);
                    System.out.println("-----------------------------\n");

                    pokemonsImpressos++;
                    if (pokemonsImpressos == 5) {
                        break;
                    }
                }
            }

            System.out.println("--- FIM DEBUG ---");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler Pokémon do arquivo", e);
        }
    }

}