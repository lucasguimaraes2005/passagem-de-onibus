package com.br.unisales.repository;

import java.util.List;

/**
 * @apiNote Interface generica para realizar as operações no banco de dados
 * 
 * @author Vito Rodrigues Franzosi
 * @Data Criação 10.04.2024
 */
public interface GenericoRepository<T> {
    List<T> findByAll();
    T save(T entity);
    void delete(Integer id);
    T findById(Integer id);
}
