package com.br.unisales.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    @Column(name = "email", nullable = false, length = 150)
    private String email;
    @Column(name = "senha", nullable = false, length = 10)
    private String senha;
    @Column(name = "grupuo", nullable = false, length = 15)
    private String grupo;
}
