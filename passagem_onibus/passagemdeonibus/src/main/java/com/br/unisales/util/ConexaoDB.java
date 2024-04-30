package com.br.unisales.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @apiNote Classe responsável por realizar a conexão com o banco de dados
 * 
 * @author Vito Rodrigues Franzosi
 * @Data Criação 10.04.2024
 */
public class ConexaoDB {
    private Connection conn;

    /**
     * @apiNote Método construtor da classe
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    public ConexaoDB() {
        try {
              // db parameters
              String url = "jdbc:sqlite:C:/sqlite/db/vendas.db";
              // create a connection to the database
              this.conn = DriverManager.getConnection(url);             
              System.out.println("Conexão com o banco de dados SQLite vendas.db foi realizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro no método construtor da classe ConexaoDB: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @apiNote Método responsável por retornar a conexão do banco de dados
     * @return Connection
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    public Connection getConexao() {
        try {
            if((this.conn!=null) && (this.conn.isClosed()))
                System.err.println("Conexão com o banco está fechada!");
            return this.conn;            
        } catch (Exception e) {
            return null;
        }
    }
}
