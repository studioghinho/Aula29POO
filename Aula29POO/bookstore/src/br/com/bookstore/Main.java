/*
 * Aluna: Maria Fernanda Ratier
 * RA: 25276440-2
 * Disciplina: Programação Orientada a Objetos (POO) - Aula 29 (Padrão DAO)
 */
package br.com.bookstore;

import br.com.bookstore.dao.ConexaoFactory;
import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/** Roteiro canônico de testes do catálogo BookStore. */
public class Main {

    public static void main(String[] args) throws IOException {
        // Começa com banco limpo para que os IDs gerados sejam sempre 1, 2 e 3
        Files.deleteIfExists(Paths.get(ConexaoFactory.ARQUIVO_BANCO));

        // 1) Inversão de Dependência: a variável é do tipo da INTERFACE
        LivroDAO dao = new LivroDAOSqlite();

        // 2) Persistência de 3 obras e verificação dos IDs gerados
        System.out.println("=== 2) Salvando livros ===");
        Livro l1 = new Livro("Clean Code", "Robert C. Martin", 89.90);
        Livro l2 = new Livro("Design Patterns", "Erich Gamma et al.", 129.90);
        Livro l3 = new Livro("Refactoring", "Martin Fowler", 109.50);

        for (Livro l : List.of(l1, l2, l3)) {
            dao.salvar(l);
            System.out.println("Salvo com ID " + l.getId() + " -> " + l.getTitulo());
        }

        // 3) Consulta do ID 2 com estilo funcional
        System.out.println("\n=== 3) Buscando o livro de ID 2 ===");
        dao.buscarPorId(2L).ifPresentOrElse(
                livro -> System.out.println("Encontrado: " + livro),
                () -> System.out.println("Aviso: nenhum livro encontrado com o ID 2."));

        // 4) ID inexistente: sem NullPointerException
        System.out.println("\n=== 4) Buscando o ID inexistente 999 ===");
        dao.buscarPorId(999L).ifPresentOrElse(
                livro -> System.out.println("Encontrado: " + livro),
                () -> System.out.println("Aviso: nenhum livro encontrado com o ID 999. "
                        + "A aplicacao continua normalmente, sem NullPointerException."));

        // 5) Listagem completa com cabeçalho tabular
        System.out.println("\n=== 5) Catalogo completo ===");
        String formato = "%-4s | %-20s | %-22s | %10s%n";
        System.out.printf(formato, "ID", "TITULO", "AUTOR", "PRECO (R$)");
        System.out.println("-".repeat(65));
        for (Livro l : dao.listarTodos()) {
            System.out.printf(formato, l.getId(), l.getTitulo(), l.getAutor(),
                    String.format("%.2f", l.getPreco()));
        }
    }
}
