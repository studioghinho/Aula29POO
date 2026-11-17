/*
 * Aluna: Maria Fernanda Ratier
 * RA: 25276440-2
 * Disciplina: Programação Orientada a Objetos (POO) - Aula 29 (Padrão DAO)
 */
package br.com.bookstore.dao;

import br.com.bookstore.model.Livro;

import java.util.List;
import java.util.Optional;

/**
 * Contrato desacoplado de persistência de Livro.
 * A camada de negócio depende desta interface (DIP) e nunca de JDBC.
 */
public interface LivroDAO {

    void salvar(Livro livro);

    Optional<Livro> buscarPorId(Long id);

    List<Livro> listarTodos();
}
