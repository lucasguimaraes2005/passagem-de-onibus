package com.br.unisales.table;

import java.sql.Date;

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
@Table(name = "passageiro")
public class Passageiro {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;
    @Column(name = "sexo", nullable = false, length = 1)
    private String sexo;
    @Column(name = "email", nullable = false, length = 150)
    private String email;
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;
}
