package com.br.unisales.table;

import java.sql.Time;
import java.util.Date;

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
@Table(name = "onibus")
public class Onibus {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "numero", nullable = false)
    private Integer numero;
    @Column(name = "cidade_origem", nullable = false, length = 150)
    private String cidadeOrigem;
    @Column(name = "uf_origem", nullable = false, length = 2)
    private String ufOrigem;
    @Column(name = "cidade_destino", nullable = false, length = 150)
    private String cidadeDestino;
    @Column(name = "uf_destino", nullable = false, length = 2)
    private String ufDestino;
    @Column(name = "data_saida", nullable = false)
    private Date dataSaida;
    @Column(name = "horario_saida", nullable = false)
    private Time horarioSaida;
    @Column(name = "preco_passagem", nullable = false)
    private Double precoPassagem;
}
