/*
 * Aluna: Maria Fernanda Ratier
 * RA: 25276440-2
 * Disciplina: Programação Orientada a Objetos (POO) - Aula 29 (Padrão DAO)
 */
package br.com.bookstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Fábrica de conexões JDBC com o arquivo SQLite local. */
public final class ConexaoFactory {

    public static final String ARQUIVO_BANCO = "bookstore.db";
    private static final String URL = "jdbc:sqlite:" + ARQUIVO_BANCO;

    private ConexaoFactory() {
    }

    /** Abre uma nova conexão. Quem chamar deve fechá-la (try-with-resources). */
    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
