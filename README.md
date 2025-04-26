# 📚 Projeto: Sistema de Gerenciamento de Pokémons

## ✨ Descrição

Este projeto implementa um **CRUD** completo de Pokémons utilizando diversas estruturas de dados:
- 🐉 **LLM** foi utilizado nesse projeto.
- 📂 **Artigos** códigos e estruturas retirados e adaptados de artigos especializados.
- 🗂️ **Arquivo Binário** para armazenamento principal dos Pokémons.
- 🪳 **Hash Extensível** para acesso rápido por nome.
- 🔀 **Lista Invertida** para gerenciamento de Pokémons ativos e atualizações.
- 🌳 (Planejado) **Árvore B** para gerenciamento eficiente baseado em ID.

Além disso, o sistema conta com **menus interativos** para realizar todas as operações, e com uma camada de **aleatorização inteligente** para buscas mais amigáveis.

---

## 📋 Funcionalidades

- **Criar** Hash Extensível baseada no arquivo binário.
- **Criar** Lista Invertida para gerenciar entradas vivas.
- **Consultar** Pokémons de forma aleatória ou manual.
- **Atualizar** Pokémons no arquivo, Hash e Lista Invertida.
- **Deletar** Pokémons de todas as estruturas.
- **Visualizar** estado atual da Hash e da Lista Invertida.

---

## 🧐 Estruturas utilizadas

| Estrutura             | Finalidade                                                           |
| ---------------------- | --------------------------------------------------------------------- |
| **Arquivo Binário**    | Armazenar dados dos Pokémons persistidos.                            |
| **Hash Extensível**    | Acesso rápido a Pokémons vivos, com splits dinâmicos de buckets.      |
| **Lista Invertida**    | Controlar os Pokémons ativos/deletados de forma eficiente.            |
| **Árvore B** (opcional)| Organização de registros baseada em ID para busca eficiente.         |

---

## 📂 Organização de Pastas

```
/Classes
    /Arquivo           # Criar e manipular o arquivo binário
    /Hash              # Implementação da Hash Extensível
    /CRUDListaInvertida # Gerenciamento da Lista Invertida
    /Menu              # Menus interativos e desenhos
    /Pokemon           # Modelos de dados (Pokémon, PokémonNoArquivo)
Main.java              # Ponto de entrada do sistema
data/                  # Pasta para armazenar binários e dados gerados
```

---

## 🔹 Como Usar

1. Garanta que o arquivo `data/dados_modificados.csv` esteja na pasta correta.
2. Rode o programa (`Main.java`).
3. No menu principal, escolha:
    - `1 - Estrutura: Árvore B / Hash / Lista Invertida` (cada estrutura tem seu peso no código).
4. Após criar, você pode:
    - Exibir estado da hash ou lista.
    - Atualizar um Pokémon.
    - Deletar um Pokémon.
    - Buscar aleatoriamente Pokémons para facilitar a navegação.

---

## 🌾 Menus

| Opção      | Ação                                                      |
|------------| --------------------------------------------------------- |
| 1 - READ   | Ler dados (mostrar Hash, buscar um Pokémon aleatório).     |
| 2 - UPDATE | Atualizar dados no sistema.                               |
| 3 - DELETE | Deletar entradas nas estruturas.                          |
| 0 - SAIR   | Encerrar o sistema.                                        |

---

## 🛠️ Tecnologias Utilizadas

- ☕ **Java 22** (compatível com 17+)
- 🖥️ Console CLI para interação
- 📁 Arquivos binários
- 📖 Manipulação de CSV (entrada de dados)

---

## 💡 Observações Importantes

- **Sempre é criado o Arquivo Binário primeiro**.
- Para operações em Hash ou Lista Invertida, **crie suas respectivas estruturas** primeiro.
- Caso atualize o nome de um Pokémon, será necessário **recriar a Hash**.
- O sistema foi pensado para **resistir a erros comuns** (arquivos não existentes, etc).

---

## 📜 Créditos

Desenvolvido com muito esforço (e um pouquinho de sofrimento 🤣) por:

- João Vitor Mendes
- Gabriela Lacerda

---

**Boa diversão e boas batalhas! 🚀**

