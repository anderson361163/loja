package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="produtos")
@NamedQuery(name="Produto.produtosPorCategoria",
query="SELECT p FROM Produto p WHERE p.categoria.nome = :nome")
//-----------USANDO HERANÇA COM ENTIDADES-----------
//1. Une as tabelas que herdam produto em uma unica tabela: SINGLE_TABLE
//No banco de dados, ele cria uma coluna chamada DTYPE onde armazena
//o nome da tabela que está sendo persistinda, com tamanho de 31 caracteres
//algo que pode ser personalizado

//2. Para uma herança de tabelas separadas (na qual gera join no seu relacionamento)
// existe essa tabela, contudo o id da tabela produto, será chave estrangeira na tabela filha
@Inheritance(strategy = InheritanceType.JOINED) 
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now();
	
	//1. SE NÃO DEFINIR COMO STRING ELE VAI ARMAZENAR A POSIÇÃO DO ENUM
	//1. @Enumerated(EnumType.STRING)
	//2. Recomendado que todos os relacionamento ManyToOne sejam preguiçosos
	@ManyToOne(fetch = FetchType.LAZY) 
	private Categoria categoria;

	public Produto() {
	}
	
	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
}
