package br.com.alura.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	private String nome;
//	private String tipo;
	
	//adiciona como chave composta da classe e nao como atributos
	@EmbeddedId
	private CategoriaId id;
	
	
	//OBRIGATORIO
	public Categoria() {
		
	}
	
	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "xpto");
	}
	
	public String getNome() {
		return this.id.getNome();
	}
	
	
//	public Categoria(String nome) {
//		this.nome = nome;
//	}
	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
}
