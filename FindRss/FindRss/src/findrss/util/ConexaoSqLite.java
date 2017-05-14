/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findrss.util;

import java.sql.*;

/**
 *
 * @author Matrix
 */
public class ConexaoSqLite {

    public static String status = "Não conectou...";

    public static java.sql.Connection getConexaoSqlite() {
        Connection connection = null;
        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src\\DataBase\\BDFindRss.sqlite");
            if (connection != null) {
                status = ("STATUS: Conectado com sucesso!");
            } else {
                status = ("STATUS: Não foi possivel realizar conex�o");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }

    public static String statusConection() {
        return status;
    }

    public static boolean FecharConexao() {
        try {
            ConexaoSqLite.getConexaoSqlite().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConexaoSqLite.getConexaoSqlite();
    }

}
