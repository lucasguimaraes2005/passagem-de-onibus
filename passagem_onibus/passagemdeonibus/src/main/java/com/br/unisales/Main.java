package com.br.unisales;

import java.sql.Connection;
import java.util.List;

import com.br.unisales.service.UsuarioService;
import com.br.unisales.table.Usuario;
import com.br.unisales.util.ConexaoDB;
import com.br.unisales.util.CriarTabela;

/**
 * @apiNote Classe responsável por iniciar todo o sistema
 *
 * @author Vito Rodrigues Franzosi
 * @Data Criação 10.04.2024
 */
public class Main {
    public static void main(String[] args) {
        Connection conn = new ConexaoDB().getConexao();
        if(conn!=null) {
            //new CriarTabela(conn);
            UsuarioService us = new UsuarioService(conn);
            List<Usuario> lista = us.listar();
            if((lista!=null) && (lista.size()>0)) {
                for(Usuario item: lista) {
                    System.out.println("Código: "+item.getId()+"\t\tNome: "+item.getNome()+"\t\tE-mail: "+item.getEmail());
                }
            } else
                System.out.println("A lista de usuários está vazia!");

            Usuario u = new Usuario(null, "franzosi", null, null, null);
            lista = us.buscarPorParametros(u);
            if((lista!=null) && (lista.size()>0)) {
                for(Usuario item: lista) {
                    System.out.println("Código: "+item.getId()+"\t\tNome: "+item.getNome()+"\t\tE-mail: "+item.getEmail());
                }
            } else
                System.out.println("A lista de usuários está vazia!");
            String email="francesco@gmail.com";
            u = us.buscarPorEmail(email);
            if((u!=null) && (u.getId()!=null))
                System.out.println("Código: "+u.getId()+"\t\tNome: "+u.getNome()+"\t\tE-mail: "+u.getEmail());
            else
                System.err.println("Não existe usuário cadastrado com o e-mail: "+email);
        }
    }
}