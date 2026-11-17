# BookStore Enterprise - Aula 29 (Padrão DAO)

Aluna: Maria Fernanda Ratier | RA: 25276440-2

## Estrutura
src/br/com/bookstore/
  Main.java                  -> roteiro de testes
  model/Livro.java           -> entidade com invariantes de domínio
  dao/LivroDAO.java          -> contrato (interface)
  dao/LivroDAOSqlite.java    -> implementação JDBC/SQLite
  dao/ConexaoFactory.java    -> fábrica de conexões

## Como executar
1. Baixe o driver `sqlite-jdbc` (.jar) e coloque na pasta `lib/`
   (se der erro de SLF4J, coloque também o `slf4j-api` na pasta `lib/`).
2. Compile e execute (Linux/Mac):

    mkdir -p out
    javac -encoding UTF-8 -d out $(find src -name "*.java")
    java -cp "out:lib/*" br.com.bookstore.Main

   No Windows (PowerShell/CMD), troque o separador `:` por `;`:

    java -cp "out;lib/*" br.com.bookstore.Main

Na IDE (IntelliJ/Eclipse/VS Code): adicione o .jar de `lib/` às bibliotecas do projeto
e execute a classe `br.com.bookstore.Main`.
