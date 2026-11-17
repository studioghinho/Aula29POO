/*
 * Aluna: Maria Fernanda Ratier
 * RA: 25276440-2
 * Disciplina: Programação Orientada a Objetos (POO) - Aula 29 (Padrão DAO)
 */
package br.com.bookstore.model;

import java.util.Locale;

/**
 * Entidade de domínio do catálogo da BookStore.
 * Não conhece JDBC nem SQL: apenas dados e regras de negócio (invariantes).
 */
public class Livro {

    private Long id;
    private String titulo;
    private String autor;
    private double preco;

    /** Construtor completo: usado para hidratar tuplas vindas do banco. */
    public Livro(Long id, String titulo, String autor, double preco) {
        // Blindagem de invariantes de domínio
        if (titulo == null || titulo.trim().length() < 2) {
            throw new IllegalArgumentException("O título é obrigatório e deve ter ao menos 2 caracteres.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("O autor é obrigatório.");
        }
        // "!(preco > 0.0)" também rejeita NaN, além de zero e negativos
        if (!(preco > 0.0)) {
            throw new IllegalArgumentException("O preço de venda deve ser estritamente positivo");
        }
        this.id = id;
        this.titulo = titulo.trim();
        this.autor = autor.trim();
        this.preco = preco;
    }

    /** Construtor de negócio: livro ainda não persistido, portanto sem id (null). */
    public Livro(String titulo, String autor, double preco) {
        this(null, titulo, autor, preco);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public double getPreco() {
        return preco;
    }

    /** Único setter permitido: sincroniza a PK gerada pelo banco com o objeto em memória. */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(Locale.forLanguageTag("pt-BR"),
                "Livro{id=%s, titulo='%s', autor='%s', preco=R$ %.2f}",
                id, titulo, autor, preco);
    }
}
