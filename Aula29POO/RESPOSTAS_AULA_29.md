# Aula 29 - Padrão DAO | Respostas do Laboratório Interativo

Aluna: Maria Fernanda Ratier | RA: 25276440-2

## Parte 1 - Questões de múltipla escolha

| Questão | Alternativa | Por quê |
|---|---|---|
| 1 | **B** | O Impedance Mismatch vem da assimetria entre grafos de objetos ricos na RAM (comportamento, encapsulamento) e tabelas relacionais de dados atômicos (tuplas, PKs). |
| 2 | **C** | `persistirNoBanco()` acopla o domínio à infraestrutura (JDBC), fere o SRP e impede testes unitários sem banco ativo. |
| 3 | **A** | Depender da interface `LivroDAO` (DIP) permite trocar SQLite, PostgreSQL ou Mock sem alterar a regra de negócio. |
| 4 | **D** | `Optional` deixa explícito que o resultado pode estar ausente e obriga o chamador a tratar isso, evitando NullPointerException acidental. |

## Parte 2 - Desafios de código (cole no lugar do código base de cada editor)

### Desafio 1 - mapearLinha

```java
private Livro mapearLinha(ResultSet rs) throws SQLException {
    Long id = rs.getLong("id");
    String titulo = rs.getString("titulo");
    String autor = rs.getString("autor");
    double preco = rs.getDouble("preco");
    return new Livro(id, titulo, autor, preco);
}
```

### Desafio 2 - salvar

```java
@Override
public void salvar(Livro livro) {
    String sql = "INSERT INTO livros (titulo, autor, preco) VALUES (?, ?, ?);";
    try (Connection conn = ConexaoFactory.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setDouble(3, livro.getPreco());

        stmt.executeUpdate();

        try (ResultSet keys = stmt.getGeneratedKeys()) {
            if (keys.next()) {
                livro.setId(keys.getLong(1));
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao salvar livro: " + e.getMessage(), e);
    }
}
```

### Desafio 3 - buscarPorId

```java
@Override
public Optional<Livro> buscarPorId(Long id) {
    String sql = "SELECT id, titulo, autor, preco FROM livros WHERE id = ?;";
    try (Connection conn = ConexaoFactory.obterConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setLong(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return Optional.of(mapearLinha(rs));
            }
            return Optional.empty();
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao consultar livro por ID: " + e.getMessage(), e);
    }
}
```

## Parte 3 - Lista oficial (projeto BookStore Enterprise)

O projeto completo está no arquivo `bookstore.zip` (veja o README dentro dele para compilar e executar).
