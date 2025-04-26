package Classes;
import java.nio.charset.StandardCharsets;

public class PokemonNoArquivo {
    private int id;
    private boolean cova;
    private int tamanhoBytes;
    private Pokemon pokemon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCova() {
        return cova;
    }

    public void setCova(boolean cova) {
        this.cova = cova;
    }

    public int getTamanhoBytes() {
        return tamanhoBytes;
    }

    public void setTamanhoBytes(int tamanhoBytes) {
        this.tamanhoBytes = tamanhoBytes;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public static int contarBytes(Pokemon pokemon) {
        int totalBytes = 0;

        // numberPokedex - int
        totalBytes += 4;

        // name - String
        totalBytes += 2; // 2 bytes para o tamanho
        totalBytes += pokemon.getName().getBytes(StandardCharsets.UTF_8).length;

        // type1 - String
        totalBytes += 2;
        totalBytes += pokemon.getType1().getBytes(StandardCharsets.UTF_8).length;

        // type2 - String (pode ser null)
        totalBytes += 2; // tamanho
        if (pokemon.getType2() != null) {
            totalBytes += pokemon.getType2().getBytes(StandardCharsets.UTF_8).length;
        }

        // abilities - String
        totalBytes += 2;
        totalBytes += pokemon.getAbilities().getBytes(StandardCharsets.UTF_8).length;

        // 7 campos inteiros: hp, att, def, spa, spd, spe, bst
        totalBytes += 7 * 4;

        // dataEpoch - long
        totalBytes += 8;

        return totalBytes;
    }

    public static int contarBytesComDebug(Pokemon pokemon) {
        int totalBytes = 0;
        System.out.println("\n--- DEBUG CONTAR BYTES ---");
        System.out.println("Calculando para: " + pokemon.getName());

        // numberPokedex - int
        totalBytes += 4;
        System.out.println("numberPokedex (int): 4 bytes");

        // name - String
        byte[] nameBytes = pokemon.getName().getBytes(StandardCharsets.UTF_8);
        int nameSize = 2 + nameBytes.length;
        totalBytes += nameSize;
        System.out.println("name (String): " + nameSize + " bytes (2 bytes de tamanho + " + nameBytes.length + " bytes de conteúdo \"" + pokemon.getName() + "\")");

        // type1 - String
        byte[] type1Bytes = pokemon.getType1().getBytes(StandardCharsets.UTF_8);
        int type1Size = 2 + type1Bytes.length;
        totalBytes += type1Size;
        System.out.println("type1 (String): " + type1Size + " bytes (2 bytes de tamanho + " + type1Bytes.length + " bytes de conteúdo \"" + pokemon.getType1() + "\")");

        // type2 - String (pode ser null)
        int type2Size = 2;
        if (pokemon.getType2() != null) {
            byte[] type2Bytes = pokemon.getType2().getBytes(StandardCharsets.UTF_8);
            type2Size += type2Bytes.length;
            System.out.println("type2 (String): " + type2Size + " bytes (2 bytes de tamanho + " + type2Bytes.length + " bytes de conteúdo \"" + pokemon.getType2() + "\")");
        } else {
            System.out.println("type2 (String): 2 bytes (null)");
        }
        totalBytes += type2Size;

        // abilities - String
        byte[] abilitiesBytes = pokemon.getAbilities().getBytes(StandardCharsets.UTF_8);
        int abilitiesSize = 2 + abilitiesBytes.length;
        totalBytes += abilitiesSize;
        System.out.println("abilities (String): " + abilitiesSize + " bytes (2 bytes de tamanho + " + abilitiesBytes.length + " bytes de conteúdo \"" + pokemon.getAbilities() + "\")");

        // 7 campos inteiros: hp, att, def, spa, spd, spe, bst
        int statsSize = 7 * 4;
        totalBytes += statsSize;
        System.out.println("stats (7 inteiros): " + statsSize + " bytes (7 * 4)");

        // dataEpoch - long
        totalBytes += 8;
        System.out.println("dataEpoch (long): 8 bytes");

        System.out.println("TOTAL CALCULADO: " + totalBytes + " bytes");
        System.out.println("--- FIM DEBUG ---\n");

        return totalBytes;
    }

}
