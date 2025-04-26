package Classes;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static Classes.CRUDbinario.Delete.deletarPokemonPorId;

public class HashExtensivel {
    private final String pastaBuckets;
    private final String caminhoDiretorio;
    private int profundidadeGlobal;
    private final int capacidadeBucket = 100;

    public HashExtensivel(String pastaBuckets, String caminhoDiretorio) {
        this.pastaBuckets = pastaBuckets;
        this.caminhoDiretorio = caminhoDiretorio;
        this.profundidadeGlobal = 1;
    }

    public void menuHash(String caminhoArquivoPokemons) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MENU HASH EXTENSÍVEL ---");
            System.out.println("1 - Criar Hash a partir do arquivo de Pokémons");
            System.out.println("2 - Mostrar Estado Atual da Hash");
            System.out.println("3 - Deletar Pokémon por ID");
            System.out.println("4 - Atualizar Pokémon por ID");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    apagarTudoAntesDeCriar(); // 💣 Nova função que apaga tudo
                    criarAPartirDoArquivoBinario(caminhoArquivoPokemons); // 💥 Cria do zero
                    break;
                case 2:
                    mostrarEstadoHash();
                    break;
                case 3:
                    System.out.println("\n--- Escolha um Pokémon para deletar ---");
                    System.out.println("1 - Exeggcute 2 - Exeggutor 3 - Cubone");
                    System.out.println("4 - Marowak 5 - Hitmonlee 6 - Hitmonchan");
                    System.out.println("7 - Lickitung 8 - Koffing  9 - Weezing" );
                    System.out.println("10 - Rhyhorn 11 - Rhydon 12 - Chansey");
                    System.out.println("13 - Tangela 14 - Kangaskhan 15 - Horsea");
                    System.out.println("16 - Seadra 17 - Goldeen 18 - Seaking");
                    System.out.println("COLINHA PARA VC, SO PRECISA ESCOLHER O NUMERO");

                    System.out.print("Digite o número do Pokémon que deseja deletar: ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine(); // limpa buffer

                    String nomeProcurado = "";
                    switch (escolha) {
                        case 1 -> nomeProcurado = "Exeggcute";
                        case 2 -> nomeProcurado = "Exeggutor";
                        case 3 -> nomeProcurado = "Cubone";
                        case 4 -> nomeProcurado = "Marowak";
                        case 5 -> nomeProcurado = "Hitmonlee";
                        case 6 -> nomeProcurado = "Hitmonchan";
                        case 7 -> nomeProcurado = "Lickitung";
                        case 8 -> nomeProcurado = "Koffing";
                        case 9 -> nomeProcurado = "Weezing";
                        case 10 -> nomeProcurado = "Rhyhorn";
                        case 11 -> nomeProcurado = "Rhydon";
                        case 12 -> nomeProcurado = "Chansey";
                        case 13 -> nomeProcurado = "Tangela";
                        case 14 -> nomeProcurado = "Kangaskhan";
                        case 15 -> nomeProcurado = "Horsea";
                        case 16 -> nomeProcurado = "Seadra";
                        case 17 -> nomeProcurado = "Goldeen";
                        case 18 -> nomeProcurado = "Seaking";
                        case 19 -> nomeProcurado = "Staryu";
                        default -> {
                            System.out.println("❌ Número inválido!");
                            return;
                        }
                    }

                    deletarPorNome(nomeProcurado, caminhoArquivoPokemons);
                    break;
                case 4:
                    if (!new File(caminhoDiretorio).exists() || new File(caminhoDiretorio).length() == 0) {
                        System.out.println("\n🚨 Hash não encontrada!");
                        System.out.println("💡 Por favor, crie a hash primeiro usando a opção '1 - Criar Hash a partir do arquivo de Pokémons'.\n");
                    } else {
                        atualizarPokemonNaHashPorId(caminhoArquivoPokemons);
                    }
                    break;
                case 0:
                    System.out.println("Saindo do menu da Hash Extensível...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }
    private void apagarTudoAntesDeCriar() {
        try {
            // Apaga todos os buckets antigos
            File pasta = new File(pastaBuckets);
            if (pasta.exists()) {
                File[] arquivos = pasta.listFiles();
                if (arquivos != null) {
                    for (File arq : arquivos) {
                        if (arq.isFile() && arq.getName().startsWith("bucket_")) {
                            if (!arq.delete()) {
                                System.out.println("⚠️ Falha ao deletar bucket: " + arq.getName());
                            }
                        }
                    }
                }
            }

            // Apaga o arquivo do diretório
            File dirArquivo = new File(caminhoDiretorio);
            if (dirArquivo.exists()) {
                if (!dirArquivo.delete()) {
                    System.out.println("⚠️ Falha ao deletar diretório.");
                }
            }

            System.out.println("🧹 Diretório e buckets antigos removidos com sucesso.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao apagar tudo antes de recriar", e);
        }
    }
    public void atualizarPokemonNaHashPorId(String caminhoArquivoBinario) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do Pokémon que deseja atualizar: ");
        int idProcurado = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivoBinario, "rw")) {
            raf.seek(0);
            int ultimoId = raf.readInt();

            boolean encontrado = false;
            String nomeAntigo = null;

            while (raf.getFilePointer() < raf.length()) {
                long posAtual = raf.getFilePointer();
                int id = raf.readInt();
                boolean cova = raf.readBoolean();
                int tamanho = raf.readInt();

                long posCampos = raf.getFilePointer();
                int numeroPokedex = raf.readInt();
                String nome = lerString(raf);

                if (!cova && id == idProcurado) {
                    nomeAntigo = nome;
                    encontrado = true;
                    break;
                }

                raf.seek(posAtual + 4 + 1 + 4 + tamanho);
            }

            if (!encontrado) {
                System.out.println("❌ Pokémon com ID " + idProcurado + " não encontrado.");
                return;
            }

            System.out.println("\n⚠️ Atenção: se você atualizar o NOME do Pokémon, será necessário criar uma nova hash depois!");

            Classes.CRUDbinario.Update.atualizarPokemonPorId(caminhoArquivoBinario, idProcurado);

            long novoOffset = buscarNovoOffsetDoPokemonAtualizado(caminhoArquivoBinario, nomeAntigo);

            if (novoOffset == -1) {
                System.out.println("❌ Erro: não foi possível encontrar o Pokémon atualizado no binário.");
                return;
            }

            try {
                atualizarOffsetNaHash(nomeAntigo, novoOffset);
            } catch (RuntimeException e) {
                System.out.println("\n🚨 Erro ao atualizar o offset na hash:");
                System.out.println("💡 Dica: Crie a hash primeiro usando a opção 'Criar Hash a partir do arquivo de Pokémons' no menu.");
                throw e;
            }

            System.out.println("✅ Pokémon atualizado na hash com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar Pokémon na hash", e);
        }
    }

    private void atualizarOffsetNaHash(String nomeProcurado, long novoOffset) {
        try (RandomAccessFile dir = new RandomAccessFile(caminhoDiretorio, "rw")) {
            int profundidade = dir.readInt(); // Lê a profundidade global
            System.out.println("[DEBUG] Profundidade Global: " + profundidade);

            while (dir.getFilePointer() < dir.length()) {
                int indice = dir.readInt();
                String bucketPath = dir.readUTF();
                System.out.println("[DEBUG] Lendo bucket: " + bucketPath);

                File bucketFile = new File(pastaBuckets + "/" + bucketPath);
                if (!bucketFile.exists()) {
                    System.out.println("[DEBUG] Bucket não encontrado: " + bucketPath);
                    continue;
                }

                try (RandomAccessFile bucket = new RandomAccessFile(bucketFile, "rw")) {
                    int profundidadeLocal = bucket.readInt(); // Lê profundidade local (não usado aqui)
                    int quantidade = bucket.readInt();        // Lê quantidade de registros

                    for (int i = 0; i < quantidade; i++) {
                        long posNome = bucket.getFilePointer();
                        String nome = lerString(bucket);
                        long posOffset = bucket.getFilePointer();
                        long offset = bucket.readLong();

                        if (nome.equalsIgnoreCase(nomeProcurado)) {
                            System.out.println("[DEBUG] Pokémon encontrado no bucket: " + nome);
                            bucket.seek(posOffset);
                            bucket.writeLong(novoOffset);
                            System.out.println("✅ Offset atualizado com sucesso na hash!");
                            return;
                        }
                    }
                }
            }





            System.out.println("❌ Pokémon \"" + nomeProcurado + "\" não encontrado em nenhum bucket.");

        } catch (IOException e) {
            throw new RuntimeException("Erro ao atualizar offset na hash", e);
        }
    }

    private long buscarNovoOffsetDoPokemonAtualizado(String caminhoArquivo, String nomeProcurado) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "r")) {
            raf.readInt(); // pula último ID

            while (raf.getFilePointer() < raf.length()) {
                long posAtual = raf.getFilePointer();
                int id = raf.readInt();
                boolean cova = raf.readBoolean();
                int tamanho = raf.readInt();

                long posCampos = raf.getFilePointer();
                int numeroPokedex = raf.readInt();
                String nome = lerString(raf);

                if (!cova && nome.equalsIgnoreCase(nomeProcurado)) {
                    return posAtual;
                }

                raf.seek(posAtual + 4 + 1 + 4 + tamanho);
            }
        }

        return -1;
    }

    public void inicializar() {
        profundidadeGlobal = 1;
        try (RandomAccessFile dir = new RandomAccessFile(caminhoDiretorio, "rw")) {
            dir.setLength(0);
            dir.writeInt(profundidadeGlobal);
            dir.writeInt(0);
            dir.writeUTF("bucket_0.bin");
            dir.writeInt(1);
            dir.writeUTF("bucket_1.bin");
            criarBucket(0, 1);
            criarBucket(1, 1);
            System.out.println("✅ Inicialização completa com profundidade 1");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao inicializar", e);
        }
    }

    private void criarBucket(int numero, int profundidadeLocal) throws IOException {
        try (RandomAccessFile bucket = new RandomAccessFile(pastaBuckets + "/bucket_" + numero + ".bin", "rw")) {
            bucket.setLength(0);
            bucket.writeInt(profundidadeLocal);
            bucket.writeInt(0);
        }
    }

    public void criarAPartirDoArquivoBinario(String caminhoArquivoPokemons) {
        inicializar();
        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivoPokemons, "r")) {
            raf.readInt();
            while (raf.getFilePointer() < raf.length()) {
                long pos = raf.getFilePointer();
                raf.readInt();
                boolean cova = raf.readBoolean();
                int tamanho = raf.readInt();

                if (!cova) {
                    long offsetPok = raf.getFilePointer();
                    raf.readInt();
                    String nome = lerString(raf);
                    inserir(nome, offsetPok);
                }

                raf.seek(pos + 4 + 1 + 4 + tamanho);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro em criarAPartirDoArquivoBinario", e);
        }
    }

    public void inserir(String nome, long offsetPokemon) {
        try (RandomAccessFile dir = new RandomAccessFile(caminhoDiretorio, "rw")) {
            profundidadeGlobal = dir.readInt();
            Map<Integer, String> diretorio = new LinkedHashMap<>();

            while (dir.getFilePointer() < dir.length()) {
                int idx = dir.readInt();
                String bucket = dir.readUTF();
                diretorio.put(idx, bucket);
            }

            int hash = nome.hashCode();
            int indice = hash & ((1 << profundidadeGlobal) - 1);

            String bucketPath = diretorio.get(indice);
            if (bucketPath == null) throw new RuntimeException("Bucket não encontrado para índice " + indice);

            if (!inserirNoBucket(bucketPath, nome, offsetPokemon)) {
                splitBucket(bucketPath, indice);
                inserir(nome, offsetPokemon);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao inserir", e);
        }
    }

    private boolean inserirNoBucket(String bucketPath, String nome, long offsetPokemon) throws IOException {
        try (RandomAccessFile bucket = new RandomAccessFile(pastaBuckets + "/" + bucketPath, "rw")) {
            bucket.seek(0);
            int profundidadeLocal = bucket.readInt();
            int quantidade = bucket.readInt();

            if (quantidade < capacidadeBucket) {
                bucket.seek(bucket.length());
                escreverString(bucket, nome);
                bucket.writeLong(offsetPokemon);
                bucket.seek(4);
                bucket.writeInt(quantidade + 1);
                System.out.println("✅ Inserido no " + bucketPath);
                return true;
            }
            return false;
        }
    }

    private void splitBucket(String bucketPathAntigo, int indiceOriginal) throws IOException {
        try (RandomAccessFile dir = new RandomAccessFile(caminhoDiretorio, "rw")) {
            profundidadeGlobal = dir.readInt();
            List<Integer> indices = new ArrayList<>();
            List<String> buckets = new ArrayList<>();

            while (dir.getFilePointer() < dir.length()) {
                indices.add(dir.readInt());
                buckets.add(dir.readUTF());
            }

            File arquivoAntigo = new File(pastaBuckets + "/" + bucketPathAntigo);
            try (RandomAccessFile bucketAntigo = new RandomAccessFile(arquivoAntigo, "rw")) {
                bucketAntigo.seek(0);
                int profLocal = bucketAntigo.readInt();
                int qtd = bucketAntigo.readInt();

                if (profLocal == profundidadeGlobal) {
                    profundidadeGlobal++;
                    List<Integer> novosIndices = new ArrayList<>();
                    List<String> novosBuckets = new ArrayList<>();
                    for (int i = 0; i < indices.size(); i++) {
                        novosIndices.add(indices.get(i));
                        novosBuckets.add(buckets.get(i));
                        novosIndices.add((1 << (profundidadeGlobal - 1)) | indices.get(i));
                        novosBuckets.add(buckets.get(i));
                    }
                    indices = novosIndices;
                    buckets = novosBuckets;
                }

                List<String> nomes = new ArrayList<>();
                List<Long> offsets = new ArrayList<>();
                for (int i = 0; i < qtd; i++) {
                    nomes.add(lerString(bucketAntigo));
                    offsets.add(bucketAntigo.readLong());
                }

                int novoNum1 = (int) (new File(pastaBuckets).list().length);
                int novoNum2 = novoNum1 + 1;

                criarBucket(novoNum1, profLocal + 1);
                criarBucket(novoNum2, profLocal + 1);

                for (int i = 0; i < nomes.size(); i++) {
                    String nome = nomes.get(i);
                    int h = nome.hashCode();
                    int b = (h & ((1 << (profLocal + 1)) - 1)) >> profLocal;
                    String target = b == 0 ? "bucket_" + novoNum1 + ".bin" : "bucket_" + novoNum2 + ".bin";
                    inserirNoBucket(target, nome, offsets.get(i));
                }

                for (int i = 0; i < indices.size(); i++) {
                    if (buckets.get(i).equals(bucketPathAntigo)) {
                        buckets.set(i, (indices.get(i) & (1 << (profundidadeGlobal - 1))) == 0 ? "bucket_" + novoNum1 + ".bin" : "bucket_" + novoNum2 + ".bin");
                    }
                }

                dir.setLength(0);
                dir.writeInt(profundidadeGlobal);
                for (int i = 0; i < indices.size(); i++) {
                    dir.writeInt(indices.get(i));
                    dir.writeUTF(buckets.get(i));
                }

                arquivoAntigo.delete();
            }
        }
    }

    private void escreverString(RandomAccessFile raf, String s) throws IOException {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        raf.writeShort(bytes.length);
        raf.write(bytes);
    }

    private String lerString(RandomAccessFile raf) throws IOException {
        int tam = raf.readShort();
        byte[] bytes = new byte[tam];
        raf.readFully(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private void mostrarEstadoHash() {
        try (RandomAccessFile dir = new RandomAccessFile(caminhoDiretorio, "r")) {
            int profundidade = dir.readInt();
            System.out.println("Profundidade Global: " + profundidade);
            System.out.println("--- Estado dos Buckets ---");

            while (dir.getFilePointer() < dir.length()) {
                int indice = dir.readInt();
                String bucketPath = dir.readUTF();
                System.out.println("\nÍndice " + indice + ": " + bucketPath);

                File bucketFile = new File(pastaBuckets + "/" + bucketPath);
                if (!bucketFile.exists()) {
                    System.out.println("  (Bucket não encontrado)");
                    continue;
                }

                try (RandomAccessFile bucket = new RandomAccessFile(bucketFile, "r")) {
                    int profundidadeLocal = bucket.readInt();
                    int quantidade = bucket.readInt();
                    System.out.println("  Profundidade Local: " + profundidadeLocal);
                    System.out.println("  Quantidade de Registros: " + quantidade);

                    for (int i = 0; i < quantidade; i++) {
                        String nome = lerString(bucket);
                        long offset = bucket.readLong();
                        if(offset > 0){
                            System.out.println("    Nome: " + nome + " | Offset: " + offset);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao mostrar estado da hash", e);
        }
    }
    public void deletarPorNome(String nomeProcurado, String caminhoArquivoPokemons) {
        try (RandomAccessFile dir = new RandomAccessFile(caminhoDiretorio, "rw")) {
            profundidadeGlobal = dir.readInt();
            List<Integer> indices = new ArrayList<>();
            List<String> buckets = new ArrayList<>();

            while (dir.getFilePointer() < dir.length()) {
                indices.add(dir.readInt());
                buckets.add(dir.readUTF());
            }

            for (String bucketPath : buckets) {
                File bucketFile = new File(pastaBuckets + "/" + bucketPath);
                if (!bucketFile.exists()) continue;

                try (RandomAccessFile bucket = new RandomAccessFile(bucketFile, "rw")) {
                    bucket.seek(0);
                    bucket.readInt(); // profundidade local
                    int quantidade = bucket.readInt();

                    for (int i = 0; i < quantidade; i++) {
                        long posAntesNome = bucket.getFilePointer();
                        String nome = lerString(bucket);
                        long offset = bucket.readLong();

                        if (nome.equalsIgnoreCase(nomeProcurado) && offset != 0) {
                            bucket.seek(posAntesNome);
                            lerString(bucket); // precisa reler pra posicionar no offset
                            bucket.writeLong(0); // zera o offset
                            System.out.println("✅ Pokemon morto na Hash, seu assassino");

                            deletarPokemonPorId(caminhoArquivoPokemons, buscarIdNoArquivo(caminhoArquivoPokemons, offset));
                            return;
                        }
                    }
                }
            }

            System.out.println("❌ Pokémon \"" + nomeProcurado + "\" não encontrado nos buckets.");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao deletar Pokémon pelo nome", e);
        }
    }
    private int buscarIdNoArquivo(String caminhoArquivo, long offset) throws IOException {
        if (offset == 0) return -1; // Se o offset já for 0, não tem nada para buscar.

        try (RandomAccessFile raf = new RandomAccessFile(caminhoArquivo, "r")) {
            raf.seek(offset); // Vai direto até o offset indicado.
            int id = raf.readInt(); // Lê o ID do Pokémon.
            return id;
        }
    }

}