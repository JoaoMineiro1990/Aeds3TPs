package Classes;

public class PokemonNaListaInvertida {
    private int id;
    private String nome;
    private long offset;

    public PokemonNaListaInvertida(String nome, long offset) {
        this.nome = nome;
        this.offset = offset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public long getOffset() {
        return offset;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Offset: " + offset;
    }
}
