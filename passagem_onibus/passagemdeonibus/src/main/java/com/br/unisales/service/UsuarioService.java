package com.br.unisales.service;

import java.sql.Connection;
import java.util.List;

import com.br.unisales.repository.UsuarioRepository;
import com.br.unisales.table.Usuario;

/**
 * @apiNote Método responsável por gerenciar os acessos na tabela usuario
 * 
 * @author Vito Rodrigues Franzosi
 * @Data Criaçao 10.04.2024
 */
public class UsuarioService {
    private UsuarioRepository repo;

    public UsuarioService(Connection conn) {
        this.repo = new UsuarioRepository(conn);
    }

    public List<Usuario> listar() {
        return this.repo.findByAll();
    }

    public Usuario salvar(Usuario usuario) {
        return this.repo.save(usuario);
    }

    public void excluir(Integer id) {
        this.repo.delete(id);
    }

    public Usuario buscarPorId(Integer id) {
        return this.repo.findById(id);
    }

    public Usuario buscarPorEmail(String email) {
        return this.repo.findByEmail(email);
    }

    public List<Usuario> buscarPorParametros(Usuario usuario) {
        return this.repo.findByParameters(usuario);
    }
}
