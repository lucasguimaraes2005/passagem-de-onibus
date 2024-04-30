package com.br.unisales.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @apiNote Classe responsável por criar as tabelas no banco de dados
 * 
 * @author Vito Rodrigues Franzosi
 * @Data Criação 10.04.2024
 */
public class CriarTabela {
    private Connection conn;

    /**
     * @apiNote Método construtor de banco de dados
     * @param Connection conn
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    public CriarTabela(Connection conn) {
        this.conn=conn;
        this.criarTabelaUsuario();
        this.criarTabelaOnibus();
        this.criarTabelaPassageiro();
        this.criarTabelaAcento();
    }

    /**
     * @apiNote Método responsável por criar a tabela usuario no banco de dados
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    private void criarTabelaUsuario() {
        try {
            String sqlUsuario = "CREATE TABLE IF NOT EXISTS usuario ( " +
            "id INTEGER NOT NULL PRIMARY KEY, " +
            "nome VARCHAR(150) NOT NULL, " +
            "email VARCHAR(150) NOT NULL, " +
            "senha VARCHAR(10) NOT NULL, " +
            "grupo VARCHAR(15) NOT NULL)";
            Statement stm = this.conn.createStatement();
            stm.execute(sqlUsuario);
            System.out.println("A tabela de usuários foi criada com sucesso!");
            this.inserirDadosTabelaUsuario();
        } catch (SQLException e) {
            System.err.println("Erro no método criarTabelaUsuario da classe CriarTabela: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @apiNote Método responsável por inserir os primeiros dados na tabela usuario
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    private void inserirDadosTabelaUsuario() {
        try {
            String sql = "INSERT INTO usuario(nome, email, senha, grupo) VALUES('Vito Franzosi', 'vito@gmail.com', '123456', 'Administrador')";
            Statement stm = this.conn.createStatement();
            stm.executeUpdate(sql);
            sql = "INSERT INTO usuario(nome, email, senha, grupo) VALUES('Francesco Franzosi', 'francesco@gmail.com', '123456', 'Vendedor')";
            stm.executeUpdate(sql);
            System.out.println("Os dados dos usuários foram inseridos com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro no método inserirDadosTabelaUsuario() da classe CriarTabela: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @apiNote Método responsável por criar a tabela onibus no banco de dados
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    private void criarTabelaOnibus() {
        try {
            String sqlUsuario = "CREATE TABLE IF NOT EXISTS onibus ( " +
            "id INTEGER NOT NULL PRIMARY KEY, " +
            "numero INTEGER NOT NULL, " +
            "cidade_origem VARCHAR(150) NOT NULL, " +
            "uf_origem VARCHAR(2) NOT NULL, " +
            "cidade_destino VARCHAR(150) NOT NULL, " +
            "uf_destino VARCHAR(2) NOT NULL, " +
            "data_saida DATE NOT NULL, " +
            "horario_saida TIME NOT NULL, " +
            "preco_passagem DOUBLE NOT NULL, " +
            "quantidade_acento INTEGER NOT NULL)";
            Statement stm = this.conn.createStatement();
            stm.execute(sqlUsuario);
            System.out.println("A tabela de onibus foi criada com sucesso!");
            this.inserirDadosTabelaOnibus();
        } catch (SQLException e) {
            System.err.println("Erro no método criarTabelaOnibus da classe CriarTabela: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @apiNote Método responsável por inserir os primeiros dados na tabela onibus
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    private void inserirDadosTabelaOnibus() {
        try {
            String sql = "INSERT INTO onibus(numero, cidade_origem, uf_origem, cidade_destino, uf_destino, " +
                                            "data_saida, horario_saida, preco_passagem, quantidade_acento) " +
                                            "VALUES(2340, 'Vitória', 'ES', 'Belo Horizonte', 'BH', '2024-05-30', " +
                                            "'21:00:00', 85.70, 5)";
            Statement stm = this.conn.createStatement();
            stm.executeUpdate(sql);
            sql = "INSERT INTO onibus(numero, cidade_origem, uf_origem, cidade_destino, uf_destino, " +
                                            "data_saida, horario_saida, preco_passagem, quantidade_acento) " +
                                            "VALUES(2350, 'Belo Horizonte', 'BH', 'Vitória', 'ES', '2024-05-30', " +
                                            "'21:30:00', 85.70, 5)";
            stm.executeUpdate(sql);
            System.out.println("Os dados dos ônibus foram inseridos com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro no método inserirDadosTabelaUsuario() da classe CriarTabela: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @apiNote Método responsável por criar a tabela passageiros no banco de dados
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    private void criarTabelaPassageiro() {
        try {
            String sqlUsuario = "CREATE TABLE IF NOT EXISTS passageiro ( " +
            "id INTEGER NOT NULL PRIMARY KEY, " +
            "nome VARCHAR(150) NOT NULL, " +
            "cpf VARCHAR(14) NOT NULL UNIQUE, " +
            "sexo VARCHAR(1) NOT NULL, " +
            "email VARCHAR(150) NOT NULL, " +
            "data_nascimento DATE NOT NULL)";
            Statement stm = this.conn.createStatement();
            stm.execute(sqlUsuario);
            System.out.println("A tabela de passageiros foi criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro no método criarTabelaPassageiro da classe CriarTabela: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @apiNote Método responsável por criar a tabela acento no banco de dados
     * 
     * @author Vito Rodrigues Franzosi
     * @Data Criação 10.04.2024
     */
    private void criarTabelaAcento() {
        try {
            String sqlUsuario = "CREATE TABLE IF NOT EXISTS acento ( " +
            "id INTEGER NOT NULL PRIMARY KEY, " +
            "id_onibus INTEGER NOT NULL, " +
            "id_passageiro INTEGER, " +
            "FOREIGN KEY (id_onibus) REFERENCES onibus(id), " +
            "FOREIGN KEY (id_passageiro) REFERENCES passageiro(id))";
            Statement stm = this.conn.createStatement();
            stm.execute(sqlUsuario);
            System.out.println("A tabela de acentos foi criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro no método criarTabelaAcento da classe CriarTabela: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
