package br.com.alura.loja.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//embuti a classe dados pessoais dentro da entidade cliente
	@Embedded
	private DadosPessoais dadosPessoais;

	public Cliente() {

	}

	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}
	
	public String getNome() {
		return this.dadosPessoais.getNome();
	}
	
	public String getCpf() {
		return this.dadosPessoais.getCpf();
	}
}
