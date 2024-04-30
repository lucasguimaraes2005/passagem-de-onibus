package com.br.unisales.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "acento")
public class Acento {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false, nullable = false)
    private Integer id;
    @ManyToOne(targetEntity=Onibus.class, fetch=FetchType.EAGER)
	@JoinColumn(name="id_onibus")
    private Onibus onibus;
    @ManyToOne(targetEntity=Passageiro.class, fetch=FetchType.EAGER)
	@JoinColumn(name="id_passageiro")
    private Passageiro passageiro;
}
